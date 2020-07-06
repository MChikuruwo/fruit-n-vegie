package com.fruitnvegie.fruitnvegieapi.handler;

import com.fruitnvegie.fruitnvegieapi.exceptions.EmailAlreadyExistsException;
import com.fruitnvegie.fruitnvegieapi.exceptions.InvalidOldPasswordException;
import com.fruitnvegie.fruitnvegieapi.exceptions.InvalidTokenException;
import com.fruitnvegie.fruitnvegieapi.exceptions.UserNotFoundException;
import com.fruitnvegie.fruitnvegieapi.models.api.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity handleUserException (UserNotFoundException e) {
        ApiResponse response = new ApiResponse(404,  e.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ApiResponse handleEntityNotFoundException (EntityNotFoundException e) {
        return new ApiResponse(404, e.getLocalizedMessage());

    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidTokenException.class)
    public ApiResponse handleInvalidTokenException (InvalidTokenException e) {
        return new ApiResponse(400, e.getLocalizedMessage());

    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidOldPasswordException.class)
    public ApiResponse handleInvalidOldPasswordException (InvalidOldPasswordException e) {
        return new ApiResponse(400, e.getLocalizedMessage());

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public ApiResponse handleGeneralException(Exception e) {
        return new ApiResponse(400, e.getLocalizedMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ApiResponse handleEmailAlreadyExistsException(EmailAlreadyExistsException e) {
        return new ApiResponse(400, e.getLocalizedMessage());
    }
}
