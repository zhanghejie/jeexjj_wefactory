<#--
/****************************************************
 * Description: 专题的简单列表页面，没有编辑功能
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/
${item.scenePicUrl}
<td>
			    ${item.content?html}
			</td>
-->
<#include "/templates/xjj-list.ftl"> 
<@list id=tabId>
	<thead>
		<tr>
			<th><input type="checkbox" class="bscheckall"></th>
	        <th>商户</th>
	        <th width="30%">标题及子标题</th>
	        <th>avatar</th>
	        <th>item_pic_url</th>
	        <th>底价</th>
	        <th>阅读次数</th>
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
			    <b>${item.title}</b><br/>
			    ${item.subtitle}
			</td>
			
			<td>
			    <img src="${item.avatar}" width="50px"/>
			</td>
			<td>
			    <img src="${item.itemPicUrl}" width="100px"/>
			</td>
			<td>
			    ${item.priceInfo}
			</td>
			<td>
			    ${item.readCount}
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd')}
			</td>
			<td>
			    ${item.status}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/topic/input/${item.id}','修改专题','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/topic/delete/${item.id}','删除专题？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>