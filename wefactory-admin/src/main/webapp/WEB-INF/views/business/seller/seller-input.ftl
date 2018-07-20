<#--
/****************************************************
 * Description: 商家的输入页面，包括添加和修改
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

<@input url="${base}/business/seller/save" id=tabId>
   <input type="hidden" name="id" value="${seller.id}"/>
   
   <@formgroup title='账号'>
	<input type="text" name="identify" value="${seller.identify}" >
   </@formgroup>
   <@formgroup title='名称'>
	<input type="text" name="name" value="${seller.name}" check-type="required">
   </@formgroup>
   <@formgroup title='密码'>
	<input type="password" name="password" value="${seller.password}" check-type="required">
   </@formgroup>
   <@formgroup title='手机'>
	<input type="text" name="mobile" value="${seller.mobile}" >
   </@formgroup>
   <@formgroup title='邮箱'>
	<input type="text" name="email" value="${seller.email}" >
   </@formgroup>
   
   <@formgroup title='状态'>
		<@swichInForm name="status" val=seller.status onTitle="有效" offTitle="无效"></@swichInForm>
   </@formgroup>
</@input>