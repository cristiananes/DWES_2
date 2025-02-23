package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements UserDetails {

	@Id
	private String usuario;
	private String contrasenia;
	private String nombreLargo;
	private int rol;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> permisos = new ArrayList<>();
		if (rol == 1) {
			//admin
			permisos.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			permisos.add(new SimpleGrantedAuthority("PERMISO_BORRADO"));
			permisos.add(new SimpleGrantedAuthority("PERMISO_VER"));
		}else if(rol == 10) {
			
			//usuario plano
			permisos.add(new SimpleGrantedAuthority("ROLE_USER"));
			permisos.add(new SimpleGrantedAuthority("PERMISO_VER"));
		}
		return permisos;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return contrasenia;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return usuario;
	}
	
}
