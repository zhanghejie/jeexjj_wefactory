<#--
/****************************************************
 * Description: 客户优惠券的简单列表页面，没有编辑功能
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
-->
<#include "/templates/xjj-list.ftl"> 
<@list id=tabId>
	<thead>
		<tr>
			<th><input type="checkbox" class="bscheckall"></th>
	        <th>customer_id</th>
	        <th>coupon_id</th>
	        <th>user_id</th>
	        <th>order_id</th>
	        <th>used_time</th>
	        <th>add_time</th>
	        <th>操作</th>
		</tr>
	</thead>
	<tbody>
		<#list page.items?if_exists as item>
		<tr>
			<td>
			<input type="checkbox" class="bscheck" data="id:${item.id}">
			</td>
			<td>
			    ${item.customerId}
			</td>
			<td>
			    ${item.couponId}
			</td>
			<td>
			    ${item.userId}
			</td>
			<td>
			    ${item.orderId}
			</td>
			<td>
			    ${item.usedTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/customer/buyer/coupon/input/${item.id}','修改客户优惠券','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/customer/buyer/coupon/delete/${item.id}','删除客户优惠券？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>