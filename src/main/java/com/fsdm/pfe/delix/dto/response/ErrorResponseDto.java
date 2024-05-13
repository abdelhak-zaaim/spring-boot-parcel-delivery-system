
/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 19:23
 *  * @modified : 26/04/2024, 17:02
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */


package com.fsdm.pfe.delix.dto.response;

import lombok.Value;

import java.io.Serializable;
import java.util.Date;

@Value

public class ErrorResponseDto implements Serializable {
    String message;
    int statusCode;
    Date timestamp;
    String path;

}
