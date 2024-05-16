/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 18:54
 *  * @modified : 16/05/2024, 18:54
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.model;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificationRequest {
    private String title;
    private String body;
    private String topic;
    private String token;
}
