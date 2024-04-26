/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 16:47
 *  * @modified : 26/04/2024, 16:47
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.validations.uservalidate.email;

import com.suivi.colis.suivicolis.exceptions.customexptions.IllegalUserAttributesException;
import com.suivi.colis.suivicolis.utils.helpers.ValidationUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {



    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            throw new IllegalUserAttributesException("Email cannot be null");
        }

        if (ValidationUtils.isValidEmail(email)) {
            throw new IllegalUserAttributesException("Invalid email format");
        }

        return true;
    }
}