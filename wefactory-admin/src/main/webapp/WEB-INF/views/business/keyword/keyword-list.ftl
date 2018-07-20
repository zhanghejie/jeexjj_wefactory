<#--
/****************************************************
 * Description: 关键词的简单列表页面，没有编辑功能
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
	        <th>seller_id</th>
	        <th>keyword</th>
	        <th>关键词的跳转链接</th>
	        <th>是否热点</th>
	        <th>is_default</th>
	        <th>is_show</th>
	        <th>sort_order</th>
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
			    ${item.sellerId}
			</td>
			<td>
			    ${item.keyword}
			</td>
			<td>
			    ${item.url}
			</td>
			<td>
			    ${item.isHot}
			</td>
			<td>
			    ${item.isDefault}
			</td>
			<td>
			    ${item.isShow}
			</td>
			<td>
			    ${item.sortOrder}
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.status}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/keyword/input/${item.id}','修改关键词','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/keyword/delete/${item.id}','删除关键词？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>