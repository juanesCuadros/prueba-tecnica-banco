package com.banco.cliente.infrastructure.input.rest;

import com.banco.cliente.infrastructure.output.jpa.repository.IClienteJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("dev")
class ClienteControllerIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private IClienteJpaRepository clienteJpaRepository;

  // JSON literal: evita que @JsonProperty(WRITE_ONLY) omita la contraseña al serializar el DTO
  private static final String CLIENTE_JSON = """
    {
      "nombre": "Jose Lema",
      "genero": "Masculino",
      "edad": 35,
      "identificacion": "1234567890",
      "direccion": "Otavalo sn y principal",
      "telefono": "098254785",
      "clienteId": "jlema",
      "contrasena": "1234",
      "estado": true
    }
    """;

  @BeforeEach
  void limpiarDatos() {
    clienteJpaRepository.deleteAll();
  }

  @Test
  @DisplayName("POST /clientes crea un cliente y retorna 201")
  void crearCliente_debeRetornar201_cuandoDatosValidos() throws Exception {
    mockMvc.perform(post("/clientes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(CLIENTE_JSON))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.clienteId").value("jlema"))
      .andExpect(jsonPath("$.nombre").value("Jose Lema"))
      .andExpect(jsonPath("$.contrasena").doesNotExist());
  }

  @Test
  @DisplayName("GET /clientes/{clienteId} retorna 404 cuando no existe")
  void obtenerCliente_debeRetornar404_cuandoNoExiste() throws Exception {
    mockMvc.perform(get("/clientes/inexistente"))
      .andExpect(status().isNotFound())
      .andExpect(jsonPath("$.status").value(404));
  }

  @Test
  @DisplayName("POST /clientes retorna 400 cuando identificación duplicada")
  void crearCliente_debeRetornar400_cuandoIdentificacionDuplicada() throws Exception {
    mockMvc.perform(post("/clientes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(CLIENTE_JSON))
      .andExpect(status().isCreated());

    String clienteDuplicadoJson = """
      {
        "nombre": "Jose Lema Copia",
        "genero": "Masculino",
        "edad": 35,
        "identificacion": "1234567890",
        "direccion": "Otra direccion",
        "telefono": "099999999",
        "clienteId": "jlema2",
        "contrasena": "abcd",
        "estado": true
      }
      """;

    mockMvc.perform(post("/clientes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(clienteDuplicadoJson))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.message").value("Ya existe una persona con esa identificación"));
  }

  @Test
  @DisplayName("POST /clientes retorna 400 cuando faltan campos obligatorios")
  void crearCliente_debeRetornar400_cuandoFaltanCampos() throws Exception {
    String incompleto = """
      {
        "nombre": "Solo nombre"
      }
      """;

    mockMvc.perform(post("/clientes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(incompleto))
      .andExpect(status().isBadRequest())
      .andExpect(jsonPath("$.error").value("Error de Validación"));
  }

  @Test
  @DisplayName("DELETE /clientes/{clienteId} retorna 204 cuando existe")
  void eliminarCliente_debeRetornar204_cuandoExiste() throws Exception {
    mockMvc.perform(post("/clientes")
        .contentType(MediaType.APPLICATION_JSON)
        .content(CLIENTE_JSON))
      .andExpect(status().isCreated());

    mockMvc.perform(delete("/clientes/jlema"))
      .andExpect(status().isNoContent());
  }
}
