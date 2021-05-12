package com.devat.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;

/*
 * Definición del procedimiento para buscar personas por nacionalidad
 */
@NamedStoredProcedureQuery(
		name = Persona.NamedQuery_ObtenerPorNacionalidad,
		procedureName = "OBTENER_PERSONAS_POR_NACIONALIDAD_ARCHIVO",
		resultClasses = Persona.class,
		parameters = {
				@StoredProcedureParameter(name = "NAC", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "FILE_DIR", type = String.class, mode = ParameterMode.IN),
				@StoredProcedureParameter(name = "FILE_NAME", type = String.class, mode = ParameterMode.IN),
		})

/*
 * Entidad (DB) Persona (asociada con la tabla REGISTROS)
 */
@Entity
public class Persona {
	
	/*
	 * Directorio y nombre del fichero para llamar al procedimiento, y trabajar.
	 * Valores constantes y sólo sujetos a cambios en la aplicación, no en runtime.
	 */
	public static final String FILE_DIR = "C:\\atSistemas";
	public static final String FILE_NAME = "personasNacionalidad.txt";
	
	// Nombre del procedimiento dentro de JPA
	public static final String NamedQuery_ObtenerPorNacionalidad = "obtenerPersonasPorNacionalidad";

	@Id
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "NACIONALIDAD")
	private String nacionalidad;
	
	@Column(name = "EDAD")
	private int edad;

	public String getNombre() {
		return nombre;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public int getEdad() {
		return edad;
	}
	
	/*
	 * Constructor de la clase Persona
	 */
	public Persona(String nombre, String nacionalidad, int edad) {
		this.nombre = nombre;
		this.nacionalidad = nacionalidad;
		this.edad = edad;
	}
}
