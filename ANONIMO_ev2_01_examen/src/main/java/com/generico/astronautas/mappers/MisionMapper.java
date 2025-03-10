package com.generico.astronautas.mappers;

import com.generico.astronautas.beans.Mision;
import com.generico.astronautas.dtos.MisionDTO;

public class MisionMapper {

	public static MisionDTO mision2MisionVista(Mision mision) {
		
		MisionDTO mv = new MisionDTO();
		
		mv.setId(mision.getId());
		mv.setNombre(mision.getNombre());
		mv.setFecha_inicio(mision.getFecha_inicio());
		mv.setFecha_fin(mision.getFecha_fin());
		
		return mv;
	}
	
}
