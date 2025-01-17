package com.openclassrooms.Openclassrooms_FS_P13_POC.config;


import java.io.IOException;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.openclassrooms.Openclassrooms_FS_P13_POC.utils.JwtUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String requestUri = request.getRequestURI();
        
        // Allow WebSocket connections without authentication
        if (requestUri.startsWith("/ws")) {
            System.out.println("Recieving WS connection");
            filterChain.doFilter(request, response);
            return;
        }
        
        Authentication authentication = JwtUtils.getAuthentication(JwtUtils.extractToken(request));
        if (request.getHeader("Authorization") == null) {
            System.out.println("BLOCKED");
            filterChain.doFilter(request, response);
            return;
        } else if (JwtUtils.isTokenValid(authentication)) {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);
        } else {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write("Unauthorized access");
        }
    }
}
