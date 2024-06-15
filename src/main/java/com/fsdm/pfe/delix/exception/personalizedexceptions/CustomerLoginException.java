/*
 *
 *  * @project : DeliX
 *  * @created : 27/05/2024, 13:44
 *  * @modified : 27/05/2024, 13:44
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.exception.personalizedexceptions;

public class CustomerLoginException extends RuntimeException {
    public CustomerLoginException(String message) {
        super(message);
    }
}
