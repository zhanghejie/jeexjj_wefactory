<#--
/****************************************************
 * Description: 订单的简单列表页面，没有编辑功能
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
	        <th>order_sn</th>
	        <th>订单状态</th>
	        <th>consignee</th>
	        <th>mobile</th>
	        <th>address</th>
	        <th>商品总费用</th>
	        <th>配送费用</th>
	        <th>优惠券减免</th>
	        <th>用户积分减免</th>
	        <th>订单费用， = goods_price + freight_price - coupon_price</th>
	        <th>实付费用， = order_price - integral_price</th>
	        <th>微信付款编号</th>
	        <th>支付状态</th>
	        <th>微信付款时间</th>
	        <th>发货编号</th>
	        <th>发货快递公司</th>
	        <th>发货开始时间</th>
	        <th>发货结束时间</th>
	        <th>用户确认收货时间</th>
	        <th>end_time</th>
	        <th>add_time</th>
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
			    ${item.customerId}
			</td>
			<td>
			    ${item.orderSn}
			</td>
			<td>
			    ${item.orderStatus}
			</td>
			<td>
			    ${item.consignee}
			</td>
			<td>
			    ${item.mobile}
			</td>
			<td>
			    ${item.address}
			</td>
			<td>
			    ${item.goodsPrice}
			</td>
			<td>
			    ${item.freightPrice}
			</td>
			<td>
			    ${item.couponPrice}
			</td>
			<td>
			    ${item.integralPrice}
			</td>
			<td>
			    ${item.orderPrice}
			</td>
			<td>
			    ${item.actualPrice}
			</td>
			<td>
			    ${item.payId}
			</td>
			<td>
			    ${item.payStatus}
			</td>
			<td>
			    ${item.payTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.shipSn}
			</td>
			<td>
			    ${item.shipChannel}
			</td>
			<td>
			    ${item.shipStartTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.shipEndTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.confirmTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.endTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.addTime?string('yyyy-MM-dd HH:mm:ss')}
			</td>
			<td>
			    ${item.status}
			</td>
			<td>
            	<@button type="purple" icon="fa fa-pencil" onclick="XJJ.edit('${base}/customer/order/input/${item.id}','修改订单','${tabId}');">修改</@button>
				<@button type="danger" icon=" fa fa-trash-o" onclick="XJJ.del('${base}/customer/order/delete/${item.id}','删除订单？',false,{id:'${tabId}'});">删除</@button>
            </td>
		</tr>
		</#list>
	</tbody>
</@list>