/*
 * *
 *  * @project : SuiviColis
 *  * @created : 25/04/2024, 15:07
 *  * @modified : 25/04/2024, 15:07
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * *
 */

package com.suivi.colis.suivicolis.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
