package com.D1.projectD1Campus.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Date;

public record VentaRequest(
        @Schema(description = "Se ingresa una fecha valida, no mayor al dia actual", example = "2026-08-18")
        @NotNull(message = "La fecha no puede ser nula")
        @Past(message = "La fecha no puede ser mator a la fecha actual")
        Date fecha,

        @Schema(description = "Se ingresa el total de la venta que no supere los 10 digitos y 2 decimales", example = "1023846972,62")
        @NotNull(message = "El total no puede estar nulo")
        @Positive(message = "El total debe ser positivo")
        @Digits(integer = 10, fraction = 2, message = "El total debe tener maximo 10 digitos y dos decimales")
        BigDecimal total) {

}
