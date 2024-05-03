
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

import com.suivi.colis.delix.service.AdminEmployeeService;
import org.springframework.stereotype.Service;


@Service
public class AdminEmployeeServiceImpl implements AdminEmployeeService {

    private final AdminEmployeeRepo adminEmployeeRepo;

    public AdminEmployeeServiceImpl(AdminEmployeeRepo adminRepo) {
        this.adminEmployeeRepo = adminRepo;
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
        return adminEmployeeRepo.save(admin);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        return adminEmployeeRepo.save(admin);
    }
}
