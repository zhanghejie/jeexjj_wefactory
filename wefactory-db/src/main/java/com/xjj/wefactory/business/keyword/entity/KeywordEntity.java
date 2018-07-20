/****************************************************
 * Description: Entity for 关键词
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.keyword.entity;

import java.util.Date;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class KeywordEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public KeywordEntity(){}
    private Long sellerId;//seller_id
    private String keyword;//keyword
    private String url;//关键词的跳转链接
    private Integer isHot;//是否热点
    private Integer isDefault;//is_default
    private Integer isShow;//is_show
    private Integer sortOrder;//sort_order
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
     * 返回keyword
     * @return keyword
     */
    public String getKeyword() {
        return keyword;
    }
    
    /**
     * 设置keyword
     * @param keyword keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    /**
     * 返回关键词的跳转链接
     * @return 关键词的跳转链接
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * 设置关键词的跳转链接
     * @param url 关键词的跳转链接
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * 返回是否热点
     * @return 是否热点
     */
    public Integer getIsHot() {
        return isHot;
    }
    
    /**
     * 设置是否热点
     * @param isHot 是否热点
     */
    public void setIsHot(Integer isHot) {
        this.isHot = isHot;
    }
    
    /**
     * 返回is_default
     * @return is_default
     */
    public Integer getIsDefault() {
        return isDefault;
    }
    
    /**
     * 设置is_default
     * @param isDefault is_default
     */
    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.business.keyword.entity.KeywordEntity").append("ID="+this.getId()).toString();
    }
}

