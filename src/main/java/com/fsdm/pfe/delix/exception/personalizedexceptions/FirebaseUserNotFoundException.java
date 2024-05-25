/*
 *
 *  * @project : DeliX
 *  * @created : 16/05/2024, 19:21
 *  * @modified : 16/05/2024, 19:21
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 *
 */

package com.fsdm.pfe.delix.exception.personalizedexceptions;

public class FirebaseUserNotFoundException extends RuntimeException {
    public FirebaseUserNotFoundException(String message) {
        super(message);
    }
}
