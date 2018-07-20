<#--
/****************************************************
 * Description: 优惠券的简单列表页面，没有编辑功能
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
	        <th>商家</th>
	        <th>名称</th>
	        <th>面额</th>
	        <th>min_amount</th>
	        <th>max_amount</th>
	        <th>min_goods_amount</th>
	        <th>有效期</th>
	        <th>status</th>
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
			    ${item.sellerId}
			</td>
			<td>
			    ${item.name}
			</td>
			<td>
			    ${item.typeMoney}
			</td>
			<td>
			    ${item.minAmount}
			</td>
			<td>
			    ${item.maxAmount}
			</td>
			<td>
			    ${item.minGoodsAmount}
			</td>
		
			<td>
			    起：<#if item.useStart?exists>${item.useStart?string('yyyy-MM-dd HH:mm:ss')}</#if><br/>
			    止：<#if item.useEnd?exists>${item.useEnd?string('yyyy-MM-dd HH:mm:ss')}</#if>
			</td>
			<td>
			    ${item.status}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/coupon/input/${item.id}','修改优惠券','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/coupon/delete/${item.id}','删除优惠券？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>