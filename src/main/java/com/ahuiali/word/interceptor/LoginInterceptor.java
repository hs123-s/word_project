package com.ahuiali.word.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();



        if( session.getAttribute("learnerId")!=null){

            return true;
        }else
        {
            response.sendRedirect("/learner/gotoLogin");
        }
        return false;
    }
}
