/****************************************************
 * Description: Controller for 品牌表
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.business.brand.web;
import com.xjj.wefactory.business.brand.entity.BrandEntity;
import com.xjj.wefactory.business.brand.service.BrandService;
import com.xjj.wefactory.business.seller.entity.SellerEntity;
import com.xjj.wefactory.business.seller.service.SellerService;

import java.util.Date;
import java.util.List;

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
@RequestMapping("/business/brand")
public class BrandController extends SpringControllerSupport{
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private SellerService sellerService;
	@SecPrivilege(title="品牌管理")
	@RequestMapping(value = "/index")
	public String index(Model model) {
		List<SellerEntity> sellerList = sellerService.findAll();
		model.addAttribute("sellerList",sellerList);
		String page = this.getViewPath("index");
		return page;
	}
	
	@SecList
	@RequestMapping(value = "/list")
	public String list(Model model,
			@QueryParameter XJJParameter query,
			@ModelAttribute("page") Pagination page
			) {
		page = brandService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("brand") BrandEntity brand,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		BrandEntity brand = brandService.getById(id);
		model.addAttribute("brand",brand);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute BrandEntity brand){
		
		validateSave(brand);
		if(brand.isNew())
		{
			brand.setAddTime(new Date());
			brandService.save(brand);
		}else
		{
			BrandEntity brandDb = brandService.getById(brand.getId());
			brandDb.setName(brand.getName());
			brandDb.setSimpleDesc(brand.getSimpleDesc());
			brandDb.setIsNewly(brand.getIsNewly());
			brandDb.setStatus(brand.getStatus());
			brandService.update(brandDb);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(BrandEntity brand){
		//必填项校验
		// 判断seller_id是否为空
		if(null==brand.getSellerId()){
			throw new ValidationException("校验失败，seller_id不能为空！");
		}
		// 判断名称是否为空
		if(null==brand.getName()){
			throw new ValidationException("校验失败，名称不能为空！");
		}
		// 判断list_pic_url是否为空
		if(null==brand.getListPicUrl()){
			throw new ValidationException("校验失败，list_pic_url不能为空！");
		}
		// 判断simple_desc是否为空
		if(null==brand.getSimpleDesc()){
			throw new ValidationException("校验失败，simple_desc不能为空！");
		}
		// 判断pic_url是否为空
		if(null==brand.getPicUrl()){
			throw new ValidationException("校验失败，pic_url不能为空！");
		}
		// 判断sort_order是否为空
		if(null==brand.getSortOrder()){
			throw new ValidationException("校验失败，sort_order不能为空！");
		}
		// 判断is_show是否为空
		if(null==brand.getIsShow()){
			throw new ValidationException("校验失败，is_show不能为空！");
		}
		// 判断floor_price是否为空
		if(null==brand.getFloorPrice()){
			throw new ValidationException("校验失败，floor_price不能为空！");
		}
		// 判断app_list_pic_url是否为空
		if(null==brand.getAppListPicUrl()){
			throw new ValidationException("校验失败，app_list_pic_url不能为空！");
		}
		// 判断is_new是否为空
		if(null==brand.getIsNewly()){
			throw new ValidationException("校验失败，is_newly不能为空！");
		}
		// 判断new_pic_url是否为空
		if(null==brand.getNewPicUrl()){
			//throw new ValidationException("校验失败，new_pic_url不能为空！");
		}
		// 判断new_sort_order是否为空
		if(null==brand.getNewSortOrder()){
			//throw new ValidationException("校验失败，new_sort_order不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		brandService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			brandService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

