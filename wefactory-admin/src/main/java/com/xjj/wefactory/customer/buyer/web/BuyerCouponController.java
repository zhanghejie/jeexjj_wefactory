/****************************************************
 * Description: Controller for 客户优惠券
 * Copyright:   Copyright (c) 2018
 * Company:     xjj
 * @author      zhanghejie
 * @version     1.0
 * @see
	HISTORY
	*  2018-07-04 zhanghejie Create File
**************************************************/
package com.xjj.wefactory.customer.buyer.web;
import com.xjj.wefactory.customer.buyer.entity.BuyerCouponEntity;
import com.xjj.wefactory.customer.buyer.service.BuyerCouponService;
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
@RequestMapping("/customer/buyer/coupon")
public class BuyerCouponController extends SpringControllerSupport{
	@Autowired
	private BuyerCouponService buyerCouponService;
	
	
	@SecPrivilege(title="客户优惠券管理")
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
		page = buyerCouponService.findPage(query,page);
		return getViewPath("list");
	}
	
	@SecCreate
	@RequestMapping("/input")
	public String create(@ModelAttribute("buyerCoupon") BuyerCouponEntity buyerCoupon,Model model){
		return getViewPath("input");
	}
	
	@SecEdit
	@RequestMapping("/input/{id}")
	public String edit(@PathVariable("id") Long id, Model model){
		BuyerCouponEntity buyerCoupon = buyerCouponService.getById(id);
		model.addAttribute("buyerCoupon",buyerCoupon);
		return getViewPath("input");
	}
	
	@SecCreate
	@SecEdit
	@RequestMapping("/save")
	public @ResponseBody XjjJson save(@ModelAttribute BuyerCouponEntity buyerCoupon){
		
		validateSave(buyerCoupon);
		if(buyerCoupon.isNew())
		{
			//buyerCoupon.setCreateDate(new Date());
			buyerCouponService.save(buyerCoupon);
		}else
		{
			buyerCouponService.update(buyerCoupon);
		}
		return XjjJson.success("保存成功");
	}
	
	
	/**
	 * 数据校验
	 **/
	private void validateSave(BuyerCouponEntity buyerCoupon){
		//必填项校验
		// 判断customer_id是否为空
		if(null==buyerCoupon.getCustomerId()){
			throw new ValidationException("校验失败，customer_id不能为空！");
		}
		// 判断coupon_id是否为空
		if(null==buyerCoupon.getCouponId()){
			throw new ValidationException("校验失败，coupon_id不能为空！");
		}
		// 判断user_id是否为空
		if(null==buyerCoupon.getUserId()){
			throw new ValidationException("校验失败，user_id不能为空！");
		}
		// 判断order_id是否为空
		if(null==buyerCoupon.getOrderId()){
			throw new ValidationException("校验失败，order_id不能为空！");
		}
		// 判断used_time是否为空
		if(null==buyerCoupon.getUsedTime()){
			throw new ValidationException("校验失败，used_time不能为空！");
		}
	}
	
	@SecDelete
	@RequestMapping("/delete/{id}")
	public @ResponseBody XjjJson delete(@PathVariable("id") Long id){
		buyerCouponService.delete(id);
		return XjjJson.success("成功删除1条");
	}
	@SecDelete
	@RequestMapping("/delete")
	public @ResponseBody XjjJson delete(@RequestParam("ids") Long[] ids){
		if(ids == null || ids.length == 0){
			return XjjJson.error("没有选择删除记录");
		}
		for(Long id : ids){
			buyerCouponService.delete(id);
		}
		return XjjJson.success("成功删除"+ids.length+"条");
	}
}

