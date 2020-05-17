package com.jiadao.config;

import com.jiadao.anno.CurrentUser;
import com.jiadao.util.UserUtil;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import lombok.extern.slf4j.Slf4j;

/**
 * 方法参数解析器
 * 为每个controller方法自动注入当前用户对象
 */
@Slf4j
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer modelAndView, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        // Object currentUserInfo = webRequest.getAttribute("currentUser", 0);
        // if(null == currentUserInfo) log.warn("---------------用户未登录---------------");
        // return currentUserInfo;
        return UserUtil.get();
    }

}