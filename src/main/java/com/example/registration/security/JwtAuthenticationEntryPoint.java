package com.example.registration.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("delegatedAuthenticationEntryPoint")
public class JwtAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

  @Autowired
  @Qualifier("handlerExceptionResolver")
  private HandlerExceptionResolver resolver;

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
      throws IOException {
    // response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    // PrintWriter writer = response.getWriter();
    // writer.println("HTTP Status 401 - " + authEx.getMessage());
    resolver.resolveException(request, response, null, authEx);
  }

  @Override
  public void afterPropertiesSet() {
    setRealmName("YOUR REALM");
    super.afterPropertiesSet();
  }
}