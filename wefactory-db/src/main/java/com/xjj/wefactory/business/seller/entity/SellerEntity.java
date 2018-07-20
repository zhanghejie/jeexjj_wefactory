/****************************************************
 * Description: Entity for 商家
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.business.seller.entity;

import java.util.Date;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SellerEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public SellerEntity(){}
    private String identify;//账号
    private String name;//名称
    private String password;//密码
    private String mobile;//手机
    private String email;//邮箱
    private String lastLoginIp;//last_login_ip
    private Date lastLoginTime;//last_login_time
    private String avatar;//头像
    private Date addTime;//创建时间
    private String status;//状态
    /**
     * 返回账号
     * @return 账号
     */
    public String getIdentify() {
        return identify;
    }
    
    /**
     * 设置账号
     * @param identify 账号
     */
    public void setIdentify(String identify) {
        this.identify = identify;
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
     * 返回密码
     * @return 密码
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * 设置密码
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
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
    
    /**
     * 返回邮箱
     * @return 邮箱
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * 设置邮箱
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * 返回last_login_ip
     * @return last_login_ip
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }
    
    /**
     * 设置last_login_ip
     * @param lastLoginIp last_login_ip
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp;
    }
    
    /**
     * 返回last_login_time
     * @return last_login_time
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    
    /**
     * 设置last_login_time
     * @param lastLoginTime last_login_time
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
    
    /**
     * 返回头像
     * @return 头像
     */
    public String getAvatar() {
        return avatar;
    }
    
    /**
     * 设置头像
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.business.seller.entity.SellerEntity").append("ID="+this.getId()).toString();
    }
}

