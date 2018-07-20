/****************************************************
 * Description: Entity for 客户优惠券
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.buyer.entity;

import java.util.Date;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BuyerCouponEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public BuyerCouponEntity(){}
    private Integer customerId;//customer_id
    private Integer couponId;//coupon_id
    private Integer userId;//user_id
    private Integer orderId;//order_id
    private Date usedTime;//used_time
    private Date addTime;//add_time
    /**
     * 返回customer_id
     * @return customer_id
     */
    public Integer getCustomerId() {
        return customerId;
    }
    
    /**
     * 设置customer_id
     * @param customerId customer_id
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    /**
     * 返回coupon_id
     * @return coupon_id
     */
    public Integer getCouponId() {
        return couponId;
    }
    
    /**
     * 设置coupon_id
     * @param couponId coupon_id
     */
    public void setCouponId(Integer couponId) {
        this.couponId = couponId;
    }
    
    /**
     * 返回user_id
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }
    
    /**
     * 设置user_id
     * @param userId user_id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    /**
     * 返回order_id
     * @return order_id
     */
    public Integer getOrderId() {
        return orderId;
    }
    
    /**
     * 设置order_id
     * @param orderId order_id
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }
    
    /**
     * 返回used_time
     * @return used_time
     */
    public Date getUsedTime() {
        return usedTime;
    }
    
    /**
     * 设置used_time
     * @param usedTime used_time
     */
    public void setUsedTime(Date usedTime) {
        this.usedTime = usedTime;
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
    

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.customer.buyer.entity.BuyerCouponEntity").append("ID="+this.getId()).toString();
    }
}

