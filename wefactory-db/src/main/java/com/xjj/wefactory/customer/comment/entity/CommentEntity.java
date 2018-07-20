/****************************************************
 * Description: Entity for 评论
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.comment.entity;

import java.util.Date;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CommentEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public CommentEntity(){}
    private Long customerId;//customer_id
    private Integer typeId;//type_id
    private Long valueId;//value_id
    private String content;//内容
    private Integer hasPicture;//has_picture
    private String picUrls;//pic_urls
    private Integer star;//评分， 1-5
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
     * 返回type_id
     * @return type_id
     */
    public Integer getTypeId() {
        return typeId;
    }
    
    /**
     * 设置type_id
     * @param typeId type_id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }
    
    /**
     * 返回value_id
     * @return value_id
     */
    public Long getValueId() {
        return valueId;
    }
    
    /**
     * 设置value_id
     * @param valueId value_id
     */
    public void setValueId(Long valueId) {
        this.valueId = valueId;
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
     * 返回has_picture
     * @return has_picture
     */
    public Integer getHasPicture() {
        return hasPicture;
    }
    
    /**
     * 设置has_picture
     * @param hasPicture has_picture
     */
    public void setHasPicture(Integer hasPicture) {
        this.hasPicture = hasPicture;
    }
    
    /**
     * 返回pic_urls
     * @return pic_urls
     */
    public String getPicUrls() {
        return picUrls;
    }
    
    /**
     * 设置pic_urls
     * @param picUrls pic_urls
     */
    public void setPicUrls(String picUrls) {
        this.picUrls = picUrls;
    }
    
    /**
     * 返回评分， 1-5
     * @return 评分， 1-5
     */
    public Integer getStar() {
        return star;
    }
    
    /**
     * 设置评分， 1-5
     * @param star 评分， 1-5
     */
    public void setStar(Integer star) {
        this.star = star;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.customer.comment.entity.CommentEntity").append("ID="+this.getId()).toString();
    }
}

