package com.generico.astronautas.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.generico.astronautas.beans.Mision;
import com.generico.astronautas.dtos.MisionDTO;
import com.generico.astronautas.mappers.MisionMapper;

@Service
public class MisionFechasService {

	public List<MisionDTO> getMisionesVista(Iterable<Mision> misiones) {
		
		List<MisionDTO> devolver = new ArrayList<MisionDTO>();
		
		for(Mision m:misiones) {
			
			devolver.add(MisionMapper.mision2MisionVista(m));
		}
		
		return devolver;
	}
	
	
}
