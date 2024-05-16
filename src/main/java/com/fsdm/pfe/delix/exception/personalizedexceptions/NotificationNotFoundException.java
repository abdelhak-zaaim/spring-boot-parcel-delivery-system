/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 18:06
 *  * @modified : 16/05/2024, 18:06
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.exception.personalizedexceptions;



public class NotificationNotFoundException extends RuntimeException {
    public NotificationNotFoundException(String message) {
        super(message);
    }
}