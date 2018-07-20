package com.xjj.wefactory.wx.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.wefactory.business.category.entity.CategoryEntity;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.CategoryService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/catalog")
public class WxCatalogController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 分类栏目
     *
     * @param id 分类类目ID
     *    如果分类类目ID是空，则选择第一个分类类目。
     *    需要注意，这里分类类目是一级类目
     * @param page 分页页数
     * @param size 分页大小
     * @return 分类栏目
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              categoryList: xxx,
     *              currentCategory: xxx,
     *              currentSubCategory: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("index")
    public Object index(Long id,
                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                        @RequestParam(value = "size", defaultValue = "10") Integer size) {

        // 所有一级分类目录
        List<CategoryEntity> l1CatList =  categoryService.findListByLevel1();

        // 当前一级分类目录
        CategoryEntity currentCategory = null;
        if(id != null){
            currentCategory = categoryService.getById(id);
        }
        else{
            currentCategory = l1CatList.get(0);
        }

        // 当前一级分类目录对应的二级分类目录
        List<CategoryEntity> currentSubCategory = null;
        if (null != currentCategory) {
            currentSubCategory = categoryService.findListByPid(currentCategory.getId());
        }

        Map<String, Object> data = new HashMap();
        data.put("categoryList", l1CatList);
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        return WxJson.ok(data);
    }

    /**
     * 当前分类栏目
     *
     * @param id 分类类目ID
     * @return 当前分类栏目
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              currentCategory: xxx,
     *              currentSubCategory: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("current")
    public Object current(Long id) {
        if(id == null){
            return WxJson.badArgument();
        }

        // 当前分类
        CategoryEntity currentCategory =categoryService.getById(id);
        List<CategoryEntity> currentSubCategory = categoryService.findListByPid(currentCategory.getId());

        Map<String, Object> data = new HashMap();
        data.put("currentCategory", currentCategory);
        data.put("currentSubCategory", currentSubCategory);
        return WxJson.ok(data);
    }
}