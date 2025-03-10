package com.generico.astronautas.servicios;

import org.springframework.stereotype.Service;

import com.generico.astronautas.beans.ParticipaId;

@Service
public class ParticipaService {

	public ParticipaId getParticipaId(Long misionId, Long astronautaId, String rol) {
		
		ParticipaId pi = new ParticipaId();
		pi.setMisionId(misionId);
		pi.setAstronautaId(astronautaId);
		pi.setRol(rol);
		
		return pi;
	}
	
	
}
