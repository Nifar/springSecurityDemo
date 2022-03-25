package com.example.springsecuritydemo.config;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    private static final String LOGIN = "user";
    private static final String PASSWORD = "password";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        if (request.getHeader("Authorization") == null || !request.getHeader("Authorization").startsWith("Basic")) {
//            response.getOutputStream().println("You don't have authorization");
//            return;
//        }
//
//        String loginAndPass = request.getHeader("Authorization");
//        String[] creditals = new String(Base64Coder.decode(loginAndPass.split(" ")[1])).split(":");
//
//        if (!creditals[0].equals(LOGIN) || !creditals[1].equals(PASSWORD)){
//            response.getOutputStream().println("Log or pass in not valid");
//            return;
//        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
