package com.banco.cuenta.infrastructure.mapper;

import com.banco.cuenta.domain.model.Cuenta;
import com.banco.cuenta.infrastructure.output.jpa.entity.CuentaEntity;
import org.springframework.stereotype.Component;

@Component
public class CuentaMapper {

  public Cuenta toDomain(CuentaEntity entity) {
    Cuenta cuenta = new Cuenta();
    cuenta.setId(entity.getId());
    cuenta.setNumeroCuenta(entity.getNumeroCuenta());
    cuenta.setTipoCuenta(entity.getTipoCuenta());
    cuenta.setSaldoInicial(entity.getSaldoInicial());
    cuenta.setSaldoActual(entity.getSaldoActual());
    cuenta.setEstado(entity.getEstado());
    cuenta.setClienteId(entity.getClienteId());
    return cuenta;
  }

  public CuentaEntity toEntity(Cuenta domain) {
    CuentaEntity entity = new CuentaEntity();
    if (domain.getId() != null) {
      entity.setId(domain.getId());
    }
    entity.setNumeroCuenta(domain.getNumeroCuenta());
    entity.setTipoCuenta(domain.getTipoCuenta());
    entity.setSaldoInicial(domain.getSaldoInicial());
    entity.setSaldoActual(domain.getSaldoActual());
    entity.setEstado(domain.getEstado());
    entity.setClienteId(domain.getClienteId());
    return entity;
  }
}
