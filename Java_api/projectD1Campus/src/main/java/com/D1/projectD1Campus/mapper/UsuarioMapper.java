package com.D1.projectD1Campus.mapper;

import com.D1.projectD1Campus.dto.request.UsuarioRequest;
import com.D1.projectD1Campus.dto.response.UsuarioResponse;
import com.D1.projectD1Campus.modelo.Usuario;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public UsuarioResponse entityToDto(Usuario usuario){
        if(usuario == null) return null;
        return new UsuarioResponse(
                usuario.getId(),
                usuario.getUsername()
        );
    }

    public Usuario dtoToEntity(UsuarioRequest dto){
        if(dto == null) return null;
        Usuario usuario = new Usuario();
        usuario.setUsername(dto.username());
        usuario.setPassword(dto.password());
        return usuario;
    }

    public void updateEntityToDto(Usuario usuario, UsuarioRequest dto){
        if(dto == null || usuario == null) return;
        usuario.setUsername(dto.username());
        usuario.setPassword(dto.password());
    }
}
