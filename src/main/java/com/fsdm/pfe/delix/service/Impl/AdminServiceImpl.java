
/*
 * *
 *  * @project : DeliX
 *  * @created : 25/04/2024, 14:59
 *  * @modified : 25/04/2024, 14:59
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.service.Impl;

import com.fsdm.pfe.delix.entity.Admin;

import com.fsdm.pfe.delix.repository.AdminRepo;
import com.fsdm.pfe.delix.service.AdminService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AdminServiceImpl implements AdminService , UserDetailsService {

    private final AdminRepo adminRepo;
    private final UserServiceImpl userService;

    public AdminServiceImpl(AdminRepo adminRepo, UserServiceImpl userService) {
        this.adminRepo = adminRepo;
        this.userService = userService;
    }


    @Override
    public void deleteAdmin(Long id) {
        adminRepo.deleteById(id);
    }

    @Override
    public Admin loadAdminById(Long id) {
        return adminRepo.findById(id).orElse(null);
    }

    public Admin saveAdmin(Admin admin) {
        admin.setPassword(userService.encodePassword(admin.getPassword()));
        return adminRepo.save(admin);
    }

    /**
     * this function used for updating the admin user
     * @since  do not use this function for updating the password !!!!
     * @deprecated
     * @param admin : Admin entity
     * @return Admin entity if the updating successfully , else : this function throw an exception
     */
    @Override
    public Admin updateAdmin(Admin admin) {
        return adminRepo.save(admin);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepo.findByEmail(email);
        admin.orElseThrow(() -> new UsernameNotFoundException("User not found:" + email));
        return admin.get();
    }



    public Admin loadUserByEmail(String email) {
        Optional<Admin> admin = adminRepo.findByEmail(email);
        return admin.orElse(null);
    }
}
