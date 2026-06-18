package com.banco.cuenta.domain.model;

import lombok.*;
import java.math.BigDecimal;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Cuenta {
  private Long id;
  private String numeroCuenta;
  private String tipoCuenta;
  private BigDecimal saldoInicial;
  private BigDecimal saldoActual;
  private Boolean estado;
  private String clienteId;
}
