package com.cq.core.boot.security.base.jwt;

import com.cq.core.boot.security.utils.HandleHttpErrorUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException e) throws IOException {
        //response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        try {
            HandleHttpErrorUtil.handleHttpError(request, response, e);
        } catch (Exception throwable) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
        }
    }
}