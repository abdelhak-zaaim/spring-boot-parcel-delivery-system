/*
 * **
 *  * @project : SuiviColis
 *  * @created : 27/04/2024, 13:09
 *  * @modified : 26/04/2024, 19:15
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */



package com.suivi.colis.suivicolis.validation.admin.privilegesgroupvalidate;

import com.suivi.colis.suivicolis.entity.PrivilegesGroup;
import com.suivi.colis.suivicolis.model.enums.Privilege;
import lombok.extern.slf4j.Slf4j;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;
@Slf4j
public class PrivilegesGroupValidator implements ConstraintValidator<PrivilegeValidation, PrivilegesGroup> {
    private PrivilegesGroup privilegesGroup;

    @Override
    public void initialize(PrivilegeValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(PrivilegesGroup privilegesGroup, ConstraintValidatorContext context) {
        log.debug("Validating PrivilegesGroup: " + privilegesGroup);

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