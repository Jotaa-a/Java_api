package com.D1.projectD1Campus.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.Date;

public record VentaResponse(
        @Schema(description = "Identificador unico de la venta",
                example = "1"
        )
        Long id,
        @Schema(description = "Fecha en que fue realizada la venta",
            example = "2026-08-18"
        )
        Date fecha,
        @Schema(description = "Total de la venta realizada",
            example = "168400"
        )
        BigDecimal total
) {
}
