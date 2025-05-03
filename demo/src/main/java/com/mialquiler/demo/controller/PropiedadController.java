package com.mialquiler.demo.controller;

import com.mialquiler.demo.entity.Propiedad;
import com.mialquiler.demo.repository.UserRepository;
import com.mialquiler.demo.service.PropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/propiedades")
public class PropiedadController {

    @Autowired
    private PropiedadService propiedadService;

    @Autowired
    private UserRepository userRepository;

    // Mostrar lista de propiedades
    @GetMapping("/all")
    public ModelAndView listarPropiedades() {
        ModelAndView mav = new ModelAndView("propiedades/propiedades");
        mav.addObject("propiedades", propiedadService.listarTodas());
        return mav;
    }

    // Mostrar formulario para crear propiedad
    @GetMapping("/crear")
    public ModelAndView mostrarFormulario() {
        ModelAndView mav = new ModelAndView("propiedades/propiedadForm");
        mav.addObject("propiedad", new Propiedad());
        mav.addObject("usuarios", userRepository.findAll()); // Para seleccionar dueño
        return mav;
    }

    // Guardar propiedad
    @PostMapping("/crear")
    public ModelAndView guardar(@ModelAttribute Propiedad propiedad) {
        propiedadService.guardar(propiedad);
        ModelAndView mav = new ModelAndView("redirect:/propiedades/all");
        return mav;
    }

    // Eliminar propiedad (por ID)
    @GetMapping("/eliminar/{id}")
    public ModelAndView eliminar(@PathVariable Long id) {
        propiedadService.eliminar(id);
        return new ModelAndView("redirect:/propiedades/all");
    }
}
