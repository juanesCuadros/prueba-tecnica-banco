package com.banco.cuenta.infrastructure.exception;

import com.banco.cuenta.domain.exception.RecursoNoEncontradoException;
import com.banco.cuenta.domain.exception.ReglaNegocioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(RecursoNoEncontradoException.class)
  public ResponseEntity<Map<String, Object>> handleNotFound(RecursoNoEncontradoException ex) {
    return buildResponse(HttpStatus.NOT_FOUND, "Not Found", ex.getMessage());
  }

  @ExceptionHandler(ReglaNegocioException.class)
  public ResponseEntity<Map<String, Object>> handleReglaNegocio(ReglaNegocioException ex) {
    return buildResponse(HttpStatus.BAD_REQUEST, "Bad Request", ex.getMessage());
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidacion(MethodArgumentNotValidException ex) {
    Map<String, String> errores = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach(error -> {
      String campo = ((FieldError) error).getField();
      errores.put(campo, error.getDefaultMessage());
    });
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now().toString());
    response.put("status", HttpStatus.BAD_REQUEST.value());
    response.put("error", "Error de Validación");
    response.put("message", errores);
    return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
    return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error", "Error inesperado en el servidor");
  }

  private ResponseEntity<Map<String, Object>> buildResponse(HttpStatus status, String error, Object message) {
    Map<String, Object> response = new HashMap<>();
    response.put("timestamp", LocalDateTime.now().toString());
    response.put("status", status.value());
    response.put("error", error);
    response.put("message", message);
    return new ResponseEntity<>(response, status);
  }
}
