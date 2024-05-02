/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 13:09
 *  * @modified : 26/04/2024, 19:15
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.validation.location;

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