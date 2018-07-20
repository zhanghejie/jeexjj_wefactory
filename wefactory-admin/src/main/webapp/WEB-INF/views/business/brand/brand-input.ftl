<#--
/****************************************************
 * Description: 品牌表的输入页面，包括添加和修改
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

  <@formgroup title='new_pic_url'>
	<input type="text" name="newPicUrl" value="${brand.newPicUrl}" check-type="required">
   </@formgroup>
   <@formgroup title='new_sort_order'>
	<input type="text" name="newSortOrder" value="${brand.newSortOrder}" check-type="required number">
   </@formgroup>
-->
<#include "/templates/xjj-index.ftl"> 

<@input url="${base}/business/brand/save" id=tabId>
   <input type="hidden" name="id" value="${brand.id}"/>
   <input type="hidden" name="sellerId" value="${brand.sellerId}" check-type="required number">
   
   <@formgroup title='名称'>
	<input type="text" name="name" value="${brand.name}" check-type="required">
   </@formgroup>
   <@formgroup title='宣传图片'>
	<input type="text" name="listPicUrl" value="${brand.listPicUrl}" check-type="required">
   </@formgroup>
   <@formgroup title='介绍'>
	<input type="text" name="simpleDesc" value="${brand.simpleDesc}" check-type="required">
   </@formgroup>
   <@formgroup title='品牌商图片'>
	<input type="text" name="picUrl" value="${brand.picUrl}" check-type="required">
   </@formgroup>
   <@formgroup title='排序'>
	<input type="text" name="sortOrder" value="${brand.sortOrder}" check-type="required number">
   </@formgroup>
   <@formgroup title='是否新的'>
	<input type="text" name="isNewly" value="${brand.isNewly}" check-type="required number">
   </@formgroup>
   <@formgroup title='是否显示'>
	<input type="text" name="isShow" value="${brand.isShow}" check-type="required number">
   </@formgroup>
   <@formgroup title='底价'>
	<input type="text" name="floorPrice" value="${brand.floorPrice}" check-type="required">
   </@formgroup>
   <@formgroup title='APP宣传图片'>
	<input type="text" name="appListPicUrl" value="${brand.appListPicUrl}" check-type="required">
   </@formgroup>
   
   <@formgroup title='状态'>
	<@swichInForm name="status" val=brand.status onTitle="有效" offTitle="无效"></@swichInForm>
   </@formgroup>
</@input>