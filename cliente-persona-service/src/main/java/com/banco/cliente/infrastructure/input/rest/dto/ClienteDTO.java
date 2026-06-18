package com.banco.cliente.infrastructure.input.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

  // Datos de Persona
  @NotBlank(message = "El nombre es obligatorio")
  private String nombre;

  @NotBlank(message = "El género es obligatorio")
  private String genero;

  @NotNull(message = "La edad es obligatoria")
  @Positive(message = "La edad debe ser mayor a cero")
  private Integer edad;

  @NotBlank(message = "La identificación es obligatoria")
  private String identificacion;

  @NotBlank(message = "La dirección es obligatoria")
  private String direccion;

  @NotBlank(message = "El teléfono es obligatorio")
  private String telefono;

  // Datos de Cliente
  @NotBlank(message = "El clienteId es obligatorio")
  private String clienteId;

  @NotBlank(message = "La contraseña es obligatoria")
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String contrasena;

  @NotNull(message = "El estado es obligatorio")
  private Boolean estado;
}
