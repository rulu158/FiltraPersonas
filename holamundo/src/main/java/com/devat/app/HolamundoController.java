package com.devat.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HolamundoController {
	
	/*
	 * Repositorio (clase para contactar con la BD desde Java)
	 */
	@Autowired
	PersonasRepo personasRepo;

	/*
	 * Función para gestionar las llamadas a:
	 *						(raíz/página inicial/index)
	 *						(holamundo)
	 * En definitiva, el endpoint del ejercicio de Spring Boot de la prueba.
	 */
	@GetMapping({"","/","/holamundo"})
	@ResponseBody
	public String holaMundo() {
		return "¡Hola mundo!";
	}
	
	/*
	 * Mapping para mostrar la prueba PL/SQL:
	 * 	Devolver todos los datos en la tabla o;
	 *  devolver sólo los datos relacionados con la nacionalidad (siglas clásicas).
	 *  Ejemplos:
	 *  	Devolver todas las personas: /sql/personas
	 *  	Devolver sólo las personas de nacionalidad puertorriqueña: /sql/personas?nacionalidad=es
	 */
	@GetMapping("/sql/personas")
	@ResponseBody
	public List<Persona> sqlPersonas(@RequestParam(required = false) String nacionalidad) throws IOException {
		
		// Si no se especifica una nacionalidad, se extraen todos los resultados
		if (nacionalidad == null || nacionalidad.trim() == "") {
			return personasRepo.obtenerPersonasRegistro();
		}

		// Si se especifica una nacionalidad, se buscan los datos (personas) necesarias
		return obtenerPersonasPorNacionalidad(nacionalidad);
	}
	
	/*
	 * Demostración de cómo utilizar un procedimiento.
	 * Cómo llamar a un procedimiento de Oracle y trabajar con él.
	 */
	private List<Persona> obtenerPersonasPorNacionalidad(String nacionalidad) throws IOException {
		
		// El procedimiento escribe en un archivo el query relacionado con la nacionalidad, y se le especifica el archivo)
		personasRepo.generarPersonasRegistroPorNacionalidad(nacionalidad, Persona.FILE_DIR, Persona.FILE_NAME);
		
		// Se lee dicho archivo
		List<String> personasStr = Files.readAllLines(Paths.get(Persona.FILE_DIR + "\\" + Persona.FILE_NAME));
		
		// Se extrae la información del archivo, y se añade al output, sacándolo en bruto (raw) y después procesándolo
		List<Persona> personas = new ArrayList<Persona>();
		for (String personaStr : personasStr) {
			String[] datosPersona = personaStr.split(" : ");
			
			String nombrePersona = datosPersona[0];
			String nacionalidadPersona = datosPersona[1];
			int edadPersona = Integer.parseInt(datosPersona[2]);

			personas.add(new Persona(nombrePersona, nacionalidadPersona, edadPersona));
		}
		
		// Se devuelve el resultado
		return personas;
	}
}
