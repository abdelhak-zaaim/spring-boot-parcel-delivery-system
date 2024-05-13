/*
 *
 *  * @project : DeliX
 *  * @created : 13/05/2024, 19:34
 *  * @modified : 13/05/2024, 19:34
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.response;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class MessageResponseDto {
    boolean success;
     String message;


    public MessageResponseDto( boolean success,String message) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccess() {
        return success;
    }
}
