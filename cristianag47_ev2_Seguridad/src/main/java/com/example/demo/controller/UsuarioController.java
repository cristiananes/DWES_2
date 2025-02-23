package com.example.demo.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.DTO.UsuarioDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.mapper.UsuarioMapper;
import com.example.demo.repository.UsuarioRepository;



import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@EnableMethodSecurity
public class UsuarioController {

	@Autowired UsuarioRepository usuarioRepository;
	@Autowired UsuarioMapper usuarioMapper;
	
	@PreAuthorize ("authentication.name==#user OR hasRole('ADMIN')" )
	@GetMapping("user/{usuario}")
	public ModelAndView getMethodName(@PathVariable String usuario) {

		ModelAndView salida = new ModelAndView("usuarios/user");

		Optional<Usuario> userOptional = usuarioRepository.findById(usuario);
		if (userOptional.isPresent()) {

			salida.addObject("user", userOptional.get());
		}

		return salida;
	}
	
	@GetMapping("/user")
	public ModelAndView getTodosUsers() {

		ModelAndView salida = new ModelAndView("usuarios/users");

		Iterable<Usuario> lista = usuarioRepository.findAll();
		salida.addObject("users", lista);
		 
		return salida;
	}

	@GetMapping("/user/adduser")
	public ModelAndView adduser() {

		ModelAndView salida = new ModelAndView("usuarios/userForm");

		salida.addObject("user", new UsuarioDTO());

		return salida;
	}
	
	@GetMapping("/user/updateuser/{usuario}")
	public ModelAndView updateuser(@PathVariable String usuario) {

		ModelAndView salida = new ModelAndView("usuarios/userForm");

		if (usuarioRepository.existsById(usuario)) {
			salida.addObject("user", usuarioMapper.deUserADTO(usuarioRepository.findById(usuario).get()));
		} else
			salida.setViewName("redirect:/user");

		return salida;
	}
	
	@PostMapping("/user/saveuser")
	public String saveuser(@ModelAttribute UsuarioDTO usuarioDTO) {

		log.info("Se recibe para guardar: "+usuarioDTO);
		
		usuarioRepository.save(usuarioMapper.deDTOAUser(usuarioDTO));

		return "redirect:/user";
	}

	@GetMapping("/user/delete/{usuario}")
	public String deleteuser(@PathVariable String usuario) {

		usuarioRepository.deleteById(usuario);

		return "redirect:/user";
	}
	
}
