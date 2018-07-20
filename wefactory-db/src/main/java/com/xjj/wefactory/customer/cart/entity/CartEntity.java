/****************************************************
 * Description: Entity for 购物车
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.cart.entity;

import java.util.Date;
import java.math.BigDecimal;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CartEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public CartEntity(){}
    private Long customerId;//customer_id
    private Long goodsId;//goods_id
    private Long productId;//product_id
    private String goodsSn;//goods_sn
    private String goodsName;//goods_name
    private BigDecimal price;//单价
    private Integer number;//数量
    private String goodsSpecVals;//规格属性组成的字符串，用来显示用
    private String goodsSpecIds;//goods_spec_ids
    private Integer checked;//checked
    private String picUrl;//pic_url
    private Date addTime;//add_time
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
     * 返回goods_id
     * @return goods_id
     */
    public Long getGoodsId() {
        return goodsId;
    }
    
    /**
     * 设置goods_id
     * @param goodsId goods_id
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    
    public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	/**
     * 返回goods_sn
     * @return goods_sn
     */
    public String getGoodsSn() {
        return goodsSn;
    }
    
    /**
     * 设置goods_sn
     * @param goodsSn goods_sn
     */
    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }
    
    /**
     * 返回goods_name
     * @return goods_name
     */
    public String getGoodsName() {
        return goodsName;
    }
    
    /**
     * 设置goods_name
     * @param goodsName goods_name
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    
    /**
     * 返回单价
     * @return 单价
     */
    public BigDecimal getPrice() {
        return price;
    }
    
    /**
     * 设置单价
     * @param price 单价
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    
    /**
     * 返回数量
     * @return 数量
     */
    public Integer getNumber() {
        return number;
    }
    
    /**
     * 设置数量
     * @param number 数量
     */
    public void setNumber(Integer number) {
        this.number = number;
    }
    
    /**
     * 返回规格属性组成的字符串，用来显示用
     * @return 规格属性组成的字符串，用来显示用
     */
    public String getGoodsSpecVals() {
        return goodsSpecVals;
    }
    
    /**
     * 设置规格属性组成的字符串，用来显示用
     * @param goodsSpecVals 规格属性组成的字符串，用来显示用
     */
    public void setGoodsSpecVals(String goodsSpecVals) {
        this.goodsSpecVals = goodsSpecVals;
    }
    
    /**
     * 返回goods_spec_ids
     * @return goods_spec_ids
     */
    public String getGoodsSpecIds() {
        return goodsSpecIds;
    }
    
    /**
     * 设置goods_spec_ids
     * @param goodsSpecIds goods_spec_ids
     */
    public void setGoodsSpecIds(String goodsSpecIds) {
        this.goodsSpecIds = goodsSpecIds;
    }
    
    /**
     * 返回checked
     * @return checked
     */
    public Integer getChecked() {
        return checked;
    }
    
    /**
     * 设置checked
     * @param checked checked
     */
    public void setChecked(Integer checked) {
        this.checked = checked;
    }
    
    /**
     * 返回pic_url
     * @return pic_url
     */
    public String getPicUrl() {
        return picUrl;
    }
    
    /**
     * 设置pic_url
     * @param picUrl pic_url
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.customer.cart.entity.CartEntity").append("ID="+this.getId()).toString();
    }
}

