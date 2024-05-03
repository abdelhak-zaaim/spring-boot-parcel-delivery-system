/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 17:11
 *  * @modified : 26/04/2024, 17:11
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.delix.util.helpers;

import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@Slf4j
public class DateUtils {

    public static Date getCurrentDateWithSpecifiedTimeZone(@NotNull  String timeZone) {
        try {
            ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of(timeZone));
            return Date.from(zonedDateTime.toInstant());
        } catch (Exception e) {
            log.debug("Exception occurred: ", e);
            throw new RuntimeException("Error while getting current date with this specified time zone :" + timeZone);
        }
    }
}
