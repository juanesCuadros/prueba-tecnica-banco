package com.banco.cuenta.domain.model;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Movimiento {
  private Long id;
  private LocalDateTime fecha;
  private String tipoMovimiento;
  private BigDecimal valor;
  private BigDecimal saldo;
  private Cuenta cuenta;
}
