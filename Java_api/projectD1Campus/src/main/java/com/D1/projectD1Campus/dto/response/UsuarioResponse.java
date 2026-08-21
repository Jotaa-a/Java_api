package com.D1.projectD1Campus.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record UsuarioResponse (
        @Schema(
                description = "Identificador unico del usuario",
                example = "2"
        )
        Long id,

        @Schema(
                description = "Nombre del usuario",
                example = "Martin Francisco"
        )
        String username
) {
}
