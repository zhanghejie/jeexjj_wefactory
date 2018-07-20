/****************************************************
 * Description: Entity for 广告
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.ad.entity;

import java.util.Date;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

public class AdEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public AdEntity(){}
    private Long sellerId;//seller_id
    private Integer position;//位置
    private String name;//名称
    private String link;//链接
    private String url;//URL地址
    private String content;//内容
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime;//开始时间
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime;//结束时间
    private Date addTime;//创建时间
    private String status;//状态
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
     * 返回地点
     * @return 地点
     */
    public Integer getPosition() {
        return position;
    }
    
    /**
     * 设置地点
     * @param position 地点
     */
    public void setPosition(Integer position) {
        this.position = position;
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
     * 返回链接
     * @return 链接
     */
    public String getLink() {
        return link;
    }
    
    /**
     * 设置链接
     * @param link 链接
     */
    public void setLink(String link) {
        this.link = link;
    }
    
    /**
     * 返回URL地址
     * @return URL地址
     */
    public String getUrl() {
        return url;
    }
    
    /**
     * 设置URL地址
     * @param url URL地址
     */
    public void setUrl(String url) {
        this.url = url;
    }
    
    /**
     * 返回内容
     * @return 内容
     */
    public String getContent() {
        return content;
    }
    
    /**
     * 设置内容
     * @param content 内容
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * 返回开始时间
     * @return 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }
    
    /**
     * 设置开始时间
     * @param startTime 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
    
    /**
     * 返回结束时间
     * @return 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }
    
    /**
     * 设置结束时间
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.business.ad.entity.AdEntity").append("ID="+this.getId()).toString();
    }
}

