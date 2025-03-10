package com.generico.astronautas.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

@Controller
public class LanguageController {

    @GetMapping("/changeLanguage")
    public ModelAndView changeLanguage(String lang) {
        // Opcional: Puedes agregar lógica adicional si es necesario
        return new ModelAndView("redirect:/?lang=" + lang);
    }
}
