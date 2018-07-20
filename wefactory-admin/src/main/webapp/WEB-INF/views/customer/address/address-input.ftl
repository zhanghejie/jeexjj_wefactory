<#--
/****************************************************
 * Description: 地址的输入页面，包括添加和修改
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

<@input url="${base}/customer/address/save" id=tabId>
   <input type="hidden" name="id" value="${address.id}"/>
   
   <@formgroup title='customer_id'>
	<input type="text" name="customerId" value="${address.customerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='名称'>
	<input type="text" name="name" value="${address.name}" check-type="required">
   </@formgroup>
   <@formgroup title='省份'>
	<input type="text" name="provinceId" value="${address.provinceId}" check-type="required number">
   </@formgroup>
   <@formgroup title='城市'>
	<input type="text" name="cityId" value="${address.cityId}" check-type="required number">
   </@formgroup>
   <@formgroup title='区'>
	<input type="text" name="areaId" value="${address.areaId}" check-type="required number">
   </@formgroup>
   <@formgroup title='地址'>
	<input type="text" name="address" value="${address.address}" check-type="required">
   </@formgroup>
   <@formgroup title='手机'>
	<input type="text" name="mobile" value="${address.mobile}" check-type="required">
   </@formgroup>
   <@formgroup title='is_default'>
	<input type="text" name="isDefault" value="${address.isDefault}" check-type="required number">
   </@formgroup>
   <@formgroup title='add_time'>
	<@date name="addTime" dateValue=address.addTime  default=true/>
   </@formgroup>
   <@formgroup title='status'>
	<input type="text" name="status" value="${address.status}" >
   </@formgroup>
</@input>