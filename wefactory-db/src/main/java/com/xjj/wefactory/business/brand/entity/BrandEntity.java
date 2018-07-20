/****************************************************
 * Description: Entity for 品牌表
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.brand.entity;

import java.util.Date;
import java.math.BigDecimal;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BrandEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public BrandEntity(){}
    private Long sellerId;//seller_id
    private String sellerName;
    private String name;//名称
    private String listPicUrl;//list_pic_url
    private String simpleDesc;//simple_desc
    private String picUrl;//pic_url
    private Integer sortOrder;//sort_order
    private Integer isShow;//is_show
    private BigDecimal floorPrice;//floor_price
    private String appListPicUrl;//app_list_pic_url
    private Integer isNewly;//isNewly
    private String newPicUrl;//new_pic_url
    private Integer newSortOrder;//new_sort_order
    private Date addTime;//add_time
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
    
    public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
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
     * 返回list_pic_url
     * @return list_pic_url
     */
    public String getListPicUrl() {
        return listPicUrl;
    }
    
    /**
     * 设置list_pic_url
     * @param listPicUrl list_pic_url
     */
    public void setListPicUrl(String listPicUrl) {
        this.listPicUrl = listPicUrl;
    }
    
    /**
     * 返回simple_desc
     * @return simple_desc
     */
    public String getSimpleDesc() {
        return simpleDesc;
    }
    
    /**
     * 设置simple_desc
     * @param simpleDesc simple_desc
     */
    public void setSimpleDesc(String simpleDesc) {
        this.simpleDesc = simpleDesc;
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
     * 返回is_show
     * @return is_show
     */
    public Integer getIsShow() {
        return isShow;
    }
    
    /**
     * 设置is_show
     * @param isShow is_show
     */
    public void setIsShow(Integer isShow) {
        this.isShow = isShow;
    }
    
    /**
     * 返回floor_price
     * @return floor_price
     */
    public BigDecimal getFloorPrice() {
        return floorPrice;
    }
    
    /**
     * 设置floor_price
     * @param floorPrice floor_price
     */
    public void setFloorPrice(BigDecimal floorPrice) {
        this.floorPrice = floorPrice;
    }
    
    /**
     * 返回app_list_pic_url
     * @return app_list_pic_url
     */
    public String getAppListPicUrl() {
        return appListPicUrl;
    }
    
    /**
     * 设置app_list_pic_url
     * @param appListPicUrl app_list_pic_url
     */
    public void setAppListPicUrl(String appListPicUrl) {
        this.appListPicUrl = appListPicUrl;
    }
    
    public Integer getIsNewly() {
		return isNewly;
	}

	public void setIsNewly(Integer isNewly) {
		this.isNewly = isNewly;
	}

	/**
     * 返回new_pic_url
     * @return new_pic_url
     */
    public String getNewPicUrl() {
        return newPicUrl;
    }
    
    /**
     * 设置new_pic_url
     * @param newPicUrl new_pic_url
     */
    public void setNewPicUrl(String newPicUrl) {
        this.newPicUrl = newPicUrl;
    }
    
    /**
     * 返回new_sort_order
     * @return new_sort_order
     */
    public Integer getNewSortOrder() {
        return newSortOrder;
    }
    
    /**
     * 设置new_sort_order
     * @param newSortOrder new_sort_order
     */
    public void setNewSortOrder(Integer newSortOrder) {
        this.newSortOrder = newSortOrder;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.business.brand.entity.BrandEntity").append("ID="+this.getId()).toString();
    }
}

