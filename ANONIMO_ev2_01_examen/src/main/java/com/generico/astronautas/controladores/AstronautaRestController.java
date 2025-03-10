package com.generico.astronautas.controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generico.astronautas.beans.Astronauta;
import com.generico.astronautas.repositorios.AstronautaRepository;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class AstronautaRestController {

	@Autowired
	private AstronautaRepository astronautaRepositorio;

	@GetMapping("/astronauta/csv")
	public void exportarCSV(HttpServletResponse response) throws IOException {

		// Configurar la respuesta HTTP para la descarga
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=\"astronautas.csv\"");

		// Obtener los datos
		List<Astronauta> astronautas = (List<Astronauta>) astronautaRepositorio.findAll();

		// Escribir los datos en el CSV
		try (PrintWriter writer = response.getWriter()) {
			// Cabeceras del CSV
			writer.println("ID,Nombre,Edad");

			// Datos de los astronautas
			for (Astronauta astronauta : astronautas) {
				writer.println(
						String.format("%d,%s,%d", astronauta.getId(), astronauta.getNombre(), astronauta.getEdad()));
			}
		}
	}
	
	
	 @GetMapping("/astronauta/xlsx")
	    public void exportarXLSX(HttpServletResponse response) throws IOException {
	        // Configurar la respuesta HTTP para la descarga
	        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
	        response.setHeader("Content-Disposition", "attachment; filename=\"astronautas.xlsx\"");

	        // Crear el libro de Excel
	        Workbook workbook = new XSSFWorkbook();
	        Sheet sheet = workbook.createSheet("Astronautas");

	        // Crear la fila de cabeceras
	        Row headerRow = sheet.createRow(0);
	        String[] cabeceras = {"ID", "Nombre", "Edad"};
	        for (int i = 0; i < cabeceras.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(cabeceras[i]);
	        }

	        // Obtener los datos
	        List<Astronauta> astronautas = (List<Astronauta>) astronautaRepositorio.findAll();

	        // Escribir los datos en el archivo
	        int rowNum = 1; // Empezamos en la fila 1 porque la fila 0 es para cabeceras
	        for (Astronauta astronauta : astronautas) {
	            Row row = sheet.createRow(rowNum++);
	            row.createCell(0).setCellValue(astronauta.getId());
	            row.createCell(1).setCellValue(astronauta.getNombre());
	            row.createCell(2).setCellValue(astronauta.getEdad());
	        }

	        // Ajustar automáticamente el tamaño de las columnas
	        for (int i = 0; i < cabeceras.length; i++) {
	            sheet.autoSizeColumn(i);
	        }

	        // Escribir el archivo en la respuesta HTTP
	        workbook.write(response.getOutputStream());
	        workbook.close();
	    }	
	
}
