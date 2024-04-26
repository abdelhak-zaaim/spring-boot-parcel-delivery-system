/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 18:25
 *  * @modified : 26/04/2024, 18:11
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 16:47
 *  * @modified : 26/04/2024, 16:47
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.models.entities.validations.user.email;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmail {

    String message() default "Invalid email format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
