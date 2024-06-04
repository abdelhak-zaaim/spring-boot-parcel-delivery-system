/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 17:24
 *  * @modified : 26/04/2024, 17:24
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public abstract class BaseExceptionHandler {

    protected static ResponseEntity<?> createErrorResponse(Object errorBody, HttpStatus status, String acceptHeader) {
        if (isJsonAcceptable(acceptHeader)) {
            return createJsonResponse(errorBody, status);
        } else {
            return createModelAndViewResponse(errorBody, status);
        }
    }

    private static boolean isJsonAcceptable(String acceptHeader) {
        return acceptHeader != null && acceptHeader.contains("application/json");
    }

    private static ResponseEntity<?> createJsonResponse(Object errorBody, HttpStatus status) {
        return new ResponseEntity<>(errorBody, status);
    }

    private static ResponseEntity<?> createModelAndViewResponse(Object errorBody, HttpStatus status) {
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("error", errorBody);
        return new ResponseEntity<>(modelAndView, status);
    }
}
