/*
 * **
 *  * @project : DeliX
 *  * @created : 01/05/2024, 19:11
 *  * @modified : 01/05/2024, 19:11
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service;

import com.suivi.colis.suivicolis.entity.PrivilegesGroup;

public interface PrivilegesGroupService {
    void deletePrivilegesGroup(Long id);
    PrivilegesGroup loadPrivilegesGroupById(Long id);
    PrivilegesGroup savePrivilegesGroup(PrivilegesGroup privilegesGroup);
    PrivilegesGroup updatePrivilegesGroup(PrivilegesGroup privilegesGroup);
}
