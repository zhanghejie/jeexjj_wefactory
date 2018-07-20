/****************************************************
 * Description: Entity for 收藏
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.collect.entity;

import java.util.Date;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class CollectEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public CollectEntity(){}
    private Long customerId;//customer_id
    private Long valueId;//value_id
    private Integer isAttention;//是否是关注
    private Integer typeId;//type_id
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
     * 返回是否是关注
     * @return 是否是关注
     */
    public Integer getIsAttention() {
        return isAttention;
    }
    
    /**
     * 设置是否是关注
     * @param isAttention 是否是关注
     */
    public void setIsAttention(Integer isAttention) {
        this.isAttention = isAttention;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.customer.collect.entity.CollectEntity").append("ID="+this.getId()).toString();
    }
}

