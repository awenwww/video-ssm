package com.qf.interceptor;

import com.qf.pojo.Admin;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AfterInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin!=null){
            return true;
        }else{
            response.sendRedirect(request.getContextPath()+"/afterAdmin/toLogin");
        }
        return false;
    }
}
