package com.jiadao.config;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiadao.util.User;
import com.jiadao.util.UserUtil;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

/**
 * token解析器，解析后放入线程本地变量中，以便后续操作使用
 */
@Slf4j
public class JWTTokenInterceptor implements HandlerInterceptor {
    //该方法的返回值决定后续的方法是否执行(controller中的方法)
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("preHandle");
        //获取controller类名称
        // System.out.println(((HandlerMethod) o).getBean().getClass().getName());
        //获取执行method的名字
        // System.out.println(((HandlerMethod)o).getMethod().getName());
        String uid = httpServletRequest.getHeader("uid");
        String name = httpServletRequest.getHeader("name");
        String dept = httpServletRequest.getHeader("dept");
        String depts = httpServletRequest.getHeader("depts");
        String roles = httpServletRequest.getHeader("roles");
        log.info("uid= {}",uid);
        User user = new User(uid,name,dept,depts,roles);
        UserUtil.set(user);
        httpServletRequest.setAttribute("startTime", new Date().getTime());
        return true;
    }

    //当controller抛出异常时，该方法不会被调用
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
        // long start = (Long) httpServletRequest.getAttribute("startTime");
        // System.out.println("time interceptor耗时" + (new Date().getTime() - start));
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
        long start = (Long) httpServletRequest.getAttribute("startTime");
        System.out.println("time interceptor耗时" + (new Date().getTime() - start));
        //当无异常的时候，e的值为null，有自定义异常时，也会为Null
        System.out.println(e);
    }
}