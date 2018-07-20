/****************************************************
 * Description: Entity for 搜索历史
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.search.entity;

import java.util.Date;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SearchHistoryEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public SearchHistoryEntity(){}
    private Long customerId;//customer_id
    private String keyword;//关键词
    private String comeFrom;//搜索来源，如PC、小程序、APP等
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
     * 返回关键词
     * @return 关键词
     */
    public String getKeyword() {
        return keyword;
    }
    
    /**
     * 设置关键词
     * @param keyword 关键词
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    /**
     * 返回搜索来源，如PC、小程序、APP等
     * @return 搜索来源，如PC、小程序、APP等
     */
    public String getComeFrom() {
        return comeFrom;
    }
    
    /**
     * 设置搜索来源，如PC、小程序、APP等
     * @param comeFrom 搜索来源，如PC、小程序、APP等
     */
    public void setComeFrom(String comeFrom) {
        this.comeFrom = comeFrom;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.customer.search.entity.SearchHistoryEntity").append("ID="+this.getId()).toString();
    }
}

