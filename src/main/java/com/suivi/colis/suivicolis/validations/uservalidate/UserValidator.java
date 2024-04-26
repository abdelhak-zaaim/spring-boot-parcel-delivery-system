/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 17:13
 *  * @modified : 25/04/2024, 17:13
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.validations.uservalidate;

import com.suivi.colis.suivicolis.models.entities.User;
import com.suivi.colis.suivicolis.utils.helpers.ValidationUtils;

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