/****************************************************
 * Description: Entity for 订单
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.order.entity;

import java.util.Date;
import java.math.BigDecimal;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class OrderEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public OrderEntity(){}
    private Long customerId;//customer_id
    private String orderSn;//order_sn
    private Integer orderStatus;//订单状态
    private String consignee;//consignee
    private String mobile;//mobile
    private String address;//address
    private BigDecimal goodsPrice;//商品总费用
    private BigDecimal freightPrice;//配送费用
    private BigDecimal couponPrice;//优惠券减免
    private BigDecimal integralPrice;//用户积分减免
    private BigDecimal orderPrice;//订单费用， = goods_price + freight_price - coupon_price
    private BigDecimal actualPrice;//实付费用， = order_price - integral_price
    private String payId;//微信付款编号
    private Integer payStatus;//支付状态
    private Date payTime;//微信付款时间
    private String shipSn;//发货编号
    private String shipChannel;//发货快递公司
    private Date shipStartTime;//发货开始时间
    private Date shipEndTime;//发货结束时间
    private Date confirmTime;//用户确认收货时间
    private Date endTime;//end_time
    private Date addTime;//add_time
    private String status;//status
    /**
     * 返回customer_id
     * @return customer_id
     */
    public Long getCustomerId() {
        return customerId;
    }
    
    /**
     * 设置customer_id
     * @param customerId customer_id
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    /**
     * 返回order_sn
     * @return order_sn
     */
    public String getOrderSn() {
        return orderSn;
    }
    
    /**
     * 设置order_sn
     * @param orderSn order_sn
     */
    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }
    
    /**
     * 返回订单状态
     * @return 订单状态
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }
    
    /**
     * 设置订单状态
     * @param orderStatus 订单状态
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
    
    /**
     * 返回consignee
     * @return consignee
     */
    public String getConsignee() {
        return consignee;
    }
    
    /**
     * 设置consignee
     * @param consignee consignee
     */
    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }
    
    /**
     * 返回mobile
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }
    
    /**
     * 设置mobile
     * @param mobile mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    /**
     * 返回address
     * @return address
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * 设置address
     * @param address address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * 返回商品总费用
     * @return 商品总费用
     */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }
    
    /**
     * 设置商品总费用
     * @param goodsPrice 商品总费用
     */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
    
    /**
     * 返回配送费用
     * @return 配送费用
     */
    public BigDecimal getFreightPrice() {
        return freightPrice;
    }
    
    /**
     * 设置配送费用
     * @param freightPrice 配送费用
     */
    public void setFreightPrice(BigDecimal freightPrice) {
        this.freightPrice = freightPrice;
    }
    
    /**
     * 返回优惠券减免
     * @return 优惠券减免
     */
    public BigDecimal getCouponPrice() {
        return couponPrice;
    }
    
    /**
     * 设置优惠券减免
     * @param couponPrice 优惠券减免
     */
    public void setCouponPrice(BigDecimal couponPrice) {
        this.couponPrice = couponPrice;
    }
    
    /**
     * 返回用户积分减免
     * @return 用户积分减免
     */
    public BigDecimal getIntegralPrice() {
        return integralPrice;
    }
    
    /**
     * 设置用户积分减免
     * @param integralPrice 用户积分减免
     */
    public void setIntegralPrice(BigDecimal integralPrice) {
        this.integralPrice = integralPrice;
    }
    
    /**
     * 返回订单费用， = goods_price + freight_price - coupon_price
     * @return 订单费用， = goods_price + freight_price - coupon_price
     */
    public BigDecimal getOrderPrice() {
        return orderPrice;
    }
    
    /**
     * 设置订单费用， = goods_price + freight_price - coupon_price
     * @param orderPrice 订单费用， = goods_price + freight_price - coupon_price
     */
    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }
    
    /**
     * 返回实付费用， = order_price - integral_price
     * @return 实付费用， = order_price - integral_price
     */
    public BigDecimal getActualPrice() {
        return actualPrice;
    }
    
    /**
     * 设置实付费用， = order_price - integral_price
     * @param actualPrice 实付费用， = order_price - integral_price
     */
    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }
    
    /**
     * 返回微信付款编号
     * @return 微信付款编号
     */
    public String getPayId() {
        return payId;
    }
    
    /**
     * 设置微信付款编号
     * @param payId 微信付款编号
     */
    public void setPayId(String payId) {
        this.payId = payId;
    }
    
    /**
     * 返回支付状态
     * @return 支付状态
     */
    public Integer getPayStatus() {
        return payStatus;
    }
    
    /**
     * 设置支付状态
     * @param payStatus 支付状态
     */
    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }
    
    /**
     * 返回微信付款时间
     * @return 微信付款时间
     */
    public Date getPayTime() {
        return payTime;
    }
    
    /**
     * 设置微信付款时间
     * @param payTime 微信付款时间
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }
    
    /**
     * 返回发货编号
     * @return 发货编号
     */
    public String getShipSn() {
        return shipSn;
    }
    
    /**
     * 设置发货编号
     * @param shipSn 发货编号
     */
    public void setShipSn(String shipSn) {
        this.shipSn = shipSn;
    }
    
    /**
     * 返回发货快递公司
     * @return 发货快递公司
     */
    public String getShipChannel() {
        return shipChannel;
    }
    
    /**
     * 设置发货快递公司
     * @param shipChannel 发货快递公司
     */
    public void setShipChannel(String shipChannel) {
        this.shipChannel = shipChannel;
    }
    
    /**
     * 返回发货开始时间
     * @return 发货开始时间
     */
    public Date getShipStartTime() {
        return shipStartTime;
    }
    
    /**
     * 设置发货开始时间
     * @param shipStartTime 发货开始时间
     */
    public void setShipStartTime(Date shipStartTime) {
        this.shipStartTime = shipStartTime;
    }
    
    /**
     * 返回发货结束时间
     * @return 发货结束时间
     */
    public Date getShipEndTime() {
        return shipEndTime;
    }
    
    /**
     * 设置发货结束时间
     * @param shipEndTime 发货结束时间
     */
    public void setShipEndTime(Date shipEndTime) {
        this.shipEndTime = shipEndTime;
    }
    
    /**
     * 返回用户确认收货时间
     * @return 用户确认收货时间
     */
    public Date getConfirmTime() {
        return confirmTime;
    }
    
    /**
     * 设置用户确认收货时间
     * @param confirmTime 用户确认收货时间
     */
    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }
    
    /**
     * 返回end_time
     * @return end_time
     */
    public Date getEndTime() {
        return endTime;
    }
    
    /**
     * 设置end_time
     * @param endTime end_time
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    
    /**
     * 返回add_time
     * @return add_time
     */
    public Date getAddTime() {
        return addTime;
    }
    
    /**
     * 设置add_time
     * @param addTime add_time
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    
    /**
     * 返回status
     * @return status
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * 设置status
     * @param status status
     */
    public void setStatus(String status) {
        this.status = status;
    }
    

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.customer.order.entity.OrderEntity").append("ID="+this.getId()).toString();
    }
}

