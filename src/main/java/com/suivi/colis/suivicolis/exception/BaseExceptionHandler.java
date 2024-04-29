/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 17:24
 *  * @modified : 26/04/2024, 17:24
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public abstract class BaseExceptionHandler {

    protected static ResponseEntity<?> createErrorResponse(Object errorBody, HttpStatus status, String acceptHeader) {
        if ((acceptHeader != null && acceptHeader.contains("application/json")||true)) {
            return new ResponseEntity<>(errorBody, status);
        } else {
            ModelAndView modelAndView = new ModelAndView("error");
            modelAndView.addObject("error", errorBody);
            return new ResponseEntity<>(modelAndView, status);
        }
    }
}