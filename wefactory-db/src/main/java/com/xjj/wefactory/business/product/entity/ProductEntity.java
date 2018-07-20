/****************************************************
 * Description: Entity for t_business_product
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.product.entity;

import java.util.Date;
import java.math.BigDecimal;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ProductEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public ProductEntity(){}
    private Integer goodsId;//goods_id
    private String goodsSpecificationIds;//goods_specification_ids
    private Integer goodsNumber;//goods_number
    private BigDecimal retailPrice;//retail_price
    private String url;//url
    private Date addTime;//add_time
    private Integer deleted;//deleted
    /**
     * 返回goods_id
     * @return goods_id
     */
    public Integer getGoodsId() {
        return goodsId;
    }
    
    /**
     * 设置goods_id
     * @param goodsId goods_id
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    
    /**
     * 返回goods_specification_ids
     * @return goods_specification_ids
     */
    public String getGoodsSpecificationIds() {
        return goodsSpecificationIds;
    }
    
    /**
     * 设置goods_specification_ids
     * @param goodsSpecificationIds goods_specification_ids
     */
    public void setGoodsSpecificationIds(String goodsSpecificationIds) {
        this.goodsSpecificationIds = goodsSpecificationIds;
    }
    
    /**
     * 返回goods_number
     * @return goods_number
     */
    public Integer getGoodsNumber() {
        return goodsNumber;
    }
    
    /**
     * 设置goods_number
     * @param goodsNumber goods_number
     */
    public void setGoodsNumber(Integer goodsNumber) {
        this.goodsNumber = goodsNumber;
    }
    
    /**
     * 返回retail_price
     * @return retail_price
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }
    
    /**
     * 设置retail_price
     * @param retailPrice retail_price
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }
    
    /**
     * 返回url
     * @return url
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * 设置url
     * @param url url
     */
    public void setUrl(String url) {
        this.url = url;
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
     * 返回deleted
     * @return deleted
     */
    public Integer getDeleted() {
        return deleted;
    }
    
    /**
     * 设置deleted
     * @param deleted deleted
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.business.product.entity.ProductEntity").append("ID="+this.getId()).toString();
    }
}

