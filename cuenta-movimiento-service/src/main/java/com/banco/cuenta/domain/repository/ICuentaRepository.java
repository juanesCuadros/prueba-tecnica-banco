package com.banco.cuenta.domain.repository;

import com.banco.cuenta.domain.model.Cuenta;

import java.util.List;
import java.util.Optional;

public interface ICuentaRepository {
  Cuenta save(Cuenta cuenta);
  Optional<Cuenta> findById(Long id);
  Optional<Cuenta> findByNumeroCuenta(String numeroCuenta);
  List<Cuenta> findAll();
  void deleteById(Long id);
}
