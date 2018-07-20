<#--
/****************************************************
 * Description: 商品的输入页面，包括添加和修改
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

<@input url="${base}/business/goods/save" id=tabId>
   <input type="hidden" name="id" value="${goods.id}"/>
   
   <@formgroup title='brand_id'>
	<input type="text" name="brandId" value="${goods.brandId}" check-type="required number">
   </@formgroup>
   <@formgroup title='category_id'>
	<input type="text" name="categoryId" value="${goods.categoryId}" check-type="required number">
   </@formgroup>
   <@formgroup title='goods_sn'>
	<input type="text" name="goodsSn" value="${goods.goodsSn}" check-type="required">
   </@formgroup>
   <@formgroup title='名称'>
	<input type="text" name="name" value="${goods.name}" check-type="required">
   </@formgroup>
   <@formgroup title='画廊图片'>
	<textarea type="text" name="gallery">${goods.gallery}</textarea>
   </@formgroup>
   <@formgroup title='keywords'>
	<input type="text" name="keywords" value="${goods.keywords}" >
   </@formgroup>
   <@formgroup title='goods_brief'>
	<input type="text" name="goodsBrief" value="${goods.goodsBrief}" >
   </@formgroup>
   <@formgroup title='is_on_sale'>
	<input type="text" name="isOnSale" value="${goods.isOnSale}" check-type="number">
   </@formgroup>
   <@formgroup title='sort_order'>
	<input type="text" name="sortOrder" value="${goods.sortOrder}" check-type="number">
   </@formgroup>
   <@formgroup title='专柜价格'>
	<input type="text" name="counterPrice" value="${goods.counterPrice}" >
   </@formgroup>
   <@formgroup title='is_newly'>
	<input type="text" name="isNewly" value="${goods.isNewly}" check-type="number">
   </@formgroup>
   <@formgroup title='商品主图'>
	<input type="text" name="primaryPicUrl" value="${goods.primaryPicUrl}" >
   </@formgroup>
   <@formgroup title='商品列表图'>
	<input type="text" name="listPicUrl" value="${goods.listPicUrl}" >
   </@formgroup>
   <@formgroup title='is_hot'>
	<input type="text" name="isHot" value="${goods.isHot}" check-type="number">
   </@formgroup>
   <@formgroup title='商品单位'>
	<input type="text" name="goodsUnit" value="${goods.goodsUnit}" >
   </@formgroup>
   <@formgroup title='零售价格'>
	<input type="text" name="retailPrice" value="${goods.retailPrice}" >
   </@formgroup>
   <@formgroup title='goods_desc'>
	${goods.goodsDesc}
   </@formgroup>
   <@formgroup title='status'>
	<input type="text" name="status" value="${goods.status}" >
   </@formgroup>
</@input>