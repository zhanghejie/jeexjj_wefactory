<#--
/****************************************************
 * Description: 专题的输入页面，包括添加和修改
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

<@input url="${base}/business/topic/save" id=tabId>
   <input type="hidden" name="id" value="${topic.id}"/>
   
   <@formgroup title='seller_id'>
	<input type="text" name="sellerId" value="${topic.sellerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='标题'>
	<input type="text" name="title" value="${topic.title}" check-type="required">
   </@formgroup>
   <@formgroup title='内容'>
	${topic.content}
   </@formgroup>
   <@formgroup title='avatar'>
	<input type="text" name="avatar" value="${topic.avatar}" check-type="required">
   </@formgroup>
   <@formgroup title='item_pic_url'>
	<input type="text" name="itemPicUrl" value="${topic.itemPicUrl}" check-type="required">
   </@formgroup>
   <@formgroup title='专题子内容'>
	<input type="text" name="subtitle" value="${topic.subtitle}" check-type="required">
   </@formgroup>
   <@formgroup title='price_info'>
	<input type="text" name="priceInfo" value="${topic.priceInfo}" check-type="required">
   </@formgroup>
   <@formgroup title='阅读次数'>
	<input type="text" name="readCount" value="${topic.readCount}" check-type="required">
   </@formgroup>
   <@formgroup title='scene_pic_url'>
	<input type="text" name="scenePicUrl" value="${topic.scenePicUrl}" check-type="required">
   </@formgroup>
   <@formgroup title='sort_order'>
	<input type="text" name="sortOrder" value="${topic.sortOrder}" check-type="required number">
   </@formgroup>
   <@formgroup title='is_show'>
	<input type="text" name="isShow" value="${topic.isShow}" check-type="required number">
   </@formgroup>
   <@formgroup title='状态'>
	<input type="text" name="status" value="${topic.status}" >
   </@formgroup>
</@input>