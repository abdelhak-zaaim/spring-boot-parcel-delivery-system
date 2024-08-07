/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 13:09
 *  * @modified : 26/04/2024, 19:15
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */


package com.fsdm.pfe.delix.validation.admin.privilegesgroupvalidate;

import com.fsdm.pfe.delix.entity.PrivilegesGroup;
import com.fsdm.pfe.delix.model.enums.Privilege;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PrivilegesGroupValidator implements ConstraintValidator<PrivilegeValidation, PrivilegesGroup> {

    @Override
    public boolean isValid(PrivilegesGroup privilegesGroup, ConstraintValidatorContext context) {
        if (privilegesGroup.getName() == null || privilegesGroup.getName().isEmpty()) {
            return false;
        }
        if (privilegesGroup.getPrivileges() == null || privilegesGroup.getPrivileges().isEmpty()) {
            return false;
        }
        return privilegesGroup.getPrivileges().stream().allMatch(Privilege::contains);
    }
}