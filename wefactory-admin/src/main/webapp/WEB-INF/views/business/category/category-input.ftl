<#--
/****************************************************
 * Description: 类目的输入页面，包括添加和修改
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

<@input url="${base}/business/category/save" id=tabId>
   <input type="hidden" name="id" value="${category.id}"/>
   
   <@formgroup title='seller_id'>
	<input type="text" name="sellerId" value="${category.sellerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='名称'>
	<input type="text" name="name" value="${category.name}" check-type="required">
   </@formgroup>
   <@formgroup title='关键词'>
	<input type="text" name="keywords" value="${category.keywords}" check-type="required">
   </@formgroup>
   <@formgroup title='front_desc'>
	<input type="text" name="frontDesc" value="${category.frontDesc}" check-type="required">
   </@formgroup>
   <@formgroup title='parent_id'>
	<input type="text" name="parentId" value="${category.parentId}" check-type="required number">
   </@formgroup>
   <@formgroup title='sort_order'>
	<input type="text" name="sortOrder" value="${category.sortOrder}" check-type="required number">
   </@formgroup>
   <@formgroup title='show_index'>
	<input type="text" name="showIndex" value="${category.showIndex}" check-type="required number">
   </@formgroup>
   <@formgroup title='is_show'>
	<input type="text" name="isShow" value="${category.isShow}" check-type="required number">
   </@formgroup>
   <@formgroup title='banner_url'>
	<input type="text" name="bannerUrl" value="${category.bannerUrl}" >
   </@formgroup>
   <@formgroup title='icon_url'>
	<input type="text" name="iconUrl" value="${category.iconUrl}" >
   </@formgroup>
   <@formgroup title='img_url'>
	<input type="text" name="imgUrl" value="${category.imgUrl}" >
   </@formgroup>
   <@formgroup title='wap_banner_url'>
	<input type="text" name="wapBannerUrl" value="${category.wapBannerUrl}" >
   </@formgroup>
   <@formgroup title='level'>
	<input type="text" name="level" value="${category.level}" check-type="required">
   </@formgroup>
   <@formgroup title='type'>
	<input type="text" name="type" value="${category.type}" check-type="required number">
   </@formgroup>
   <@formgroup title='front_name'>
	<input type="text" name="frontName" value="${category.frontName}" >
   </@formgroup>
   <@formgroup title='status'>
	<input type="text" name="status" value="${category.status}" >
   </@formgroup>
</@input>