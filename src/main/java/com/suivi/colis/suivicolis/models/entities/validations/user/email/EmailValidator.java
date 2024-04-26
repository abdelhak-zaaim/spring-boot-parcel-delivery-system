/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 18:25
 *  * @modified : 26/04/2024, 18:11
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.suivicolis.models.entities.validations.user.email;

import com.suivi.colis.suivicolis.exceptions.personalizedexceptions.IllegalUserAttributesException;
import com.suivi.colis.suivicolis.utils.helpers.ValidationUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public void initialize(ValidEmail constraintAnnotation) {

    }


    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || !ValidationUtils.isValidEmail(email)) {
            throw new IllegalUserAttributesException("Invalid email format");
        }

        return true;
    }
}