<#--
/****************************************************
 * Description: 商家的简单列表页面，没有编辑功能
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
	        <th>账号</th>
	        <th>名称</th>
	        <th>密码</th>
	        <th>手机</th>
	        <th>邮箱</th>
	        <th>最后登陆时间</th>
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
			    ${item.identify}
			</td>
			<td>
			    ${item.name}
			</td>
			<td>
			    ${item.password}
			</td>
			<td>
			    ${item.mobile}
			</td>
			<td>
			    ${item.email}
			</td>
			<td>
				<#if item.lastLoginTime?exists>
			    ${item.lastLoginTime?string('yyyy-MM-dd HH:mm:ss')}
			    </#if>
			</td>
			<td>
				<#if item.addTime?exists>
			    ${item.addTime?if_exists?string('yyyy-MM-dd HH:mm:ss')}
			    </#if>
			</td>
			<td>
			   <span class="label <#if item.status=XJJConstants.COMMON_STATUS_VALID>label-info</#if> arrowed-in arrowed-in-right">${XJJDict.getText(item.status)}</span>
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/seller/input/${item.id}','修改商家','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/seller/delete/${item.id}','删除商家？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>