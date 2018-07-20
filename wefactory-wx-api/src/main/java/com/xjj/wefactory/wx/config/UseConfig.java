package com.xjj.wefactory.wx.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.xjj.wefactory.wx.annotation.support.LoginUserHandlerMethodArgumentResolver;

import java.util.List;

@Configuration
public class UseConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    	super.addArgumentResolvers(argumentResolvers);
        argumentResolvers.add(new LoginUserHandlerMethodArgumentResolver());
    }
}
