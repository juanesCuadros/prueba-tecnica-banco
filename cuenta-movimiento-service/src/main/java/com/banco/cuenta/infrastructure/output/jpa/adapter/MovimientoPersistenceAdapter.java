package com.banco.cuenta.infrastructure.output.jpa.adapter;

import com.banco.cuenta.domain.model.Movimiento;
import com.banco.cuenta.domain.repository.IMovimientoRepository;
import com.banco.cuenta.infrastructure.mapper.MovimientoMapper;
import com.banco.cuenta.infrastructure.output.jpa.entity.MovimientoEntity;
import com.banco.cuenta.infrastructure.output.jpa.repository.ICuentaJpaRepository;
import com.banco.cuenta.infrastructure.output.jpa.repository.IMovimientoJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MovimientoPersistenceAdapter implements IMovimientoRepository {

  private final IMovimientoJpaRepository movimientoJpaRepository;
  private final ICuentaJpaRepository cuentaJpaRepository;
  private final MovimientoMapper mapper;

  @Override
  public Movimiento save(Movimiento movimiento) {
    MovimientoEntity entity = new MovimientoEntity();
    entity.setFecha(movimiento.getFecha());
    entity.setTipoMovimiento(movimiento.getTipoMovimiento());
    entity.setValor(movimiento.getValor());
    entity.setSaldo(movimiento.getSaldo());
    // Usa referencia JPA para evitar problema de entidad detached
    entity.setCuenta(cuentaJpaRepository.getReferenceById(movimiento.getCuenta().getId()));
    return mapper.toDomain(movimientoJpaRepository.save(entity));
  }

  @Override
  public List<Movimiento> findByFechaBetweenAndCuentaClienteId(
      LocalDateTime inicio, LocalDateTime fin, String clienteId) {
    return movimientoJpaRepository
      .findByFechaBetweenAndCuentaClienteId(inicio, fin, clienteId)
      .stream()
      .map(mapper::toDomain)
      .collect(Collectors.toList());
  }
}
