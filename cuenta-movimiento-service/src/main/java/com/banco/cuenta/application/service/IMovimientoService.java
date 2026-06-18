package com.banco.cuenta.application.service;

import com.banco.cuenta.domain.model.Movimiento;

import java.math.BigDecimal;

public interface IMovimientoService {
  Movimiento registrarMovimiento(String numeroCuenta, BigDecimal valor);
}
