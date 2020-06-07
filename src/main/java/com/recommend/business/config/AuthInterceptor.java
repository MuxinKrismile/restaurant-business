package com.recommend.business.config;

import com.alibaba.fastjson.JSONObject;
import com.recommend.business.bean.AuthResponse;
import com.recommend.business.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@Component
public class AuthInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthService authService;

    private void returnJson(HttpServletResponse response, String json) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        try (PrintWriter writer = response.getWriter()) {
            writer.print(json);
        } catch (IOException e) {
            logger.error("response error", e);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = request.getHeader("token");
        //System.out.println(token);
        if (token == null || this.authService.auth(token).getCode() != 200) {
            AuthResponse authResponse = new AuthResponse();
            authResponse.setCode(500);
            authResponse.setMsg("invalid token");
            String jsonObjectStr = JSONObject.toJSONString(authResponse);
            returnJson(response, jsonObjectStr);
            return false;
        }

        return true;
    }
}
