/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 19:14
 *  * @modified : 25/04/2024, 19:14
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.validations.uservalidate.age;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AgeLimitValidator.class)
public @interface AgeLimit{
   int minimumAge() default 18;
   String message() default "User age not valid for registration";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
}