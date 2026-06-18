package com.banco.cuenta.infrastructure.input.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CuentaDTO {

  private Long id;

  @NotBlank(message = "El número de cuenta es obligatorio")
  private String numeroCuenta;

  @NotBlank(message = "El tipo de cuenta es obligatorio")
  private String tipoCuenta;

  @NotNull(message = "El saldo inicial es obligatorio")
  @PositiveOrZero(message = "El saldo inicial no puede ser negativo")
  private BigDecimal saldoInicial;

  private BigDecimal saldoActual;

  @NotNull(message = "El estado es obligatorio")
  private Boolean estado;

  @NotBlank(message = "El clienteId es obligatorio")
  private String clienteId;
}
