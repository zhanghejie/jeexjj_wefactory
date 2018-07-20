/****************************************************
 * Description: Controller for 购物车
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.customer.cart.web;
import com.xjj.wefactory.customer.cart.entity.CartEntity;
import com.xjj.wefactory.customer.cart.service.CartService;
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
@RequestMapping("/customer/cart")
public class CartController extends SpringControllerSupport{
	@Autowired
	private CartService cartService;
	
	
	@SecPrivilege(title="购物车管理")
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
		page = cartService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("cart") CartEntity cart,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		CartEntity cart = cartService.getById(id);
		model.addAttribute("cart",cart);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute CartEntity cart){
		
		validateSave(cart);
		if(cart.isNew())
		{
			//cart.setCreateDate(new Date());
			cartService.save(cart);
		}else
		{
			cartService.update(cart);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(CartEntity cart){
		//必填项校验
		// 判断customer_id是否为空
		if(null==cart.getCustomerId()){
			throw new ValidationException("校验失败，customer_id不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		cartService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			cartService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

