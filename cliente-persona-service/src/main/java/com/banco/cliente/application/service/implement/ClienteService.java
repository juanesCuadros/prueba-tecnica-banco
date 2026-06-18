package com.banco.cliente.application.service.implement;

import com.banco.cliente.application.service.interfac.IClienteService;
import com.banco.cliente.domain.exception.RecursoNoEncontradoException;
import com.banco.cliente.domain.exception.ReglaNegocioException;
import com.banco.cliente.domain.model.Cliente;
import com.banco.cliente.domain.repository.IClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService {

  private final IClienteRepository clienteRepository;

  @Override
  @Transactional
  public Cliente crear(Cliente cliente) {
    clienteRepository.findByIdentificacion(cliente.getIdentificacion())
      .ifPresent(c -> { throw new ReglaNegocioException("Ya existe una persona con esa identificación"); });

    clienteRepository.findByClienteId(cliente.getClienteId())
      .ifPresent(c -> { throw new ReglaNegocioException("El clienteId ya está en uso"); });

    return clienteRepository.save(cliente);
  }

  @Override
  @Transactional(readOnly = true)
  public Cliente obtenerPorClienteId(String clienteId) {
    return clienteRepository.findByClienteId(clienteId)
      .orElseThrow(() -> new RecursoNoEncontradoException("Cliente no encontrado con clienteId: " + clienteId));
  }

  @Override
  @Transactional(readOnly = true)
  public List<Cliente> obtenerTodos() {
    return clienteRepository.findAll();
  }

  @Override
  @Transactional
  public Cliente actualizar(String clienteId, Cliente clienteActualizado) {
    Cliente clienteExistente = obtenerPorClienteId(clienteId);

    clienteExistente.setNombre(clienteActualizado.getNombre());
    clienteExistente.setGenero(clienteActualizado.getGenero());
    clienteExistente.setEdad(clienteActualizado.getEdad());
    clienteExistente.setDireccion(clienteActualizado.getDireccion());
    clienteExistente.setTelefono(clienteActualizado.getTelefono());
    clienteExistente.setContrasena(clienteActualizado.getContrasena());
    clienteExistente.setEstado(clienteActualizado.getEstado());

    return clienteRepository.save(clienteExistente);
  }

  @Override
  @Transactional
  public void eliminar(String clienteId) {
    Cliente cliente = obtenerPorClienteId(clienteId);
    clienteRepository.delete(cliente);
  }
}
