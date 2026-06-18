package com.banco.cuenta.infrastructure.mapper;

import com.banco.cuenta.domain.model.Movimiento;
import com.banco.cuenta.infrastructure.output.jpa.entity.MovimientoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovimientoMapper {

  private final CuentaMapper cuentaMapper;

  public Movimiento toDomain(MovimientoEntity entity) {
    Movimiento mov = new Movimiento();
    mov.setId(entity.getId());
    mov.setFecha(entity.getFecha());
    mov.setTipoMovimiento(entity.getTipoMovimiento());
    mov.setValor(entity.getValor());
    mov.setSaldo(entity.getSaldo());
    mov.setCuenta(cuentaMapper.toDomain(entity.getCuenta()));
    return mov;
  }
}
