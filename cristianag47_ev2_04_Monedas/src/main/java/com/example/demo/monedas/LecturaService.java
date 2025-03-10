package com.example.demo.monedas;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



@Component
public class LecturaService {
    @Value("${exchangerate.api.key}")
    private String key;
    @Scheduled(fixedRate = 10000)
    public void leerDatosMonedas() {
        System.out.println("Han pasado 30 segundos");
        // URL para consultar
        String url_str = "https://v6.exchangerate-api.com/v6/"+key+"/latest/USD";
        
        try {
            // Realizamos la petición
            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Parseamos la respuesta como JSON
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();
            System.out.println(jsonobj.get("base_code").getAsString());
            System.out.println(jsonobj.get("time_next_update_utc").getAsString());
            System.out.println(jsonobj.get("time_next_update_unix").getAsLong());
            
            JsonObject conversion_rates = jsonobj.getAsJsonObject("conversion_rates");
            System.out.println("EUR >> "+conversion_rates.get("EUR").getAsFloat());
            

            // Accedemos a alguna clave en el JSON
            String req_result = jsonobj.get("result").getAsString();
            System.out.println("Resultado de la API: " + req_result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
