package com.D1.projectD1Campus.controller;

import com.D1.projectD1Campus.dto.request.ProductoRequest;
import com.D1.projectD1Campus.dto.request.UsuarioRequest;
import com.D1.projectD1Campus.dto.response.UsuarioResponse;
import com.D1.projectD1Campus.service.UsuarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Usuario")
@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
@Validated
public class UsuarioController {
    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioResponse> crear(
            @Valid @RequestBody UsuarioRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> actualizar(
            @PathVariable Long id,
            @RequestParam String password,
            @Valid @RequestBody UsuarioRequest dto){
        return ResponseEntity.ok(usuarioService.actualizar(id, password, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar (
            @PathVariable Long id,
            @RequestParam String password){
        usuarioService.eliminarUsuario(id, password);
        return new ResponseEntity<>((HttpStatus.NO_CONTENT));
    }

    @GetMapping("/filtrarPorUsername")
    public ResponseEntity<List<UsuarioResponse>> filtrarPorUsername (
            @RequestParam String username
    ) {
        return ResponseEntity.ok(usuarioService.obtenerPorUser(username));
    }
}
