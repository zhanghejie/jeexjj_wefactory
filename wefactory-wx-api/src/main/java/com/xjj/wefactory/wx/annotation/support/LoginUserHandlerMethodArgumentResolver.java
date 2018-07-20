package com.xjj.wefactory.wx.annotation.support;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.xjj.wefactory.wx.annotation.LoginUser;
import com.xjj.wefactory.wx.token.UserTokenManager;


public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    public static final String LOGIN_TOKEN_KEY = "wx-jeexjj-token";
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Long.class)&&parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        if(token == null || token.isEmpty()){
            return null;
        }
        Long userId = UserTokenManager.getUserId(token);
        return userId;
    }
}
