<#--
/****************************************************
 * Description: 地区信息的输入页面，包括添加和修改
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

<@input url="${base}/customer/region/save" id=tabId>
   <input type="hidden" name="id" value="${region.id}"/>
   
   <@formgroup title='pid'>
	<input type="text" name="pid" value="${region.pid}" check-type="required number">
   </@formgroup>
   <@formgroup title='name'>
	<input type="text" name="name" value="${region.name}" check-type="required">
   </@formgroup>
   <@formgroup title='1, 2, 3, 4'>
	<input type="text" name="type" value="${region.type}" check-type="required number">
   </@formgroup>
   <@formgroup title='code'>
	<input type="text" name="code" value="${region.code}" check-type="required number">
   </@formgroup>
</@input>