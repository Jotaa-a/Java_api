package com.D1.projectD1Campus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ProductoRequest(
        @Schema(description = "Se ingresa el nombre del prpducto entre 2 y 50 caracteres", example = "Pizza")
        @NotBlank(message = "No se permite el nombre vacio")
        @Size(min=2, max=50, message = "Error, el nombre debe tener entre 2 y 50 caracteres")
        String nombre,

        @Schema(description = "Se ingresa la descripcion del producto entre 2 y 50 caracteres", example = "Pizza con doble carne")
        @NotBlank(message = "No se permite la descripcion vacía.")
        @Size(min=2, max = 50, message = "Error, el nombre debe tener entre 2 y 50 caracteres")
        String descripcion,

        @Schema(description = "Se imgresa el precio de compra del producto", example = "12000")
        @NotNull(message = "El precio de compra no puede estar nulo")
        @Positive(message = "El precio de compra debe ser positivo")
        @Digits(integer = 10, fraction = 2, message = "El precio de compra debe tener maximo 10 digitos y 2 decimales")
        BigDecimal precioCompra,

        @Schema(description = "Se imgresa el precio de venta del producto", example = "20000")
        @NotNull(message = "El precio de venta no puede estar nulo")
        @Positive(message = "El precio de venta debe ser positivo")
        @Digits(integer = 10, fraction = 2, message = "El precio de venta debe tener maximo 10 digitos y 2 decimales")
        BigDecimal precioVenta) {

}
