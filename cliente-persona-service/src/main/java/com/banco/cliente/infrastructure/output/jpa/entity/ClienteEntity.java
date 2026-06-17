package com.banco.cliente.infrastructure.output.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "cliente_id")
public class ClienteEntity extends PersonaEntity {

  @Column(name = "cliente_id_negocio", nullable = false, unique = true, length = 50)
  private String clienteId;

  @Column(nullable = false)
  private String contrasena;

  @Column(nullable = false)
  private Boolean estado;
}
