package com.viheakode.electronic_store.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");

        String path = (String) request.getAttribute("javax.servlet.error.request_uri");
        if (path == null) {
            path = request.getRequestURI();
        }

        String json = """
                {
                  "status": 403,
                  "error": "Forbidden",
                  "message": "%s",
                  "path": "%s"
                }
                """.formatted(accessDeniedException.getMessage(), path);

        response.getWriter().write(json);
    }
}
