package com.D1.projectD1Campus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record DetalleVentaRequest(
        @Schema(description = "Se ingresa el id de la venta realizada", example = "13")
        @NotNull(message = "La venta no puede ser nula")
        @Positive(message = "El codifo de venra debe ser positiva")
        Long ventaId,

        @Schema(description = "Se ingrea el id del producto vendidio", example = "11")
        @NotNull(message = "El producto no puede ser nulo")
        @Positive(message = "El codifo del producto debe ser positivo")
        Long productoId,

        @Schema(description = "se ingresa la cantidad de productos vendidos", example = "30")
        @NotNull(message = "La cantidad no puede ser nulo")
        @Positive(message = "La cantidad debe ser positivo")
        @Digits(integer=10, fraction = 2, message = "La cantidad debe tener mazimo 10 numeros y 2 decimales")
        BigDecimal cantidad,

        @Schema(description = "Se ingresa el subtotal de la venta que no supere los 10 digitos y 2 decimales", example = "1023846972,62")
        @NotNull(message = "El subtotal no puede ser nulo")
        @Positive(message = "El subtotal debe ser positivo")
        @Digits(integer=10, fraction = 2, message = "El subtotal debe tener maximo 10 digitos y 2 decimales")
        BigDecimal subtotal
) {
}
