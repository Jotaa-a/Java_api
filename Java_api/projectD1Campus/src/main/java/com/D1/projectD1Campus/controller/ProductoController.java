package com.D1.projectD1Campus.controller;

import com.D1.projectD1Campus.dto.request.ProductoRequest;
import com.D1.projectD1Campus.dto.response.ProductoReponse;
import com.D1.projectD1Campus.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.hibernate.sql.ast.tree.expression.Summarization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "Producto", description = "Procesa el CRUD de productos")
@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    @Operation(summary = "Crear nuevo producto", description = "Requiere un request point o un json para ingresar informacion")
    @ApiResponses(
            value={
                @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
                @ApiResponse(responseCode = "400", description = "Cuerpo mal estructurado")
            }
    )
    @PostMapping
    public ResponseEntity<ProductoReponse> crear(@RequestBody ProductoRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardar(dto));
    }

    @Operation(summary = "Obtiene todos los productos", description = "No requiere ningun parametro")
    @GetMapping
    public ResponseEntity<List<ProductoReponse>> listar(){
        return ResponseEntity.ok(productoService.obtenerTodas());
    }


    @Operation(summary = "Filtra los productos por id", description = "Requiere una variable de busqueda de tipo id")
    @GetMapping("/{id}")
    public ResponseEntity<ProductoReponse> obtenerProductoPorId(
            @Parameter(description = "Id del producto a buscar", example = "1")
            @PathVariable Long id){
        return ResponseEntity.ok(productoService.obtenerPorId(id));
    }

    @Operation(summary = "Actualiza los productos por id", description = "Requiere una variable de busqueda de tipo id de la siguiente manera: http://localhost:8080/api/producto/1")
    @PutMapping("/{id}")
    public ResponseEntity<ProductoReponse> actualizar(@PathVariable Long id, @RequestBody ProductoRequest dto){
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    @Operation(summary = "Elimina los productos por id", description = "Requiere una variable de busqueda de tipo id de la siguiente manera: http://localhost:8080/api/producto/1")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        productoService.eliminarProducto(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Obtiene los productos filtrados por nombre", description = "Requiere una variable de busqueda de la siguiente forma http://localhost:8080/filtrarnombre?nombre='Pera'")
    @GetMapping("/filtrarnombre")
    public ResponseEntity<List<ProductoReponse>> buscarPorNombre(
            @Parameter(description = "Nombre a filtrar", example = "Pizza")
            @RequestParam String nombre){
        return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
    }

    @Operation(summary = "Filtro por precio de venta mayor que", description = "Requiere un precio de comparacion de forma: http://localhost:8080/precioVentMayorQue?precio=12000")
    @GetMapping("/precioVentaMayorQue")
    public ResponseEntity<List<ProductoReponse>> filtrarPrecioDeVentaMayorQue (
            @Parameter(description = "Precio de comparacion", example = "12000")
            @RequestParam BigDecimal precio){
        return ResponseEntity.ok(productoService.filtrarPrecioDeVentaMayorOIgualQue(precio));
    }

    @Operation(summary = "Filtro por precio de venta menor que", description = "Requiere un precio de comparacion de forma: http://localhost:8080/precioVentaMenorQue?precio=15000")
    @GetMapping("/precioVentaMenorQue")
    public ResponseEntity<List<ProductoReponse>> filtrarPrecioDeVentaMenorQue (
            @Parameter(description = "Precio de comparacion", example = "15000")
            @RequestParam BigDecimal precio){
        return ResponseEntity.ok(productoService.filtrarPrecioDeVentaMenorOIgualQue(precio));
    }

    @Operation(summary = "Filtro de productos en rango de precio", description = "Requiere un precio minimo y un precio maximno de venta para filtrar ")
    @GetMapping("/precioVentaEntre")
    public ResponseEntity<List<ProductoReponse>> filtrarPrecioDeVentaEntre (
            @Parameter(description = "Precio minimo de venta", example = "10000")
            @RequestParam BigDecimal precio1,
            @Parameter(description = "Precio mazimo de venta", example = "20000")
            @RequestParam BigDecimal precio2){
        return ResponseEntity.ok(productoService.filtrarPrecioDeVentaEntre(precio1, precio2));
    }

    @Operation(summary = "Filtro de nombre y precio", description = "Requiere un parametro de tipo 'nombre' y uno de tipo 'precio'")
    @GetMapping("/filtroNombreYPrecio")
    public ResponseEntity<List<ProductoReponse>> filtrarPorNombreYPrecio (
            @Parameter(description = "Nombre del producto", example = "Pizza")
            @RequestParam String nombre,
            @Parameter(description = "Precio del producto", example = "20000")
            @RequestParam BigDecimal precio){
        return ResponseEntity.ok(productoService.diltrarPorNombreAndPrecioVentaMayos(nombre, precio));
    }
}
