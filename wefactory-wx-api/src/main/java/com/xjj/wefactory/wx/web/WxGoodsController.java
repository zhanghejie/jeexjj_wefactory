package com.xjj.wefactory.wx.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.jdbc.StringUtils;
import com.xjj.framework.web.support.XJJParameter;
import com.xjj.wefactory.business.brand.entity.BrandEntity;
import com.xjj.wefactory.business.category.entity.CategoryEntity;
import com.xjj.wefactory.business.goods.entity.GoodsAttributeEntity;
import com.xjj.wefactory.business.goods.entity.GoodsEntity;
import com.xjj.wefactory.business.issue.entity.IssueEntity;
import com.xjj.wefactory.business.product.entity.ProductEntity;
import com.xjj.wefactory.customer.buyer.entity.BuyerEntity;
import com.xjj.wefactory.customer.comment.entity.CommentEntity;
import com.xjj.wefactory.customer.footprint.entity.FootprintEntity;
import com.xjj.wefactory.customer.search.entity.SearchHistoryEntity;
import com.xjj.wefactory.wx.annotation.LoginUser;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.BrandService;
import com.xjj.wefactory.wx.service.BuyerService;
import com.xjj.wefactory.wx.service.CartService;
import com.xjj.wefactory.wx.service.CategoryService;
import com.xjj.wefactory.wx.service.CollectService;
import com.xjj.wefactory.wx.service.CommentService;
import com.xjj.wefactory.wx.service.FootprintService;
import com.xjj.wefactory.wx.service.GoodsAttributeService;
import com.xjj.wefactory.wx.service.GoodsService;
import com.xjj.wefactory.wx.service.GoodsSpecificationService;
import com.xjj.wefactory.wx.service.IssueService;
import com.xjj.wefactory.wx.service.ProductService;
import com.xjj.wefactory.wx.service.SearchHistoryService;

