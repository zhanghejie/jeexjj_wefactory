package com.xjj.wefactory.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.wefactory.business.brand.entity.BrandEntity;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.BrandService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/brand")
public class WxBrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 品牌列表
     *
     * @param page 分页页数
     * @param size 分页大小
     * @return 品牌列表
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              brandList: xxx,
     *              totalPages: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("list")
    public Object list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "size", defaultValue = "10") Integer size) {

        List<BrandEntity> brandList = brandService.findListLimit(null, (page-1)*size, size);
        int total = brandService.getCount(null);
        int totalPages = (int) Math.ceil((double) total / size);

        Map<String, Object> data = new HashMap();
        data.put("brandList", brandList);
        data.put("totalPages", totalPages);
        return WxJson.ok(data);
    }

    /**
     * 品牌详情
     *
     * @param id 品牌ID
     * @return 品牌详情
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              brand: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("detail")
    public Object detail(Long id) {
        if(id == null){
            return WxJson.badArgument();
        }

        BrandEntity entity = brandService.getById(id);
        if(entity == null){
            return WxJson.badArgumentValue();
        }

        Map<String, Object> data = new HashMap();
        data.put("brand",entity);
        return WxJson.ok(data);
    }
}