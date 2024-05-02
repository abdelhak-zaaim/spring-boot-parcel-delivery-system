/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 13:09
 *  * @modified : 26/04/2024, 19:19
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 18:25
 *  * @modified : 26/04/2024, 18:11
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.suivicolis.validation.user.email;


import com.suivi.colis.suivicolis.exception.personalizedexceptions.DataValidationException;
import com.suivi.colis.suivicolis.util.helpers.ValidationUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    @Override
    public void initialize(ValidEmail constraintAnnotation) {

    }


    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || !ValidationUtils.isValidEmail(email)) {
            throw new DataValidationException("Invalid email format");
        }

        return true;
    }
}