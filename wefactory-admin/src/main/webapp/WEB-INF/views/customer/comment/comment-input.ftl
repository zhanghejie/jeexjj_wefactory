<#--
/****************************************************
 * Description: 评论的输入页面，包括添加和修改
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

<@input url="${base}/customer/comment/save" id=tabId>
   <input type="hidden" name="id" value="${comment.id}"/>
   
   <@formgroup title='customer_id'>
	<input type="text" name="customerId" value="${comment.customerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='type_id'>
	<input type="text" name="typeId" value="${comment.typeId}" check-type="required number">
   </@formgroup>
   <@formgroup title='value_id'>
	<input type="text" name="valueId" value="${comment.valueId}" check-type="required number">
   </@formgroup>
   <@formgroup title='内容'>
	<input type="text" name="content" value="${comment.content}" >
   </@formgroup>
   <@formgroup title='has_picture'>
	<input type="text" name="hasPicture" value="${comment.hasPicture}" check-type="required number">
   </@formgroup>
   <@formgroup title='pic_urls'>
	<input type="text" name="picUrls" value="${comment.picUrls}" >
   </@formgroup>
   <@formgroup title='评分， 1-5'>
	<input type="text" name="star" value="${comment.star}" check-type="number">
   </@formgroup>
   <@formgroup title='add_time'>
	<@date name="addTime" dateValue=comment.addTime  default=true/>
   </@formgroup>
</@input>