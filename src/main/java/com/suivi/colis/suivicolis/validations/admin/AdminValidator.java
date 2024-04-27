/*
 * **
 *  * @project : SuiviColis
 *  * @created : 27/04/2024, 13:09
 *  * @modified : 26/04/2024, 19:19
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */



package com.suivi.colis.suivicolis.validations.admin;



import com.suivi.colis.suivicolis.exceptions.personalizedexceptions.DataValidationException;
import com.suivi.colis.suivicolis.entities.Admin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class AdminValidator implements ConstraintValidator<AdminValidate, Admin> {

    @Override
    public boolean isValid(Admin admin, ConstraintValidatorContext context) {

        if (admin.getAdminLevel() == null) {
            throw new DataValidationException("Admin level is required");
        }
        if (admin.getAdminLevel() == 2 && admin.getPrivilegesGroup() == null) {
            throw new DataValidationException("Admin level 2 must have a privileges group");
        }
        return true;
    }
}
