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
 *  * @modified : 25/04/2024, 18:37
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 15:46
 *  * @modified : 25/04/2024, 15:46
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.services;

import com.suivi.colis.suivicolis.entities.PrivilegesGroup;
import com.suivi.colis.suivicolis.repositorys.PrivilegesGroupRepo;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PrivilegesGroupService {

    private PrivilegesGroupRepo privilegesGroupRepo;
    private ApplicationContext applicationContext;


    public PrivilegesGroupService(PrivilegesGroupRepo privilegesGroupRepo, ApplicationContext applicationContext) {
        this.privilegesGroupRepo = privilegesGroupRepo;
        this.applicationContext = applicationContext;
    }

    public void addPrivilegesGroup(PrivilegesGroup privilegesGroup) throws IllegalArgumentException {

        Validator validator = applicationContext.getBean(Validator.class);

        BindingResult errors = new BeanPropertyBindingResult(privilegesGroup, "PrivilegesGroup");
        validator.validate(privilegesGroup, errors);
        if (errors.hasErrors()) {
            throw new IllegalArgumentException("Invalid PrivilegesGroup data");
        }
        privilegesGroupRepo.save(privilegesGroup);
    }
}
