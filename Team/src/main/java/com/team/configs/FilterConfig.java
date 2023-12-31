package com.team.configs;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Configuration;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;


@Configuration
public class FilterConfig implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        String ip = req.getRemoteAddr();
        String header = req.getHeader("user-agent");
        Enumeration<String> enumeration= req.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String key = enumeration.nextElement();
            System.out.println("key  :: "+key);
        }

        System.out.println("This line call");
        String url = req.getRequestURI();
        System.out.println("URL :: " + url);
        String[] freeUrls = {"/player/login","/player/register","/player/deleteAllPlayer","/team/deleteAllTeamPlayer","/team/teamCreate","/team/teamYedekCreate"};

        boolean loginStatus = true;
        for (String item : freeUrls) {
            if (url.equals(item)) {
                loginStatus = false;
                break;
            }
        }

        if (loginStatus) {
            // session control
            boolean status = req.getSession().getAttribute("player") == null;
            if (status) {
                PrintWriter printWriter = res.getWriter();
                String json = "{ \"status\": false, \"result\": \"Please Login\" }";
                printWriter.println(json);
                res.setContentType("application/json");
                res.setStatus(401);

            } else {
                try {
                    filterChain.doFilter(req, res);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
            }

        } else {
            filterChain.doFilter(req, res);
        }

        System.out.println("url :: "+url + "  ip :: "+ip+"  header :: "+header+"");
    }
}
