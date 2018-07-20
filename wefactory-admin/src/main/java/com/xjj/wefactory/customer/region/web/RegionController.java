/****************************************************
 * Description: Controller for 地区信息
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.customer.region.web;
import com.xjj.wefactory.customer.region.entity.RegionEntity;
import com.xjj.wefactory.customer.region.service.RegionService;
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
@RequestMapping("/customer/region")
public class RegionController extends SpringControllerSupport{
	@Autowired
	private RegionService regionService;
	
	
	@SecPrivilege(title="地区信息管理")
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
		page = regionService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("region") RegionEntity region,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		RegionEntity region = regionService.getById(id);
		model.addAttribute("region",region);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute RegionEntity region){
		
		validateSave(region);
		if(region.isNew())
		{
			//region.setCreateDate(new Date());
			regionService.save(region);
		}else
		{
			regionService.update(region);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(RegionEntity region){
		//必填项校验
		// 判断pid是否为空
		if(null==region.getPid()){
			throw new ValidationException("校验失败，pid不能为空！");
		}
		// 判断name是否为空
		if(null==region.getName()){
			throw new ValidationException("校验失败，name不能为空！");
		}
		// 判断1, 2, 3, 4是否为空
		if(null==region.getType()){
			throw new ValidationException("校验失败，1, 2, 3, 4不能为空！");
		}
		// 判断code是否为空
		if(null==region.getCode()){
			throw new ValidationException("校验失败，code不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		regionService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			regionService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

