/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 18:24
 *  * @modified : 26/04/2024, 18:14
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */



package com.suivi.colis.suivicolis.models.entities.validations.admin;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


   @Target(ElementType.TYPE)
   @Retention(RetentionPolicy.RUNTIME)
   @Constraint(validatedBy = AdminValidator.class)
   public @interface AdminValidate {
      String message() default "Invalid privileges group for admin level";
      Class<?>[] groups() default {};
      Class<? extends Payload>[] payload() default {};

}
