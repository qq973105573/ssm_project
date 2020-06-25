package com.hwp.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器:
 * 1.编写拦截器类，实现拦截器的接口或者抽象类
 * 2.实现拦截器的拦截方法
 * 3.spring管理拦截器bean
 * 4.设置拦截规则和放行规则
 */
public class LoginInterceptor implements HandlerInterceptor {
    //方法执行前拦截

    /**
     * 为登录跳转到登录页
     * 登录用户放行
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser!=null){
            return true;//放行
        }

        response.sendRedirect(request.getContextPath()+"/login.html");//跳转到登录页面
        return false;
    }

    //方法执行后，视图解析器处理前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
    //返回页面前
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
