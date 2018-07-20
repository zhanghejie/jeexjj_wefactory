<#--
/****************************************************
 * Description: 评论的简单列表页面，没有编辑功能
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
	        <th>type_id</th>
	        <th>value_id</th>
	        <th>内容</th>
	        <th>has_picture</th>
	        <th>pic_urls</th>
	        <th>评分， 1-5</th>
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
			    ${item.typeId}
			</td>
			<td>
			    ${item.valueId}
			</td>
			<td>
			    ${item.content}
			</td>
			<td>
			    ${item.hasPicture}
			</td>
			<td>
			    ${item.picUrls}
			</td>
			<td>
			    ${item.star}
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/customer/comment/input/${item.id}','修改评论','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/customer/comment/delete/${item.id}','删除评论？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>