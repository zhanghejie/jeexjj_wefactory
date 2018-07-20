package com.xjj.wefactory.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wefactory.business.ad.entity.AdEntity;
import com.xjj.wefactory.business.brand.entity.BrandEntity;
import com.xjj.wefactory.business.category.entity.CategoryEntity;
import com.xjj.wefactory.business.goods.entity.GoodsEntity;
import com.xjj.wefactory.business.topic.entity.TopicEntity;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.AdService;
import com.xjj.wefactory.wx.service.BrandService;
import com.xjj.wefactory.wx.service.CartService;
import com.xjj.wefactory.wx.service.CategoryService;
import com.xjj.wefactory.wx.service.GoodsService;
import com.xjj.wefactory.wx.service.TopicService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/home")
public class WxHomeController extends WxBaseController{
    private final Log logger = LogFactory.getLog(WxHomeController.class);

    @Autowired
    private AdService adService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CartService cartService;

    /**
     * app首页
     *
     * @return app首页相关信息
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              banner: xxx,
     *              channel: xxx,
     *              newGoodsList: xxx,
     *              hotGoodsList: xxx,
     *              topicList: xxx,
     *              floorGoodsList: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("/index")
    public Object index() {
        Map<String, Object> data = new HashMap<>();
        
        //查询广告
        XJJParameter param = new XJJParameter();
        param.addQuery("query.position@eq@l", 1);
        List<AdEntity> banner = adService.findList(param);
        data.put("banner", banner);
        
        //查询一级类目level
        List<CategoryEntity> channel = categoryService.findListByLevel1();
        data.put("channel", channel);

        //查询新品
        param.clear();
        param.addQuery("query.isNewly@eq@i", "1");
        List<GoodsEntity> newGoods = goodsService.findListLimit(param,0,4);
        data.put("newGoodsList", newGoods);

        //查询热销品
        param.clear();
        param.addQuery("query.isHot@eq@i", "1");
        List<GoodsEntity> hotGoods = goodsService.findListLimit(param,0, 3);
        data.put("hotGoodsList", hotGoods);

        param.clear();
        //查询品牌
        List<BrandEntity> brandList = brandService.findListLimit(param,0,4);
        data.put("brandList", brandList);

        //专题精选
        List<TopicEntity> topicList = topicService.findListLimit(param,0, 3);
        data.put("topicList", topicList);

        List<Map> categoryList = new ArrayList<>();
        //查询一级类目非推荐
        param.clear();
        param.addQuery("query.level@eq@s", "L1");
        param.addQuery("query.name@ne@s", "推荐 ");
        List<CategoryEntity> catL1List = categoryService.findListLimit(param,0, 6);
        
        System.out.println("catL1List==="+catL1List);
        for (CategoryEntity catL1 : catL1List) {
            List<CategoryEntity> catL2List = categoryService.findListByPid(catL1.getId());
            List<Long> l2List = new ArrayList<>();
            for (CategoryEntity catL2 : catL2List) {
                l2List.add(catL2.getId());
            }
            
            System.out.println("catL1.getId()=="+catL1.getId()+"catL2=="+l2List);

            List<GoodsEntity> categoryGoods = null;
            if(l2List.size() == 0){
                categoryGoods = new ArrayList<>();
            }
            else{
                categoryGoods = goodsService.findListByColumnValuesLimit("category_id",l2List.toArray(), 0, 5);
            }

            Map catGoods = new HashMap();
            catGoods.put("id", catL1.getId());
            catGoods.put("name", catL1.getName());
            catGoods.put("goodsList", categoryGoods);
            categoryList.add(catGoods);
        }
        data.put("floorGoodsList", categoryList);

        return WxJson.ok(data);
    }
}