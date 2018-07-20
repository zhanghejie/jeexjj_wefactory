/****************************************************
 * Description: Entity for t_customer_footprint
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.footprint.entity;

import java.util.Date;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class FootprintEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public FootprintEntity(){}
    private Long customerId;//customer_id
    private Long goodsId;//goods_id
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
     * 返回goods_id
     * @return goods_id
     */
    public Long getGoodsId() {
        return goodsId;
    }
    
    /**
     * 设置goods_id
     * @param goodsId goods_id
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.customer.footprint.entity.FootprintEntity").append("ID="+this.getId()).toString();
    }
}

