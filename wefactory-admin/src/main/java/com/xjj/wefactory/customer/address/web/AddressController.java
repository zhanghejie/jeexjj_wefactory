/****************************************************
 * Description: Controller for 地址
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.customer.address.web;
import com.xjj.wefactory.customer.address.entity.AddressEntity;
import com.xjj.wefactory.customer.address.service.AddressService;
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
@RequestMapping("/customer/address")
public class AddressController extends SpringControllerSupport{
	@Autowired
	private AddressService addressService;
	
	
	@SecPrivilege(title="地址管理")
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
		page = addressService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("address") AddressEntity address,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		AddressEntity address = addressService.getById(id);
		model.addAttribute("address",address);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute AddressEntity address){
		
		validateSave(address);
		if(address.isNew())
		{
			//address.setCreateDate(new Date());
			addressService.save(address);
		}else
		{
			addressService.update(address);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(AddressEntity address){
		//必填项校验
		// 判断customer_id是否为空
		if(null==address.getCustomerId()){
			throw new ValidationException("校验失败，customer_id不能为空！");
		}
		// 判断名称是否为空
		if(null==address.getName()){
			throw new ValidationException("校验失败，名称不能为空！");
		}
		// 判断省份是否为空
		if(null==address.getProvinceId()){
			throw new ValidationException("校验失败，省份不能为空！");
		}
		// 判断城市是否为空
		if(null==address.getCityId()){
			throw new ValidationException("校验失败，城市不能为空！");
		}
		// 判断区是否为空
		if(null==address.getAreaId()){
			throw new ValidationException("校验失败，区不能为空！");
		}
		// 判断地址是否为空
		if(null==address.getAddress()){
			throw new ValidationException("校验失败，地址不能为空！");
		}
		// 判断手机是否为空
		if(null==address.getMobile()){
			throw new ValidationException("校验失败，手机不能为空！");
		}
		// 判断is_default是否为空
		if(null==address.getIsDefault()){
			throw new ValidationException("校验失败，is_default不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		addressService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			addressService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

