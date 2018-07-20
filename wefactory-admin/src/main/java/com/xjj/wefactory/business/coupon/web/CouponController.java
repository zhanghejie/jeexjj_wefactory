/****************************************************
 * Description: Controller for 优惠券
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.business.coupon.web;
import com.xjj.wefactory.business.ad.entity.AdEntity;
import com.xjj.wefactory.business.coupon.entity.CouponEntity;
import com.xjj.wefactory.business.coupon.service.CouponService;
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
@RequestMapping("/business/coupon")
public class CouponController extends SpringControllerSupport{
	@Autowired
	private CouponService couponService;
	
	
	@SecPrivilege(title="优惠券管理")
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
		page = couponService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("coupon") CouponEntity coupon,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		CouponEntity coupon = couponService.getById(id);
		model.addAttribute("coupon",coupon);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute CouponEntity coupon){
		
		validateSave(coupon);
		if(coupon.isNew())
		{
			coupon.setAddTime(new Date());
			couponService.save(coupon);
		}else
		{
			CouponEntity couponDB = couponService.getById(coupon.getId());
			BeanUtils.copyProperties(coupon, couponDB, "addTime");
			couponService.update(couponDB);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(CouponEntity coupon){
		//必填项校验
		// 判断seller_id是否为空
		if(null==coupon.getSellerId()){
			throw new ValidationException("校验失败，seller_id不能为空！");
		}
		// 判断name是否为空
		if(null==coupon.getName()){
			throw new ValidationException("校验失败，name不能为空！");
		}
		// 判断type_money是否为空
		if(null==coupon.getTypeMoney()){
			throw new ValidationException("校验失败，type_money不能为空！");
		}
		// 判断send_type是否为空
		if(null==coupon.getSendType()){
			throw new ValidationException("校验失败，send_type不能为空！");
		}
		// 判断min_amount是否为空
		if(null==coupon.getMinAmount()){
			throw new ValidationException("校验失败，min_amount不能为空！");
		}
		// 判断max_amount是否为空
		if(null==coupon.getMaxAmount()){
			throw new ValidationException("校验失败，max_amount不能为空！");
		}
		// 判断min_goods_amount是否为空
		if(null==coupon.getMinGoodsAmount()){
			throw new ValidationException("校验失败，min_goods_amount不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		couponService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			couponService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

