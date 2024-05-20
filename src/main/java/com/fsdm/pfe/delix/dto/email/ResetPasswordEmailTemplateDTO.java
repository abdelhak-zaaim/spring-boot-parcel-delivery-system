/*
 *
 *  * @project : DeliX
 *  * @created : 20/05/2024, 13:09
 *  * @modified : 20/05/2024, 13:09
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.email;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ResetPasswordEmailTemplateDTO {
    String name;
    String actionUrl;
    String operatingSystem;
    String timeLeft;
    String browserName;
}
