package com.D1.projectD1Campus.service.impl;

import com.D1.projectD1Campus.dto.request.UsuarioRequest;
import com.D1.projectD1Campus.dto.response.UsuarioResponse;
import com.D1.projectD1Campus.excepcion.BusinessRuleException;
import com.D1.projectD1Campus.mapper.UsuarioMapper;
import com.D1.projectD1Campus.modelo.Usuario;
import com.D1.projectD1Campus.repositorio.UsuarioRepository;
import com.D1.projectD1Campus.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;


    @Override
    public UsuarioResponse crear(UsuarioRequest dto) {
        Usuario usuario = usuarioMapper.dtoToEntity(dto);
        return usuarioMapper.entityToDto(usuarioRepository.save(usuario));
    }

    @Override
    public UsuarioResponse actualizar(Long id, String password, UsuarioRequest dto) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        if(!usuario.getPassword().equals(password)){
            throw new BusinessRuleException(
                    "Contraseña no valida"
            );
        }
        return usuarioMapper.entityToDto(usuarioRepository.save(usuario));

    }

    @Override
    public void eliminarUsuario(Long id, String password) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("No se encontro ningun usuario"));
        if (!usuario.getPassword().equals(password)){
            throw new BusinessRuleException(
                    "Contraseña incorrecta"
            );
        }
        usuarioRepository.delete(usuario);
    }

    @Override
    public List<UsuarioResponse> obtenerPorUser(String username) {
        List<Usuario> usuarios = usuarioRepository.findByUsername(username);
        return usuarios.stream().map(usuarioMapper::entityToDto).toList();
    }
}
