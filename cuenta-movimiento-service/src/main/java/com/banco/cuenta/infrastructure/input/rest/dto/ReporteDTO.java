package com.banco.cuenta.infrastructure.input.rest.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReporteDTO {
  private LocalDateTime fecha;
  private String cliente;
  private String numeroCuenta;
  private String tipoCuenta;
  private BigDecimal saldoInicial;
  private Boolean estado;
  private String movimiento;
  private BigDecimal saldoDisponible;
}
