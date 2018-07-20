/****************************************************
 * Description: Entity for 商品
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
import org.apache.ibatis.annotations.Case;
import org.apache.ibatis.annotations.TypeDiscriminator;

public class GoodsEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public GoodsEntity(){}
    private Long brandId;//brand_id
    private Long categoryId;//category_id
    private String goodsSn;//goods_sn
    private String name;//名称
    private String[] gallery;//画廊图片
    private String keywords;//keywords
    private String goodsBrief;//goods_brief
    private Integer isOnSale;//is_on_sale
    private Integer sortOrder;//sort_order
    private BigDecimal counterPrice;//专柜价格
    private Integer isNewly;//是否新品
    private String primaryPicUrl;//商品主图
    private String listPicUrl;//商品列表图
    private Integer isHot;//is_hot
    private String goodsUnit;//商品单位
    private BigDecimal retailPrice;//零售价格
    private String goodsDesc;//goods_desc
    private String attributes;//参数{lable:"",val="",addTime=""}
    private Date addTime;//add_time
    private String status;//status
    /**
     * 返回brand_id
     * @return brand_id
     */
    public Long getBrandId() {
        return brandId;
    }
    
    /**
     * 设置brand_id
     * @param brandId brand_id
     */
    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
    
    /**
     * 返回category_id
     * @return category_id
     */
    public Long getCategoryId() {
        return categoryId;
    }
    
    /**
     * 设置category_id
     * @param categoryId category_id
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
     * 返回名称
     * @return 名称
     */
    public String getName() {
        return name;
    }
    
    /**
     * 设置名称
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * 返回画廊图片
     * @return 画廊图片
     */
    public String[] getGallery() {
        return gallery;
    }
    
    /**
     * 设置画廊图片
     * @param gallery 画廊图片
     */
    public void setGallery(String[] gallery) {
        this.gallery = gallery;
    }
    
    /**
     * 返回keywords
     * @return keywords
     */
    public String getKeywords() {
        return keywords;
    }
    
    /**
     * 设置keywords
     * @param keywords keywords
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    
    /**
     * 返回goods_brief
     * @return goods_brief
     */
    public String getGoodsBrief() {
        return goodsBrief;
    }
    
    /**
     * 设置goods_brief
     * @param goodsBrief goods_brief
     */
    public void setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief;
    }
    
    /**
     * 返回is_on_sale
     * @return is_on_sale
     */
    public Integer getIsOnSale() {
        return isOnSale;
    }
    
    /**
     * 设置is_on_sale
     * @param isOnSale is_on_sale
     */
    public void setIsOnSale(Integer isOnSale) {
        this.isOnSale = isOnSale;
    }
    
    /**
     * 返回sort_order
     * @return sort_order
     */
    public Integer getSortOrder() {
        return sortOrder;
    }
    
    /**
     * 设置sort_order
     * @param sortOrder sort_order
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
    
    /**
     * 返回专柜价格
     * @return 专柜价格
     */
    public BigDecimal getCounterPrice() {
        return counterPrice;
    }
    
    /**
     * 设置专柜价格
     * @param counterPrice 专柜价格
     */
    public void setCounterPrice(BigDecimal counterPrice) {
        this.counterPrice = counterPrice;
    }

    
    public Integer getIsNewly() {
		return isNewly;
	}

	public void setIsNewly(Integer isNewly) {
		this.isNewly = isNewly;
	}

	/**
     * 返回商品主图
     * @return 商品主图
     */
    public String getPrimaryPicUrl() {
        return primaryPicUrl;
    }
    
    /**
     * 设置商品主图
     * @param primaryPicUrl 商品主图
     */
    public void setPrimaryPicUrl(String primaryPicUrl) {
        this.primaryPicUrl = primaryPicUrl;
    }
    
    /**
     * 返回商品列表图
     * @return 商品列表图
     */
    public String getListPicUrl() {
        return listPicUrl;
    }
    
    /**
     * 设置商品列表图
     * @param listPicUrl 商品列表图
     */
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }
    
    /**
     * 返回is_hot
     * @return is_hot
     */
    public Integer getIsHot() {
        return isHot;
    }
    
    /**
     * 设置is_hot
     * @param isHot is_hot
     */
    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }
    
    /**
     * 返回商品单位
     * @return 商品单位
     */
    public String getGoodsUnit() {
        return goodsUnit;
    }
    
    /**
     * 设置商品单位
     * @param goodsUnit 商品单位
     */
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }
    
    /**
     * 返回零售价格
     * @return 零售价格
     */
    public BigDecimal getRetailPrice() {
        return retailPrice;
    }
    
    /**
     * 设置零售价格
     * @param retailPrice 零售价格
     */
    public void setRetailPrice(BigDecimal retailPrice) {
        this.retailPrice = retailPrice;
    }
    
    /**
     * 返回goods_desc
     * @return goods_desc
     */
    public String getGoodsDesc() {
        return goodsDesc;
    }
    
    /**
     * 设置goods_desc
     * @param goodsDesc goods_desc
     */
    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }
    
    /**
     * 返回参数{lable:"",val="",addTime=""}
     * @return 参数{lable:"",val="",addTime=""}
     */
    public String getAttributes() {
        return attributes;
    }
    
    /**
     * 设置参数{lable:"",val="",addTime=""}
     * @param attributes 参数{lable:"",val="",addTime=""}
     */
    public void setAttributes(String attributes) {
        this.attributes = attributes;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.business.goods.entity.GoodsEntity").append("ID="+this.getId()).toString();
    }
}

