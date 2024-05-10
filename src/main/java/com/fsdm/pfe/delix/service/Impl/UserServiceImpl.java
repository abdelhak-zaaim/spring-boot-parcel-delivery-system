
/*
 * **
 *  * @project : DeliX
 *  * @created : 24/04/2024, 16:34
 *  * @modified : 24/04/2024, 16:34
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.entity.Customer;
import com.fsdm.pfe.delix.entity.VerificationToken;
import com.fsdm.pfe.delix.entity.User;
import com.fsdm.pfe.delix.exception.UserNotFoundException;
import com.fsdm.pfe.delix.repository.UserRepo;

import com.fsdm.pfe.delix.securingweb.SecurityConfig;
import com.fsdm.pfe.delix.service.UserService;
import com.fsdm.pfe.delix.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepo userRepository;
    private final SecurityConfig webSecurityConfig;
    private final EmailServiceImpl emailService;

    public UserServiceImpl(UserRepo userRepository, SecurityConfig webSecurityConfig, EmailServiceImpl emailService) {
        this.userRepository = userRepository;
        this.webSecurityConfig = webSecurityConfig;
        this.emailService = emailService;
    }

    @Override
    public User loadUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public boolean existsUserByEmail(String email) {
        return userRepository.existsUserByEmail(email);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public void updateUserEmail(Long userId, String newEmail) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        user.setEmail(newEmail);
        userRepository.save(user);
    }

    public String encodePassword(String password) {
        return SecurityConfig.passwordEncoder().encode(password);
    }


    public void sendEmailVerificationToken(String email, String verificationToken) {

        String verificationLink = Constants.BASE_URL + "/verify?token=" + verificationToken;

        emailService.sendSimpleMessage(email, "Please verify your email",
                "Click the following link to verify your email: " + verificationLink);

    }
}


