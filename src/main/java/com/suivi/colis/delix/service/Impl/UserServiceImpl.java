
/*
 * **
 *  * @project : DeliX
 *  * @created : 24/04/2024, 16:34
 *  * @modified : 24/04/2024, 16:34
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.delix.service.Impl;

import com.suivi.colis.delix.entity.User;
import com.suivi.colis.delix.repository.UserRepo;

import com.suivi.colis.delix.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo userRepository;

    public UserServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
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
        return null;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found:" + email));
        return user.get();

    }


}

