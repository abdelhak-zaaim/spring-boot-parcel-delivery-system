/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 19:09
 *  * @modified : 26/04/2024, 18:26
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 18:24
 *  * @modified : 26/04/2024, 01:53
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 17:42
 *  * @modified : 25/04/2024, 17:42
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.models.entities.validations.admin.privilegesgroupvalidate;

import com.suivi.colis.suivicolis.models.entities.PrivilegesGroup;
import com.suivi.colis.suivicolis.models.enums.Privilege;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class PrivilegesGroupValidator implements ConstraintValidator<PrivilegeValidation, PrivilegesGroup> {
    private PrivilegesGroup privilegesGroup;

    @Override
    public void initialize(PrivilegeValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(PrivilegesGroup privilegesGroup, ConstraintValidatorContext context) {
        System.out.println("Validating PrivilegesGroup: " + privilegesGroup);

        if (privilegesGroup.getName() == null || privilegesGroup.getName().isEmpty()) {
            return false;
        }
        if (privilegesGroup.getPrivileges() == null || privilegesGroup.getPrivileges().isEmpty()) {
            return false;
        }
        Set<Privilege> uniquePrivileges = new HashSet<>();
        for (Privilege privilege : privilegesGroup.getPrivileges()) {
            if (!Privilege.contains(privilege) || !uniquePrivileges.add(privilege)) {
                return false;
            }
        }
        return true;
    }


}