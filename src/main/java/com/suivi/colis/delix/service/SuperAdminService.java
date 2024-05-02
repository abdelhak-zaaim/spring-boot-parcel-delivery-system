/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 19:29
 *  * @modified : 01/05/2024, 19:29
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.service;

import com.suivi.colis.delix.entity.SuperAdmin;

public interface SuperAdminService
{
    void deleteSuperAdmin(Long id);

    SuperAdmin loadSuperAdminById(Long id);

    SuperAdmin saveSuperAdmin(SuperAdmin superAdmin);

    SuperAdmin updateSuperAdmin(SuperAdmin superAdmin);
}
