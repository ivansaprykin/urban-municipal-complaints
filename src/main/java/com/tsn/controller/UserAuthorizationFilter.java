package com.tsn.controller;

import com.tsn.util.UserDetails;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserAuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        UserDetails userDetails = (UserDetails) session.getAttribute("user");

        if(userDetails != null) {
            boolean userHasRoleAdmin = userDetails.getUserRole().equals("Admin");
            boolean userHasRoleUser = userDetails.getUserRole().equals("User");

            if(userHasRoleAdmin || userHasRoleUser) {
                chain.doFilter(request, response);
            }
        } else {
            // отправляем на страницу логина
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return; //break filter chain, requested JSP/servlet will not be executed
        }


        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
