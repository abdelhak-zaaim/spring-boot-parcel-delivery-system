
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
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PrivilegesGroupServiceImpl {

    private final PrivilegesGroupRepo privilegesGroupRepo;
    private final ApplicationContext applicationContext;


    public PrivilegesGroupServiceImpl(PrivilegesGroupRepo privilegesGroupRepo, ApplicationContext applicationContext) {
        this.privilegesGroupRepo = privilegesGroupRepo;
        this.applicationContext = applicationContext;
    }

    public PrivilegesGroup addPrivilegesGroup(PrivilegesGroup privilegesGroup) throws IllegalArgumentException {

        Validator validator = applicationContext.getBean(Validator.class);

        BindingResult errors = new BeanPropertyBindingResult(privilegesGroup, "PrivilegesGroup");
        validator.validate(privilegesGroup, errors);
        if (errors.hasErrors()) {
            throw new IllegalArgumentException("Invalid PrivilegesGroup data");
        }
      return   privilegesGroupRepo.save(privilegesGroup);
    }

    public PrivilegesGroup getPrivilegesGroupById(Long id) {
        return privilegesGroupRepo.findById(id).orElse(null);
    }
}
