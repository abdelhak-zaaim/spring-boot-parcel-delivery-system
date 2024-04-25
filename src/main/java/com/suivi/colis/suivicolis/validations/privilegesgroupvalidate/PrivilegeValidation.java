/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 17:42
 *  * @modified : 25/04/2024, 17:42
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.validations.privilegesgroupvalidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PrivilegesGroupValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrivilegeValidation {
    String message() default "Invalid PrivilegesGroup";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}