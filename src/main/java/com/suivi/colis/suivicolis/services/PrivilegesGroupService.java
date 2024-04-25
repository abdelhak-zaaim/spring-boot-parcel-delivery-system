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

import com.suivi.colis.suivicolis.models.PrivilegesGroup;
import com.suivi.colis.suivicolis.models.enums.Privilege;
import com.suivi.colis.suivicolis.repositorys.PrivilegesGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.*;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;

@Service
public class PrivilegesGroupService {
   @Autowired
   private PrivilegesGroupRepo privilegesGroupRepo;

    @Autowired
    private Validator validator;

    public void addPrivilegesGroup(PrivilegesGroup privilegesGroup) throws IllegalArgumentException {


        BindingResult errors = new BeanPropertyBindingResult(privilegesGroup, "PrivilegesGroup");
        validator.validate(privilegesGroup, errors);
        if (errors.hasErrors()) {
            throw new IllegalArgumentException("Invalid PrivilegesGroup data");
        }
        privilegesGroupRepo.save(privilegesGroup);
    }
}
