package com.banco.cuenta.infrastructure.output.jpa.adapter;

import com.banco.cuenta.domain.model.Cuenta;
import com.banco.cuenta.domain.repository.ICuentaRepository;
import com.banco.cuenta.infrastructure.mapper.CuentaMapper;
import com.banco.cuenta.infrastructure.output.jpa.repository.ICuentaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CuentaPersistenceAdapter implements ICuentaRepository {

  private final ICuentaJpaRepository jpaRepository;
  private final CuentaMapper mapper;

  @Override
  public Cuenta save(Cuenta cuenta) {
    return mapper.toDomain(jpaRepository.save(mapper.toEntity(cuenta)));
  }

  @Override
  public Optional<Cuenta> findById(Long id) {
    return jpaRepository.findById(id).map(mapper::toDomain);
  }

  @Override
  public Optional<Cuenta> findByNumeroCuenta(String numeroCuenta) {
    return jpaRepository.findByNumeroCuenta(numeroCuenta).map(mapper::toDomain);
  }

  @Override
  public List<Cuenta> findAll() {
    return jpaRepository.findAll().stream()
      .map(mapper::toDomain)
      .collect(Collectors.toList());
  }

  @Override
  public void deleteById(Long id) {
    jpaRepository.deleteById(id);
  }
}
