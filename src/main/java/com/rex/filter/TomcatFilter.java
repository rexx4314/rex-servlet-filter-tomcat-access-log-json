package com.rex.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class TomcatFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TenantUserFilter initialized");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;

            String tenantId = "rex-tenant-id";
            String userId = "rex-user-id";

            httpRequest.setAttribute("tenantId", tenantId);
            httpRequest.setAttribute("userId", userId);

            System.out.println("Added tenantId and userId to ThreadContext and request attributes");
        }

        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            System.out.println("Filter execution failed");
            throw e;
        }
    }

    @Override
    public void destroy() {
        System.out.println("TenantUserFilter destroyed");
    }
}
