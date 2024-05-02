/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 13:09
 *  * @modified : 26/04/2024, 18:32
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

/*
 * **
 *  * @project : DeliX
 *  * @created : 25/04/2024, 17:13
 *  * @modified : 25/04/2024, 17:13
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.validation.user;

import com.suivi.colis.delix.entity.User;
import com.suivi.colis.delix.util.helpers.ValidationUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserValidator implements ConstraintValidator<UserValidate, User> {

   @Override
   public void initialize(UserValidate constraintAnnotation) {
   }

   @Override
   public boolean isValid(User user, ConstraintValidatorContext context) {
      // todo : add more validation , hadxi makafix , for now its okay

      return user.getName() != null && !user.getName().isEmpty() &&
              user.getEmail() != null && !user.getEmail().isEmpty() && ValidationUtils.isValidEmail(user.getEmail());
   }
}