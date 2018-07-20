/****************************************************
 * Description: Controller for 订单商品
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.customer.order.web;
import com.xjj.wefactory.customer.order.entity.OrderGoodsEntity;
import com.xjj.wefactory.customer.order.service.OrderGoodsService;
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
@RequestMapping("/customer/order/goods")
public class OrderGoodsController extends SpringControllerSupport{
	@Autowired
	private OrderGoodsService orderGoodsService;
	
	
	@SecPrivilege(title="订单商品管理")
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
		page = orderGoodsService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("orderGoods") OrderGoodsEntity orderGoods,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		OrderGoodsEntity orderGoods = orderGoodsService.getById(id);
		model.addAttribute("orderGoods",orderGoods);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute OrderGoodsEntity orderGoods){
		
		validateSave(orderGoods);
		if(orderGoods.isNew())
		{
			//orderGoods.setCreateDate(new Date());
			orderGoodsService.save(orderGoods);
		}else
		{
			orderGoodsService.update(orderGoods);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(OrderGoodsEntity orderGoods){
		//必填项校验
		// 判断order_id是否为空
		if(null==orderGoods.getOrderId()){
			throw new ValidationException("校验失败，order_id不能为空！");
		}
		// 判断goods_id是否为空
		if(null==orderGoods.getGoodsId()){
			throw new ValidationException("校验失败，goods_id不能为空！");
		}
		// 判断商品名称是否为空
		if(null==orderGoods.getGoodsName()){
			throw new ValidationException("校验失败，商品名称不能为空！");
		}
		// 判断goods_sn是否为空
		if(null==orderGoods.getGoodsSn()){
			throw new ValidationException("校验失败，goods_sn不能为空！");
		}
		// 判断数量是否为空
		if(null==orderGoods.getNumber()){
			throw new ValidationException("校验失败，数量不能为空！");
		}
		// 判断单价是否为空
		if(null==orderGoods.getPrice()){
			throw new ValidationException("校验失败，单价不能为空！");
		}
		// 判断goods_spec_vals是否为空
		if(null==orderGoods.getGoodsSpecVals()){
			throw new ValidationException("校验失败，goods_spec_vals不能为空！");
		}
		// 判断pic_url是否为空
		if(null==orderGoods.getPicUrl()){
			throw new ValidationException("校验失败，pic_url不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		orderGoodsService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			orderGoodsService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

