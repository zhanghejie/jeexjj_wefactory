/****************************************************
 * Description: Controller for 收藏
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-11 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.customer.collect.web;
import com.xjj.wefactory.customer.collect.entity.CollectEntity;
import com.xjj.wefactory.customer.collect.service.CollectService;
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
@RequestMapping("/customer/collect")
public class CollectController extends SpringControllerSupport{
	@Autowired
	private CollectService collectService;
	
	
	@SecPrivilege(title="收藏管理")
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
		page = collectService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("collect") CollectEntity collect,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		CollectEntity collect = collectService.getById(id);
		model.addAttribute("collect",collect);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute CollectEntity collect){
		
		validateSave(collect);
		if(collect.isNew())
		{
			//collect.setCreateDate(new Date());
			collectService.save(collect);
		}else
		{
			collectService.update(collect);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(CollectEntity collect){
		//必填项校验
		// 判断customer_id是否为空
		if(null==collect.getCustomerId()){
			throw new ValidationException("校验失败，customer_id不能为空！");
		}
		// 判断user_id是否为空
		if(null==collect.getCustomerId()){
			throw new ValidationException("校验失败，user_id不能为空！");
		}
		// 判断value_id是否为空
		if(null==collect.getValueId()){
			throw new ValidationException("校验失败，value_id不能为空！");
		}
		// 判断是否是关注是否为空
		if(null==collect.getIsAttention()){
			throw new ValidationException("校验失败，是否是关注不能为空！");
		}
		// 判断type_id是否为空
		if(null==collect.getTypeId()){
			throw new ValidationException("校验失败，type_id不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		collectService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			collectService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

