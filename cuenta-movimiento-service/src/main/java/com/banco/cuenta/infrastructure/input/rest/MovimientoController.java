package com.banco.cuenta.infrastructure.input.rest;

import com.banco.cuenta.application.service.IMovimientoService;
import com.banco.cuenta.domain.model.Movimiento;
import com.banco.cuenta.infrastructure.input.rest.dto.MovimientoRequestDTO;
import com.banco.cuenta.infrastructure.input.rest.dto.MovimientoResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
@RequiredArgsConstructor
public class MovimientoController {

  private final IMovimientoService movimientoService;

  @PostMapping
  public ResponseEntity<MovimientoResponseDTO> registrar(@Valid @RequestBody MovimientoRequestDTO dto) {
    Movimiento mov = movimientoService.registrarMovimiento(dto.getNumeroCuenta(), dto.getValor());
    return new ResponseEntity<>(toDTO(mov), HttpStatus.CREATED);
  }

  private MovimientoResponseDTO toDTO(Movimiento mov) {
    return MovimientoResponseDTO.builder()
      .id(mov.getId())
      .fecha(mov.getFecha())
      .tipoMovimiento(mov.getTipoMovimiento())
      .valor(mov.getValor())
      .saldo(mov.getSaldo())
      .numeroCuenta(mov.getCuenta().getNumeroCuenta())
      .build();
  }
}
