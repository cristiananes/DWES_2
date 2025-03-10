package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.monedas.LecturaService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class ConversionController {

    @Autowired
    private LecturaService lecturaService; // Inyectamos el service (aunque su método programado se ejecuta de forma periódica)

    @GetMapping("/")
    public String showForm(Model model) {
        List<String> monedas = Arrays.asList("USD", "EUR", "GBP", "JPY", "CAD");
        model.addAttribute("monedas", monedas);
        return "form";
    }

    @PostMapping("/convertir")
    public String convertir(
            @RequestParam("monedaOrigen") String monedaOrigen,
            @RequestParam("monedaDestino") String monedaDestino,
            @RequestParam("monto") double monto,
            Model model) {

        // Para este ejemplo, usaremos valores fijos de conversión.
        // NOTA: LecturaService ya se encarga de consultar la API (en forma periódica) pero no retorna datos;
        // por ello, aquí simulamos la conversión.
        double tasaConversion = 1.0;
        if ("USD".equals(monedaOrigen) && "EUR".equals(monedaDestino)) {
            tasaConversion = 0.85;
        } else if ("EUR".equals(monedaOrigen) && "USD".equals(monedaDestino)) {
            tasaConversion = 1.18;
        }
        // Puedes agregar más casos según tus necesidades.

        double montoConvertido = monto * tasaConversion;

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("monedaOrigen", monedaOrigen);
        resultado.put("monedaDestino", monedaDestino);
        resultado.put("montoOrigen", monto);
        resultado.put("montoConvertido", montoConvertido);

        model.addAttribute("resultado", resultado);
        model.addAttribute("monedas", Arrays.asList("USD", "EUR", "GBP", "JPY", "CAD"));
        return "form";
    }
}
