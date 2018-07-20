package com.xjj.wefactory.wx.web;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xjj.framework.Constants;
import com.xjj.framework.utils.JacksonUtil;
import com.xjj.wefactory.business.goods.entity.GoodsEntity;
import com.xjj.wefactory.business.product.entity.ProductEntity;
import com.xjj.wefactory.customer.address.entity.AddressEntity;
import com.xjj.wefactory.customer.cart.entity.CartEntity;
import com.xjj.wefactory.wx.annotation.LoginUser;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.AddressService;
import com.xjj.wefactory.wx.service.CartService;
import com.xjj.wefactory.wx.service.GoodsService;
import com.xjj.wefactory.wx.service.GoodsSpecificationService;
import com.xjj.wefactory.wx.service.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/cart")
public class WxCartController {
    private final Log logger = LogFactory.getLog(WxCartController.class);

    @Autowired
    private CartService cartService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProductService productService;
    @Autowired
    private GoodsSpecificationService goodsSpecificationService;
    @Autowired
    private AddressService addressService;

    /**
     * 购物车
     *
     * @param userId 用户ID
     * @return 购物车
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              cartList: xxx,
     *              cartTotal: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("index")
    public Object index(@LoginUser Long userId) {
        if(userId == null){
            return WxJson.unlogin();
        }
        
        List<CartEntity> cartList = cartService.findListByProperty("customerId", userId);
        Integer goodsCount = 0;
        BigDecimal goodsAmount = new BigDecimal(0.00);
        Integer checkedGoodsCount = 0;
        BigDecimal checkedGoodsAmount = new BigDecimal(0.00);
        for (CartEntity cart : cartList) {
            goodsCount += cart.getNumber();
            goodsAmount = goodsAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            if (Constants.COMMON_SIMPLE_YES.equals(cart.getChecked().intValue())){
                checkedGoodsCount += cart.getNumber();
                checkedGoodsAmount = checkedGoodsAmount.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
            }
        }
        Map<String, Object> cartTotal = new HashMap<>();
        cartTotal.put("goodsCount", goodsCount);
        cartTotal.put("goodsAmount", goodsAmount);
        cartTotal.put("checkedGoodsCount", checkedGoodsCount);
        cartTotal.put("checkedGoodsAmount", checkedGoodsAmount);

        Map<String, Object> result = new HashMap<>();
        result.put("cartList", cartList);
        result.put("cartTotal", cartTotal);

        return WxJson.ok(result);
    }

    /**
     * 添加商品加入购物车
     * 如果已经存在购物车货品，则添加数量；
     * 否则添加新的购物车货品项。
     *
     * @param userId 用户ID
     * @param cart 购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 加入购物车操作结果
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data: xxx
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("add")
    public Object add(@LoginUser Long userId, @RequestBody CartEntity cart) {
        if(userId == null){
            return WxJson.unlogin();
        }
        if(cart == null){
            return WxJson.badArgument();
        }
        
        Long productId = cart.getProductId();
        Integer number = cart.getNumber().intValue();
        Long goodsId = cart.getGoodsId();
        if(!ObjectUtils.allNotNull(productId, number, goodsId)){
            return WxJson.badArgument();
        }

        //判断商品是否可以购买
        GoodsEntity goods = goodsService.getById(goodsId);
        if (goods == null || Constants.COMMON_SIMPLE_YES.equals(goods.getIsOnSale())) {
            return WxJson.fail(400, "商品已下架");
        }

        ProductEntity product = productService.getById(productId);
        //判断购物车中是否存在此规格商品
        CartEntity existCart = cartService.queryExist(goodsId, productId, userId);
        if(existCart == null){
            //取得规格的信息,判断规格库存
            if(product == null || number > product.getGoodsNumber() ){
                return WxJson.fail(400, "库存不足");
            }

            cart.setId(null);
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName((goods.getName()));
            cart.setPicUrl(goods.getListPicUrl());
            cart.setPrice(product.getRetailPrice());
            cart.setGoodsSpecIds(product.getGoodsSpecificationIds());
            cart.setCustomerId(userId);
            cart.setChecked(Constants.COMMON_SIMPLE_YES);
            cartService.save(cart);
        }
        else{
            //取得规格的信息,判断规格库存
            int num = existCart.getNumber() + number;
            if(num >  product.getGoodsNumber()){
                return WxJson.fail(400, "库存不足");
            }
            existCart.setNumber(num);
            cartService.update(existCart);
        }

        return goodscount(userId);
    }

    /**
     * 立即购买商品
     *
     * 和 前面一个方法add的区别在于
     * 1. 如果购物车内已经存在购物车货品，前者的逻辑是数量添加，这里的逻辑是数量覆盖
     * 2. 添加成功以后，前者的逻辑是返回当前购物车商品数量，这里的逻辑是返回对应购物车项的ID
     *
     * @param userId 用户ID
     * @param cart 购物车商品信息， { goodsId: xxx, productId: xxx, number: xxx }
     * @return 即购买操作结果
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data: xxx
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("fastadd")
    public Object fastadd(@LoginUser Long userId, @RequestBody CartEntity cart) {
        if(userId == null){
            return WxJson.unlogin();
        }
        if(cart == null){
            return WxJson.badArgument();
        }

        Long productId = cart.getProductId();
        Integer number = cart.getNumber().intValue();
        Long goodsId = cart.getGoodsId();
        if(!ObjectUtils.allNotNull(productId, number, goodsId)){
            return WxJson.badArgument();
        }

        //判断商品是否可以购买
        GoodsEntity goods = goodsService.getById(goodsId);
        if (goods == null || !Constants.COMMON_SIMPLE_YES.equals(goods.getIsOnSale())) {
            return WxJson.fail(400, "商品已下架");
        }

        ProductEntity product = productService.getById(productId);
        //判断购物车中是否存在此规格商品
        CartEntity existCart = cartService.queryExist(goodsId, productId, userId);
        if(existCart == null){
            //取得规格的信息,判断规格库存
            if(product == null || number > product.getGoodsNumber() ){
                return WxJson.fail(400, "库存不足");
            }

            cart.setId(null);
            cart.setGoodsSn(goods.getGoodsSn());
            cart.setGoodsName((goods.getName()));
            cart.setPicUrl(goods.getListPicUrl());
            cart.setPrice(product.getRetailPrice());
            cart.setGoodsSpecIds(product.getGoodsSpecificationIds());
            cart.setCustomerId(userId);
            cart.setChecked(Constants.COMMON_SIMPLE_YES);
            cartService.save(cart);
        }
        else{
            //取得规格的信息,判断规格库存
            int num = number;
            if(num >  product.getGoodsNumber()){
                return WxJson.fail(400, "库存不足");
            }
            existCart.setNumber(num);
            cartService.update(existCart);
        }

        return WxJson.ok(existCart != null ? existCart.getId() : cart.getId());
    }

    /**
     * 更新指定的购物车信息
     * 目前只支持修改商品的数量
     *
     * @param userId 用户ID
     * @param cart 购物车商品信息， { id: xxx, goodsId: xxx, productId: xxx, number: xxx }
     * @return 更新购物车操作结果
     *   成功则 { code: 0, msg: '成功' }
     *   失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("update")
    public Object update(@LoginUser Long userId, @RequestBody CartEntity cart) {
        if(userId == null){
            return WxJson.unlogin();
        }
        if(cart == null){
            return WxJson.badArgument();
        }
        Long productId = cart.getProductId();
        Integer number = cart.getNumber().intValue();
        Long goodsId = cart.getGoodsId();
        Long id = cart.getId();
        if(!ObjectUtils.allNotNull(id, productId, number, goodsId)){
            return WxJson.badArgument();
        }

        //判断是否存在该订单
        // 如果不存在，直接返回错误
        CartEntity existCart = cartService.getById(id);
        if(existCart == null){
            return WxJson.badArgumentValue();
        }

        // 判断goodsId和productId是否与当前cart里的值一致
        if(!existCart.getGoodsId().equals(goodsId)){
            return WxJson.badArgumentValue();
        }
        if(!existCart.getProductId().equals(productId)){
            return WxJson.badArgumentValue();
        }

        //判断商品是否可以购买
        GoodsEntity goods = goodsService.getById(goodsId);
        if (goods == null || !Constants.COMMON_SIMPLE_YES.equals(goods.getIsOnSale())) {
            return WxJson.fail(403, "商品已下架");
        }

        //取得规格的信息,判断规格库存
        ProductEntity product = productService.getById(productId);
        if(product == null || product.getGoodsNumber() < number){
            return WxJson.fail(403, "库存不足");
        }

        existCart.setNumber(number);
        cartService.update(existCart);
        return WxJson.ok();
    }

    /**
     * 购物车商品勾选
     * 如果原来没有勾选，则设置勾选状态；如果商品已经勾选，则设置非勾选状态。
     *
     * @param userId 用户ID
     * @param body 购物车商品信息， { productIds: xxx }
     * @return 购物车信息
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data: xxx
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("checked")
    public Object checked(@LoginUser Long userId, @RequestBody String body) {
        if(userId == null){
            return WxJson.unlogin();
        }
        if(body == null){
            return WxJson.badArgument();
        }
        
        List<Long> productIds = JacksonUtil.parseLongList(body, "productIds");
        if(productIds == null){
            return WxJson.badArgument();
        }

        Integer checkValue = JacksonUtil.parseInteger(body, "isChecked");
        if(checkValue == null){
            return WxJson.badArgument();
        }
        Boolean isChecked = ((checkValue.intValue()) == 1);

        cartService.updateCheck(userId, productIds, isChecked);
        return index(userId);
    }

    /**
     * 购物车商品删除
     *
     * @param userId 用户ID
     * @param body 购物车商品信息， { productIds: xxx }
     * @return 购物车信息
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data: xxx
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("delete")
    public Object delete(@LoginUser Long userId, @RequestBody String body) {
        if(userId == null){
            return WxJson.unlogin();
        }
        if(body == null){
            return WxJson.badArgument();
        }
        
        List<Long> productIds = JacksonUtil.parseLongList(body, "productIds");

        if(productIds == null || productIds.size() == 0){
            return WxJson.badArgument();
        }

        cartService.deleteByProductId(userId,productIds);
        return index(userId);
    }

    /**
     * 购物车商品数量
     * 如果用户没有登录，则返回空数据。
     *
     * @param userId 用户ID
     * @return 购物车商品数量
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data: xxx
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @RequestMapping("goodscount")
    public Object goodscount(@LoginUser Long userId) {
        if(userId == null){
            return WxJson.ok(0);
        }
        
        int goodsCount = 0;
        List<CartEntity> cartList = cartService.findListByProperty("customerId", userId);
        for(CartEntity cart : cartList){
            goodsCount += cart.getNumber();
        }

        return WxJson.ok(goodsCount);
    }

    /**
     * 购物车下单信息
     *
     * @param userId 用户ID
     * @param cartId 购物车商品ID
     *    如果购物车商品ID是空，则下单当前用户所有购物车商品；
     *    如果购物车商品ID非空，则只下单当前购物车商品。
     * @param addressId 收货地址ID
     *    如果收货地址ID是空，则查询当前用户的默认地址。
     * @param couponId 优惠券ID
     *    目前不支持
     * @return 购物车下单信息
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *      {
     *          addressId: xxx,
     *          checkedAddress: xxx,
     *          couponId: xxx,
     *          checkedCoupon: xxx,
     *          goodsTotalPrice: xxx,
     *          freightPrice: xxx,
     *          couponPrice: xxx,
     *          orderTotalPrice: xxx,
     *          actualPrice: xxx,
     *          checkedGoodsList: xxx
     *      }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("checkout")
    public Object checkout(@LoginUser Long userId, Long cartId, Long addressId, Long couponId) {
        if(userId == null){
            return WxJson.unlogin();
        }

        // 收货地址
        AddressEntity checkedAddress = null;
        if(addressId == null || addressId.equals(0L)){
            checkedAddress = addressService.getDefault(userId);
            // 如果仍然没有地址，则是没有收获地址
            // 返回一个空的地址id=0，这样前端则会提醒添加地址
            if(checkedAddress == null){
                checkedAddress = new AddressEntity();
                checkedAddress.setId(0L);
                addressId = 0L;
            }
            else{
                addressId = checkedAddress.getId();
            }

        }
        else {
            checkedAddress = addressService.getById(addressId);
            // 如果null, 则报错
            if(checkedAddress == null){
                return WxJson.badArgumentValue();
            }
        }

        // 获取可用的优惠券信息
        // 使用优惠券减免的金额
        BigDecimal couponPrice = new BigDecimal(0.00);

        // 商品价格
        List<CartEntity> checkedGoodsList = null;
        if(cartId == null || cartId.equals(0)) {
            checkedGoodsList = cartService.queryByUidAndChecked(userId);
        }
        else {
        	CartEntity cart = cartService.getById(cartId);
            if (cart == null){
                return WxJson.badArgumentValue();
            }
            checkedGoodsList = new ArrayList<>(1);
            checkedGoodsList.add(cart);
        }
        BigDecimal checkedGoodsPrice = new BigDecimal(0.00);
        for (CartEntity cart : checkedGoodsList) {
            checkedGoodsPrice = checkedGoodsPrice.add(cart.getPrice().multiply(new BigDecimal(cart.getNumber())));
        }

        // 根据订单商品总价计算运费，满88则免运费，否则8元；
        BigDecimal freightPrice = new BigDecimal(0.00);
        if(checkedGoodsPrice.compareTo(new BigDecimal(88.00)) == -1){
            freightPrice = new BigDecimal(8.00);
        }

        // 可以使用的其他钱，例如用户积分
        BigDecimal integralPrice = new BigDecimal(0.00);

        // 订单费用
        BigDecimal orderTotalPrice = checkedGoodsPrice.add(freightPrice).subtract(couponPrice);
        BigDecimal actualPrice = orderTotalPrice.subtract(integralPrice);

        Map<String, Object> data = new HashMap<>();
        data.put("addressId", addressId);
        data.put("checkedAddress", checkedAddress);
        data.put("couponId", couponId);
        data.put("checkedCoupon", 0);
        data.put("couponList", "");
        data.put("goodsTotalPrice", checkedGoodsPrice);
        data.put("freightPrice", freightPrice);
        data.put("couponPrice", couponPrice);
        data.put("orderTotalPrice", orderTotalPrice);
        data.put("actualPrice", actualPrice);
        data.put("checkedGoodsList", checkedGoodsList);
        return WxJson.ok(data);
    }

    /**
     * 商品优惠券列表
     * 目前不支持
     *
     * @param userId 用户ID
     * @return 商品优惠券信息
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data: xxx
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("checkedCouponList")
    public Object checkedCouponList(@LoginUser Integer userId) {
        if(userId == null){
            return WxJson.unlogin();
        }
        return WxJson.unsupport();
    }
}