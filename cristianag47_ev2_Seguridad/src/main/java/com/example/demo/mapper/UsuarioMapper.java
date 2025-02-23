package com.example.demo.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UsuarioMapper {

	@Autowired UsuarioRepository usuarioRepository;
	public Usuario deDTOAUser(UsuarioDTO usuarioDTO) {
		Usuario usuario = new Usuario();
		usuario.setUsuario(usuarioDTO.getUsuario());;
		usuario.setContrasenia(usuarioDTO.getContrasenia());
		usuario.setNombreLargo(usuarioDTO.getNombreLargo());
		return usuario;
	}
	
	public UsuarioDTO deUserADTO (Usuario usuario) {
		UsuarioDTO usuarioDTO= new UsuarioDTO();
		
		usuarioDTO.setNombreLargo(usuario.getNombreLargo());
		usuarioDTO.setContrasenia(usuarioDTO.getContrasenia());
		usuarioDTO.setUsuario(usuario.getUsuario());
		return usuarioDTO;
	}
}
