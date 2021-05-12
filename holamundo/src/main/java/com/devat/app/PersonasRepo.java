package com.devat.app;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonasRepo extends CrudRepository<Persona, String> {
	
	/*
	 * Función interfaz para buscar todas las personas (clase Persona)
	 */
	@Query(
			value = "SELECT * FROM REGISTRO",
			nativeQuery = true)
	List<Persona> obtenerPersonasRegistro();
	
	/*
	 * Función interfaz para buscar todos los resultados relacionados con la nacioladidad de la persona (clase Persona).
	 */
	@Procedure(name = Persona.NamedQuery_ObtenerPorNacionalidad)
	void generarPersonasRegistroPorNacionalidad(@Param("NAC") String nacionalidad, @Param("FILE_DIR") String fileDir, @Param("FILE_NAME") String fileName);
}