package com.example.simpleMall.Interceptor;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : HaiZhou Yuan
 * @mailto : abelyuan0822@gmail.com
 * @created : 9/22/2022, Thursday
 **/
@Component
public class LoginInterceptor implements HandlerInterceptor {

    Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/admin") && null == request.getSession().getAttribute("adminId")) {
            request.getSession().setAttribute("errorMsg", "please log in");
            String redirectTo = request.getRequestURI() + "?" + request.getQueryString();
            if(null==request.getRequestURI()) {
                redirectTo = null;
            }
            request.getSession().setAttribute("redirectTo",redirectTo);

            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        } else if (requestURI.startsWith("/customer") && null == request.getSession().getAttribute("customerId")) {
            request.getSession().setAttribute("errorMsg", "please log in");
            response.sendRedirect(request.getContextPath() + "/customer/login");
            return false;
        } else if (requestURI.startsWith("/download") && null == request.getSession().getAttribute("customerId")) {
            request.getSession().setAttribute("errorMsg", "please log in");
            response.sendRedirect(request.getContextPath() + "/customer/login");
            return false;
        }else {
            request.getSession().removeAttribute("errorMsg");
            return true;
        }

    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
