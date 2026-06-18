package com.banco.cuenta.infrastructure.input.rest.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoResponseDTO {
  private Long id;
  private LocalDateTime fecha;
  private String tipoMovimiento;
  private BigDecimal valor;
  private BigDecimal saldo;
  private String numeroCuenta;
}
