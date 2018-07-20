package com.xjj.wefactory.wx.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.framework.utils.JacksonUtil;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wefactory.business.goods.entity.GoodsEntity;
import com.xjj.wefactory.customer.collect.entity.CollectEntity;
import com.xjj.wefactory.wx.annotation.LoginUser;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.CollectService;
import com.xjj.wefactory.wx.service.GoodsService;

@RestController
@RequestMapping("/wx/collect")
public class WxCollectController {
    @Autowired
    private CollectService collectService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 用户收藏列表
     *
     * @param userId 用户ID
     * @param type 类型，如果是0则是商品收藏，如果是1则是专题收藏
     *    目前没有使用
     * @param page 分页页数
     * @param size 分页大小
     * @return 用户收藏列表
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              collectList: xxx,
     *              totalPages: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("list")
    public Object list(@LoginUser Long userId, Byte type,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if(userId == null){
            return WxJson.unlogin();
        }
        if(type == null){
            return WxJson.badArgument();
        }
        
        //按类型查询
        XJJParameter param = new XJJParameter();
        param.addQuery("query.customerId@eq@l", userId);
        param.addQuery("query.typeId@eq@l", type);
        List<CollectEntity> collectList = collectService.findListLimit(param, (page-1)*size, size);
        
        //查询总数
        int count = collectService.getCount(param);
        
        
        int totalPages = (int) Math.ceil((double) count / size);

        List<Object> collects = new ArrayList<>(collectList.size());
        for(CollectEntity collect : collectList){
            Map<String, Object> c = new HashMap();
            c.put("id", collect.getId());
            c.put("type", collect.getTypeId());
            c.put("valueId", collect.getValueId());

            GoodsEntity goods = goodsService.getById(collect.getValueId());
            c.put("name", goods.getName());
            c.put("brief", goods.getGoodsBrief());
            c.put("picUrl", goods.getListPicUrl());
            c.put("retailPrice", goods.getRetailPrice());

            collects.add(c);
        }

        Map<String, Object> result = new HashMap();
        result.put("collectList", collects);
        result.put("totalPages", totalPages);
        return WxJson.ok(result);
    }

    /**
     * 用户收藏添加或删除
     *
     * @param userId 用户ID
     * @param body 请求内容
     * @return 操作结果
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              type: xxx,
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("addordelete")
    public Object addordelete(@LoginUser Long userId, @RequestBody String body) {
        if(userId == null){
            return WxJson.unlogin();
        }
        if(body == null){
            return WxJson.badArgument();
        }

        Integer type = JacksonUtil.parseInteger(body, "type");
        Long valueId = JacksonUtil.parseLong(body, "valueId");
        if(null==type || null==valueId){
            return WxJson.badArgument();
        }

        CollectEntity collect = collectService.getByTypeAndValue(userId, type, valueId);

        String handleType = null;
        if(collect != null){
            handleType = "delete";
            collectService.delete(collect.getId());
        }
        else{
            handleType = "add";
            collect = new CollectEntity();
            collect.setCustomerId(userId);
            collect.setValueId(valueId);
            collect.setTypeId(type);
            collect.setAddTime(new Date());
            collectService.save(collect);
        }

        Map<String, Object> data = new HashMap();
        data.put("type", handleType);
        return WxJson.ok(data);
    }
}