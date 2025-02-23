package com.marcosd.seguridad.controllers;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;


import com.marcosd.seguridad.beans.User;
import com.marcosd.seguridad.repositorios.UserRepository;

@Controller
public class UserController {

	@Autowired
	private UserRepository userRepositorio;

	@GetMapping("/{id}")
	public ModelAndView getMethodName(@PathVariable Long id) {

		ModelAndView salida = new ModelAndView("users/user");

		Optional<User> userOptional = userRepositorio.findById(id);
		if (userOptional.isPresent()) {

			salida.addObject("user", userOptional.get());
		}

		return salida;
	}

	@GetMapping("/user")
	public ModelAndView getTodosUsers() {

		ModelAndView salida = new ModelAndView("users/users");

		Iterable<User> lista = userRepositorio.findAll();
		salida.addObject("users", lista);
		 
		return salida;
	}

	@GetMapping("/user/adduser")
	public ModelAndView adduser() {

		ModelAndView salida = new ModelAndView("users/userForm");

		salida.addObject("user", new User());

		return salida;
	}

	
	@GetMapping("/user/updateuser/{id}")
	public ModelAndView updateuser(@PathVariable Long id) {

		ModelAndView salida = new ModelAndView("users/userForm");

		if (userRepositorio.existsById(id)) {
			salida.addObject("user", userRepositorio.findById(id).get());
		} else
			salida.setViewName("redirect:/user");

		return salida;
	}

	
	@PostMapping("/user/saveuser")
	public String saveuser(@ModelAttribute User user) {

		userRepositorio.save(user);

		return "redirect:/user";

	}

	@GetMapping("/user/delete/{id}")
	public String deleteuser(@PathVariable Long id) {

		userRepositorio.deleteById(id);

		return "redirect:/user";
	}

}
