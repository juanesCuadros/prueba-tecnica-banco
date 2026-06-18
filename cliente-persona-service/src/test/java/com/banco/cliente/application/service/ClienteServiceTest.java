package com.banco.cliente.application.service;

import com.banco.cliente.application.service.implement.ClienteService;
import com.banco.cliente.domain.exception.RecursoNoEncontradoException;
import com.banco.cliente.domain.exception.ReglaNegocioException;
import com.banco.cliente.domain.model.Cliente;
import com.banco.cliente.domain.repository.IClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

  @Mock
  private IClienteRepository clienteRepository;

  @InjectMocks
  private ClienteService clienteService;

  private Cliente clienteBase;

  @BeforeEach
  void setUp() {
    clienteBase = new Cliente(1L, "Jose Lema", "Masculino", 35,
      "1234567890", "Otavalo sn", "098254785",
      "jlema", "1234", true);
  }

  @Test
  @DisplayName("Crear cliente exitosamente cuando no existe duplicado")
  void crear_debeRetornarClienteCreado_cuandoDatosValidos() {
    when(clienteRepository.findByIdentificacion(anyString())).thenReturn(Optional.empty());
    when(clienteRepository.findByClienteId(anyString())).thenReturn(Optional.empty());
    when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteBase);

    Cliente resultado = clienteService.crear(clienteBase);

    assertThat(resultado).isNotNull();
    assertThat(resultado.getClienteId()).isEqualTo("jlema");
    verify(clienteRepository, times(1)).save(any(Cliente.class));
  }

  @Test
  @DisplayName("Crear cliente falla cuando la identificación ya existe")
  void crear_debeLanzarReglaNegocio_cuandoIdentificacionDuplicada() {
    when(clienteRepository.findByIdentificacion(anyString())).thenReturn(Optional.of(clienteBase));

    assertThatThrownBy(() -> clienteService.crear(clienteBase))
      .isInstanceOf(ReglaNegocioException.class)
      .hasMessageContaining("identificación");

    verify(clienteRepository, never()).save(any());
  }

  @Test
  @DisplayName("Crear cliente falla cuando el clienteId ya existe")
  void crear_debeLanzarReglaNegocio_cuandoClienteIdDuplicado() {
    when(clienteRepository.findByIdentificacion(anyString())).thenReturn(Optional.empty());
    when(clienteRepository.findByClienteId(anyString())).thenReturn(Optional.of(clienteBase));

    assertThatThrownBy(() -> clienteService.crear(clienteBase))
      .isInstanceOf(ReglaNegocioException.class)
      .hasMessageContaining("clienteId");

    verify(clienteRepository, never()).save(any());
  }

  @Test
  @DisplayName("Obtener cliente por clienteId existente")
  void obtenerPorClienteId_debeRetornarCliente_cuandoExiste() {
    when(clienteRepository.findByClienteId("jlema")).thenReturn(Optional.of(clienteBase));

    Cliente resultado = clienteService.obtenerPorClienteId("jlema");

    assertThat(resultado).isNotNull();
    assertThat(resultado.getNombre()).isEqualTo("Jose Lema");
  }

  @Test
  @DisplayName("Obtener cliente lanza excepción cuando no existe")
  void obtenerPorClienteId_debeLanzarRecursoNoEncontrado_cuandoNoExiste() {
    when(clienteRepository.findByClienteId(anyString())).thenReturn(Optional.empty());

    assertThatThrownBy(() -> clienteService.obtenerPorClienteId("inexistente"))
      .isInstanceOf(RecursoNoEncontradoException.class)
      .hasMessageContaining("Cliente no encontrado");
  }

  @Test
  @DisplayName("Obtener todos los clientes retorna lista completa")
  void obtenerTodos_debeRetornarListaDeClientes() {
    when(clienteRepository.findAll()).thenReturn(List.of(clienteBase));

    List<Cliente> resultado = clienteService.obtenerTodos();

    assertThat(resultado).hasSize(1);
    assertThat(resultado.get(0).getClienteId()).isEqualTo("jlema");
  }

  @Test
  @DisplayName("Actualizar cliente exitosamente cuando existe")
  void actualizar_debeActualizarCampos_cuandoClienteExiste() {
    Cliente clienteActualizado = new Cliente(null, "Jose Lema Actualizado", "Masculino",
      36, "1234567890", "Nueva dirección", "099999999",
      "jlema", "nueva-clave", true);

    when(clienteRepository.findByClienteId("jlema")).thenReturn(Optional.of(clienteBase));
    when(clienteRepository.save(any(Cliente.class))).thenAnswer(inv -> inv.getArgument(0));

    Cliente resultado = clienteService.actualizar("jlema", clienteActualizado);

    assertThat(resultado.getNombre()).isEqualTo("Jose Lema Actualizado");
    assertThat(resultado.getDireccion()).isEqualTo("Nueva dirección");
    verify(clienteRepository).save(any(Cliente.class));
  }

  @Test
  @DisplayName("Eliminar cliente exitosamente cuando existe")
  void eliminar_debeEliminarCliente_cuandoExiste() {
    when(clienteRepository.findByClienteId("jlema")).thenReturn(Optional.of(clienteBase));
    doNothing().when(clienteRepository).delete(any(Cliente.class));

    assertThatCode(() -> clienteService.eliminar("jlema")).doesNotThrowAnyException();

    verify(clienteRepository).delete(any(Cliente.class));
  }
}
