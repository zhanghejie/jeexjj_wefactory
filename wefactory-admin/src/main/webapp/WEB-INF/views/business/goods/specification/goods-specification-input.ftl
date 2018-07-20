<#--
/****************************************************
 * Description: 商品规格的输入页面，包括添加和修改
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

<@input url="${base}/business/goods/specification/save" id=tabId>
   <input type="hidden" name="id" value="${goodsSpecification.id}"/>
   
   <@formgroup title='goods_id'>
	<input type="text" name="goodsId" value="${goodsSpecification.goodsId}" check-type="required number">
   </@formgroup>
   <@formgroup title='value'>
	<input type="text" name="value" value="${goodsSpecification.value}" check-type="required">
   </@formgroup>
   <@formgroup title='pic_url'>
	<input type="text" name="picUrl" value="${goodsSpecification.picUrl}" check-type="required">
   </@formgroup>
   <@formgroup title='specification'>
	<input type="text" name="specification" value="${goodsSpecification.specification}" check-type="required">
   </@formgroup>
   <@formgroup title='单价'>
	<input type="text" name="price" value="${goodsSpecification.price}" >
   </@formgroup>
   <@formgroup title='库存量'>
	<input type="text" name="stockNumber" value="${goodsSpecification.stockNumber}" check-type="number">
   </@formgroup>
   <@formgroup title='创建时间'>
	<@date name="addTime" dateValue=goodsSpecification.addTime  default=true/>
   </@formgroup>
   <@formgroup title='状态'>
	<input type="text" name="status" value="${goodsSpecification.status}" >
   </@formgroup>
</@input>