package com.netcracker.edu.sat.stub.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestLogFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger("requestLogger");
    private String nodeId = Integer.toHexString((int)(Math.random() * 256)) + "-";
    private AtomicInteger requestId = new AtomicInteger(0);

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        RequestWrapper wrappedRequest = new RequestWrapper((HttpServletRequest) request);
        try {
            Thread.currentThread().setName(nodeId + requestId.incrementAndGet());
            logger.info(wrappedRequest.getRemoteAddr() + " " + wrappedRequest.getMethod() + " " + wrappedRequest.getRequestURI() + " " + wrappedRequest.getContent());
            filterChain.doFilter(wrappedRequest, response);

        }
        catch (Throwable e) {
            logger.error("", e);
        }
    }

    public void destroy() {
    }
}