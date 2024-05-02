/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 18:30
 *  * @modified : 27/04/2024, 18:29
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 17:54
 *  * @modified : 27/04/2024, 17:54
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.securingweb;

import com.suivi.colis.suivicolis.entity.User;
import com.suivi.colis.suivicolis.service.Impl.UserServiceImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final UserServiceImpl userService;


    public MyUserDetailsService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> user = userService.getUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found:" + email));
        return new MyUserDetails(user.get());

    }
}
