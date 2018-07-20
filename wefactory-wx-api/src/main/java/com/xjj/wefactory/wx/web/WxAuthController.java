package com.xjj.wefactory.wx.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xjj.framework.Constants;
import com.xjj.framework.utils.BCryptPasswordEncoder;
import com.xjj.framework.utils.JacksonUtil;
import com.xjj.framework.utils.RegexUtil;
import com.xjj.wefactory.customer.buyer.entity.BuyerEntity;
import com.xjj.wefactory.wx.json.WxJson;
import com.xjj.wefactory.wx.service.BuyerService;
import com.xjj.wefactory.wx.token.UserInfo;
import com.xjj.wefactory.wx.token.UserToken;
import com.xjj.wefactory.wx.token.UserTokenManager;
import com.xjj.wefactory.wx.token.WxLoginInfo;
import com.xjj.wefactory.wx.util.IpUtil;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import me.chanjar.weixin.common.error.WxErrorException;

@RestController
@RequestMapping("/wx/auth")
public class WxAuthController {
    @Autowired
    private BuyerService buyerService;

    @Autowired
    private WxMaService wxMaService;

    /**
     * 账号登录
     *
     * @param body 请求内容，{ username: xxx, password: xxx }
     * @param request 请求对象
     * @return 登录结果
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              token: xxx,
     *              tokenExpire: xxx,
     *              userInfo: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @RequestMapping("login")
    public Object login(@RequestBody String body, HttpServletRequest request) {
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");
        if(username == null || password == null){
            return WxJson.badArgument();
        }

        List<BuyerEntity> userList =buyerService.findListByProperty("username", username);
        BuyerEntity user = null;
        if(userList.size() > 1){
            return WxJson.serious();
        }
        else if(userList.size() == 0){
            return WxJson.badArgumentValue();
        }
        else {
            user = userList.get(0);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if(!encoder.matches(password, user.getPassword())){
            return WxJson.fail(403, "账号密码不对");
        }

        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(username);
        userInfo.setAvatarUrl(user.getAvatar());

        // token
        UserToken userToken = UserTokenManager.generateToken(user.getId());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", userInfo);
        return WxJson.ok(result);
    }

    /**
     * 微信登录
     *
     * @param wxLoginInfo 请求内容，{ code: xxx, userInfo: xxx }
     * @param request 请求对象
     * @return 登录结果
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              token: xxx,
     *              tokenExpire: xxx,
     *              userInfo: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @RequestMapping("login_by_weixin")
    public Object loginByWeixin(@RequestBody WxLoginInfo wxLoginInfo, HttpServletRequest request) {
        String code = wxLoginInfo.getCode();
        UserInfo userInfo = wxLoginInfo.getUserInfo();
        if(code == null || userInfo == null){
            return WxJson.badArgument();
        }

        String sessionKey = null;
        String openId = null;
        try {
            WxMaJscode2SessionResult result = this.wxMaService.getUserService().getSessionInfo(code);
            sessionKey = result.getSessionKey();
            openId = result.getOpenid();
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        if(sessionKey == null || openId == null){
            return WxJson.fail();
        }

        BuyerEntity user = buyerService.getByOpenId(openId);
        if(user == null){
            user = new BuyerEntity();
            user.setUsername(userInfo.getNickName());  // 其实没有用，因为用户没有真正注册
            user.setPassword(openId);                  // 其实没有用，因为用户没有真正注册
            user.setWxOpenid(openId);
            user.setAvatar(userInfo.getAvatarUrl());
            user.setNickname(userInfo.getNickName());
            user.setGender(String.valueOf(userInfo.getGender()));
            user.setUserLevel(String.valueOf(0));
            user.setStatus(Constants.COMMON_STATUS_VALID);
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.client(request));
            user.setAddTime(new Date());

            buyerService.save(user);
        }
        else{
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IpUtil.client(request));
            buyerService.update(user);
        }

        // token
        UserToken userToken = UserTokenManager.generateToken(user.getId());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", userInfo);
        return WxJson.ok(result);
    }

    /**
     * 账号注册
     *
     * @param body 请求内容
     *  {
     *      username: xxx,
     *      password: xxx,
     *      mobile: xxx
     *      code: xxx
     *  }
     *  其中code是手机验证码，目前还不支持手机短信验证码
     * @param request 请求对象
     * @return 登录结果
     *   成功则
     *  {
     *      code: 0,
     *      msg: '成功',
     *      data:
     *          {
     *              token: xxx,
     *              tokenExpire: xxx,
     *              userInfo: xxx
     *          }
     *  }
     *   失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("register")
    public Object register(@RequestBody String body, HttpServletRequest request) {
        String username = JacksonUtil.parseString(body, "username");
        String password = JacksonUtil.parseString(body, "password");
        String mobile = JacksonUtil.parseString(body, "mobile");
        String code = JacksonUtil.parseString(body, "code");
        Long sellerId = JacksonUtil.parseLong(body, "sellerId");
        
        
        System.out.println("sellerId=="+sellerId);
        System.out.println("body"+body);

        if(username == null || password == null || mobile == null || code == null){
            return WxJson.badArgument();
        }
        List<BuyerEntity> userList = buyerService.findListByProperty("username",username);
        if(userList.size() > 0){
            return WxJson.fail(403, "用户名已注册");
        }

        userList = buyerService.findListByProperty("mobile",mobile);
        if(userList.size() > 0){
            return WxJson.fail(403, "手机号已注册");
        }
        if(!RegexUtil.isMobileExact(mobile)){
            return WxJson.fail(403, "手机号格式不正确");
        }
        BuyerEntity user = new BuyerEntity();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);

        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setMobile(mobile);
        user.setWxOpenid("");
        user.setAvatar("http://127.0.0.1:8081/wefactory-wx-api/files/default/avatar-default.jpg");
        user.setNickname(username);
        user.setGender("0");
        user.setUserLevel("0");
        user.setStatus(Constants.COMMON_STATUS_VALID);
        user.setLastLoginTime(new Date());
        user.setLastLoginIp(IpUtil.client(request));
        user.setAddTime(new Date());
        user.setSellerId(sellerId);
        buyerService.save(user);


        // userInfo
        UserInfo userInfo = new UserInfo();
        userInfo.setNickName(username);
        userInfo.setAvatarUrl(user.getAvatar());

        // token
        UserToken userToken = UserTokenManager.generateToken(user.getId());

        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());
        result.put("userInfo", userInfo);
        
        
        
        System.out.println("user.getId()=="+user.getId());
        System.out.println("token=="+userToken.getToken());
        System.out.println("tokenExpire=="+userToken.getExpireTime());
        System.out.println("userInfo=="+userInfo);
        
        return WxJson.ok(result);
    }

    /**
     * 账号密码重置
     *
     * @param body 请求内容
     *  {
     *      password: xxx,
     *      mobile: xxx
     *      code: xxx
     *  }
     *  其中code是手机验证码，目前还不支持手机短信验证码
     * @param request 请求对象
     * @return 登录结果
     *   成功则 { code: 0, msg: '成功' }
     *   失败则 { code: XXX, msg: XXX }
     */
    @PostMapping("reset")
    public Object reset(@RequestBody String body, HttpServletRequest request) {
        String password = JacksonUtil.parseString(body, "password");
        String mobile = JacksonUtil.parseString(body, "mobile");
        String code = JacksonUtil.parseString(body, "code");

        if(mobile == null || code == null || password == null){
            return WxJson.badArgument();
        }

        List<BuyerEntity> userList = buyerService.findListByProperty("mobile",mobile);
        
        BuyerEntity user = null;
        if(userList.size() > 1){
            return WxJson.serious();
        }
        else if(userList.size() == 0){
            return WxJson.fail(403, "手机号未注册");
        }
        else{
            user = userList.get(0);
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode(password);
        user.setPassword(encodedPassword);

        buyerService.update(user);

        return WxJson.ok();
    }
}
