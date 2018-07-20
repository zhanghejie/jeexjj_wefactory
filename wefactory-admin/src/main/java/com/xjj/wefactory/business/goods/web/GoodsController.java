/****************************************************
 * Description: Controller for 商品
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.business.goods.web;
import com.xjj.wefactory.business.goods.entity.GoodsEntity;
import com.xjj.wefactory.business.goods.service.GoodsService;
import java.util.Date;
import com.xjj.framework.json.XjjJson;
import com.xjj.framework.exception.ValidationException;
import com.xjj.framework.web.SpringControllerSupport;
import com.xjj.framework.web.support.Pagination;
import com.xjj.framework.web.support.QueryParameter;
import com.xjj.framework.web.support.XJJParameter;

import org.springframework.beans.BeanUtils;
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
@RequestMapping("/business/goods")
public class GoodsController extends SpringControllerSupport{
	@Autowired
	private GoodsService goodsService;
	
	
	@SecPrivilege(title="商品管理")
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
		page = goodsService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("goods") GoodsEntity goods,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		GoodsEntity goods = goodsService.getById(id);
		model.addAttribute("goods",goods);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute GoodsEntity goods){
		
		validateSave(goods);
		if(goods.isNew())
		{
			goods.setAddTime(new Date());
			goodsService.save(goods);
		}else
		{
			
			GoodsEntity goodsDB = goodsService.getById(goods.getId());
			BeanUtils.copyProperties(goods, goodsDB, "addTime","goodsDesc","attributes");
			System.out.println("goodsDB.getAttributes()=="+goodsDB.getAttributes());
			goodsService.update(goodsDB);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(GoodsEntity goods){
		//必填项校验
		// 判断brand_id是否为空
		if(null==goods.getBrandId()){
			throw new ValidationException("校验失败，brand_id不能为空！");
		}
		// 判断category_id是否为空
		if(null==goods.getCategoryId()){
			throw new ValidationException("校验失败，category_id不能为空！");
		}
		// 判断goods_sn是否为空
		if(null==goods.getGoodsSn()){
			throw new ValidationException("校验失败，goods_sn不能为空！");
		}
		// 判断名称是否为空
		if(null==goods.getName()){
			throw new ValidationException("校验失败，名称不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		goodsService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			goodsService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

