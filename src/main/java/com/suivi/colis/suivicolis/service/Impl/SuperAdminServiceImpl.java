/*
 * **
 *  * @project : SuiviColis
 *  * @created : 01/05/2024, 19:30
 *  * @modified : 01/05/2024, 19:30
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service.Impl;

import com.suivi.colis.suivicolis.entity.SuperAdmin;
import com.suivi.colis.suivicolis.repository.SuperAdminRepo;
import com.suivi.colis.suivicolis.service.SuperAdminService;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminServiceImpl implements SuperAdminService {
    SuperAdminRepo superAdminRepo;

    public SuperAdminServiceImpl(SuperAdminRepo superAdminRepo) {
        this.superAdminRepo = superAdminRepo;
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
        return superAdminRepo.save(superAdmin);
    }

    @Override
    public SuperAdmin updateSuperAdmin(SuperAdmin superAdmin) {
        return superAdminRepo.save(superAdmin);
    }
}
