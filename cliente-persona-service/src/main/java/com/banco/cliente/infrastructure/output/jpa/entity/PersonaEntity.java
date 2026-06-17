package com.banco.cliente.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonaEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long personaId;

  @Column(nullable = false, length = 100)
  private String nombre;

  @Column(nullable = false, length = 20)
  private String genero;

  @Column(nullable = false)
  private Integer edad;

  @Column(nullable = false, unique = true, length = 20)
  private String identificacion;

  @Column(nullable = false, length = 150)
  private String direccion;

  @Column(nullable = false, length = 20)
  private String telefono;
}
