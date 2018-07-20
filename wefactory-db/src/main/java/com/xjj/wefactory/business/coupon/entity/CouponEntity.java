/****************************************************
 * Description: Entity for 优惠券
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.coupon.entity;

import java.util.Date;
import java.math.BigDecimal;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

public class CouponEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public CouponEntity(){}
    private Long sellerId;//seller_id
    private String name;//name
    private BigDecimal typeMoney;//type_money
    private Integer sendType;//send_type
    private BigDecimal minAmount;//min_amount
    private BigDecimal maxAmount;//max_amount
    private BigDecimal minGoodsAmount;//min_goods_amount
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date sendStart;//send_start
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date sendEnd;//send_end
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date useStart;//use_start
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date useEnd;//use_end
    private Date addTime;//add_time
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private String status;//status
    /**
     * 返回seller_id
     * @return seller_id
     */
    public Long getSellerId() {
        return sellerId;
    }
    
    /**
     * 设置seller_id
     * @param sellerId seller_id
     */
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
    
    /**
     * 返回name
     * @return name
     */
    public String getName() {
        return name;
    }
    
    /**
     * 设置name
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 返回type_money
     * @return type_money
     */
    public BigDecimal getTypeMoney() {
        return typeMoney;
    }
    
    /**
     * 设置type_money
     * @param typeMoney type_money
     */
    public void setTypeMoney(BigDecimal typeMoney) {
        this.typeMoney = typeMoney;
    }
    
    /**
     * 返回send_type
     * @return send_type
     */
    public Integer getSendType() {
        return sendType;
    }
    
    /**
     * 设置send_type
     * @param sendType send_type
     */
    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }
    
    /**
     * 返回min_amount
     * @return min_amount
     */
    public BigDecimal getMinAmount() {
        return minAmount;
    }
    
    /**
     * 设置min_amount
     * @param minAmount min_amount
     */
    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }
    
    /**
     * 返回max_amount
     * @return max_amount
     */
    public BigDecimal getMaxAmount() {
        return maxAmount;
    }
    
    /**
     * 设置max_amount
     * @param maxAmount max_amount
     */
    public void setMaxAmount(BigDecimal maxAmount) {
        this.maxAmount = maxAmount;
    }
    
    /**
     * 返回min_goods_amount
     * @return min_goods_amount
     */
    public BigDecimal getMinGoodsAmount() {
        return minGoodsAmount;
    }
    
    /**
     * 设置min_goods_amount
     * @param minGoodsAmount min_goods_amount
     */
    public void setMinGoodsAmount(BigDecimal minGoodsAmount) {
        this.minGoodsAmount = minGoodsAmount;
    }
    
    /**
     * 返回send_start
     * @return send_start
     */
    public Date getSendStart() {
        return sendStart;
    }
    
    /**
     * 设置send_start
     * @param sendStart send_start
     */
    public void setSendStart(Date sendStart) {
        this.sendStart = sendStart;
    }
    
    /**
     * 返回send_end
     * @return send_end
     */
    public Date getSendEnd() {
        return sendEnd;
    }
    
    /**
     * 设置send_end
     * @param sendEnd send_end
     */
    public void setSendEnd(Date sendEnd) {
        this.sendEnd = sendEnd;
    }
    
    /**
     * 返回use_start
     * @return use_start
     */
    public Date getUseStart() {
        return useStart;
    }
    
    /**
     * 设置use_start
     * @param useStart use_start
     */
    public void setUseStart(Date useStart) {
        this.useStart = useStart;
    }
    
    /**
     * 返回use_end
     * @return use_end
     */
    public Date getUseEnd() {
        return useEnd;
    }
    
    /**
     * 设置use_end
     * @param useEnd use_end
     */
    public void setUseEnd(Date useEnd) {
        this.useEnd = useEnd;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.business.coupon.entity.CouponEntity").append("ID="+this.getId()).toString();
    }
}

