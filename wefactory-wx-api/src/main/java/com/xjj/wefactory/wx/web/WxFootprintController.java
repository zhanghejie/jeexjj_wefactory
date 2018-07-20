package com.xjj.wefactory.wx.web;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xjj.framework.utils.JacksonUtil;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wefactory.business.goods.entity.GoodsEntity;
import com.xjj.wefactory.customer.footprint.entity.FootprintEntity;
import com.xjj.wefactory.wx.annotation.LoginUser;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.FootprintService;
import com.xjj.wefactory.wx.service.GoodsService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/footprint")
public class WxFootprintController {
    @Autowired
    private FootprintService footprintService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 删除用户足迹
     *
     * @param userId 用户ID
     * @param body 请求内容， { footprintId: xxx }
     * @return 删除操作结果
     *   成功则 { code: 0, msg: '成功' }
     *   失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("delete")
    public Object delete(@LoginUser Long userId, @RequestBody String body) {
        if(userId == null){
            return WxJson.unlogin();
        }
        if(body == null){
            return WxJson.badArgument();
        }

        Long footprintId = JacksonUtil.parseLong(body, "footprintId");
        if(footprintId == null){
            return WxJson.badArgument();
        }
        FootprintEntity footprint = footprintService.getById(footprintId);

        if(footprint == null){
            return WxJson.badArgumentValue();
        }
        if(!footprint.getCustomerId().equals(userId)){
            return WxJson.badArgumentValue();
        }

        footprintService.delete(footprintId);
        return WxJson.ok();
    }

    /**
     * 用户足迹列表
     *
     * @param page 分页页数
     * @param size 分页大小
     * @return 用户足迹列表
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              footprintList: xxx,
     *              totalPages: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("list")
    public Object list(@LoginUser Long userId,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if(userId == null){
            return WxJson.unlogin();
        }
        
        
        //查询用户的足迹
        XJJParameter param = new XJJParameter();
        param.addQuery("query.customerId@eq@l", userId);
        param.addOrderByDesc("addTime");
        
        List<FootprintEntity> footprintList = footprintService.findListLimit(param, (page-1)*size, size);
        int count = footprintService.getCount(param);
        int totalPages = (int) Math.ceil((double) count / size);

        List<Object> footprintVoList = new ArrayList<>(footprintList.size());
        for(FootprintEntity footprint : footprintList){
            Map<String, Object> c = new HashMap();
            c.put("id", footprint.getId());
            c.put("goodsId", footprint.getGoodsId());
            c.put("addTime", footprint.getAddTime());

            GoodsEntity goods = goodsService.getById(footprint.getGoodsId());
            c.put("name", goods.getName());
            c.put("brief", goods.getGoodsBrief());
            c.put("picUrl", goods.getListPicUrl());
            c.put("retailPrice", goods.getRetailPrice());
            footprintVoList.add(c);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("footprintList", footprintVoList);
        result.put("totalPages", totalPages);
        return WxJson.ok(result);
    }

}