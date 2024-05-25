/*
 *
 *  * @project : DeliX
 *  * @created : 10/05/2024, 16:37
 *  * @modified : 10/05/2024, 16:37
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class MessageDto implements Serializable {

    private final int code;
    private final String message;
    private Object data;

    public MessageDto(int code, String message) {
        this.code = code;

        this.message = message;
    }

}
