<#--
/****************************************************
 * Description: 商品规格的简单列表页面，没有编辑功能
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
	        <th>goods_id</th>
	        <th>value</th>
	        <th>pic_url</th>
	        <th>specification</th>
	        <th>单价</th>
	        <th>库存量</th>
	        <th>创建时间</th>
	        <th>状态</th>
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
			    ${item.goodsId}
			</td>
			<td>
			    ${item.value}
			</td>
			<td>
			    ${item.picUrl}
			</td>
			<td>
			    ${item.specification}
			</td>
			<td>
			    ${item.price}
			</td>
			<td>
			    ${item.stockNumber}
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.status}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/goods/specification/input/${item.id}','修改商品规格','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/goods/specification/delete/${item.id}','删除商品规格？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>