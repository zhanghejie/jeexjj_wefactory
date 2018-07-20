/****************************************************
 * Description: Entity for 商品规格
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.goods.entity;

import java.util.Date;
import java.math.BigDecimal;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GoodsSpecificationEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public GoodsSpecificationEntity(){}
    private Integer goodsId;//goods_id
    private String value;//value
    private String picUrl;//pic_url
    private String specification;//specification
    private BigDecimal price;//单价
    private Integer stockNumber;//库存量
    private Date addTime;//创建时间
    private String status;//状态
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
     * 返回value
     * @return value
     */
    public String getValue() {
        return value;
    }
    
    /**
     * 设置value
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
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
     * 返回specification
     * @return specification
     */
    public String getSpecification() {
        return specification;
    }
    
    /**
     * 设置specification
     * @param specification specification
     */
    public void setSpecification(String specification) {
        this.specification = specification;
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
     * 返回库存量
     * @return 库存量
     */
    public Integer getStockNumber() {
        return stockNumber;
    }
    
    /**
     * 设置库存量
     * @param stockNumber 库存量
     */
    public void setStockNumber(Integer stockNumber) {
        this.stockNumber = stockNumber;
    }
    
    /**
     * 返回创建时间
     * @return 创建时间
     */
    public Date getAddTime() {
        return addTime;
    }
    
    /**
     * 设置创建时间
     * @param addTime 创建时间
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    
    /**
     * 返回状态
     * @return 状态
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * 设置状态
     * @param status 状态
     */
    public void setStatus(String status) {
        this.status = status;
    }
    

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.business.goods.entity.GoodsSpecificationEntity").append("ID="+this.getId()).toString();
    }
}

