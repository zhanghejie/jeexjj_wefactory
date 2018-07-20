<#--
/****************************************************
 * Description: 常见问题的输入页面，包括添加和修改
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

<@input url="${base}/business/issue/save" id=tabId>
   <input type="hidden" name="id" value="${issue.id}"/>
   
   <@formgroup title='seller_id'>
	<input type="text" name="sellerId" value="${issue.sellerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='问题'>
	<input type="text" name="question" value="${issue.question}" >
   </@formgroup>
   <@formgroup title='答案'>
	<input type="text" name="answer" value="${issue.answer}" >
   </@formgroup>
   <@formgroup title='status'>
	<input type="text" name="status" value="${issue.status}" >
   </@formgroup>
</@input>