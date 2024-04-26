/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 18:24
 *  * @modified : 26/04/2024, 18:19
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.suivicolis.models.entities.validations.admin;


import com.suivi.colis.suivicolis.exceptions.customexptions.IllegalUserAttributesException;
import com.suivi.colis.suivicolis.models.entities.Admin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class AdminValidator implements ConstraintValidator<AdminValidate, Admin> {

    @Override
    public boolean isValid(Admin admin, ConstraintValidatorContext context) {

        if (admin.getAdminLevel() == null) {
            throw new IllegalUserAttributesException("Admin level is required");
        }
        if (admin.getAdminLevel() == 2 && admin.getPrivilegesGroup() == null) {
            throw new IllegalUserAttributesException("Admin level 2 must have a privileges group");
        }
        return true;
    }
}
