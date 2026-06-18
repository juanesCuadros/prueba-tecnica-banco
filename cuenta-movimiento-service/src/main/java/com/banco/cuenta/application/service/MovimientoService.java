package com.banco.cuenta.application.service;

import com.banco.cuenta.domain.exception.RecursoNoEncontradoException;
import com.banco.cuenta.domain.exception.ReglaNegocioException;
import com.banco.cuenta.domain.model.Cuenta;
import com.banco.cuenta.domain.model.Movimiento;
import com.banco.cuenta.domain.repository.ICuentaRepository;
import com.banco.cuenta.domain.repository.IMovimientoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MovimientoService implements IMovimientoService {

  private final ICuentaRepository cuentaRepository;
  private final IMovimientoRepository movimientoRepository;

  @Override
  @Transactional
  public Movimiento registrarMovimiento(String numeroCuenta, BigDecimal valor) {
    Cuenta cuenta = cuentaRepository.findByNumeroCuenta(numeroCuenta)
      .orElseThrow(() -> new RecursoNoEncontradoException("Cuenta no encontrada: " + numeroCuenta));

    if (!Boolean.TRUE.equals(cuenta.getEstado())) {
      throw new ReglaNegocioException("La cuenta está inactiva");
    }

    BigDecimal nuevoSaldo = cuenta.getSaldoActual().add(valor);
    if (nuevoSaldo.compareTo(BigDecimal.ZERO) < 0) {
      throw new ReglaNegocioException("Saldo no disponible");
    }

    cuenta.setSaldoActual(nuevoSaldo);
    cuentaRepository.save(cuenta);

    Movimiento mov = new Movimiento();
    mov.setFecha(LocalDateTime.now());
    mov.setTipoMovimiento(valor.compareTo(BigDecimal.ZERO) >= 0 ? "CRÉDITO" : "DÉBITO");
    mov.setValor(valor);
    mov.setSaldo(nuevoSaldo);
    mov.setCuenta(cuenta);

    return movimientoRepository.save(mov);
  }
}
