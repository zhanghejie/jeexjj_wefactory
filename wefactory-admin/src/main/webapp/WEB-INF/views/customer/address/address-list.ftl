<#--
/****************************************************
 * Description: 地址的简单列表页面，没有编辑功能
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
	        <th>名称</th>
	        <th>省份</th>
	        <th>城市</th>
	        <th>区</th>
	        <th>地址</th>
	        <th>手机</th>
	        <th>is_default</th>
	        <th>add_time</th>
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
			    ${item.customerId}
			</td>
			<td>
			    ${item.name}
			</td>
			<td>
			    ${item.provinceId}
			</td>
			<td>
			    ${item.cityId}
			</td>
			<td>
			    ${item.areaId}
			</td>
			<td>
			    ${item.address}
			</td>
			<td>
			    ${item.mobile}
			</td>
			<td>
			    ${item.isDefault}
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.status}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/customer/address/input/${item.id}','修改地址','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/customer/address/delete/${item.id}','删除地址？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>