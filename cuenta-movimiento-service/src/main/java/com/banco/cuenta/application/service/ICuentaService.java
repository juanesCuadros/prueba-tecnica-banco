package com.banco.cuenta.application.service;

import com.banco.cuenta.domain.model.Cuenta;

import java.util.List;

public interface ICuentaService {
  Cuenta crear(Cuenta cuenta);
  Cuenta obtenerPorNumeroCuenta(String numeroCuenta);
  List<Cuenta> obtenerTodas();
  Cuenta actualizar(Long id, Cuenta cuenta);
  void eliminar(Long id);
}
