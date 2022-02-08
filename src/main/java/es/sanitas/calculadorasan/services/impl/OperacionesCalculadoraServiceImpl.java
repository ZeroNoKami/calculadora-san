package es.sanitas.calculadorasan.services.impl;

import java.util.Objects;
import es.sanitas.calculadorasan.constants.OperacionesCalculadoraConstants;
import es.sanitas.calculadorasan.model.OperacionCalculadoraWeb;
import es.sanitas.calculadorasan.services.OperacionesCalculadoraService;
import org.springframework.stereotype.Service;

@Service
public class OperacionesCalculadoraServiceImpl implements OperacionesCalculadoraService {

	/**
	 * Método que opera sobre los 2 numeros dados
	 *
	 * @param operacion
	 *            con la operacion a realizar
	 * 
	 * @return Integer resultado de la operacion
	 * 
	 */
	public Double operarNumeros(OperacionCalculadoraWeb operacion) {
		//Aqui va un log de trazabilidad
		// Inicializamos la variable que devolveremos
		Double resultado = 0.0;

		try{
			// Dado que en el futuro se añadiran nuevas operaciones he decidido hacerlo
			// en un switch donde se calculará segun la variable operacion
			if (Objects.nonNull(operacion) && Objects.nonNull(operacion.getTipo())) {
				if(operacion.getNumeros().size()>1){
					switch (operacion.getTipo()) {
						case OperacionesCalculadoraConstants.SUMAR:
							//Aqui va un log de trazabilidad
							resultado = operacion.getNumeros().stream().reduce(0.0, Double::sum);
							break;
						case OperacionesCalculadoraConstants.RESTAR:
							//Aqui va un log de trazabilidad
							resultado = operacion.getNumeros().stream().reduce(0.0, (a, b)-> a - b);
							break;
						case OperacionesCalculadoraConstants.MULTIPLICAR:
							//Aqui va un log de trazabilidad
							resultado = operacion.getNumeros().stream().reduce(1.0, (a, b) -> a * b);
							break;
						case OperacionesCalculadoraConstants.DIVIDIR:
							//Aqui va un log de trazabilidad
							resultado = operacion.getNumeros().stream().reduce(1.0, (a,b) -> a/b);
							break;
						default:
							// Aqui va  un log para control de errores
							throw new IllegalArgumentException("No existe el tipo de operacion elegido");
					}
				} else {
					// Aqui va  un log para control de errores
					throw new IllegalArgumentException("Debe añadir mas de un numero para poder operar.");
				}

			}
			// Aqui va un log con la operacion OK
			return resultado;
		}catch(Exception e){
			// Aqui va un log de control de error
			throw e;
		}

	}

}
