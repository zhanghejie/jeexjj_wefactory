/****************************************************
 * Description: Controller for 评论
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.customer.comment.web;
import com.xjj.wefactory.customer.comment.entity.CommentEntity;
import com.xjj.wefactory.customer.comment.service.CommentService;
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
@RequestMapping("/customer/comment")
public class CommentController extends SpringControllerSupport{
	@Autowired
	private CommentService commentService;
	
	
	@SecPrivilege(title="评论管理")
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
		page = commentService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("comment") CommentEntity comment,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		CommentEntity comment = commentService.getById(id);
		model.addAttribute("comment",comment);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute CommentEntity comment){
		
		validateSave(comment);
		if(comment.isNew())
		{
			//comment.setCreateDate(new Date());
			commentService.save(comment);
		}else
		{
			commentService.update(comment);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(CommentEntity comment){
		//必填项校验
		// 判断customer_id是否为空
		if(null==comment.getCustomerId()){
			throw new ValidationException("校验失败，customer_id不能为空！");
		}
		// 判断type_id是否为空
		if(null==comment.getTypeId()){
			throw new ValidationException("校验失败，type_id不能为空！");
		}
		// 判断value_id是否为空
		if(null==comment.getValueId()){
			throw new ValidationException("校验失败，value_id不能为空！");
		}
		// 判断has_picture是否为空
		if(null==comment.getHasPicture()){
			throw new ValidationException("校验失败，has_picture不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		commentService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			commentService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

