package com.banco.cliente.infrastructure.input.rest;

import com.banco.cliente.application.service.interfac.IClienteService;
import com.banco.cliente.infrastructure.input.rest.dto.ClienteDTO;
import com.banco.cliente.infrastructure.input.rest.mapper.ClienteMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

  private final IClienteService clienteService;
  private final ClienteMapper clienteMapper;

  @PostMapping
  public ResponseEntity<ClienteDTO> crear(@Valid @RequestBody ClienteDTO dto) {
    var clienteCreado = clienteService.crear(clienteMapper.toDomain(dto));
    return new ResponseEntity<>(clienteMapper.toDTO(clienteCreado), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<ClienteDTO>> listarTodos() {
    List<ClienteDTO> clientes = clienteService.obtenerTodos().stream()
      .map(clienteMapper::toDTO)
      .collect(Collectors.toList());
    return ResponseEntity.ok(clientes);
  }

  @GetMapping("/{clienteId}")
  public ResponseEntity<ClienteDTO> obtenerPorId(@PathVariable String clienteId) {
    var cliente = clienteService.obtenerPorClienteId(clienteId);
    return ResponseEntity.ok(clienteMapper.toDTO(cliente));
  }

  @PutMapping("/{clienteId}")
  public ResponseEntity<ClienteDTO> actualizar(@PathVariable String clienteId, @Valid @RequestBody ClienteDTO dto) {
    var clienteActualizado = clienteService.actualizar(clienteId, clienteMapper.toDomain(dto));
    return ResponseEntity.ok(clienteMapper.toDTO(clienteActualizado));
  }

  @DeleteMapping("/{clienteId}")
  public ResponseEntity<Void> eliminar(@PathVariable String clienteId) {
    clienteService.eliminar(clienteId);
    return ResponseEntity.noContent().build();
  }
}
