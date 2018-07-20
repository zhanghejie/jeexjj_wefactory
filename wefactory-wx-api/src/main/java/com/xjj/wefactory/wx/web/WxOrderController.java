package com.xjj.wefactory.wx.web;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayAppOrderResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.service.WxPayService;
import com.xjj.framework.utils.JacksonUtil;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wefactory.business.product.entity.ProductEntity;
import com.xjj.wefactory.customer.address.entity.AddressEntity;
import com.xjj.wefactory.customer.buyer.entity.BuyerEntity;
import com.xjj.wefactory.customer.cart.entity.CartEntity;
import com.xjj.wefactory.customer.order.entity.OrderEntity;
import com.xjj.wefactory.customer.order.entity.OrderGoodsEntity;
import com.xjj.wefactory.util.OrderHandleOption;
import com.xjj.wefactory.util.OrderUtil;
import com.xjj.wefactory.wx.annotation.LoginUser;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.AddressService;
import com.xjj.wefactory.wx.service.BuyerService;
import com.xjj.wefactory.wx.service.CartService;
import com.xjj.wefactory.wx.service.OrderGoodsService;
import com.xjj.wefactory.wx.service.OrderService;
import com.xjj.wefactory.wx.service.ProductService;
import com.xjj.wefactory.wx.service.RegionService;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 订单设计
 *
 * 订单状态：
 * 101 订单生成，未支付；102，下单后未支付用户取消；103，下单后未支付超时系统自动取消
 * 201 支付完成，商家未发货；202，订单生产，已付款未发货，但是退款取消；
 * 301 商家发货，用户未确认；
 * 401 用户确认收货，订单结束； 402 用户没有确认收货，但是快递反馈已收获后，超过一定时间，系统自动确认收货，订单结束。
 *
 * 当101用户未付款时，此时用户可以进行的操作是取消订单，或者付款操作
 * 当201支付完成而商家未发货时，此时用户可以取消订单并申请退款
 * 当301商家已发货时，此时用户可以有确认收货的操作
 * 当401用户确认收货以后，此时用户可以进行的操作是删除订单，评价商品，或者再次购买
 * 当402系统自动确认收货以后，此时用户可以删除订单，评价商品，或者再次购买
 *
 * 目前不支持订单退货和售后服务
 *
 */
@RestController
@RequestMapping("/wx/order")
public class WxOrderController {
    private final Log logger = LogFactory.getLog(WxOrderController.class);

    @Autowired
    private PlatformTransactionManager txManager;

    @Autowired
    private BuyerService buyerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderGoodsService orderGoodsService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CartService cartService;
    @Autowired
    private RegionService regionService;
    @Autowired
    private ProductService productService;

    @Autowired
    private WxPayService wxPayService;

    public WxOrderController() {
    }

    private String detailedAddress(AddressEntity address) {
        Long provinceId = address.getProvinceId();
        Long cityId = address.getCityId();
        Long areaId = address.getAreaId();
        String provinceName = regionService.getById(provinceId).getName();
        String cityName = regionService.getById(cityId).getName();
        String areaName = regionService.getById(areaId).getName();
        String fullRegion = provinceName + " " + cityName + " " + areaName;
        return fullRegion + " " + address.getAddress();
    }

