package es.sanitas.calculadorasan.services;

import es.sanitas.calculadorasan.model.OperacionCalculadoraWeb;

/**
 * Interfaz de las operaciones de la calculadora
 * 
 * @author Julio
 *
 */
public interface OperacionesCalculadoraService {
	
	/**
	 * Método que opera sobre los 2 numeros dados
	 *
	 * @param operacion
	 * 		con la operacion a realizar
	 *  
	 * @return Integer resultado de la operacion
	 * 
	 */
	 Double operarNumeros(OperacionCalculadoraWeb operacion);

}
