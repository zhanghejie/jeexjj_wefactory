<#--
/****************************************************
 * Description: 购物车的简单列表页面，没有编辑功能
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
	        <th>goods_id</th>
	        <th>goods_sn</th>
	        <th>goods_name</th>
	        <th>单价</th>
	        <th>数量</th>
	        <th>规格属性组成的字符串，用来显示用</th>
	        <th>goods_spec_ids</th>
	        <th>checked</th>
	        <th>pic_url</th>
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
			    ${item.goodsId}
			</td>
			<td>
			    ${item.goodsSn}
			</td>
			<td>
			    ${item.goodsName}
			</td>
			<td>
			    ${item.price}
			</td>
			<td>
			    ${item.number}
			</td>
			<td>
			    ${item.goodsSpecVals}
			</td>
			<td>
			    ${item.goodsSpecIds}
			</td>
			<td>
			    ${item.checked}
			</td>
			<td>
			    ${item.picUrl}
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/customer/cart/input/${item.id}','修改购物车','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/customer/cart/delete/${item.id}','删除购物车？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>