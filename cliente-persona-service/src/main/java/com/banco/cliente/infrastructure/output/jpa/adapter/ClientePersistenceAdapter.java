package com.banco.cliente.infrastructure.output.jpa.adapter;

import com.banco.cliente.domain.model.Cliente;
import com.banco.cliente.domain.repository.IClienteRepository;
import com.banco.cliente.infrastructure.output.jpa.entity.ClienteEntity;
import com.banco.cliente.infrastructure.output.jpa.repository.IClienteJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ClientePersistenceAdapter implements IClienteRepository {

  private final IClienteJpaRepository jpaRepository;

  @Override
  public Cliente save(Cliente cliente) {
    ClienteEntity entity = toEntity(cliente);
    ClienteEntity savedEntity = jpaRepository.save(entity);
    return toDomain(savedEntity);
  }

  @Override
  public Optional<Cliente> findByClienteId(String clienteId) {
    return jpaRepository.findByClienteId(clienteId).map(this::toDomain);
  }

  @Override
  public Optional<Cliente> findByIdentificacion(String identificacion) {
    return jpaRepository.findByIdentificacion(identificacion).map(this::toDomain);
  }

  @Override
  public List<Cliente> findAll() {
    return jpaRepository.findAll().stream()
      .map(this::toDomain)
      .collect(Collectors.toList());
  }

  @Override
  public void delete(Cliente cliente) {
    jpaRepository.delete(toEntity(cliente));
  }


  private ClienteEntity toEntity(Cliente domain) {
    ClienteEntity entity = new ClienteEntity();

    if (domain.getId() != null) {
      entity.setPersonaId(domain.getId());
    }
    entity.setNombre(domain.getNombre());
    entity.setGenero(domain.getGenero());
    entity.setEdad(domain.getEdad());
    entity.setIdentificacion(domain.getIdentificacion());
    entity.setDireccion(domain.getDireccion());
    entity.setTelefono(domain.getTelefono());
    entity.setClienteId(domain.getClienteId());
    entity.setContrasena(domain.getContrasena());
    entity.setEstado(domain.getEstado());
    return entity;
  }

  private Cliente toDomain(ClienteEntity entity) {
    return new Cliente(
      entity.getPersonaId(),
      entity.getNombre(),
      entity.getGenero(),
      entity.getEdad(),
      entity.getIdentificacion(),
      entity.getDireccion(),
      entity.getTelefono(),
      entity.getClienteId(),
      entity.getContrasena(),
      entity.getEstado()
    );
  }
}
