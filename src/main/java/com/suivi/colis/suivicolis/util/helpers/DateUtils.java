/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 17:11
 *  * @modified : 26/04/2024, 17:11
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.util.helpers;

import com.suivi.colis.suivicolis.util.Constants;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class DateUtils {

   public static Date getCurrentDateWithSpecifiedTimeZone() {


      ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(Constants.TIME_ZONE));
      return Date.from(zonedDateTime.toInstant());
   }
}
