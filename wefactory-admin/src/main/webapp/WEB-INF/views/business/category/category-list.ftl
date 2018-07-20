<#--
/****************************************************
 * Description: 类目的简单列表页面，没有编辑功能
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

	        <th>sort_order</th>
	        <th>show_index</th>
	        <th>is_show</th>
	        <th>banner_url</th>
	        <th>wap_banner_url</th>
	        <th>前台名称</th>
			<td>
			    ${item.sortOrder}
			</td>
			<td>
			    ${item.showIndex}
			</td>
			<td>
			    ${item.isShow}
			</td>
			<td>
			    ${item.bannerUrl}
			</td>
			<td>
			    ${item.wapBannerUrl}
			</td>
			<td>
			    ${item.frontName}
			</td>

-->
<#include "/templates/xjj-list.ftl"> 
<@list id=tabId>
	<thead>
		<tr>
			<th><input type="checkbox" class="bscheckall"></th>
	        <th>商家</th>
	        <th>名称</th>
	        <th>关键词</th>
	        <th>简介</th>
	        <th>父类目id</th>
	        <th>类目图标</th>
	        <th>类目图片</th>
	       
	        <th>级别</th>
	        <th>创建时间</th>
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
			    ${item.name}
			</td>
			<td>
			    ${item.keywords}
			</td>
			<td>
			    ${item.frontDesc}
			</td>
			<td>
			    ${item.parentId}
			</td>
			
			<td>
			    <img src="${item.iconUrl}" width="40"/>
			</td>
			<td>
			    <img src="${item.imgUrl}" width="120"/>
			</td>
			
			<td>
			    ${item.level}
			</td>
			
			<td>
			    ${item.addTime?string('yyyy-MM-dd')}
			</td>
			<td>
			    ${item.status}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/category/input/${item.id}','修改类目','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/category/delete/${item.id}','删除类目？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>