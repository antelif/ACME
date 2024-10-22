package com.antelif.acme.controller;

import static com.antelif.acme.model.error.AcmeError.INPUT_VALIDATIONS_ERROR;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import com.antelif.acme.model.error.AcmeError;
import com.antelif.acme.model.error.ErrorResponse;
import com.antelif.acme.model.error.exception.AcmeException;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    ErrorResponse error = new ErrorResponse(AcmeError.UNKNOWN_ERROR, INTERNAL_SERVER_ERROR.value());
    log.error(error + " " + exception.getMessage());
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

  /**
   * Handles MethodArgumentNotValidException that occurs during input validations.
   *
   * @param exception the exception that was thrown.
   * @return an Error Response object with information about the error that occurred.
   */
  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  @ResponseStatus(INTERNAL_SERVER_ERROR)
  public ErrorResponse methodArgumentNotValidExceptionHandler(
      MethodArgumentNotValidException exception) {
    ErrorResponse error =
        new ErrorResponse(
            INPUT_VALIDATIONS_ERROR,
            INTERNAL_SERVER_ERROR.value(),
            Optional.ofNullable(exception.getFieldError())
                .map(FieldError::getDefaultMessage)
                .orElseGet(exception::getMessage));

    log.error(error + " " + exception.getMessage());

    return error;
  }
}
