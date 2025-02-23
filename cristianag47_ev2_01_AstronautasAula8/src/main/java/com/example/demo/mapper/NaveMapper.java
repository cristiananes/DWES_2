package com.example.demo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.NaveDTO;
import com.example.demo.entity.Mision;
import com.example.demo.entity.NaveEspacial;
import com.example.demo.repository.MisionRepository;
import com.example.demo.repository.NaveEspacialRepository;

@Service
public class NaveMapper {
	
	@Autowired private NaveEspacialRepository naveEspacialRepository;
	@Autowired private MisionRepository misionRepository;

	public NaveDTO nave2NaveDTO(NaveEspacial nave) {
		
		NaveDTO naveDTO = new NaveDTO();
		naveDTO.setId(nave.getId());
		naveDTO.setNombre(nave.getNombre());
		naveDTO.setCapacidad(nave.getCapacidad());

		List<Long> misiones = new ArrayList<Long>();
		for(Mision m: nave.getMisiones()) {
			misiones.add(m.getId());
			
		}
		naveDTO.setMisiones(misiones);
		return naveDTO;
	}
	
	public NaveEspacial naveDTO2Nave(NaveDTO naveDTO) {
		NaveEspacial nave = new NaveEspacial();
		nave.setId(naveDTO.getId());
		nave.setNombre(naveDTO.getNombre());
		nave.setCapacidad(naveDTO.getCapacidad());
		List<Mision> misiones = new ArrayList<Mision>();
		for (Long id:naveDTO.getMisiones()) {
			misiones.add(misionRepository.findById(id).get());
		}
		nave.setMisiones(misiones);
		return nave;
	}
	
	
}

