package com.D1.projectD1Campus.controller;

import com.D1.projectD1Campus.dto.request.VentaRequest;
import com.D1.projectD1Campus.dto.response.VentaResponse;
import com.D1.projectD1Campus.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "Venta", description = "Procesa el CRUD de ventas")
@RestController
@RequestMapping("/api/venta")
@RequiredArgsConstructor
@Validated
public class VentaController {
    private final VentaService ventaService;

    @Operation(summary = "Crear nueva venta", description = "Requiere un request point o un json para ingresar la venta")
    @PostMapping
    public ResponseEntity<VentaResponse> crear(@Valid @RequestBody VentaRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.guardar(dto));
    }

    @Operation(summary = "Listar todas las ventas", description = "Nom requiere parametros")
    @GetMapping
    public ResponseEntity<List<VentaResponse>> listar(){
        return ResponseEntity.ok(ventaService.obtenerTodas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener venta por ID", description = "Requiere id Venta como parametro para filtrar")
    public ResponseEntity<VentaResponse> obtenerVentaPorId(@PathVariable Long id){
        return ResponseEntity.ok(ventaService.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar venta por ID", description = "Requiere id Venta para actualizar datos")
    public ResponseEntity<VentaResponse> actualizar(@PathVariable Long id, @Valid  @RequestBody VentaRequest dto){
        return ResponseEntity.ok(ventaService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar venta por ID", description = "Requiere id venta para eliminar de la base de datos")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        ventaService.eliminarVenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/mayorOIgualQue")
    @Operation(summary = "Filtrar mayor o igual que", description = "Filtas las ventas donde el total haya sido mayor o igual al parametro")
    public ResponseEntity<List<VentaResponse>> filtrarPorTotalMayorOIgualQue(
            @Parameter(description = "Total para comparar", example = "160000")
            @RequestParam BigDecimal total){
        return  ResponseEntity.ok(ventaService.findByTotalGreaterThanEqual(total));
    }

    @GetMapping("/filtroEntreFechas")
    @Operation(summary = "FIltro de venta entre fecha", description = "Filtra las venta realizadas en un rango de fechas")
    public ResponseEntity<List<VentaResponse>> filtrarEntreFechas(
            @Parameter(description = "Fecha de inicio", example = "2026-08-10")
            @RequestParam String fechaInicio,
            @Parameter(description = "Fecha de fin (debe ser mayor a la fecha de inicio)", example = "2026-08-20")
            @RequestParam String fechaFin){
        return ResponseEntity.ok(ventaService.findByFechaBetween(fechaInicio, fechaFin));
    }

    @GetMapping("/filtroPorAnio")
    @Operation(summary = "Filtro de ventas por mes y anio", description = "Filtra todas las ventas realizadas durante un mes y año especifico")
    public ResponseEntity<List<VentaResponse>> filtrarPorMesYAnio (
            @Parameter(description = "Mes para consulta", example = "10")
            @RequestParam Integer mes,
            @Parameter(description = "Anio para consulta", example = "2024")
            @RequestParam Integer anio){
        return ResponseEntity.ok(ventaService.findByMesYAnio(mes, anio));
    }
}
