package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Mision;
import com.example.demo.repository.MisionRepository;
import com.example.demo.vistas.MisionVista;

@Service
public class MisionFechaService {

	
	//metodo que pasa de mision a misionVista
	public MisionVista misionFechada(Mision mision) {
		MisionVista misionVista = new MisionVista();
		misionVista.setId(mision.getId());
		misionVista.setNombre(mision.getNombre());
		misionVista.setFechaInicio(mision.getFechaInicio().getDayOfMonth()+" de "+mision.getFechaInicio().getMonth()+" del "+mision.getFechaInicio().getYear());
		misionVista.setFechaFin(mision.getFechaFin().getDayOfMonth()+" de "+mision.getFechaFin().getMonth()+" del "+mision.getFechaFin().getYear());
		misionVista.setNave(mision.getNave());
		return misionVista;
	}
	
	//como iterable se puede sin priblema 
	//Pero si devolvemos un list podemos ir añadiendole cosas mas facil por eso mejor
	//devolver un list para añadir, borrar....
	//En este caso no hace falta porque no necesitmamos ni añadir ni quitar
	
	//El que transforma los cambios de mision a misionVista es el mapper
	@Autowired MisionRepository misionRepository;
	public List<MisionVista> misionesFechadas() {
		List<Mision> misiones = (List<Mision>) misionRepository.findAll();
	    List<MisionVista> listaMisionVistas = new ArrayList<>();

	    // Recorremos cada Mision, la convertimos a MisionVista y la añadimos a la lista.
	    for (Mision mision : misiones) {
	        MisionVista misionVista = misionFechada(mision);
	        listaMisionVistas.add(misionVista);
	    }
		return listaMisionVistas;
	}
	
	
}
