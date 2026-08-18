package com.D1.projectD1Campus.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

public record ProductoReponse(
        @Schema(
                description = "Identificador unico del producto",
                example = "2"
        )
        Long id,

        @Schema(description = "Nombre del producto",
            example = "Pizza"
        )
        String nombre,

        @Schema(description = "Precio al que fue comprado el producto",
            example = "12000"
        )
        BigDecimal precioCompra,

        @Schema(description = "Precio al que es vendido el producto",
                example = "20000"
        )
        BigDecimal precioVenta

) {
}