    /**
     * 订单列表
     *
     * @param userId   用户ID
     * @param showType 订单信息
     *                 0， 全部订单
     *                 1，待付款
     *                 2，待发货
     *                 3，待收货
     *                 4，待评价
     * @param page     分页页数
     * @param size     分页大小
     * @return 订单操作结果
     * 成功则
     * {
     * code: 0,
     * msg: '成功',
     * data:
     * {
     * data: xxx ,
     * count: xxx
     * }
     * }
     * 失败则 { code: XXX, msg: XXX }
     */
    @RequestMapping("list")
    public Object list(@LoginUser Long userId, Integer showType,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
    	
        if (userId == null) {
            return WxJson.unlogin();
        }
        if (showType == null) {
            showType = 0;
        }

        List<Integer> orderStatus = OrderUtil.orderStatus(showType);
        
        //按类型查询
        XJJParameter param = new XJJParameter();
        param.addQuery("query.customerId@eq@l", userId);
        param.addQuery("query.orderStatus@in@s", orderStatus);
        
        List<OrderEntity> orderList = orderService.findList(param);
        int count = orderService.getCount(param);

        List<Map<String, Object>> orderVoList = new ArrayList<>(orderList.size());
        for (OrderEntity order : orderList) {
            Map<String, Object> orderVo = new HashMap<>();
            orderVo.put("id", order.getId());
            orderVo.put("orderSn", order.getOrderSn());
            orderVo.put("actualPrice", order.getActualPrice());
            orderVo.put("orderStatusText", OrderUtil.orderStatusText(order));
            orderVo.put("handleOption", OrderUtil.build(order));

            //根据orderId 查询
            List<OrderGoodsEntity> orderGoodsList = orderGoodsService.findListByProperty("orderId", order.getId());
            List<Map<String, Object>> orderGoodsVoList = new ArrayList<>(orderGoodsList.size());
            for (OrderGoodsEntity orderGoods : orderGoodsList) {
                Map<String, Object> orderGoodsVo = new HashMap<>();
                orderGoodsVo.put("id", orderGoods.getId());
                orderGoodsVo.put("goodsName", orderGoods.getGoodsName());
                orderGoodsVo.put("number", orderGoods.getNumber());
                orderGoodsVo.put("picUrl", orderGoods.getPicUrl());
                orderGoodsVoList.add(orderGoodsVo);
            }
            orderVo.put("goodsList", orderGoodsVoList);

            orderVoList.add(orderVo);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("count", count);
        result.put("data", orderVoList);

        return WxJson.ok(result);
    }

    /**
     * 订单详情
     *
     * @param userId  用户ID
     * @param orderId 订单信息
     * @return 订单操作结果
     * 成功则
     * {
     * code: 0,
     * msg: '成功',
     * data:
     * {
     * orderInfo: xxx ,
     * orderGoods: xxx
     * }
     * }
     * 失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("detail")
    public Object detail(@LoginUser Long userId, Long orderId) {
        if (userId == null) {
            return WxJson.unlogin();
        }
        if (orderId == null) {
            return WxJson.badArgument();
        }

        // 订单信息
        OrderEntity order = orderService.getById(orderId);
        if (null == order) {
            return WxJson.fail(403, "订单不存在");
        }
        if (!order.getCustomerId().equals(userId)) {
            return WxJson.fail(403, "不是当前用户的订单");
        }
        Map<String, Object> orderVo = new HashMap<String, Object>();
        orderVo.put("id", order.getId());
        orderVo.put("orderSn", order.getOrderSn());
        orderVo.put("addTime", LocalDate.now());
        orderVo.put("consignee", order.getConsignee());
        orderVo.put("mobile", order.getMobile());
        orderVo.put("address", order.getAddress());
        orderVo.put("goodsPrice", order.getGoodsPrice());
        orderVo.put("freightPrice", order.getFreightPrice());
        orderVo.put("actualPrice", order.getActualPrice());
        orderVo.put("orderStatusText", OrderUtil.orderStatusText(order));
        orderVo.put("handleOption", OrderUtil.build(order));

        List<OrderGoodsEntity> orderGoodsList = orderGoodsService.findListByProperty("orderId",order.getId());
        List<Map<String, Object>> orderGoodsVoList = new ArrayList<>(orderGoodsList.size());
        for (OrderGoodsEntity orderGoods : orderGoodsList) {
            Map<String, Object> orderGoodsVo = new HashMap<>();
            orderGoodsVo.put("id", orderGoods.getId());
            orderGoodsVo.put("orderId", orderGoods.getOrderId());
            orderGoodsVo.put("goodsId", orderGoods.getGoodsId());
            orderGoodsVo.put("goodsName", orderGoods.getGoodsName());
            orderGoodsVo.put("number", orderGoods.getNumber());
            orderGoodsVo.put("retailPrice", orderGoods.getPrice());
            orderGoodsVo.put("picUrl", orderGoods.getPicUrl());
            orderGoodsVo.put("goodsSpecificationValues", orderGoods.getGoodsSpecVals());
            orderGoodsVoList.add(orderGoodsVo);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("orderInfo", orderVo);
        result.put("orderGoods", orderGoodsVoList);
        return WxJson.ok(result);

    }

    /**
     * 提交订单
     * 1. 根据购物车ID、地址ID、优惠券ID，创建订单表项
     * 2. 购物车清空
     * 3. TODO 优惠券设置已用
     * 4. 商品货品数量减少
     *
     * @param userId 用户ID
     * @param body   订单信息，{ cartId：xxx, addressId: xxx, couponId: xxx }
     * @return 订单操作结果
     * 成功则 { code: 0, msg: '成功', data: { orderId: xxx } }
     * 失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("submit")
    public Object submit(@LoginUser Long userId, @RequestBody String body) {
        if (userId == null) {
            return WxJson.unlogin();
        }
        if (body == null) {
            return WxJson.badArgument();
        }
        Long cartId = JacksonUtil.parseLong(body, "cartId");
        Long addressId = JacksonUtil.parseLong(body, "addressId");
        Long couponId = JacksonUtil.parseLong(body, "couponId");
        if (cartId == null || addressId == null || couponId == null) {
            return WxJson.badArgument();
        }

        // 收货地址
        AddressEntity checkedAddress = addressService.getById(addressId);

        // 获取可用的优惠券信息
        // 使用优惠券减免的金额
        BigDecimal couponPrice = new BigDecimal(0.00);

        // 货品价格
        List<CartEntity> checkedGoodsList = null;
        if (cartId.equals(0)) {
            checkedGoodsList = cartService.queryByUidAndChecked(userId);
        } else {
        	CartEntity cart = cartService.getById(cartId);
            checkedGoodsList = new ArrayList<>(1);
            checkedGoodsList.add(cart);
        }
        if (checkedGoodsList.size() == 0) {
            return WxJson.badArgumentValue();
        }
        BigDecimal checkedGoodsPrice = new BigDecimal(0.00);
        for (CartEntity checkGoods : checkedGoodsList) {
            checkedGoodsPrice = checkedGoodsPrice.add(checkGoods.getPrice().multiply(new BigDecimal(checkGoods.getNumber())));
        }

        // 根据订单商品总价计算运费，满88则免运费，否则8元；
        BigDecimal freightPrice = new BigDecimal(0.00);
        if (checkedGoodsPrice.compareTo(new BigDecimal(88.00)) < 0) {
            freightPrice = new BigDecimal(8.00);
        }

        // 可以使用的其他钱，例如用户积分
        BigDecimal integralPrice = new BigDecimal(0.00);


        // 订单费用
        BigDecimal orderTotalPrice = checkedGoodsPrice.add(freightPrice).subtract(couponPrice);
        BigDecimal actualPrice = orderTotalPrice.subtract(integralPrice);

        // 开启事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);
        Long orderId = null;
        OrderEntity order = null;
        try {
            // 订单
            order = new OrderEntity();
            order.setCustomerId(userId);
            order.setOrderSn(orderService.generateOrderSn(userId));
            order.setAddTime(new Date());
            order.setOrderStatus(OrderUtil.STATUS_CREATE);
            order.setConsignee(checkedAddress.getName());
            order.setMobile(checkedAddress.getMobile());
            String detailedAddress = detailedAddress(checkedAddress);
            order.setAddress(detailedAddress);
            order.setGoodsPrice(checkedGoodsPrice);
            order.setFreightPrice(freightPrice);
            order.setCouponPrice(couponPrice);
            order.setIntegralPrice(integralPrice);
            order.setOrderPrice(orderTotalPrice);
            order.setActualPrice(actualPrice);
            // 添加订单表项
            orderService.save(order);
            orderId = order.getId();

            for (CartEntity cartGoods : checkedGoodsList) {
                // 订单商品
                OrderGoodsEntity orderGoods = new OrderGoodsEntity();
                orderGoods.setOrderId(order.getId());
                orderGoods.setGoodsId(cartGoods.getGoodsId());
                orderGoods.setGoodsSn(cartGoods.getGoodsSn());
                orderGoods.setProductId(cartGoods.getProductId());
                orderGoods.setGoodsName(cartGoods.getGoodsName());
                orderGoods.setPicUrl(cartGoods.getPicUrl());
                orderGoods.setPrice(cartGoods.getPrice());
                orderGoods.setNumber(cartGoods.getNumber());
                orderGoods.setGoodsSpecIds(cartGoods.getGoodsSpecIds());
                orderGoods.setAddTime(new Date());

                // 添加订单商品表项
                orderGoodsService.save(orderGoods);
            }

            // 删除购物车里面的商品信息
            cartService.clearGoods(userId);

            // 商品货品数量减少
            for (CartEntity checkGoods : checkedGoodsList) {
                Long productId = checkGoods.getProductId();
                ProductEntity product = productService.getById(productId);

                Integer remainNumber = product.getGoodsNumber() - checkGoods.getNumber();
                if (remainNumber < 0) {
                    throw new RuntimeException("下单的商品货品数量大于库存量");
                }
                product.setGoodsNumber(remainNumber);
                productService.update(product);
            }
        } catch (Exception ex) {
            txManager.rollback(status);
            logger.error("系统内部错误", ex);
            return WxJson.fail(403, "下单失败");
        }
        txManager.commit(status);

        Map<String, Object> data = new HashMap<>();
        data.put("orderId", orderId);
        return WxJson.ok(data);
    }

    /**
     * 取消订单
     * 1. 检测当前订单是否能够取消
     * 2. 设置订单取消状态
     * 3. 商品货品数量增加
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     * 成功则 { code: 0, msg: '成功' }
     * 失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("cancel")
    public Object cancel(@LoginUser Integer userId, @RequestBody String body) {
        if (userId == null) {
            return WxJson.unlogin();
        }
        Long orderId = JacksonUtil.parseLong(body, "orderId");
        if (orderId == null) {
            return WxJson.badArgument();
        }

        OrderEntity order = orderService.getById(orderId);
        if (order == null) {
            return WxJson.badArgumentValue();
        }
        if (!order.getCustomerId().equals(userId)) {
            return WxJson.badArgumentValue();
        }

        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isCancel()) {
            return WxJson.fail(403, "订单不能取消");
        }

        // 开启事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);
        try {
            // 设置订单已取消状态
            order.setOrderStatus(OrderUtil.STATUS_CANCEL);
            order.setEndTime(new Date());
            orderService.update(order);

            // 商品货品数量增加
            List<OrderGoodsEntity> orderGoodsList = orderGoodsService.findListByProperty("orderId",orderId);
            for (OrderGoodsEntity orderGoods : orderGoodsList) {
                Long productId = orderGoods.getProductId();
                ProductEntity product = productService.getById(productId);
                Integer number = product.getGoodsNumber() + orderGoods.getNumber();
                product.setGoodsNumber(number);
                productService.update(product);
            }
        } catch (Exception ex) {
            txManager.rollback(status);
            logger.error("系统内部错误", ex);
            return WxJson.fail(403, "订单取消失败");
        }
        txManager.commit(status);

        return WxJson.ok();
    }

    /**
     * 付款订单的预支付会话标识
     *
     * 1. 检测当前订单是否能够付款
     * 2. 微信支付平台返回支付订单ID
     * 3. 设置订单付款状态
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     * 成功则 { code: 0, msg: '模拟付款支付成功' }
     * 失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("prepay")
    public Object prepay(@LoginUser Long userId, @RequestBody String body) {
        if(userId == null){
            return WxJson.unlogin();
        }
        Long orderId = JacksonUtil.parseLong(body, "orderId");
        if (orderId == null) {
            return WxJson.badArgument();
        }

        OrderEntity order = orderService.getById(orderId);
        if (order == null) {
            return WxJson.badArgumentValue();
        }
        if (!order.getCustomerId().equals(userId)) {
            return WxJson.badArgumentValue();
        }

        // 检测是否能够取消
        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isPay()) {
            return WxJson.fail(403, "订单不能支付");
        }

        BuyerEntity user = buyerService.getById(userId);
        String openid = user.getWxOpenid();
        if(openid == null){
            return WxJson.fail(403, "订单不能支付");
        }
        WxPayMpOrderResult result = null;
        try {
            WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
            orderRequest.setOutTradeNo(order.getOrderSn());
            orderRequest.setOpenid(openid);
            // TODO 更有意义的显示名称
            orderRequest.setBody("小商场-订单测试支付");
            // 元转成分
            Integer fee = 1;
            // 这里演示仅支付1分
            // 实际项目取消下面两行注释
            // BigDecimal actualPrice = order.getActualPrice();
            // fee = actualPrice.multiply(new BigDecimal(100)).intValue();
            orderRequest.setTotalFee(fee);
            // TODO 用户IP地址
            orderRequest.setSpbillCreateIp("123.12.12.123");

            result = wxPayService.createOrder(orderRequest);
        } catch (Exception e) {
            e.printStackTrace();
            return WxJson.fail(403, "订单不能支付");
        }

        orderService.update(order);
        return WxJson.ok(result);
    }

    /**
     * 付款成功回调接口
     * 1. 检测当前订单是否是付款状态
     * 2. 设置订单付款成功状态相关信息
     * 3. 响应微信支付平台
     *
     * @param request
     * @param response
     * @return 订单操作结果
     * 成功则 WxPayNotifyResponse.success的XML内容
     * 失败则 WxPayNotifyResponse.fail的XML内容
     *
     * 注意，这里pay-notify是示例地址，开发者应该设立一个隐蔽的回调地址
     */
    @PostMapping("pay-notify")
    public Object payNotify(HttpServletRequest request, HttpServletResponse response) {
        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            WxPayOrderNotifyResult result = wxPayService.parseOrderNotifyResult(xmlResult);

            String orderSn = result.getOutTradeNo();
            String payId = result.getTransactionId();
            // 分转化成元
            String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());

            OrderEntity order = orderService.getByOrderSn(orderSn);
            if(order == null){
                throw new Exception("订单不存在 sn=" + orderSn);
            }

            // 检查这个订单是否已经处理过
            if(OrderUtil.isPayStatus(order) && order.getPayId() != null){
                return WxPayNotifyResponse.success("处理成功!");
            }

            // 检查支付订单金额
            // TODO 这里1分钱需要改成实际订单金额
            if(!totalFee.equals("0.01")){
                throw new Exception("支付金额不符合 totalFee=" + totalFee);
            }

            order.setPayId(payId);
            order.setPayTime(new Date());
            order.setOrderStatus(OrderUtil.STATUS_PAY);
            orderService.update(order);

            return WxPayNotifyResponse.success("处理成功!");
        } catch (Exception e) {
            logger.error("微信回调结果异常,异常原因 " + e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }
    }


