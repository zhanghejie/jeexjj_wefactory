/****************************************************
 * Description: Entity for 地址
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.address.entity;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.xjj.framework.entity.EntitySupport;

public class AddressEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public AddressEntity(){}
    private Long customerId;//customer_id
    private String name;//名称
    private Long provinceId;//省份
    private Long cityId;//城市
    private Long areaId;//区
    private String address;//地址
    private String mobile;//手机
    private Boolean isDefault;//is_default
    private Date addTime;//add_time
    private String status;//status
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
     * 返回省份
     * @return 省份
     */
    public Long getProvinceId() {
        return provinceId;
    }
    
    /**
     * 设置省份
     * @param provinceId 省份
     */
    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }
    
    /**
     * 返回城市
     * @return 城市
     */
    public Long getCityId() {
        return cityId;
    }
    
    /**
     * 设置城市
     * @param cityId 城市
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }
    
    /**
     * 返回区
     * @return 区
     */
    public Long getAreaId() {
        return areaId;
    }
    
    /**
     * 设置区
     * @param areaId 区
     */
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
    
    /**
     * 返回地址
     * @return 地址
     */
    public String getAddress() {
        return address;
    }
    
    /**
     * 设置地址
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * 返回手机
     * @return 手机
     */
    public String getMobile() {
        return mobile;
    }
    
    /**
     * 设置手机
     * @param mobile 手机
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
//    /**
//     * 返回is_default
//     * @return is_default
//     */
//    public Boolean getIsDefault() {
//        return isDefault;
//    }
//    
//    /**
//     * 设置is_default
//     * @param isDefault is_default
//     */
//    public void setIsDefault(Boolean isDefault) {
//        this.isDefault = isDefault;
//    }
    public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.customer.address.entity.AddressEntity").append("ID="+this.getId()).toString();
    }
}

