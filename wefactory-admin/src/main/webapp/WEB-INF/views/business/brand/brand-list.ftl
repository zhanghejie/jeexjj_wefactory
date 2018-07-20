<#--
/****************************************************
 * Description: 品牌表的简单列表页面，没有编辑功能
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
			<th><input type="checkbox" class="bscheckall" width="5%"></th>
	        <th width="10%">商家</th>
	        <th width="10%">名称</th>
	        <th width="45%">介绍</th>
	        <th width="10%">入驻时间</th>
	        <th width="10%">状态</th>
	        <th width="25%">操作</th>
		</tr>
	</thead>
	<tbody>
		<#list page.items?if_exists as item>
		<tr>
			<td>
			<input type="checkbox" class="bscheck" data="id:${item.id}">
			</td>
			<td>
			    ${item.sellerName}
			</td>
			<td>
			    ${item.name}
			</td>
			<td>
			    ${item.simpleDesc}
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd')}
			</td>
			<td>
			    <span class="label <#if item.status=XJJConstants.COMMON_STATUS_VALID>label-info</#if> arrowed-in arrowed-in-right">${XJJDict.getText(item.status)}</span>
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/brand/input/${item.id}','修改品牌【商家：${item.sellerName}】','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/brand/delete/${item.id}','删除品牌表？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>