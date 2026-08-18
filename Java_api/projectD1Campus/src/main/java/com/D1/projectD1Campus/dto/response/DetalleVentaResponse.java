package com.D1.projectD1Campus.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Informacion explicita de detalle de venta")
public record DetalleVentaResponse(
        @Schema(description = "Identidifacor unico detalle de venta",
                example = "1"
        )
        Long id,
        @Schema(description = "Informacion de la venta asociada como foranea"
        )
        VentaResponse ventaResponse,

        @Schema(description = "Informacion del pŕoducto vendido como foraneo")
        ProductoReponse productoReponse,
        @Schema(description = "Cantidad de productos vendidos",
            example = "3"
        )
        BigDecimal cantidad,
        @Schema(description = "Subtotal de la venta realizada",
            example = "130600"
        )
        BigDecimal subtotal

) {
}
