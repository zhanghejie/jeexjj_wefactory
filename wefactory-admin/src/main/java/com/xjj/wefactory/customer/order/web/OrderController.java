/****************************************************
 * Description: Controller for 订单
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.customer.order.web;
import com.xjj.wefactory.customer.order.entity.OrderEntity;
import com.xjj.wefactory.customer.order.service.OrderService;
import java.util.Date;
import com.xjj.framework.json.XjjJson;
import com.xjj.framework.exception.ValidationException;
import com.xjj.framework.web.SpringControllerSupport;
import com.xjj.framework.web.support.Pagination;
import com.xjj.framework.web.support.QueryParameter;
import com.xjj.framework.web.support.XJJParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xjj.framework.security.annotations.SecCreate;
import com.xjj.framework.security.annotations.SecDelete;
import com.xjj.framework.security.annotations.SecEdit;
import com.xjj.framework.security.annotations.SecList;
import com.xjj.framework.security.annotations.SecPrivilege;

@Controller
@RequestMapping("/customer/order")
public class OrderController extends SpringControllerSupport{
	@Autowired
	private OrderService orderService;
	
	
	@SecPrivilege(title="订单管理")
	@RequestMapping(value = "/index")
	public String index(Model model) {
		String page = this.getViewPath("index");
		return page;
	}
	
	@SecList
	@RequestMapping(value = "/list")
	public String list(Model model,
			@QueryParameter XJJParameter query,
			@ModelAttribute("page") Pagination page
			) {
		page = orderService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("order") OrderEntity order,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		OrderEntity order = orderService.getById(id);
		model.addAttribute("order",order);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute OrderEntity order){
		
		validateSave(order);
		if(order.isNew())
		{
			//order.setCreateDate(new Date());
			orderService.save(order);
		}else
		{
			orderService.update(order);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(OrderEntity order){
		//必填项校验
		// 判断customer_id是否为空
		if(null==order.getCustomerId()){
			throw new ValidationException("校验失败，customer_id不能为空！");
		}
		// 判断order_sn是否为空
		if(null==order.getOrderSn()){
			throw new ValidationException("校验失败，order_sn不能为空！");
		}
		// 判断订单状态是否为空
		if(null==order.getOrderStatus()){
			throw new ValidationException("校验失败，订单状态不能为空！");
		}
		// 判断consignee是否为空
		if(null==order.getConsignee()){
			throw new ValidationException("校验失败，consignee不能为空！");
		}
		// 判断mobile是否为空
		if(null==order.getMobile()){
			throw new ValidationException("校验失败，mobile不能为空！");
		}
		// 判断address是否为空
		if(null==order.getAddress()){
			throw new ValidationException("校验失败，address不能为空！");
		}
		// 判断商品总费用是否为空
		if(null==order.getGoodsPrice()){
			throw new ValidationException("校验失败，商品总费用不能为空！");
		}
		// 判断配送费用是否为空
		if(null==order.getFreightPrice()){
			throw new ValidationException("校验失败，配送费用不能为空！");
		}
		// 判断优惠券减免是否为空
		if(null==order.getCouponPrice()){
			throw new ValidationException("校验失败，优惠券减免不能为空！");
		}
		// 判断用户积分减免是否为空
		if(null==order.getIntegralPrice()){
			throw new ValidationException("校验失败，用户积分减免不能为空！");
		}
		// 判断订单费用， = goods_price + freight_price - coupon_price是否为空
		if(null==order.getOrderPrice()){
			throw new ValidationException("校验失败，订单费用， = goods_price + freight_price - coupon_price不能为空！");
		}
		// 判断实付费用， = order_price - integral_price是否为空
		if(null==order.getActualPrice()){
			throw new ValidationException("校验失败，实付费用， = order_price - integral_price不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		orderService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			orderService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

