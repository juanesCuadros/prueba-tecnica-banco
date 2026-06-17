package com.banco.cliente.infrastructure.input.rest.mapper;

import com.banco.cliente.domain.model.Cliente;
import com.banco.cliente.infrastructure.input.rest.dto.ClienteDTO;
import org.springframework.stereotype.Component;

@Component
public class ClienteMapper {

  public Cliente toDomain(ClienteDTO dto) {
    if (dto == null) return null;

    Cliente cliente = new Cliente();
    cliente.setNombre(dto.getNombre());
    cliente.setGenero(dto.getGenero());
    cliente.setEdad(dto.getEdad());
    cliente.setIdentificacion(dto.getIdentificacion());
    cliente.setDireccion(dto.getDireccion());
    cliente.setTelefono(dto.getTelefono());
    cliente.setClienteId(dto.getClienteId());
    cliente.setContrasena(dto.getContrasena());
    cliente.setEstado(dto.getEstado());
    return cliente;
  }

  public ClienteDTO toDTO(Cliente cliente) {
    if (cliente == null) return null;

    ClienteDTO dto = new ClienteDTO();
    dto.setNombre(cliente.getNombre());
    dto.setGenero(cliente.getGenero());
    dto.setEdad(cliente.getEdad());
    dto.setIdentificacion(cliente.getIdentificacion());
    dto.setDireccion(cliente.getDireccion());
    dto.setTelefono(cliente.getTelefono());
    dto.setClienteId(cliente.getClienteId());
    dto.setContrasena(cliente.getContrasena());
    dto.setEstado(cliente.getEstado());
    return dto;
  }
}
