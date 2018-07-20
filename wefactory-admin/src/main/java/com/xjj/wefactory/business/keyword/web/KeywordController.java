/****************************************************
 * Description: Controller for 关键词
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.business.keyword.web;
import com.xjj.wefactory.business.keyword.entity.KeywordEntity;
import com.xjj.wefactory.business.keyword.service.KeywordService;
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
@RequestMapping("/business/keyword")
public class KeywordController extends SpringControllerSupport{
	@Autowired
	private KeywordService keywordService;
	
	
	@SecPrivilege(title="关键词管理")
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
		page = keywordService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("keyword") KeywordEntity keyword,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		KeywordEntity keyword = keywordService.getById(id);
		model.addAttribute("keyword",keyword);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute KeywordEntity keyword){
		
		validateSave(keyword);
		if(keyword.isNew())
		{
			//keyword.setCreateDate(new Date());
			keywordService.save(keyword);
		}else
		{
			keywordService.update(keyword);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(KeywordEntity keyword){
		//必填项校验
		// 判断seller_id是否为空
		if(null==keyword.getSellerId()){
			throw new ValidationException("校验失败，seller_id不能为空！");
		}
		// 判断keyword是否为空
		if(null==keyword.getKeyword()){
			throw new ValidationException("校验失败，keyword不能为空！");
		}
		// 判断关键词的跳转链接是否为空
		if(null==keyword.getUrl()){
			throw new ValidationException("校验失败，关键词的跳转链接不能为空！");
		}
		// 判断是否热点是否为空
		if(null==keyword.getIsHot()){
			throw new ValidationException("校验失败，是否热点不能为空！");
		}
		// 判断is_default是否为空
		if(null==keyword.getIsDefault()){
			throw new ValidationException("校验失败，is_default不能为空！");
		}
		// 判断is_show是否为空
		if(null==keyword.getIsShow()){
			throw new ValidationException("校验失败，is_show不能为空！");
		}
		// 判断sort_order是否为空
		if(null==keyword.getSortOrder()){
			throw new ValidationException("校验失败，sort_order不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		keywordService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			keywordService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

