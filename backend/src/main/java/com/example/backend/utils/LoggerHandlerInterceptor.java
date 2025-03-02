package com.example.backend.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerHandlerInterceptor implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(LoggerHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String message = String.format(
                "[%s] %-4s %s",
                request.getLocalAddr(),
                request.getMethod(),
                request.getRequestURI()
        );
        logger.debug(message);
        return true;
    }
}
