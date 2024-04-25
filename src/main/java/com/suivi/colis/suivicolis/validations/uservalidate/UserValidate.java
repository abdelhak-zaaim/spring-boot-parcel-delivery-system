/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 17:09
 *  * @modified : 25/04/2024, 17:08
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */


package com.suivi.colis.suivicolis.validations.uservalidate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserValidate {
   String message() default "Invalid User";
   Class<?>[] groups() default {};
   Class<? extends Payload>[] payload() default {};
}