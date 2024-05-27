/*
 *
 *  * @project : DeliX
 *  * @created : 27/05/2024, 13:38
 *  * @modified : 27/05/2024, 13:38
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.config;

import com.fsdm.pfe.delix.service.Impl.LoginAttemptServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class DelixAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private final LoginAttemptServiceImpl loginAttemptService;

    public DelixAuthenticationFailureHandler(LoginAttemptServiceImpl loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        loginAttemptService.loginFailed(request.getRemoteAddr());

    }
}
