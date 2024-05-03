
/*
 * *
 *  * @project : DeliX
 *  * @created : 25/04/2024, 14:59
 *  * @modified : 25/04/2024, 14:59
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.suivi.colis.delix.service.Impl;

import com.suivi.colis.delix.entity.Admin;
import com.suivi.colis.delix.repository.AdminEmployeeRepo;

import com.suivi.colis.delix.service.AdminService;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

    private final AdminEmployeeRepo adminEmployeeRepo;
    private final UserServiceImpl userService;

    public AdminServiceImpl(AdminEmployeeRepo adminRepo, UserServiceImpl userService) {
        this.adminEmployeeRepo = adminRepo;
        this.userService = userService;
    }


    @Override
    public void deleteAdmin(Long id) {
        adminEmployeeRepo.deleteById(id);
    }

    @Override
    public Admin loadAdminById(Long id) {
        return adminEmployeeRepo.findById(id).orElse(null);
    }

    public Admin saveAdmin(Admin admin) {
        admin.setPassword(userService.encodePassword(admin.getPassword()));
        return adminEmployeeRepo.save(admin);
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
        return adminEmployeeRepo.save(admin);
    }
}
