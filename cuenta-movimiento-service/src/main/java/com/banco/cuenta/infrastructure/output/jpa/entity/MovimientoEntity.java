package com.banco.cuenta.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "movimiento")
@Getter
@Setter
public class MovimientoEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private LocalDateTime fecha;

  @Column(nullable = false, length = 20)
  private String tipoMovimiento;

  @Column(nullable = false, precision = 15, scale = 2)
  private BigDecimal valor;

  @Column(nullable = false, precision = 15, scale = 2)
  private BigDecimal saldo;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cuenta_id", nullable = false)
  private CuentaEntity cuenta;
}
