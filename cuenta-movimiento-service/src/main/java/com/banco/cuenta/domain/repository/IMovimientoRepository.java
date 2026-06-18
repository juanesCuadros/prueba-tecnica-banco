package com.banco.cuenta.domain.repository;

import com.banco.cuenta.domain.model.Movimiento;

import java.time.LocalDateTime;
import java.util.List;

public interface IMovimientoRepository {
  Movimiento save(Movimiento movimiento);
  List<Movimiento> findByFechaBetweenAndCuentaClienteId(LocalDateTime inicio, LocalDateTime fin, String clienteId);
}
