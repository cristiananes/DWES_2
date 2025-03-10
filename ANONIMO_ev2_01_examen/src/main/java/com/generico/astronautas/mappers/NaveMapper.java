package com.generico.astronautas.mappers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generico.astronautas.beans.Mision;
import com.generico.astronautas.beans.Nave;
import com.generico.astronautas.dtos.NaveDTO;
import com.generico.astronautas.repositorios.MisionRepository;
import com.generico.astronautas.repositorios.NaveRepository;

@Service
public class NaveMapper {

	@Autowired
	private MisionRepository misionRepository;
	
	
	public NaveDTO nave2NaveDTO(Nave nave) {
		
		NaveDTO naveDTO = new NaveDTO();
		naveDTO.setId(nave.getId());
		naveDTO.setCapacidad(nave.getCapacidad());
		naveDTO.setNombre(nave.getNombre());
		
		List<Long> misiones = new ArrayList<Long>();
		for(Mision m: nave.getMisiones()) {
			
			misiones.add(m.getId());
		}
		naveDTO.setMisiones(misiones);
		
		return naveDTO;
	}
	
	
	
	
	public Nave naveDTO2Nave(NaveDTO naveDTO) {
		
		Nave nave = new Nave();
		nave.setId(naveDTO.getId());
		nave.setCapacidad(naveDTO.getCapacidad());
		nave.setNombre(naveDTO.getNombre());
		
		List<Mision> misiones = new ArrayList<Mision>();
		for(Long id:naveDTO.getMisiones()) {
			
			misiones.add(misionRepository.findById(id).get());
		}
		nave.setMisiones(misiones);
		
		return nave;
	}
	
	
}
