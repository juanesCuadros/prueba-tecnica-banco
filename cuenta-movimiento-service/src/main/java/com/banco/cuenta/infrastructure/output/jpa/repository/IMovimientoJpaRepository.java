package com.banco.cuenta.infrastructure.output.jpa.repository;

import com.banco.cuenta.infrastructure.output.jpa.entity.MovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IMovimientoJpaRepository extends JpaRepository<MovimientoEntity, Long> {
  List<MovimientoEntity> findByFechaBetweenAndCuentaClienteId(
    LocalDateTime inicio, LocalDateTime fin, String clienteId);
}
