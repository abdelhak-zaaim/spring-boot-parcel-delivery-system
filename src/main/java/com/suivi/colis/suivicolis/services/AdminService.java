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
 *  * @modified : 25/04/2024, 15:13
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * *
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 14:59
 *  * @modified : 25/04/2024, 14:59
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 *
 */

package com.suivi.colis.suivicolis.services;

import com.suivi.colis.suivicolis.entity.AdminEmployee;
import com.suivi.colis.suivicolis.repository.AdminRepo;

import org.springframework.stereotype.Service;


@Service
public class AdminService {

    private final AdminRepo adminRepo;

    public AdminService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    public AdminEmployee getAdmin(Long id) {
        return adminRepo.findById(id).orElse(null);
    }

    public AdminEmployee saveAdmin(AdminEmployee admin) {
        return adminRepo.save(admin);
    }
}
