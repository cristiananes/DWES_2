package com.example.demo.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImplementation 
implements UserDetailsService{

	@Autowired private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		if(usuarioRepository.existsById(username)) {
			Usuario usuario = usuarioRepository.findById(username).get();
			return usuario;
			//devolverle el usuario como userDetails
		}else throw new UsernameNotFoundException("No exite " +username+ " en este servidor");
			
		

		
		
	}

}
