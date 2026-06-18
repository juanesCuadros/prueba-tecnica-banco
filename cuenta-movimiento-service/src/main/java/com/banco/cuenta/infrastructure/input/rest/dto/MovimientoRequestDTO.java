package com.banco.cuenta.infrastructure.input.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MovimientoRequestDTO {

  @NotBlank(message = "El número de cuenta es obligatorio")
  private String numeroCuenta;

  @NotNull(message = "El valor es obligatorio")
  private BigDecimal valor;
}
