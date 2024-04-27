/*
 * **
 *  * @project : SuiviColis
 *  * @created : 27/04/2024, 13:09
 *  * @modified : 26/04/2024, 19:15
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */



package com.suivi.colis.suivicolis.validations.location;

import com.suivi.colis.suivicolis.models.MapsLocationPoint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MapsLocationPointValidator implements ConstraintValidator<ValidMapsLocationPoint, MapsLocationPoint> {

    @Override
    public void initialize(ValidMapsLocationPoint constraintAnnotation) {
    }

    @Override
    public boolean isValid(MapsLocationPoint value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        if (!value.isValid()) {
            throw new IllegalArgumentException("Invalid MapsLocationPoint: " + value);
        }
        return true;
    }
}