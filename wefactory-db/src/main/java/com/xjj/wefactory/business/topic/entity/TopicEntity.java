/****************************************************
 * Description: Entity for 专题
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.topic.entity;

import java.util.Date;
import java.math.BigDecimal;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class TopicEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public TopicEntity(){}
    private Long sellerId;//seller_id
    private String title;//标题
    private String content;//内容
    private String avatar;//avatar
    private String itemPicUrl;//item_pic_url
    private String subtitle;//专题子内容
    private BigDecimal priceInfo;//price_info
    private String readCount;//阅读次数
    private String scenePicUrl;//scene_pic_url
    private Integer sortOrder;//sort_order
    private Integer isShow;//is_show
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
     * 返回标题
     * @return 标题
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * 设置标题
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
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
     * 返回avatar
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }
    
    /**
     * 设置avatar
     * @param avatar avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    /**
     * 返回item_pic_url
     * @return item_pic_url
     */
    public String getItemPicUrl() {
        return itemPicUrl;
    }
    
    /**
     * 设置item_pic_url
     * @param itemPicUrl item_pic_url
     */
    public void setItemPicUrl(String itemPicUrl) {
        this.itemPicUrl = itemPicUrl;
    }
    
    /**
     * 返回专题子内容
     * @return 专题子内容
     */
    public String getSubtitle() {
        return subtitle;
    }
    
    /**
     * 设置专题子内容
     * @param subtitle 专题子内容
     */
    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
    
    /**
     * 返回price_info
     * @return price_info
     */
    public BigDecimal getPriceInfo() {
        return priceInfo;
    }
    
    /**
     * 设置price_info
     * @param priceInfo price_info
     */
    public void setPriceInfo(BigDecimal priceInfo) {
        this.priceInfo = priceInfo;
    }
    
    /**
     * 返回阅读次数
     * @return 阅读次数
     */
    public String getReadCount() {
        return readCount;
    }
    
    /**
     * 设置阅读次数
     * @param readCount 阅读次数
     */
    public void setReadCount(String readCount) {
        this.readCount = readCount;
    }
    
    /**
     * 返回scene_pic_url
     * @return scene_pic_url
     */
    public String getScenePicUrl() {
        return scenePicUrl;
    }
    
    /**
     * 设置scene_pic_url
     * @param scenePicUrl scene_pic_url
     */
    public void setScenePicUrl(String scenePicUrl) {
        this.scenePicUrl = scenePicUrl;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.business.topic.entity.TopicEntity").append("ID="+this.getId()).toString();
    }
}

