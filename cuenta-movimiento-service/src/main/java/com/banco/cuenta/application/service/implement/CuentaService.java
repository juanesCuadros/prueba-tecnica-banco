package com.banco.cuenta.application.service.implement;

import com.banco.cuenta.application.service.ICuentaService;
import com.banco.cuenta.domain.exception.RecursoNoEncontradoException;
import com.banco.cuenta.domain.exception.ReglaNegocioException;
import com.banco.cuenta.domain.model.Cuenta;
import com.banco.cuenta.domain.repository.ICuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaService implements ICuentaService {

  private final ICuentaRepository cuentaRepository;

  @Override
  @Transactional
  public Cuenta crear(Cuenta cuenta) {
    cuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta())
      .ifPresent(c -> { throw new ReglaNegocioException("Ya existe una cuenta con el número: " + cuenta.getNumeroCuenta()); });

    cuenta.setSaldoActual(cuenta.getSaldoInicial());
    return cuentaRepository.save(cuenta);
  }

  @Override
  @Transactional(readOnly = true)
  public Cuenta obtenerPorNumeroCuenta(String numeroCuenta) {
    return cuentaRepository.findByNumeroCuenta(numeroCuenta)
      .orElseThrow(() -> new RecursoNoEncontradoException("Cuenta no encontrada con número: " + numeroCuenta));
  }

  @Override
  @Transactional(readOnly = true)
  public List<Cuenta> obtenerTodas() {
    return cuentaRepository.findAll();
  }

  @Override
  @Transactional
  public Cuenta actualizar(Long id, Cuenta cuentaActualizada) {
    Cuenta existente = cuentaRepository.findById(id)
      .orElseThrow(() -> new RecursoNoEncontradoException("Cuenta no encontrada con id: " + id));

    existente.setTipoCuenta(cuentaActualizada.getTipoCuenta());
    existente.setEstado(cuentaActualizada.getEstado());
    existente.setClienteId(cuentaActualizada.getClienteId());
    return cuentaRepository.save(existente);
  }

  @Override
  @Transactional
  public void eliminar(Long id) {
    cuentaRepository.findById(id)
      .orElseThrow(() -> new RecursoNoEncontradoException("Cuenta no encontrada con id: " + id));
    cuentaRepository.deleteById(id);
  }
}
