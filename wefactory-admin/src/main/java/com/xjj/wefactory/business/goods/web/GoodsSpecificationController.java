/****************************************************
 * Description: Controller for 商品规格
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.business.goods.web;
import com.xjj.wefactory.business.goods.entity.GoodsSpecificationEntity;
import com.xjj.wefactory.business.goods.service.GoodsSpecificationService;
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
@RequestMapping("/business/goods/specification")
public class GoodsSpecificationController extends SpringControllerSupport{
	@Autowired
	private GoodsSpecificationService goodsSpecificationService;
	
	
	@SecPrivilege(title="商品规格管理")
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
		page = goodsSpecificationService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("goodsSpecification") GoodsSpecificationEntity goodsSpecification,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		GoodsSpecificationEntity goodsSpecification = goodsSpecificationService.getById(id);
		model.addAttribute("goodsSpecification",goodsSpecification);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute GoodsSpecificationEntity goodsSpecification){
		
		validateSave(goodsSpecification);
		if(goodsSpecification.isNew())
		{
			//goodsSpecification.setCreateDate(new Date());
			goodsSpecificationService.save(goodsSpecification);
		}else
		{
			goodsSpecificationService.update(goodsSpecification);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(GoodsSpecificationEntity goodsSpecification){
		//必填项校验
		// 判断goods_id是否为空
		if(null==goodsSpecification.getGoodsId()){
			throw new ValidationException("校验失败，goods_id不能为空！");
		}
		// 判断value是否为空
		if(null==goodsSpecification.getValue()){
			throw new ValidationException("校验失败，value不能为空！");
		}
		// 判断pic_url是否为空
		if(null==goodsSpecification.getPicUrl()){
			throw new ValidationException("校验失败，pic_url不能为空！");
		}
		// 判断specification是否为空
		if(null==goodsSpecification.getSpecification()){
			throw new ValidationException("校验失败，specification不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		goodsSpecificationService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			goodsSpecificationService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

