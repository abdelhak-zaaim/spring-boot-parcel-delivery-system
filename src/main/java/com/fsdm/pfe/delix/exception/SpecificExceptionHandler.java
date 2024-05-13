/*
 * **
 *  * @project : DeliX
 *  * @created : 26/04/2024, 17:21
 *  * @modified : 26/04/2024, 17:21
 *  * @description : This file is part of the DeliX project.
 *  * @license : MIT License
 * **
 */

package com.fsdm.pfe.delix.exception;



import com.fsdm.pfe.delix.dto.response.ArgumentNotValidDto;
import com.fsdm.pfe.delix.dto.response.ErrorResponseDto;
import com.fsdm.pfe.delix.dto.response.ResponseDataDto;
import com.fsdm.pfe.delix.exception.personalizedexceptions.DataValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Order(1)
@ControllerAdvice
public class SpecificExceptionHandler {



    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ResponseDataDto> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(ResponseDataDto.builder().data(errors).success(true).error(null).message("please verify the inputs").build(), HttpStatus.BAD_REQUEST);
    }




    @ExceptionHandler(DataValidationException.class)
    public ResponseEntity<?> handleInvalidUserAttributesException(DataValidationException ex, HttpServletRequest request) {
        ErrorResponseDto error = new ErrorResponseDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), new Date(), request.getRequestURI());

        return BaseExceptionHandler.createErrorResponse(error, HttpStatus.BAD_REQUEST, request.getHeader("Accept"));
    }

}
