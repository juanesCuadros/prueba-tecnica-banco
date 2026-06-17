package com.banco.cliente.infrastructure.output.jpa.repository;

import com.banco.cliente.infrastructure.output.jpa.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface IClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {
  Optional<ClienteEntity> findByClienteId(String clienteId);
  Optional<ClienteEntity> findByIdentificacion(String identificacion);
}
