
/*
 * *
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 14:59
 *  * @modified : 25/04/2024, 14:59
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *
 */

package com.suivi.colis.suivicolis.service.Impl;

import com.suivi.colis.suivicolis.entity.AdminEmployee;
import com.suivi.colis.suivicolis.repository.AdminRepo;

import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl {

    private final AdminRepo adminRepo;

    public AdminServiceImpl(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public AdminEmployee getAdmin(Long id) {
        return adminRepo.findById(id).orElse(null);
    }

    public AdminEmployee saveAdmin(AdminEmployee admin) {
        return adminRepo.save(admin);
    }
}
