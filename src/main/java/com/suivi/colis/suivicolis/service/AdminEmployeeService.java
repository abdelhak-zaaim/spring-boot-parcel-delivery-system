/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 19:24
 *  * @modified : 01/05/2024, 19:24
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service;
import com.suivi.colis.suivicolis.entity.AdminEmployee;
public interface AdminEmployeeService {
    void deleteAdmin(Long id);

    AdminEmployee loadAdminById(Long id);

    AdminEmployee saveAdmin(AdminEmployee admin);

    AdminEmployee updateAdmin(AdminEmployee admin);
}
