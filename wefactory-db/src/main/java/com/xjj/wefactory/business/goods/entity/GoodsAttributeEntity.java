/****************************************************
 * Description: Entity for t_business_goods_attribute
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-11 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.goods.entity;

import java.util.Date;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class GoodsAttributeEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public GoodsAttributeEntity(){}
    private Long goodsId;//goods_id
    private String value;//value
    private String attribute;//attribute
    private Date addTime;//add_time
    private Integer deleted;//deleted
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
     * 返回value
     * @return value
     */
    public String getValue() {
        return value;
    }
    
    /**
     * 设置value
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }
    
    /**
     * 返回attribute
     * @return attribute
     */
    public String getAttribute() {
        return attribute;
    }
    
    /**
     * 设置attribute
     * @param attribute attribute
     */
    public void setAttribute(String attribute) {
        this.attribute = attribute;
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
     * 返回deleted
     * @return deleted
     */
    public Integer getDeleted() {
        return deleted;
    }
    
    /**
     * 设置deleted
     * @param deleted deleted
     */
    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.business.goods.entity.GoodsAttributeEntity").append("ID="+this.getId()).toString();
    }
}

