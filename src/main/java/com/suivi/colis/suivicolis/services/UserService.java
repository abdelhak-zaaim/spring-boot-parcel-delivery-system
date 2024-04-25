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

import com.suivi.colis.suivicolis.models.User;
import com.suivi.colis.suivicolis.repositorys.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {
    @Autowired
    private  UserRepo userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User appUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
        String username = appUser.getUsername();
        String password = appUser.getPassword();
        Collection<String> authorities = appUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Set<GrantedAuthority> guthauthoritys = new HashSet<>();



//        authorities.forEach(role -> {
//            GrantedAuthority ahth = new SimpleGrantedAuthority(role);
//            guthauthoritys.add(ahth);
//        });
//
        guthauthoritys= authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());


        return new org.springframework.security.core.userdetails.User(username, password, guthauthoritys);

//        Optional<User> user = userRepository.findByEmail(email);
//        if (user.isPresent()) {
//            var userObj = user.get();
//            return org.springframework.security.core.userdetails.User.builder()
//                    .username(userObj.getUsername())
//                    .password(userObj.getPassword())
//                    .roles(getRoles(userObj))
//                    .build();
//        } else {
//            throw new UsernameNotFoundException(email);
//        }
    }
    private String[] getRoles(User user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }

}
