package com.xjj.wefactory.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.wefactory.customer.region.entity.RegionEntity;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.RegionService;

import java.util.List;

@RestController
@RequestMapping("/wx/region")
public class WxRegionController {
    private final Log logger = LogFactory.getLog(WxRegionController.class);

    @Autowired
    private RegionService regionService;

    /**
     * 区域数据
     *
     * 根据父区域ID，返回子区域数据。
     * 如果父区域ID是0，则返回省级区域数据；
     *
     * @param pid 父区域ID
     * @return 区域数据
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data: xxx
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("list")
    public Object list(Long pid) {
        if(pid == null){
            return WxJson.badArgument();
        }

        //List<RegionEntity> regionList = regionService.queryByPid(pid);
        //根据父区域ID，返回子区域数据。
        List<RegionEntity> regionList = regionService.findListByProperty("pid", pid);
        return WxJson.ok(regionList);
    }


}