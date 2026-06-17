package com.banco.cliente.application.service.interfac;

import com.banco.cliente.domain.model.Cliente;

import java.util.List;

public interface IClienteService {
  Cliente crear(Cliente cliente);
  Cliente obtenerPorClienteId(String clienteId);
  List<Cliente> obtenerTodos();
  Cliente actualizar(String clienteId, Cliente cliente);
  void eliminar(String clienteId);
}
