<#--
/****************************************************
 * Description: 广告的简单列表页面，没有编辑功能
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
	        <th>广告位置</th>
	        <th>标题</th>
	        <th>图片</th>
	        <th>活动链接</th>
	        <th>内容</th>
	        <th>起止时间</th>
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
			    ${item.position}
			</td>
			<td>
			    ${item.name}
			</td>
			<td>
			    <img src="${item.url}" width="200px"/>
			</td>
			<td>
			    ${item.link}
			</td>
			<td>
			    ${item.content}
			</td>
			<td>
			              起：<#if item.startTime?exists>${item.startTime?string('yyyy-MM-dd HH:mm:ss')}</#if><br/>
			              止：<#if item.endTime?exists>${item.endTime?string('yyyy-MM-dd HH:mm:ss')}</#if>
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.status}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/ad/input/${item.id}','修改广告','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/ad/delete/${item.id}','删除广告？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>