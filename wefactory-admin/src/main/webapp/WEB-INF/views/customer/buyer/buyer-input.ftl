<#--
/****************************************************
 * Description: 客户的输入页面，包括添加和修改
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
-->
<#include "/templates/xjj-index.ftl"> 

<@input url="${base}/customer/buyer/save" id=tabId>
   <input type="hidden" name="id" value="${buyer.id}"/>
   
   <@formgroup title='seller_id'>
	<input type="text" name="sellerId" value="${buyer.sellerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='用户名称'>
	<input type="text" name="username" value="${buyer.username}" >
   </@formgroup>
   <@formgroup title='密码'>
	<input type="text" name="password" value="${buyer.password}" >
   </@formgroup>
   <@formgroup title='0 男， 1 女， 2 未知'>
	<input type="text" name="gender" value="${buyer.gender}" >
   </@formgroup>
   <@formgroup title='生日'>
	<@date name="birthday" dateValue=buyer.birthday  default=true/>
   </@formgroup>
   <@formgroup title='最后登陆时间'>
	<@date name="lastLoginTime" dateValue=buyer.lastLoginTime  default=true/>
   </@formgroup>
   <@formgroup title='last_login_ip'>
	<input type="text" name="lastLoginIp" value="${buyer.lastLoginIp}" >
   </@formgroup>
   <@formgroup title='0 普通用户，1 VIP用户，2 高级VIP用户'>
	<input type="text" name="userLevel" value="${buyer.userLevel}" >
   </@formgroup>
   <@formgroup title='用户昵称或网络名称'>
	<input type="text" name="nickname" value="${buyer.nickname}" >
   </@formgroup>
   <@formgroup title='用户手机号码'>
	<input type="text" name="mobile" value="${buyer.mobile}" >
   </@formgroup>
   <@formgroup title='register_ip'>
	<input type="text" name="registerIp" value="${buyer.registerIp}" >
   </@formgroup>
   <@formgroup title='头像'>
	<input type="text" name="avatar" value="${buyer.avatar}" >
   </@formgroup>
   <@formgroup title='wx_openid'>
	<input type="text" name="wxOpenid" value="${buyer.wxOpenid}" >
   </@formgroup>
   <@formgroup title='wx_unionid'>
	<input type="text" name="wxUnionid" value="${buyer.wxUnionid}" >
   </@formgroup>
   <@formgroup title='add_time'>
	<@date name="addTime" dateValue=buyer.addTime  default=true/>
   </@formgroup>
   <@formgroup title='0 可用, 1 禁用, 2 删除'>
	<input type="text" name="status" value="${buyer.status}" >
   </@formgroup>
</@input>