    /**
     * 订单申请退款
     * 1. 检测当前订单是否能够退款
     * 2. 设置订单申请退款状态
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     * 成功则 { code: 0, msg: '成功' }
     * 失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("refund")
    public Object refund(@LoginUser Long userId, @RequestBody String body) {
        if (userId == null) {
            return WxJson.unlogin();
        }
        Long orderId = JacksonUtil.parseLong(body, "orderId");
        if (orderId == null) {
            return WxJson.badArgument();
        }

        OrderEntity order = orderService.getById(orderId);
        if (order == null) {
            return WxJson.badArgument();
        }
        if (!order.getCustomerId().equals(userId)) {
            return WxJson.badArgumentValue();
        }

        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isRefund()) {
            return WxJson.fail(403, "订单不能取消");
        }

        // 设置订单申请退款状态
        order.setOrderStatus(OrderUtil.STATUS_REFUND);
        orderService.update(order);

        return WxJson.ok();
    }

    /**
     * 确认收货
     * 1. 检测当前订单是否能够确认订单
     * 2. 设置订单确认状态
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     * 成功则 { code: 0, msg: '成功' }
     * 失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("confirm")
    public Object confirm(@LoginUser Long userId, @RequestBody String body) {
        if (userId == null) {
            return WxJson.unlogin();
        }
        Long orderId = JacksonUtil.parseLong(body, "orderId");
        if (orderId == null) {
            return WxJson.badArgument();
        }

        OrderEntity order = orderService.getById(orderId);
        if (order == null) {
            return WxJson.badArgument();
        }
        if (!order.getCustomerId().equals(userId)) {
            return WxJson.badArgumentValue();
        }

        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isConfirm()) {
            return WxJson.fail(403, "订单不能确认收货");
        }

        order.setOrderStatus(OrderUtil.STATUS_CONFIRM);
        order.setConfirmTime(new Date());
        orderService.update(order);
        return WxJson.ok();
    }

    /**
     * 删除订单
     * 1. 检测当前订单是否删除
     * 2. 设置订单删除状态
     *
     * @param userId 用户ID
     * @param body   订单信息，{ orderId：xxx }
     * @return 订单操作结果
     * 成功则 { code: 0, msg: '成功' }
     * 失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("delete")
    public Object delete(@LoginUser Long userId, @RequestBody String body) {
        if (userId == null) {
            return WxJson.unlogin();
        }
        Long orderId = JacksonUtil.parseLong(body, "orderId");
        if (orderId == null) {
            return WxJson.badArgument();
        }

        OrderEntity order = orderService.getById(orderId);
        if (order == null) {
            return WxJson.badArgument();
        }
        if (!order.getCustomerId().equals(userId)) {
            return WxJson.badArgumentValue();
        }

        OrderHandleOption handleOption = OrderUtil.build(order);
        if (!handleOption.isDelete()) {
            return WxJson.fail(403, "订单不能删除");
        }

        // 订单order_status没有字段用于标识删除
        // 而是存在专门的delete字段表示是否删除
        orderService.delete(orderId);

        return WxJson.ok();
    }

    /**
     * 可以评价的订单商品信息
     *
     * @param userId  用户ID
     * @param orderId 订单ID
     * @param goodsId 商品ID
     * @return 订单操作结果
     * 成功则 { code: 0, msg: '成功', data: xxx }
     * 失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("comment")
    public Object comment(@LoginUser Long userId, Long orderId, Long goodsId) {
        if (userId == null) {
            return WxJson.unlogin();
        }
        if (orderId == null) {
            return WxJson.badArgument();
        }
        
        List<OrderGoodsEntity> orderGoodsList = orderGoodsService.findByOidAndGid(orderId, goodsId);
        int size = orderGoodsList.size();

        Assert.state(size < 2, "存在多个符合条件的订单商品");

        if (size == 0) {
            return WxJson.badArgumentValue();
        }

        OrderGoodsEntity orderGoods = orderGoodsList.get(0);
        return WxJson.ok(orderGoods);
    }

}