/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 18:51
 *  * @modified : 25/04/2024, 18:39
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 18:39
 *  * @modified : 25/04/2024, 15:38
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 24/04/2024, 16:34
 *  * @modified : 24/04/2024, 16:34
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *  **
 */

package com.suivi.colis.suivicolis.services;

import com.suivi.colis.suivicolis.entities.User;
import com.suivi.colis.suivicolis.models.enums.Privilege;
import com.suivi.colis.suivicolis.models.enums.Role;
import com.suivi.colis.suivicolis.repositorys.UserRepo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService {

    private final UserRepo userRepository;

    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Optional<User> getUserByEmail(String email) {
        System.out.println("email: " + email);
        Optional<User> user = userRepository.findByEmail(email);

        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}


