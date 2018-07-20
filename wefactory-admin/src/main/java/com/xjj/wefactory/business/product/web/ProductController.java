/****************************************************
 * Description: Controller for t_business_product
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-11 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.business.product.web;
import com.xjj.wefactory.business.product.entity.ProductEntity;
import com.xjj.wefactory.business.product.service.ProductService;
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
@RequestMapping("/business/product")
public class ProductController extends SpringControllerSupport{
	@Autowired
	private ProductService productService;
	
	
	@SecPrivilege(title="t_business_product管理")
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
		page = productService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("product") ProductEntity product,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		ProductEntity product = productService.getById(id);
		model.addAttribute("product",product);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute ProductEntity product){
		
		validateSave(product);
		if(product.isNew())
		{
			//product.setCreateDate(new Date());
			productService.save(product);
		}else
		{
			productService.update(product);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(ProductEntity product){
		//必填项校验
		// 判断goods_id是否为空
		if(null==product.getGoodsId()){
			throw new ValidationException("校验失败，goods_id不能为空！");
		}
		// 判断goods_specification_ids是否为空
		if(null==product.getGoodsSpecificationIds()){
			throw new ValidationException("校验失败，goods_specification_ids不能为空！");
		}
		// 判断goods_number是否为空
		if(null==product.getGoodsNumber()){
			throw new ValidationException("校验失败，goods_number不能为空！");
		}
		// 判断retail_price是否为空
		if(null==product.getRetailPrice()){
			throw new ValidationException("校验失败，retail_price不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		productService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			productService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

