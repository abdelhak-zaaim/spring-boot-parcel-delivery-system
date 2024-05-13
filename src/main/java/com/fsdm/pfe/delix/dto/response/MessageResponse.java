/*
 *
 *  * @project : DeliX
 *  * @created : 13/05/2024, 19:33
 *  * @modified : 13/05/2024, 19:33
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.dto.response;

import lombok.Value;

import java.io.Serializable;
@Value
public class MessageResponse implements Serializable {
   String message;
   boolean success;
}
