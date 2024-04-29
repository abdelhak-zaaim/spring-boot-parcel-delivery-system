/*
 * **
 *  * @project : SuiviColis
 *  * @created : 26/04/2024, 17:21
 *  * @modified : 26/04/2024, 17:21
 *  * @description : This file is part of the SuiviColis project.
 *  * @license : MIT License
 * **
 */

package com.suivi.colis.suivicolis.exception;



import com.suivi.colis.suivicolis.dto.ErrorDto;
import com.suivi.colis.suivicolis.exception.personalizedexceptions.DataValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@Order(1)
@ControllerAdvice
public class SpecificExceptionHandler {
    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity<?> handleInvalidUserAttributesException(DataValidationException ex, HttpServletRequest request) {
        ErrorDto error = new ErrorDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), new Date(), request.getRequestURI());

        return BaseExceptionHandler.createErrorResponse(error, HttpStatus.BAD_REQUEST, request.getHeader("Accept"));
    }

}
