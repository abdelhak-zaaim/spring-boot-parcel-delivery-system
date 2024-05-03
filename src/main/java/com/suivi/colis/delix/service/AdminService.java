/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 19:24
 *  * @modified : 01/05/2024, 19:24
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.service;
import com.suivi.colis.delix.entity.Admin;

public interface AdminService {
    void deleteAdmin(Long id);

    Admin loadAdminById(Long id);

    Admin saveAdmin(Admin admin);

    Admin updateAdmin(Admin admin);
}
