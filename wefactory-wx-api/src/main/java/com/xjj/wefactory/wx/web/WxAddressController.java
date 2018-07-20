package com.xjj.wefactory.wx.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.xjj.framework.utils.RegexUtil;
import com.xjj.wefactory.customer.address.entity.AddressEntity;
import com.xjj.wefactory.wx.annotation.LoginUser;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.AddressService;
import com.xjj.wefactory.wx.service.RegionService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx/address")
public class WxAddressController {
    private final Log logger = LogFactory.getLog(WxAddressController.class);

    @Autowired
    private AddressService addressService;
    @Autowired
    private RegionService regionService;

    /**
     * 用户收货地址列表
     *
     * @param userId 用户ID
     * @return 收货地址列表
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data: xxx
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("list")
    public Object list(@LoginUser Long userId) {
        if(userId == null){
            return WxJson.unlogin();
        }
        List<AddressEntity> addressList = addressService.findListByCustomerId(userId);
        List<Map<String, Object>> addressVoList = new ArrayList<>(addressList.size());
        for(AddressEntity address : addressList){
            Map<String, Object> addressVo = new HashMap<>();
            addressVo.put("id", address.getId());
            addressVo.put("name", address.getName());
            addressVo.put("mobile", address.getMobile());
            addressVo.put("isDefault", address.getIsDefault());
            String province = regionService.getById(address.getProvinceId()).getName();
            String city = regionService.getById(address.getCityId()).getName();
            String area = regionService.getById(address.getAreaId()).getName();
            String addr = address.getAddress();
            String detailedAddress = province + city + area + " " + addr;
            addressVo.put("detailedAddress", detailedAddress);

            addressVoList.add(addressVo);
        }
        return WxJson.ok(addressVoList);
    }

    /**
     * 收货地址详情
     *
     * @param userId 用户ID
     * @param id 收获地址ID
     * @return 收货地址详情
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *        {
     *           id: xxx,
     *           name: xxx,
     *           provinceId: xxx,
     *           cityId: xxx,
     *           districtId: xxx,
     *           mobile: xxx,
     *           address: xxx,
     *           isDefault: xxx,
     *           provinceName: xxx,
     *           cityName: xxx,
     *           areaName: xxx
     *        }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @GetMapping("detail")
    public Object detail(@LoginUser Long userId, Long id) {
        if(userId == null){
            return WxJson.unlogin();
        }
        if(id == null){
            return WxJson.badArgument();
        }

        AddressEntity address = addressService.getById(id);
        if(address == null){
            return WxJson.badArgumentValue();
        }

        Map<Object, Object> data = new HashMap<Object, Object>();
        data.put("id", address.getId());
        data.put("name", address.getName());
        data.put("provinceId", address.getProvinceId());
        data.put("cityId", address.getCityId());
        data.put("districtId", address.getAreaId());
        data.put("mobile", address.getMobile());
        data.put("address", address.getAddress());
        data.put("isDefault", address.getIsDefault());
        String pname = regionService.getById(address.getProvinceId()).getName();
        data.put("provinceName", pname);
        String cname = regionService.getById(address.getCityId()).getName();
        data.put("cityName", cname);
        String dname = regionService.getById(address.getAreaId()).getName();
        data.put("areaName", dname);
        return WxJson.ok(data);
    }

    /**
     * 添加或更新收货地址
     *
     * @param userId 用户ID
     * @param address 用户收货地址
     * @return 添加或更新操作结果
     *   成功则 { code: 0, msg: '成功' }
     *   失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("save")
    public Object save(@LoginUser Long userId, @RequestBody AddressEntity address) {
        if(userId == null){
            return WxJson.unlogin();
        }
        if(address == null){
            return WxJson.badArgument();
        }
        
        // 测试收货手机号码是否正确
        String mobile = address.getMobile();
        if(!RegexUtil.isMobileExact(mobile)){
            return WxJson.badArgument();
        }

        if(null != address.getIsDefault() && address.getIsDefault()){
            // 重置其他收获地址的默认选项
            addressService.resetDefault(userId);
        }

        if (address.isNew()) {
            address.setId(null);
            address.setCustomerId(userId);
            
            addressService.save(address);
        } else {
            address.setCustomerId(userId);
            addressService.update(address);
        }
        return WxJson.ok(address.getId());
    }

    /**
     * 删除收货地址
     *
     * @param userId 用户ID
     * @param address 用户收货地址
     * @return 删除结果
     *   成功则 { code: 0, msg: '成功' }
     *   失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("delete")
    public Object delete(@LoginUser Integer userId, @RequestBody AddressEntity address) {
        if(userId == null){
            return WxJson.unlogin();
        }
        if(address == null){
            return WxJson.badArgument();
        }

        addressService.delete(address.getId());
        return WxJson.ok();
    }
}