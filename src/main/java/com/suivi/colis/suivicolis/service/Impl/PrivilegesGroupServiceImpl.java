
/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 15:46
 *  * @modified : 25/04/2024, 15:46
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.service.Impl;

import com.suivi.colis.suivicolis.entity.PrivilegesGroup;
import com.suivi.colis.suivicolis.repository.PrivilegesGroupRepo;
import com.suivi.colis.suivicolis.service.PrivilegesGroupService;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PrivilegesGroupServiceImpl implements PrivilegesGroupService {

    private final PrivilegesGroupRepo privilegesGroupRepo;

    public PrivilegesGroupServiceImpl(PrivilegesGroupRepo privilegesGroupRepo) {
        this.privilegesGroupRepo = privilegesGroupRepo;
    }


    @Override
    public void deletePrivilegesGroup(Long id) {
        privilegesGroupRepo.deleteById(id);
    }

    @Override
    public PrivilegesGroup loadPrivilegesGroupById(Long id) {
        return privilegesGroupRepo.findById(id).orElse(null);
    }

    @Override
    public PrivilegesGroup savePrivilegesGroup(PrivilegesGroup privilegesGroup) {
        return privilegesGroupRepo.save(privilegesGroup);
    }

    @Override
    public PrivilegesGroup updatePrivilegesGroup(PrivilegesGroup privilegesGroup) {
        return privilegesGroupRepo.save(privilegesGroup);
    }
}
