package com.antelif.acme.controller;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.antelif.acme.model.error.ErrorResponse;
import com.antelif.acme.model.error.exception.AcmeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Handles exception occurring and returns appropriate responses to clients. */
@RestControllerAdvice
@Slf4j
public class ExceptionHandlerController {

  /***
   * Handles all exceptions thrown by the system due to internal errors.
   * @return an Error Response object of Acme error.
   */
  @ExceptionHandler(value = Exception.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public ErrorResponse exceptionHandler(Exception exception) {
    ErrorResponse error = new ErrorResponse(exception, INTERNAL_SERVER_ERROR.value());
    log.error(error.toString());
    return error;
  }

  /***
   * Handles all custom exceptions that extend AcmeException.
   * @param exception the exception that was thrown.
   * @return an Error Response object with information about the error that occurred.
   */
  @ExceptionHandler(value = AcmeException.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public ErrorResponse genericExceptionHandler(AcmeException exception) {
    ErrorResponse error = new ErrorResponse(exception, INTERNAL_SERVER_ERROR.value());
    log.error(error.toString());
    return error;
  }
}
