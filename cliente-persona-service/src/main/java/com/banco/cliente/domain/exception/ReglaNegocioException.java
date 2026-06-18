package com.banco.cliente.domain.exception;

public class ReglaNegocioException extends RuntimeException {
  public ReglaNegocioException(String mensaje) {
    super(mensaje);
  }
}
