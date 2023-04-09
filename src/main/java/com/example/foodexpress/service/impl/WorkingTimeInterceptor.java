package com.example.foodexpress.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import java.time.LocalTime;

@Configuration
public class WorkingTimeInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler) throws Exception {

    var requestURI = request.getRequestURI();
    if (!requestURI.equals("/working-time")) {
      LocalTime now = LocalTime.now();
      if (now.getHour() <= 11) {
        response.sendRedirect("/working-time");
        return false;
      }
    }

    return HandlerInterceptor.super.preHandle(request, response, handler);
  }
}
