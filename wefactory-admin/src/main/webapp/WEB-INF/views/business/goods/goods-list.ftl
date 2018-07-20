<#--
/****************************************************
 * Description: 商品的简单列表页面，没有编辑功能
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
	        <th>品牌id</th>
	        <th>类目id</th>
	        <th>名称和简介</th>
	        <th>上架</th>
	        <th>专柜价格</th>
	        <th>商品主图</th>
	        <th>商品列表图</th>
	        <th>is_hot</th>
	        <th>零售价格</th>
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
			    ${item.brandId}
			</td>
			<td>
			    ${item.categoryId}
			</td>
			<td>
			    <b>${item.name}</b><br/>
			    ${item.goodsBrief}
			</td>
			<td>
			    ${item.isOnSale}
			</td>
			<td>
			    ${item.counterPrice}
			</td>
			<td>
			    <img src="${item.primaryPicUrl}" width="100px"/>
			</td>
			<td>
			    <img src="${item.listPicUrl}" width="100px"/>
			</td>
			<td>
			    ${item.isHot}
			</td>
			<td>
			    ${item.retailPrice}
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd')}
			</td>
			<td>
			    ${item.status}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/goods/input/${item.id}','修改商品','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/goods/delete/${item.id}','删除商品？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>