/*
 * **
 *  * @project : DeliX
 *  * @created : 27/04/2024, 13:09
 *  * @modified : 26/04/2024, 19:19
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
 *  * @created : 25/04/2024, 19:15
 *  * @modified : 25/04/2024, 19:15
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.validation.user.age;

import com.fsdm.pfe.delix.exception.personalizedexceptions.DataValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class AgeLimitValidator implements ConstraintValidator<AgeLimit, Date> {
   int minimumAge;

   @Override
   public void initialize(AgeLimit constraintAnnotation) {
      this.minimumAge = constraintAnnotation.minimumAge();
   }

   @Override
   public boolean isValid(Date birthDate, ConstraintValidatorContext constraintValidatorContext) {
      if(birthDate == null){
         throw new DataValidationException("Date of birth cannot be null");
      }
      LocalDate today = LocalDate.now();
      LocalDate minimumAgeYearsAgo = today.minusYears(this.minimumAge);

      Instant instant = birthDate.toInstant();
      ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
      LocalDate birthDateLocal = zdt.toLocalDate();
      if(minimumAgeYearsAgo.isBefore(birthDateLocal)) {
         throw new DataValidationException("User must be at least " + this.minimumAge + " years old");
      }
      return true;
   }
}