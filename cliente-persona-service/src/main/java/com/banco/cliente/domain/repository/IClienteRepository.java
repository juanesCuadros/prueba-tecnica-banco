package com.banco.cliente.domain.repository;

import com.banco.cliente.domain.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteRepository {
  Cliente save(Cliente cliente);
  Optional<Cliente> findByClienteId(String clienteId);
  Optional<Cliente> findByIdentificacion(String identificacion);
  List<Cliente> findAll();
  void delete(Cliente cliente);
}
