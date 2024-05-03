/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 18:44
 *  * @modified : 01/05/2024, 18:44
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.service;

import com.suivi.colis.delix.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    void deleteUser(Long id);
    User loadUserById(Long id);
    User saveUser(User user);
    User updateUser(User user);



}
