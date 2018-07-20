<#--
/****************************************************
 * Description: 常见问题的简单列表页面，没有编辑功能
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
	        <th>商户</th>
	        <th>问题</th>
	        <th>答案</th>
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
			    ${item.sellerId}
			</td>
			<td>
			    ${item.question}
			</td>
			<td>
			    ${item.answer}
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.status}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/issue/input/${item.id}','修改常见问题','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/issue/delete/${item.id}','删除常见问题？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>