package com.example.demo.auditoria;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Auditoria;
import com.example.demo.repository.AuditoriaRepository;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

@Component
public class AuditoriaListener {
	   @Autowired
	    private AuditoriaRepository auditoriaRepository;

	    @PrePersist
	    public void prePersist(Object entity) {
	        registrarEvento(entity, "CREAR");
	    }

	    @PreUpdate
	    public void preUpdate(Object entity) {
	        registrarEvento(entity, "ACTUALIZAR");
	    }

	    @PreRemove
	    public void preRemove(Object entity) {
	        registrarEvento(entity, "ELIMINAR");
	    }

	    private void registrarEvento(Object entity, String operacion) {
	        if (auditoriaRepository == null) {
	            auditoriaRepository = SpringContextHolder.getBean(AuditoriaRepository.class);
	        }
	        Auditoria audit = new Auditoria();
	        // Se registra el nombre de la entidad (por ejemplo, "Usuario")
	        audit.setEntidad(entity.getClass().getSimpleName());

	        // Intentamos extraer el ID suponiendo que la entidad tiene un método getId()
	        try {
	            Method method = entity.getClass().getMethod("getUsuario");
	            String id = (String) method.invoke(entity);
	            audit.setEntidadId(id);
	        } catch (Exception e) {
	            audit.setEntidadId(null);
	        }

	        audit.setFechaHora(LocalDateTime.now());

	        // Obtenemos el usuario actual a partir de Spring Security, o "anonymous" si no está autenticado
	        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	        String usuario = (auth != null && auth.isAuthenticated()) ? auth.getName() : "anonymous";
	        audit.setUsuario(usuario);

	        audit.setOperacion(operacion);

	        // Guardamos el registro de auditoría
	        auditoriaRepository.save(audit);
	    }
}
