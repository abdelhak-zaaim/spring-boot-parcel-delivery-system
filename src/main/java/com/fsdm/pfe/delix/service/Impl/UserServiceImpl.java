
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

import com.fsdm.pfe.delix.entity.User;
import com.fsdm.pfe.delix.entity.notification.Notification;
import com.fsdm.pfe.delix.exception.UserNotFoundException;
import com.fsdm.pfe.delix.exception.personalizedexceptions.NotificationNotFoundException;
import com.fsdm.pfe.delix.repository.UserRepo;

import com.fsdm.pfe.delix.config.SecurityConfig;
import com.fsdm.pfe.delix.repository.notification.NotificationRepo;
import com.fsdm.pfe.delix.service.UserService;
import com.fsdm.pfe.delix.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepository;
    private final NotificationRepo notificationRepository;

    private final EmailServiceImpl emailService;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserRepo userRepository, NotificationRepo notificationRepository, EmailServiceImpl emailService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    @Override
    public User loadUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }




    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
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

public Collection<User> loadAllUsers() {
        return userRepository.findAll();
    }


    public void updateUserEmail(Long userId, String newEmail) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        user.setEmail(newEmail);
        userRepository.save(user);
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }


    public void sendEmailVerification(String email, String verificationToken, String baseUrl) {

        String verificationLink = baseUrl + "/verify?token=" + verificationToken;

        emailService.sendSimpleMessage(email, "Please verify your email",
                "Click the following link to verify your email: " + verificationLink);

    }

    public void sendPasswordResetEmail(String email, String resetToken, String baseUrl) {

        String resetLink = baseUrl + "/reset-password?token=" + resetToken;

        emailService.sendSimpleMessage(email, "Password reset",
                "Click the following link to reset your password: " + resetLink);

    }

    public void addNotificationToUser(Long userId, Notification notification) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        user.addNotification(notification);
        userRepository.save(user);
    }


    public void addNotificationToAllUsers(Notification notification) {
        List<User> users = userRepository.findAll();
        users.forEach(user -> user.addNotification(notification));
        userRepository.saveAll(users);
    }

    public void deleteNotificationFromUser(Long userId, Long notificationId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User with id " + userId + " not found"));

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationNotFoundException("Notification with id " + notificationId + " not found"));

        user.getNotifications().remove(notification);
        notificationRepository.delete(notification);
        userRepository.save(user);
    }
}


