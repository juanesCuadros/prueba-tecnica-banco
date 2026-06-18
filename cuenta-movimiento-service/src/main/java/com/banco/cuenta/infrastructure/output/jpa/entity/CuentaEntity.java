package com.banco.cuenta.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "cuenta")
@Getter
@Setter
public class CuentaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false, length = 20)
  private String numeroCuenta;

  @Column(nullable = false, length = 30)
  private String tipoCuenta;

  @Column(nullable = false, precision = 15, scale = 2)
  private BigDecimal saldoInicial;

  @Column(nullable = false, precision = 15, scale = 2)
  private BigDecimal saldoActual;

  @Column(nullable = false)
  private Boolean estado;

  @Column(nullable = false, length = 50)
  private String clienteId;
}
