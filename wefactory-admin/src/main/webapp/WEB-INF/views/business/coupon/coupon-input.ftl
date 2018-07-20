<#--
/****************************************************
 * Description: 优惠券的输入页面，包括添加和修改
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

<@input url="${base}/business/coupon/save" id=tabId>
   <input type="hidden" name="id" value="${coupon.id}"/>
   
   <@formgroup title='seller_id'>
	<input type="text" name="sellerId" value="${coupon.sellerId}" check-type="required number">
   </@formgroup>
   <@formgroup title='name'>
	<input type="text" name="name" value="${coupon.name}" check-type="required">
   </@formgroup>
   <@formgroup title='type_money'>
	<input type="text" name="typeMoney" value="${coupon.typeMoney}" check-type="required">
   </@formgroup>
   <@formgroup title='send_type'>
	<input type="text" name="sendType" value="${coupon.sendType}" check-type="required number">
   </@formgroup>
   <@formgroup title='min_amount'>
	<input type="text" name="minAmount" value="${coupon.minAmount}" check-type="required">
   </@formgroup>
   <@formgroup title='max_amount'>
	<input type="text" name="maxAmount" value="${coupon.maxAmount}" check-type="required">
   </@formgroup>
   <@formgroup title='min_goods_amount'>
	<input type="text" name="minGoodsAmount" value="${coupon.minGoodsAmount}" check-type="required">
   </@formgroup>
   <@formgroup title='send_start'>
	<@datetime name="sendStart" dateValue=coupon.sendStart  default=true/>
   </@formgroup>
   <@formgroup title='send_end'>
	<@datetime name="sendEnd" dateValue=coupon.sendEnd  default=true/>
   </@formgroup>
   <@formgroup title='有效开始时间'>
	<@datetime name="useStart" dateValue=coupon.useStart  default=true/>
   </@formgroup>
   <@formgroup title='有效结束时间'>
	<@datetime name="useEnd" dateValue=coupon.useEnd  default=true/>
   </@formgroup>
   <@formgroup title='status'>
	<input type="text" name="status" value="${coupon.status}" >
   </@formgroup>
</@input>