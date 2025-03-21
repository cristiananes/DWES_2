package com.example.demo.controladores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Astronauta;
import com.example.demo.repository.AstronautaRepository;
import com.example.demo.service.AstronautaService;


@Controller
public class AstronautaController {

    @Autowired
    AstronautaRepository astronautaRepository;
    @Autowired
    AstronautaService astronautaService;

    @GetMapping("/astronauta/{id}")
    public ModelAndView getAstronauta(@PathVariable Long id) {
        ModelAndView salida = new ModelAndView("astronauta/astronauta"); 
        Optional<Astronauta> astronautaOptional = astronautaRepository.findById(id);
        if (astronautaOptional.isPresent()) {
            salida.addObject("astronauta", astronautaOptional.get());
        }
        return salida;
    }

    @GetMapping("/astronauta")
    public ModelAndView getTodosAstronautas() {
        ModelAndView salida = new ModelAndView("redirect:/astronauta/pagina/0");
        return salida;
    }

    @GetMapping("/astronauta/pagina/{pagina}")
    public ModelAndView getTodosAstronautaspag(@PathVariable int pagina) {
        ModelAndView salida = new ModelAndView("astronauta/astronautas"); 
        Page<Astronauta> datos = astronautaService.obtenerAstronautasPaginados(pagina);
        salida.addObject("pagina", pagina + 1);
        salida.addObject("hayAnterior", datos.hasPrevious());
        salida.addObject("haySiguiente", datos.hasNext());
        salida.addObject("astronautas", datos);
        return salida;
    }

    @GetMapping("/astronauta/eliminar/{id}")
    public ModelAndView eliminarAstronauta(@PathVariable Long id) {
        ModelAndView salida = new ModelAndView("redirect:/astronauta");
        astronautaRepository.deleteById(id);
        return salida;
    }

    @GetMapping("/astronauta/addAstronauta")
    public ModelAndView addAstronauta() {
        ModelAndView salida = new ModelAndView("astronauta/astronautaForm"); 
        salida.addObject("astronauta", new Astronauta());
        return salida;
    }

    @GetMapping("/updateAstronauta/{id}")
    public ModelAndView updateAstronauta(@PathVariable Long id) {
        ModelAndView salida = new ModelAndView("astronauta/astronautaForm"); 
        Optional<Astronauta> a = astronautaRepository.findById(id);
        if (a.isPresent()) {
            salida.addObject("astronauta", a);
        } else {
            salida.setViewName("redirect:/astronauta");
        }
        return salida;
    }

    @PostMapping("/astronauta/save")
    public String saveAstronauta(@ModelAttribute Astronauta astronauta) {
        astronautaRepository.save(astronauta);
        return "redirect:/astronauta";
    }
}
