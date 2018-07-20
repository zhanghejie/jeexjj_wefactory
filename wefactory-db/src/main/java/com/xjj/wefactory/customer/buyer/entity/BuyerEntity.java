/****************************************************
 * Description: Entity for 客户
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

package com.xjj.wefactory.customer.buyer.entity;

import java.util.Date;
import com.xjj.framework.entity.EntitySupport;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class BuyerEntity extends EntitySupport {

    private static final long serialVersionUID = 1L;
    public BuyerEntity(){}
    private Long sellerId;//seller_id
    private String username;//用户名称
    private String password;//密码
    private String gender;//0 男， 1 女， 2 未知
    private Date birthday;//生日
    private Date lastLoginTime;//最后登陆时间
    private String lastLoginIp;//last_login_ip
    private String userLevel;//0 普通用户，1 VIP用户，2 高级VIP用户
    private String nickname;//用户昵称或网络名称
    private String mobile;//用户手机号码
    private String registerIp;//register_ip
    private String avatar;//头像
    private String wxOpenid;//wx_openid
    private String wxUnionid;//wx_unionid
    private Date addTime;//add_time
    private String status;//0 可用, 1 禁用, 2 删除
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
     * 返回用户名称
     * @return 用户名称
     */
    public String getUsername() {
        return username;
    }
    
    /**
     * 设置用户名称
     * @param username 用户名称
     */
    public void setUsername(String username) {
        this.username = username;
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
     * 返回0 男， 1 女， 2 未知
     * @return 0 男， 1 女， 2 未知
     */
    public String getGender() {
        return gender;
    }
    
    /**
     * 设置0 男， 1 女， 2 未知
     * @param gender 0 男， 1 女， 2 未知
     */
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    /**
     * 返回生日
     * @return 生日
     */
    public Date getBirthday() {
        return birthday;
    }
    
    /**
     * 设置生日
     * @param birthday 生日
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    
    /**
     * 返回最后登陆时间
     * @return 最后登陆时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }
    
    /**
     * 设置最后登陆时间
     * @param lastLoginTime 最后登陆时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
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
     * 返回0 普通用户，1 VIP用户，2 高级VIP用户
     * @return 0 普通用户，1 VIP用户，2 高级VIP用户
     */
    public String getUserLevel() {
        return userLevel;
    }
    
    /**
     * 设置0 普通用户，1 VIP用户，2 高级VIP用户
     * @param userLevel 0 普通用户，1 VIP用户，2 高级VIP用户
     */
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel;
    }
    
    /**
     * 返回用户昵称或网络名称
     * @return 用户昵称或网络名称
     */
    public String getNickname() {
        return nickname;
    }
    
    /**
     * 设置用户昵称或网络名称
     * @param nickname 用户昵称或网络名称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
    /**
     * 返回用户手机号码
     * @return 用户手机号码
     */
    public String getMobile() {
        return mobile;
    }
    
    /**
     * 设置用户手机号码
     * @param mobile 用户手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    /**
     * 返回register_ip
     * @return register_ip
     */
    public String getRegisterIp() {
        return registerIp;
    }
    
    /**
     * 设置register_ip
     * @param registerIp register_ip
     */
    public void setRegisterIp(String registerIp) {
        this.registerIp = registerIp;
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
     * 返回wx_openid
     * @return wx_openid
     */
    public String getWxOpenid() {
        return wxOpenid;
    }
    
    /**
     * 设置wx_openid
     * @param wxOpenid wx_openid
     */
    public void setWxOpenid(String wxOpenid) {
        this.wxOpenid = wxOpenid;
    }
    
    /**
     * 返回wx_unionid
     * @return wx_unionid
     */
    public String getWxUnionid() {
        return wxUnionid;
    }
    
    /**
     * 设置wx_unionid
     * @param wxUnionid wx_unionid
     */
    public void setWxUnionid(String wxUnionid) {
        this.wxUnionid = wxUnionid;
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
     * 返回0 可用, 1 禁用, 2 删除
     * @return 0 可用, 1 禁用, 2 删除
     */
    public String getStatus() {
        return status;
    }
    
    /**
     * 设置0 可用, 1 禁用, 2 删除
     * @param status 0 可用, 1 禁用, 2 删除
     */
    public void setStatus(String status) {
        this.status = status;
    }
    

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SIMPLE_STYLE).append("com.xjj.wefactory.customer.buyer.entity.BuyerEntity").append("ID="+this.getId()).toString();
    }
}

