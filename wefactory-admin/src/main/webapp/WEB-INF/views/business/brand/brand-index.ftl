<#--
/****************************************************
 * Description: 品牌表的列表页面
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
    *  2018-07-04 zhanghejie Create File
**************************************************/

<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/business/brand/input','修改品牌表','${tabId}');">修改</@button>
<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/business/brand/delete','删除品牌表？',true,{id:'${tabId}'});">删除</@button>
-->
<#include "/templates/xjj-index.ftl">
<@navList navs=navArr/>

<@content id=tabId>
	<@query queryUrl="${base}//business/brand/list" id=tabId>
	
		<@querygroup title='名称'>
			<input type="search" name="query.name@lk@s" class="form-control input-sm" placeholder="请输入名称" aria-controls="dynamic-table">
	    </@querygroup>
	  	    
		<@querygroup title='状态'>
			<@select name="query.status@eq@s" list=XJJConstants.COMMON_STATUS_LIST></@select>
	    </@querygroup>
	    
		<@button type="info" icon="glyphicon glyphicon-search" onclick="XJJ.query({id:'${tabId}'});">查询</@button>
	</@query>
	
	
	<@button type="info" icon="glyphicon glyphicon-plus" onclick="XJJ.add('${base}/business/brand/input','添加品牌表','${tabId}');">增加</@button>
	<@button type="grey" icon="fa fa-cloud-upload">上传</@button>
</@content>