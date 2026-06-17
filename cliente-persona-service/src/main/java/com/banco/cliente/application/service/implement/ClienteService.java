package com.banco.cliente.application.service.implement;

import com.banco.cliente.application.service.interfac.IClienteService;
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
  @Transactional // Garantiza que si algo falla, no se guarde basura en la BD
  public Cliente crear(Cliente cliente) {
    // Regla de negocio: Validar que no exista la identificación o el clienteId
    clienteRepository.findByIdentificacion(cliente.getIdentificacion())
      .ifPresent(c -> { throw new RuntimeException("Ya existe una persona con esa identificación"); });

    clienteRepository.findByClienteId(cliente.getClienteId())
      .ifPresent(c -> { throw new RuntimeException("El clienteId ya está en uso"); });

    return clienteRepository.save(cliente);
  }

  @Override
  @Transactional(readOnly = true) // Mejora el rendimiento para consultas de solo lectura
  public Cliente obtenerPorClienteId(String clienteId) {
    return clienteRepository.findByClienteId(clienteId)
      .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + clienteId));
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

    // Actualizamos los campos permitidos (simulando un PUT)
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
