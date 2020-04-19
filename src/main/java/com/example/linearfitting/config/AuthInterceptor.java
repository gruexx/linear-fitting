package com.example.linearfitting.config;

import com.example.linearfitting.entity.base.Auth;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 */
@Slf4j
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String url = request.getRequestURI();
        log.info("|url:{}|^_^|preHandle", url);

        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            Auth auth = method.getMethodAnnotation(Auth.class);

            String overAuth = request.getParameter("overAuth");
            if (StringUtils.isNotBlank(overAuth) && String.valueOf(1).equals(overAuth)) {
                return true;
            }
            if (auth != null) {
                String token = getTokenFromKey(request.getCookies(), "token");
                log.info("|token:{}|^_^|preHandle", token);
                // 从Header里面获取token
                if (!token.isEmpty()) {
                    log.info(token);
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    private String getTokenFromKey(Cookie[] cookies, String key) {
        if (cookies == null || cookies.length == 0) {
            return null;
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(key)) {
                return cookie.getValue();
            }
        }
        return null;
    }
}
