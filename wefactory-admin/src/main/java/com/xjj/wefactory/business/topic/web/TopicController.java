/****************************************************
 * Description: Controller for 专题
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.business.topic.web;
import com.xjj.wefactory.business.ad.entity.AdEntity;
import com.xjj.wefactory.business.topic.entity.TopicEntity;
import com.xjj.wefactory.business.topic.service.TopicService;
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
@RequestMapping("/business/topic")
public class TopicController extends SpringControllerSupport{
	@Autowired
	private TopicService topicService;
	
	
	@SecPrivilege(title="专题管理")
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
		page = topicService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("topic") TopicEntity topic,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		TopicEntity topic = topicService.getById(id);
		model.addAttribute("topic",topic);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute TopicEntity topic){
		
		validateSave(topic);
		if(topic.isNew())
		{
			topic.setAddTime(new Date());
			topicService.save(topic);
		}else
		{
			TopicEntity topicDB = topicService.getById(topic.getId());
			BeanUtils.copyProperties(topic, topicDB, "addTime","content");
			topicService.update(topicDB);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(TopicEntity topic){
		//必填项校验
		// 判断seller_id是否为空
		if(null==topic.getSellerId()){
			throw new ValidationException("校验失败，seller_id不能为空！");
		}
		// 判断标题是否为空
		if(null==topic.getTitle()){
			throw new ValidationException("校验失败，标题不能为空！");
		}
		// 判断avatar是否为空
		if(null==topic.getAvatar()){
			throw new ValidationException("校验失败，avatar不能为空！");
		}
		// 判断item_pic_url是否为空
		if(null==topic.getItemPicUrl()){
			throw new ValidationException("校验失败，item_pic_url不能为空！");
		}
		// 判断专题子内容是否为空
		if(null==topic.getSubtitle()){
			throw new ValidationException("校验失败，专题子内容不能为空！");
		}
		// 判断price_info是否为空
		if(null==topic.getPriceInfo()){
			throw new ValidationException("校验失败，price_info不能为空！");
		}
		// 判断阅读次数是否为空
		if(null==topic.getReadCount()){
			throw new ValidationException("校验失败，阅读次数不能为空！");
		}
		// 判断scene_pic_url是否为空
		if(null==topic.getScenePicUrl()){
			throw new ValidationException("校验失败，scene_pic_url不能为空！");
		}
		// 判断sort_order是否为空
		if(null==topic.getSortOrder()){
			throw new ValidationException("校验失败，sort_order不能为空！");
		}
		// 判断is_show是否为空
		if(null==topic.getIsShow()){
			throw new ValidationException("校验失败，is_show不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		topicService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			topicService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