@RestController
@RequestMapping("/wx/goods")
public class WxGoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ProductService productService;
    @Autowired
    private IssueService goodsIssueService;
    @Autowired
    private GoodsAttributeService goodsAttributeService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private BuyerService buyerService;
    @Autowired
    private CollectService collectService;
    @Autowired
    private FootprintService footprintService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SearchHistoryService searchHistoryService;
    @Autowired
    private CartService cartService;
    @Autowired
    private GoodsSpecificationService goodsSpecificationService;

    /**
     * 商品详情
     *
     * 用户可以不登录。
     * 如果用户登录，则记录用户足迹以及返回用户收藏信息。
     *
     * @param userId 用户ID
     * @param id 商品ID
     * @return 商品详情
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              info: xxx,
     *              userHasCollect: xxx,
     *              issue: xxx,
     *              comment: xxx,
     *              specificationList: xxx,
     *              productList: xxx,
     *              attribute: xxx,
     *              brand: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("detail")
    public Object detail(@LoginUser Long userId, Long id) {
        if(id == null){
            return WxJson.badArgument();
        }

        // 商品信息
        GoodsEntity info = goodsService.getById(id);

        // 商品属性
        List<GoodsAttributeEntity> goodsAttributeList = goodsAttributeService.findListByGoodsId(id);

        // 商品规格
        // 返回的是定制的GoodsSpecificationVo
        Object specificationList = goodsSpecificationService.getSpecificationVoList(id);

        // 商品规格对应的数量和价格
        List<ProductEntity> productList = productService.findListByGoodsId(id);

        // 商品问题，这里是一些通用问题
        List<IssueEntity> issue = goodsIssueService.findAll();

        // 商品品牌商
        BrandEntity brand = brandService.getById(info.getBrandId());

        // =======查询评论开始=======
        XJJParameter param = new XJJParameter();
        param.addQuery("query.valueId@eq@l", 1);
        param.addQuery("query.typeId@eq@i", 0);
        param.addOrderByDesc("addTime");
        List<CommentEntity> comments = commentService.findListLimit(param, 0, 2);
        // =======查询评论结束========
        
        List<Map<String, Object>> commentsVo = new ArrayList<>(comments.size());
        int commentCount = commentService.getCount(param);
        for(CommentEntity comment : comments){
            Map<String, Object> c = new HashMap<>();
            c.put("id", comment.getId());
            c.put("addTime", comment.getAddTime());
            c.put("content", comment.getContent());
            BuyerEntity user = buyerService.getById(comment.getCustomerId());
            c.put("nickname", user.getNickname());
            c.put("avatar", user.getAvatar());
            c.put("picList", comment.getPicUrls());
            commentsVo.add(c);
        }
        Map<String, Object> commentList = new HashMap<>();
        commentList.put("count", commentCount);
        commentList.put("data", commentsVo);

        // 用户收藏
        int userHasCollect = 0;
        if(userId != null) {
        	//查询用户收藏数
            userHasCollect = collectService.getCountByUserIdAndValueId(userId, id);
        }

        // 记录用户的足迹
        if(userId != null) {
            FootprintEntity footprint = new FootprintEntity();
            footprint.setAddTime(new Date());
            footprint.setCustomerId(userId);
            footprint.setGoodsId(id);
            footprintService.save(footprint);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("info", info);
        data.put("userHasCollect", userHasCollect);
        data.put("issue", issue);
        data.put("comment", commentList);
        data.put("specificationList", specificationList);
        data.put("productList", productList);
        data.put("attribute", goodsAttributeList);
        data.put("brand", brand);

        return WxJson.ok(data);
    }

    /**
     * 商品分类类目
     *
     * TODO 可能应该合并到WxCatalogController中
     *
     * @param id 分类类目ID
     * @return 商品分类类目
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              currentCategory: xxx,
     *              parentCategory: xxx,
     *              brotherCategory: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("category")
    public Object category(Long id) {
        if(id == null){
            return WxJson.badArgument();
        }
        CategoryEntity cur = categoryService.getById(id);
        CategoryEntity parent = null;
        List<CategoryEntity> children = null;

        if(cur.getParentId().intValue() == 0){
            parent = cur;
            children = categoryService.findListByPid(cur.getId());
            cur = children.get(0);
        }
        else{
            parent = categoryService.getById(cur.getParentId());
            children = categoryService.findListByPid(cur.getParentId());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("currentCategory", cur);
        data.put("parentCategory", parent);
        data.put("brotherCategory", children);
        return WxJson.ok(data);
    }

    /**
     * 根据条件搜素商品
     *
     * 1. 这里的前五个参数都是可选的，甚至都是空
     * 2. 用户是可选登录，如果登录，则记录用户的搜索关键字
     *
     * @param categoryId 分类类目ID
     * @param brandId 品牌商ID
     * @param keyword 关键字
     * @param isNew 是否新品
     * @param isHot 是否热买
     * @param userId 用户ID
     * @param page 分页页数
     * @param size 分页大小
     * @param sort 排序方式
     * @param order 排序类型，顺序或者降序
     * @return 根据条件搜素的商品详情
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              goodsList: xxx,
     *              filterCategoryList: xxx,
     *              count: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("list")
    public Object list(Integer categoryId, Long brandId, String keyword, Boolean isNew, Boolean isHot,
                       @LoginUser Long userId,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size,
                       String sort, String order) {

        //添加到搜索历史
        if (userId != null && !StringUtils.isNullOrEmpty(keyword)) {
            SearchHistoryEntity searchHistoryVo = new SearchHistoryEntity();
            searchHistoryVo.setAddTime(new Date());
            searchHistoryVo.setKeyword(keyword);
            searchHistoryVo.setCustomerId(userId);
            searchHistoryVo.setComeFrom("wx");
            searchHistoryService.save(searchHistoryVo);
        }

        //查询列表数据
        XJJParameter param = new XJJParameter();
        param.addQuery("query.categoryId@eq@l", categoryId);
        if(null!=brandId)
        {
        	param.addQuery("query.brandId@eq@l",brandId);
        }
        if(!StringUtils.isNullOrEmpty(keyword))
        {
        	param.addQuery("query.keyword@lk@s",keyword);
        }
        if(null!=isHot)
        {
        	param.addQuery("query.isHot@eq@i",isHot?1:0);
        }
        
        if(null!=isNew)
        {
        	param.addQuery("query.isNewly@eq@i",isNew?1:0);
        }
        
        if(!StringUtils.isNullOrEmpty(sort) && !StringUtils.isNullOrEmpty(order))
        {
        	if("desc".equalsIgnoreCase(order))
            {
            	param.addOrderByDesc(sort);
            }else
            {
            	param.addOrderByAsc(sort);
            }
        }
        
        //List<GoodsEntity> goodsList = goodsService.querySelective(categoryId, brandId, keyword, isHot, isNew, page, size, sort, order);
        //int total = goodsService.countSelective(categoryId, brandId, keyword, isHot, isNew, page, size, sort, order);
        System.out.println("param.getSqlCondition()==="+param.getSqlCondition());
        List<GoodsEntity> goodsList = goodsService.findListLimit(param, (page-1)*size, size);
        int total = goodsService.getCount(param);
        System.out.println("total=="+total);
        // 查询商品所属类目列表。
        List<Long> goodsCatIds = goodsService.getCategoryIds(brandId, keyword, isHot, isNew);
        List<CategoryEntity> categoryList = null;
        if(goodsCatIds.size() != 0) {
        	param.clear();
        	param.addQuery("query.id@in@s",goodsCatIds);
        	param.addQuery("query.level@eq@s","L2");
        	
            categoryList = categoryService.findList(param);
        }

        Map<String, Object> data = new HashMap<>();
        data.put("goodsList", goodsList);
        data.put("filterCategoryList", categoryList);
        data.put("count", total);
        return WxJson.ok(data);
    }

    /**
     * 新品首发页面的横幅数据
     *
     * TODO 其实可以删除
     *
     * @return 新品首发页面的栏目数据
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              bannerInfo: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("new")
    public Object newGoods() {
        Map<String, String> bannerInfo = new HashMap<>();
        bannerInfo.put("url", "");
        bannerInfo.put("name", "坚持初心，为你寻觅世间好物");
        bannerInfo.put("imgUrl", "http://yanxuan.nosdn.127.net/8976116db321744084774643a933c5ce.png");

        Map<String, Object> data = new HashMap<>();
        data.put("bannerInfo", bannerInfo);
        return WxJson.ok(data);
    }

    /**
     * 人气推荐页面的横幅数据
     *
     * TODO 其实可以删除
     *
     * @return 人气推荐页面的栏目数据
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              bannerInfo: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("hot")
    public Object hotGoods() {
        Map<String, String> bannerInfo = new HashMap<>();
        bannerInfo.put("url", "");
        bannerInfo.put("name", "大家都在买的严选好物");
        bannerInfo.put("imgUrl", "http://yanxuan.nosdn.127.net/8976116db321744084774643a933c5ce.png");
        Map<String, Object> data = new HashMap<>();
        data.put("bannerInfo", bannerInfo);
        return WxJson.ok(data);
    }

    /**
     * 商品页面推荐商品
     *
     * @return 商品页面推荐商品
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              goodsList: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("related")
    public Object related(Long id) {
        if(id == null){
            return WxJson.badArgument();
        }

        GoodsEntity goods = goodsService.getById(id);
        if(goods == null){
            return WxJson.badArgumentValue();
        }

        // 目前的商品推荐算法仅仅是推荐同类目的其他商品
        Long categoryId = goods.getCategoryId();

        // 查找六个相关商品
        int related = 6;
        
        XJJParameter param = new XJJParameter();
        param.addQuery("query.categoryId@eq@l", categoryId);
        
        List<GoodsEntity> goodsList = goodsService.findListLimit(param, 0, related);
        Map<String, Object> data = new HashMap<>();
        data.put("goodsList", goodsList);
        return WxJson.ok(data);
    }

    /**
     * 在售的商品总数
     *
     * @return 在售的商品总数
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              goodsCount: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("count")
    public Object count() {
        Integer goodsCount = goodsService.getCountByOnSale(1);
        Map<String, Object> data = new HashMap<>();
        data.put("goodsCount", goodsCount);
        return WxJson.ok(data);
    }

}