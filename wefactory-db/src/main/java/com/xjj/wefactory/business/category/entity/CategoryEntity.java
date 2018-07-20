/****************************************************
 * Description: Entity for 类目
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.category.entity;

import java.util.Date;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CategoryEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public CategoryEntity(){}
    private Long sellerId;//seller_id
    private String name;//名称
    private String keywords;//关键词
    private String frontDesc;//front_desc
    private Long parentId;//parent_id
    private Integer sortOrder;//sort_order
    private Integer showIndex;//show_index
    private Integer isShow;//is_show
    private String bannerUrl;//banner_url
    private String iconUrl;//icon_url
    private String imgUrl;//img_url
    private String wapBannerUrl;//wap_banner_url
    private String level;//level
    private Integer type;//type
    private String frontName;//front_name
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
     * 返回关键词
     * @return 关键词
     */
    public String getKeywords() {
        return keywords;
    }
    
    /**
     * 设置关键词
     * @param keywords 关键词
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }
    
    /**
     * 返回front_desc
     * @return front_desc
     */
    public String getFrontDesc() {
        return frontDesc;
    }
    
    /**
     * 设置front_desc
     * @param frontDesc front_desc
     */
    public void setFrontDesc(String frontDesc) {
        this.frontDesc = frontDesc;
    }
    
    /**
     * 返回parent_id
     * @return parent_id
     */
    public Long getParentId() {
        return parentId;
    }
    
    /**
     * 设置parent_id
     * @param parentId parent_id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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
     * 返回show_index
     * @return show_index
     */
    public Integer getShowIndex() {
        return showIndex;
    }
    
    /**
     * 设置show_index
     * @param showIndex show_index
     */
    public void setShowIndex(Integer showIndex) {
        this.showIndex = showIndex;
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
     * 返回banner_url
     * @return banner_url
     */
    public String getBannerUrl() {
        return bannerUrl;
    }
    
    /**
     * 设置banner_url
     * @param bannerUrl banner_url
     */
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
    
    /**
     * 返回icon_url
     * @return icon_url
     */
    public String getIconUrl() {
        return iconUrl;
    }
    
    /**
     * 设置icon_url
     * @param iconUrl icon_url
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    
    /**
     * 返回img_url
     * @return img_url
     */
    public String getImgUrl() {
        return imgUrl;
    }
    
    /**
     * 设置img_url
     * @param imgUrl img_url
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
    
    /**
     * 返回wap_banner_url
     * @return wap_banner_url
     */
    public String getWapBannerUrl() {
        return wapBannerUrl;
    }
    
    /**
     * 设置wap_banner_url
     * @param wapBannerUrl wap_banner_url
     */
    public void setWapBannerUrl(String wapBannerUrl) {
        this.wapBannerUrl = wapBannerUrl;
    }
    
    /**
     * 返回level
     * @return level
     */
    public String getLevel() {
        return level;
    }
    
    /**
     * 设置level
     * @param level level
     */
    public void setLevel(String level) {
        this.level = level;
    }
    
    /**
     * 返回type
     * @return type
     */
    public Integer getType() {
        return type;
    }
    
    /**
     * 设置type
     * @param type type
     */
    public void setType(Integer type) {
        this.type = type;
    }
    
    /**
     * 返回front_name
     * @return front_name
     */
    public String getFrontName() {
        return frontName;
    }
    
    /**
     * 设置front_name
     * @param frontName front_name
     */
    public void setFrontName(String frontName) {
        this.frontName = frontName;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.business.category.entity.CategoryEntity").append("ID="+this.getId()).toString();
    }
}

