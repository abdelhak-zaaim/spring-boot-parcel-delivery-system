/*
 *
 *  * @project : DeliX
 *  * @created : 10/05/2024, 17:16
 *  * @modified : 10/05/2024, 17:16
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.exception.personalizedexceptions;

public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException(String message) {
        super(message);
    }
}
