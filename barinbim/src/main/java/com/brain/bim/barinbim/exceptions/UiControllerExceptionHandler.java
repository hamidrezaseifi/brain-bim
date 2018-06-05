package com.brain.bim.barinbim.exceptions;

import java.io.IOException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.thymeleaf.exceptions.TemplateInputException;

@RestControllerAdvice
public class UiControllerExceptionHandler {

  @ExceptionHandler(value = { IOException.class })
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public UiRestResponse throwIoException(final ConstraintViolationException ex) {
    return UiRestResponse.createError(HttpStatus.BAD_REQUEST, ex);
  }

  @ExceptionHandler(value = { NoHandlerFoundException.class })
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public UiRestResponse throwNoHandlerFoundException(final Exception ex) {
    return UiRestResponse.createError(HttpStatus.NOT_FOUND, ex);
  }

  @ExceptionHandler(value = { ValidationException.class })
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public UiRestResponse throwValidationException(final ValidationException ex) {
    return UiRestResponse.createError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
  }

  @ExceptionHandler(value = { TemplateInputException.class })
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public UiRestResponse throwTemplateInputException(final Exception ex) {
    return UiRestResponse.createError(HttpStatus.NOT_FOUND, ex);
  }

  @ExceptionHandler(value = { Exception.class })
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public UiRestResponse throwUnknownException(final Exception ex) {
    return UiRestResponse.createError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
  }

}
