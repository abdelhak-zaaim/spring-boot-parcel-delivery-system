/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 19:30
 *  * @modified : 01/05/2024, 19:30
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.service.Impl;

import com.suivi.colis.delix.entity.SuperAdmin;
import com.suivi.colis.delix.repository.SuperAdminRepo;
import com.suivi.colis.delix.service.SuperAdminService;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {
    private final SuperAdminRepo superAdminRepo;
    private final UserServiceImpl userService;

    public SuperAdminServiceImpl(SuperAdminRepo superAdminRepo, UserServiceImpl userService) {
        this.superAdminRepo = superAdminRepo;
        this.userService = userService;
    }

    @Override
    public void deleteSuperAdmin(Long id) {
        superAdminRepo.deleteById(id);
    }

    @Override
    public SuperAdmin loadSuperAdminById(Long id) {
        return superAdminRepo.findById(id).orElse(null);
    }

    @Override
    public SuperAdmin saveSuperAdmin(SuperAdmin superAdmin) {
        superAdmin.setPassword(userService.encodePassword(superAdmin.getPassword()));
        return superAdminRepo.save(superAdmin);
    }

    @Override
    public SuperAdmin updateSuperAdmin(SuperAdmin superAdmin) {
        return superAdminRepo.save(superAdmin);
    }
}
