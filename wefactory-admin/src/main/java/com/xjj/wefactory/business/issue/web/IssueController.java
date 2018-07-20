/****************************************************
 * Description: Controller for 常见问题
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.business.issue.web;
import com.xjj.wefactory.business.ad.entity.AdEntity;
import com.xjj.wefactory.business.issue.entity.IssueEntity;
import com.xjj.wefactory.business.issue.service.IssueService;
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
@RequestMapping("/business/issue")
public class IssueController extends SpringControllerSupport{
	@Autowired
	private IssueService issueService;
	
	
	@SecPrivilege(title="常见问题管理")
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
		page = issueService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("issue") IssueEntity issue,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		IssueEntity issue = issueService.getById(id);
		model.addAttribute("issue",issue);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute IssueEntity issue){
		
		validateSave(issue);
		if(issue.isNew())
		{
			issue.setAddTime(new Date());
			issueService.save(issue);
		}else
		{
			IssueEntity issueDB = issueService.getById(issue.getId());
			BeanUtils.copyProperties(issue, issueDB, "addTime");
			issueService.update(issueDB);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(IssueEntity issue){
		//必填项校验
		// 判断seller_id是否为空
		if(null==issue.getSellerId()){
			throw new ValidationException("校验失败，seller_id不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		issueService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			issueService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

