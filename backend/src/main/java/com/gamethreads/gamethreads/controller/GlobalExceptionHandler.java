package com.gamethreads.gamethreads.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.gamethreads.gamethreads.exception.status.Status400Exception;

@RestControllerAdvice
public class GlobalExceptionHandler {
  // 400 - Bad Request
  // Exception thrown by jakarta validation decorators
  // This method sorts and returns the first error found in alphabetical order
  // This allows the frontend to receive a single error at a time
  // As explained in a comment below, field errors are seemingly randomly
  // organized, which makes testing a pain
  // Sorting the errors makes testing deterministic and removes randomness from
  // the mix
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> jakartaExceptionHandler(MethodArgumentNotValidException ex) {
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    // Make it modifiable
    // By default getFieldErrors is not modifiable
    fieldErrors = new ArrayList<>(fieldErrors);
    fieldErrors.sort(Comparator.comparing(FieldError::getField));

    // Only return the first error alphabetically to make things easy and
    // deterministic
    // By default, the "first" error is essentially random
    Map<String, String> res = new HashMap<>();
    res.put("error", fieldErrors.get(0).getDefaultMessage());
    return res;
  }

  // 400 - Bad Request
  // Used for custom exceptions with invalid inputs, missing fields or anything
  // else considered a bad request
  @ExceptionHandler(Status400Exception.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public Map<String, String> status400Handler(Status400Exception ex) {
    Map<String, String> res = new HashMap<>();
    res.put("error", ex.getMessage());
    return res;
  }
}
