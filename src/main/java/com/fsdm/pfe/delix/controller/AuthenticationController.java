/*
 * **
 *  * @project : DeliX
 *  * @created : 24/04/2024, 17:08
 *  * @modified : 24/04/2024, 17:08
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.controller;

import com.fsdm.pfe.delix.dto.request.RegisterRequestDto;
import com.fsdm.pfe.delix.dto.request.ResetPasswordEmailRequestDto;
import com.fsdm.pfe.delix.dto.request.ResetPasswordRequestDto;
import com.fsdm.pfe.delix.dto.response.LoginResponseDto;
import com.fsdm.pfe.delix.dto.response.MessageDto;
import com.fsdm.pfe.delix.dto.response.ResponseDataDto;
import com.fsdm.pfe.delix.entity.Customer;
import com.fsdm.pfe.delix.service.Impl.PasswordResetTokenServiceImpl;
import com.fsdm.pfe.delix.service.Impl.UserServiceImpl;
import com.fsdm.pfe.delix.util.Constants;
import com.fsdm.pfe.delix.util.helpers.HttpUtils;
import com.fsdm.pfe.delix.exception.personalizedexceptions.UserRegistrationException;
import com.fsdm.pfe.delix.service.Impl.CustomerServiceImpl;
import com.fsdm.pfe.delix.service.Impl.LoginLogServiceImpl;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import com.fsdm.pfe.delix.entity.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class AuthenticationController {
    private final CustomerServiceImpl customerService;
    private final LoginLogServiceImpl loginLogService;

    private final Validator validator;
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();
    private final PasswordResetTokenServiceImpl passwordResetTokenService;
    private final UserServiceImpl userServiceImpl;

    public AuthenticationController(CustomerServiceImpl customerService, LoginLogServiceImpl loginLogService, Validator validator, @Qualifier("authenticationManagerUser") AuthenticationManager authenticationManager, PasswordResetTokenServiceImpl passwordResetTokenService, UserServiceImpl userServiceImpl) {
        this.customerService = customerService;
        this.loginLogService = loginLogService;
        this.validator = validator;

        this.authenticationManager = authenticationManager;
        this.passwordResetTokenService = passwordResetTokenService;
        this.userServiceImpl = userServiceImpl;
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginCustomer(@RequestBody LoginRequest loginRequest, HttpServletRequest request, HttpServletResponse response) {
        try {
            // Create an authentication request using the provided username and password
            Authentication authenticationRequest =
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password());

            // Attempt to authenticate the user
            Authentication authenticationResponse =
                    this.authenticationManager.authenticate(authenticationRequest);

            SecurityContext context = SecurityContextHolder.createEmptyContext();
            context.setAuthentication(authenticationResponse);
            securityContextRepository.saveContext(context, request, response);


            if (authenticationResponse.isAuthenticated()) {
                User user = (User) authenticationResponse.getPrincipal();


                if (!user.isEmailVerified()) {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDto(false, false, "Email non vérifié", "Email non vérifié, veuillez vérifier votre email"));
                }

                String ipAddress = request.getHeader("X-Forwarded-For");
                if (ipAddress == null) {
                    ipAddress = request.getRemoteAddr();
                }

                // Save login log
                loginLogService.saveLoginLog((User) authenticationResponse.getPrincipal(), request.getHeader("User-Agent"), ipAddress, true, "login");
            }

            return ResponseEntity.ok(new LoginResponseDto(true, authenticationResponse.isAuthenticated(), null, "Login successful"));


        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDto(false, false, e.getMessage(), e.getMessage()));
        } catch (BadCredentialsException e) {
            // Handle incorrect password
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDto(false, false, e.getMessage(), "Incorrect Email or Password"));
        } catch (AuthenticationException e) {
            // Handle other authentication failures
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDto(false, false, e.getMessage(), e.getMessage()));
        }
    }

    public record LoginRequest(String username, String password) {
    }

    @GetMapping("/login")
    public String loginPage() {
        return "home/login";
    }


    @GetMapping("/register")
    public String registerPage() {
        return "home/register";
    }

    @PostMapping("/register")
    public ResponseEntity<MessageDto> registerAccount(@Valid RegisterRequestDto registerRequestDto, HttpServletRequest request) {

        try {
            String baseUrl;
            try {
                baseUrl = HttpUtils.getServerUrl(request);
            } catch (Exception e) {
                baseUrl = Constants.BASE_URL;
            }


            Customer customer = customerService.registerCustomer(registerRequestDto, baseUrl);

            MessageDto messageDto = new MessageDto(200, "Account created successfully , please check your email to verify your account");
            messageDto.setData(customer);
            return ResponseEntity.ok(messageDto);
        } catch (UserRegistrationException e) {
            return ResponseEntity.badRequest().body(new MessageDto(300, e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new MessageDto(300, "An error occurred, please try again"));
        }
    }


    @GetMapping("/verify")
    public String verifyEmail(@RequestParam String token) {
        if (token == null || token.isEmpty()) {
            return "/home/index";
        }
        if (customerService.verifyEmail(token)) {
            return "home/verified";
        }

        return "home/index";
    }


    @GetMapping("/test/index")
    public String index() {
        return "home/index_if_login";
    }


    @GetMapping("/forbidden-page")
    public String forbiddenPage() {
        return "home/forbiddenPage";  // This should be the name of your forbidden page view
    }


    @GetMapping("/reset-password")
    public String resetPasswordPage(Model model, @RequestParam(required = false) String token) {
        if (token == null || token.isEmpty()) {
            return "home/resetPassword";
        }

        if (!userServiceImpl.existsUserByResetToken(token)) {

            return "home/invalidToken";
        } else if (passwordResetTokenService.isExpiredByToken(token)) {
            passwordResetTokenService.deletePasswordResetToken(token);
            return "home/invalidToken";
        }


        model.addAttribute("token", token);
        return "home/resetPassword";
    }


    @PostMapping("/reset-password-request")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordEmailRequestDto resetPasswordRequestDto, HttpServletRequest request) {
        DataBinder binder = new DataBinder(resetPasswordRequestDto);
        binder.setValidator(validator);
        binder.validate();
        BindingResult result = binder.getBindingResult();
        if (result.hasErrors()) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error(result.getAllErrors()).message("please verify the inputs").build());
        }

        try {
            String baseUrl;
            try {
                baseUrl = HttpUtils.getServerUrl(request);
            } catch (Exception e) {
                baseUrl = Constants.BASE_URL;
            }
            String operatingSystemName= ""; String browserName = "";
            try {
                String userAgentHeader = request.getHeader("User-Agent");
                UserAgent userAgent = UserAgent.parseUserAgentString(userAgentHeader);
                OperatingSystem operatingSystem = userAgent.getOperatingSystem();
                Browser browser = userAgent.getBrowser();

                 operatingSystemName = operatingSystem.getName();
                 browserName = browser.getName();

            }catch (Exception ignored){

            }


            userServiceImpl.resetPassword(resetPasswordRequestDto.getEmail(), baseUrl, operatingSystemName, browserName);

            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(true).error(null).message("Password reset link sent to your email").build());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.badRequest().body(ResponseDataDto.builder().data(null).success(false).error(e.getMessage()).message(e.getMessage()).build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDataDto.builder().data(null).success(false).error(e.getMessage()).message(e.getMessage()).build());
        }

    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPasswordPost(@RequestBody ResetPasswordRequestDto resetPasswordRequestDto) {

        System.out.println("resetPasswordRequestDto = " + resetPasswordRequestDto);

        DataBinder binder = new DataBinder(resetPasswordRequestDto);
        binder.setValidator(validator);
        binder.validate();
        BindingResult result = binder.getBindingResult();
        if (result.hasErrors()) {
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(false).error(result.getAllErrors()).message("please verify the inputs").build());
        }

        try {
            userServiceImpl.resetPasswordByToken(resetPasswordRequestDto.getToken(), resetPasswordRequestDto.getPassword(), resetPasswordRequestDto.getConfirmPassword());
            return ResponseEntity.ok(ResponseDataDto.builder().data(null).success(true).error(null).message("Password reset successfully").build());
        } catch (ResponseStatusException e) {
            return ResponseEntity.badRequest().body(ResponseDataDto.builder().data(null).success(false).error(e.getMessage()).message(e.getReason()).build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(ResponseDataDto.builder().data(null).success(false).error(e.getMessage()).message(e.getMessage()).build());
        }

    }
}
