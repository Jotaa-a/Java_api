package com.D1.projectD1Campus.controller;

import com.D1.projectD1Campus.dto.request.DetalleVentaRequest;
import com.D1.projectD1Campus.dto.response.DetalleVentaResponse;
import com.D1.projectD1Campus.service.DetalleVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "DetalleVenta", description = "Procesa el CRUD de Detalles de venta")
@RestController
@RequestMapping("/api/detalle")
@RequiredArgsConstructor
@Validated
public class DetalleVentaController {
    private final DetalleVentaService detalleVentaService;

    @Operation(summary = "Ingreso datos Detalle de venta", description = "Requiere un request o un json para ingresar datos")
    @PostMapping
    public ResponseEntity<DetalleVentaResponse> crear(@Valid @RequestBody DetalleVentaRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleVentaService.crear(dto));
    }

    @Operation(summary = "Obtiene todos los detalles de venta", description = "No requiere ningun parametro")
    @GetMapping
    public ResponseEntity<List<DetalleVentaResponse>> listar(){
        return ResponseEntity.ok(detalleVentaService.listarTodos());
    }

    @Operation(summary = "Filtra los detalle por id", description = "Requiere una variable de busqueda de tipo id")
    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaResponse> obtenerDetallePorId(@PathVariable Long id){
        return ResponseEntity.ok(detalleVentaService.buscarPorId(id));
    }

    @Operation(summary = "Actualiza los detalle por id", description = "Requiere una variable de busqueda de tipo id")
    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaResponse> actualizar(@PathVariable Long id, @Valid @RequestBody DetalleVentaRequest dto){
        return ResponseEntity.ok(detalleVentaService.actualizar(id, dto));
    }

    @Operation(summary = "Elimina detalle por id", description = "Requiere una variable de busqueda de tipo id")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        detalleVentaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Busqueda por id Producto", description = "Busca el detalle de venta por el identificador de un producto")
    @GetMapping("/producto/{id}")
    public ResponseEntity<List<DetalleVentaResponse>> listarPorIdProducto(@PathVariable Long id){
        return ResponseEntity.ok(detalleVentaService.buscarPorIdProducto(id));
    }

    @Operation(summary = "Busqueda por id Venta", description = "Busca el detalle de venta por el identificador de la venta")
    @GetMapping("/venta/{id}")
    public ResponseEntity<List<DetalleVentaResponse>> listarPorIdVenta(@PathVariable Long id){
        return ResponseEntity.ok(detalleVentaService.buscarPorIdProducto(id));
    }
    @Operation(summary = "Filtro por cantidad menor que", description = "Filtra detalle de venta donde se haya vendido menos de: ")
    @GetMapping("/filtroCantidadMenorQue")
    public ResponseEntity<List<DetalleVentaResponse>> listarPorCantidadMenorQue(@RequestParam BigDecimal cantidad){
        return ResponseEntity.ok(detalleVentaService.filtrarPorCantidadesmenorOIgualQue(cantidad));
    }
}

