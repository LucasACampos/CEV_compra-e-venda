package br.com.cev.request.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.UUID;

@Component
public class RequestIDFilter extends OncePerRequestFilter {

    public static final String REQUEST_ID_HEADER_NAME = "X-Request-ID";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String requestId = UUID.randomUUID().toString();

        MDC.put(REQUEST_ID_HEADER_NAME, requestId);

        try {
            response.setHeader(REQUEST_ID_HEADER_NAME, requestId);
            filterChain.doFilter(request, response);
        }finally {
            MDC.remove(REQUEST_ID_HEADER_NAME);
        }

    }
}
