package com.mialquiler.demo.controller;

import com.mialquiler.demo.entity.Usuario;
import com.mialquiler.demo.repository.UserRepository;
import com.mialquiler.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;


    //Obtener lista de todos los usuarios
    @GetMapping("/all")
    public ModelAndView obtenerListaUsuarios(){
        ModelAndView salida = new ModelAndView("users/users");
        salida.addObject("users", userRepository.findAll());
        return salida;
    }

    
    //Ruta que te lleva al formulario de crear un usuario
    @GetMapping("/crear")
    public ModelAndView crearUsuario(){
        ModelAndView salida = new ModelAndView("users/usersForm");
        salida.addObject("usuario", new Usuario());
        return salida;
    }
    //guardar un nuevo usuario
    @PostMapping("/create")
    public  ModelAndView guardarUsuario(@ModelAttribute Usuario usuario){
       Boolean guardado = userService.CompraracionUsername(usuario);

       if (guardado == true){
           ModelAndView salida = new ModelAndView("users/users");
           salida.addObject("users", userRepository.findAll());
           salida.addObject("mensaje", "Usuario guardado correctamente");
           return salida;
       }else {
           ModelAndView salida = new ModelAndView("users/usersForm");
           salida.addObject("usuario", new Usuario());
           salida.addObject("mensaje", "Nombre de usuario existente utiliza otro");
           return salida;
       }

    }
}
