<#--
/****************************************************
 * Description: 客户的简单列表页面，没有编辑功能
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
	        <th>用户名称</th>
	        <th>密码</th>
	        <th>0 男， 1 女， 2 未知</th>
	        <th>生日</th>
	        <th>最后登陆时间</th>
	        <th>last_login_ip</th>
	        <th>0 普通用户，1 VIP用户，2 高级VIP用户</th>
	        <th>用户昵称或网络名称</th>
	        <th>用户手机号码</th>
	        <th>register_ip</th>
	        <th>头像</th>
	        <th>wx_openid</th>
	        <th>wx_unionid</th>
	        <th>add_time</th>
	        <th>0 可用, 1 禁用, 2 删除</th>
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
			    ${item.username}
			</td>
			<td>
			    ${item.password}
			</td>
			<td>
			    ${item.gender}
			</td>
			<td>
			    ${item.birthday?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.lastLoginTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.lastLoginIp}
			</td>
			<td>
			    ${item.userLevel}
			</td>
			<td>
			    ${item.nickname}
			</td>
			<td>
			    ${item.mobile}
			</td>
			<td>
			    ${item.registerIp}
			</td>
			<td>
			    ${item.avatar}
			</td>
			<td>
			    ${item.wxOpenid}
			</td>
			<td>
			    ${item.wxUnionid}
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.status}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/customer/buyer/input/${item.id}','修改客户','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/customer/buyer/delete/${item.id}','删除客户？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>