package com.banco.cuenta.infrastructure.input.rest;

import com.banco.cuenta.application.service.ICuentaService;
import com.banco.cuenta.domain.model.Cuenta;
import com.banco.cuenta.infrastructure.input.rest.dto.CuentaDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

  private final ICuentaService cuentaService;

  @PostMapping
  public ResponseEntity<CuentaDTO> crear(@Valid @RequestBody CuentaDTO dto) {
    return new ResponseEntity<>(toDTO(cuentaService.crear(toDomain(dto))), HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<CuentaDTO>> listarTodas() {
    List<CuentaDTO> cuentas = cuentaService.obtenerTodas().stream()
      .map(this::toDTO)
      .collect(Collectors.toList());
    return ResponseEntity.ok(cuentas);
  }

  @GetMapping("/{numeroCuenta}")
  public ResponseEntity<CuentaDTO> obtenerPorNumero(@PathVariable String numeroCuenta) {
    return ResponseEntity.ok(toDTO(cuentaService.obtenerPorNumeroCuenta(numeroCuenta)));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CuentaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CuentaDTO dto) {
    return ResponseEntity.ok(toDTO(cuentaService.actualizar(id, toDomain(dto))));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> eliminar(@PathVariable Long id) {
    cuentaService.eliminar(id);
    return ResponseEntity.noContent().build();
  }

  private Cuenta toDomain(CuentaDTO dto) {
    Cuenta c = new Cuenta();
    c.setNumeroCuenta(dto.getNumeroCuenta());
    c.setTipoCuenta(dto.getTipoCuenta());
    c.setSaldoInicial(dto.getSaldoInicial());
    c.setEstado(dto.getEstado());
    c.setClienteId(dto.getClienteId());
    return c;
  }

  private CuentaDTO toDTO(Cuenta cuenta) {
    CuentaDTO dto = new CuentaDTO();
    dto.setId(cuenta.getId());
    dto.setNumeroCuenta(cuenta.getNumeroCuenta());
    dto.setTipoCuenta(cuenta.getTipoCuenta());
    dto.setSaldoInicial(cuenta.getSaldoInicial());
    dto.setSaldoActual(cuenta.getSaldoActual());
    dto.setEstado(cuenta.getEstado());
    dto.setClienteId(cuenta.getClienteId());
    return dto;
  }
}
