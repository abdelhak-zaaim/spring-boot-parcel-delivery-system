/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 19:08
 *  * @modified : 26/04/2024, 19:08
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.models.entities.validations.location;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MapsLocationPointValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMapsLocationPoint {
   String message() default "Invalid MapsLocationPoint";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
}