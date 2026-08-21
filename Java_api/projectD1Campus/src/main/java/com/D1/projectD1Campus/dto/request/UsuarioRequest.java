package com.D1.projectD1Campus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(
        @NotBlank(message = "No se permite un usuario vacio")
        @Size(min = 2, max = 50, message = "Error, el usuario debe tener entre 2 y 50 caracteres")
        String username,

        @NotBlank(message = "No se permite una contraseña vacia")
        String password
) {

}
