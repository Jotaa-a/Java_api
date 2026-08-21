package com.D1.projectD1Campus.service;

import com.D1.projectD1Campus.dto.request.UsuarioRequest;
import com.D1.projectD1Campus.dto.response.UsuarioResponse;

import java.util.List;

public interface UsuarioService {
    UsuarioResponse crear (UsuarioRequest dto);
    UsuarioResponse actualizar (Long id, String password, UsuarioRequest dto);
    void eliminarUsuario (Long id, String password);
    List<UsuarioResponse> obtenerPorUser(String user);
}
