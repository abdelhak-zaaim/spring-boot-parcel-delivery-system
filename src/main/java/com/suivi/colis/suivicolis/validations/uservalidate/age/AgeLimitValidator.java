/*
 * **
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 19:15
 *  * @modified : 25/04/2024, 19:15
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.validations.uservalidate.age;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class AgeLimitValidator implements ConstraintValidator<AgeLimit, LocalDate> {
   int minimumAge;

   @Override
   public void initialize(AgeLimit constraintAnnotation) {
      this.minimumAge = constraintAnnotation.minimumAge();
   }

   @Override
   public boolean isValid(LocalDate birthDate, ConstraintValidatorContext constraintValidatorContext) {
      if(birthDate == null){
         return false;
      }
      LocalDate today = LocalDate.now();
      LocalDate minimumAgeYearsAgo = today.minusYears(this.minimumAge);
      return !minimumAgeYearsAgo.isBefore(birthDate);
   }
}