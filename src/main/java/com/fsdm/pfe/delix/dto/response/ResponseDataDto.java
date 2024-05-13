/*
 *
 *  * @project : DeliX
 *  * @created : 12/05/2024, 21:39
 *  * @modified : 12/05/2024, 21:39
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.io.Serializable;
@Value
@Builder

public class ResponseDataDto implements Serializable {
    boolean success;
    String message;
    Object error;
    Object data;

}
