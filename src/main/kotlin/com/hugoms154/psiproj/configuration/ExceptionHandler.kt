package com.hugoms154.psiproj.configuration

import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

data class ExceptionResponse(val message: String?, val errors: List<CustomError>)
data class CustomError(val field: String, val message: String?)

@RestControllerAdvice
class ExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException::class)
  fun validationError(exception: MethodArgumentNotValidException): ResponseEntity<ExceptionResponse> {
    val errors = exception.bindingResult.allErrors.map {
      val castedError = it as FieldError
      CustomError(castedError.field, castedError.defaultMessage)
    }

    return ResponseEntity.badRequest().body(ExceptionResponse("error message?", errors))
  }
}
