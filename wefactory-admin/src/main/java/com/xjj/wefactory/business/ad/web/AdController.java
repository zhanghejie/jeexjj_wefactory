/****************************************************
 * Description: Controller for 广告
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.business.ad.web;
import com.xjj.wefactory.business.ad.entity.AdEntity;
import com.xjj.wefactory.business.ad.service.AdService;
import com.xjj.wefactory.business.goods.entity.GoodsEntity;

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
@RequestMapping("/business/ad")
public class AdController extends SpringControllerSupport{
	@Autowired
	private AdService adService;
	
	
	@SecPrivilege(title="广告管理")
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
		page = adService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("ad") AdEntity ad,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		AdEntity ad = adService.getById(id);
		model.addAttribute("ad",ad);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute AdEntity ad){
		
		validateSave(ad);
		if(ad.isNew())
		{
			ad.setAddTime(new Date());
			adService.save(ad);
		}else
		{
			AdEntity adDB = adService.getById(ad.getId());
			BeanUtils.copyProperties(ad, adDB, "addTime");
			adService.update(adDB);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(AdEntity ad){
		//必填项校验
		// 判断seller_id是否为空
		if(null==ad.getSellerId()){
			throw new ValidationException("校验失败，seller_id不能为空！");
		}
		// 判断地点是否为空
		if(null==ad.getPosition()){
			throw new ValidationException("校验失败，地点不能为空！");
		}
		// 判断名称是否为空
		if(null==ad.getName()){
			throw new ValidationException("校验失败，名称不能为空！");
		}
		// 判断链接是否为空
		if(null==ad.getLink()){
			throw new ValidationException("校验失败，链接不能为空！");
		}
		// 判断URL地址是否为空
		if(null==ad.getUrl()){
			throw new ValidationException("校验失败，URL地址不能为空！");
		}
		// 判断内容是否为空
		if(null==ad.getContent()){
			throw new ValidationException("校验失败，内容不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		adService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			adService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

