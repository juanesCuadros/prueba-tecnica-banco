package com.banco.cuenta.infrastructure.input.rest;

import com.banco.cuenta.application.service.ReporteService;
import com.banco.cuenta.infrastructure.input.rest.dto.ReporteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reportes")
@RequiredArgsConstructor
public class ReporteController {

  private final ReporteService reporteService;

  @GetMapping
  public ResponseEntity<List<ReporteDTO>> obtenerReporte(
    @RequestParam String cliente,
    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String fechaInicio,
    @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") String fechaFin) {

    List<ReporteDTO> reporte = reporteService.generarReporte(cliente, fechaInicio, fechaFin);
    return ResponseEntity.ok(reporte);
  }
}
