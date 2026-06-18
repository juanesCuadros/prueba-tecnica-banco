package com.banco.cuenta.infrastructure.output.jpa.repository;

import com.banco.cuenta.infrastructure.output.jpa.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICuentaJpaRepository extends JpaRepository<CuentaEntity, Long> {
  Optional<CuentaEntity> findByNumeroCuenta(String numeroCuenta);
}
