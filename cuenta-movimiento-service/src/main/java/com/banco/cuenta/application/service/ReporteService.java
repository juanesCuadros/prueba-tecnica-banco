package com.banco.cuenta.application.service;

import com.banco.cuenta.domain.model.Movimiento;
import com.banco.cuenta.domain.repository.IMovimientoRepository;
import com.banco.cuenta.infrastructure.input.rest.dto.ReporteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReporteService {

  private final IMovimientoRepository movimientoRepository;

  @Transactional(readOnly = true)
  public List<ReporteDTO> generarReporte(String clienteId, String fechaInicio, String fechaFin) {
    LocalDateTime inicio = LocalDate.parse(fechaInicio).atStartOfDay();
    LocalDateTime fin = LocalDate.parse(fechaFin).atTime(LocalTime.MAX);

    List<Movimiento> movimientos = movimientoRepository
      .findByFechaBetweenAndCuentaClienteId(inicio, fin, clienteId);

    return movimientos.stream()
      .map(mov -> ReporteDTO.builder()
        .fecha(mov.getFecha())
        .cliente(mov.getCuenta().getClienteId())
        .numeroCuenta(mov.getCuenta().getNumeroCuenta())
        .tipoCuenta(mov.getCuenta().getTipoCuenta())
        .saldoInicial(mov.getCuenta().getSaldoInicial())
        .estado(mov.getCuenta().getEstado())
        .movimiento(mov.getTipoMovimiento() + ": " + mov.getValor())
        .saldoDisponible(mov.getSaldo())
        .build())
      .collect(Collectors.toList());
  }
}
