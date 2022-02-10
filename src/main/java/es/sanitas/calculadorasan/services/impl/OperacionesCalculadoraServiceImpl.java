package es.sanitas.calculadorasan.services.impl;

import java.util.Objects;
import es.sanitas.calculadorasan.constants.OperacionesCalculadoraConstants;
import es.sanitas.calculadorasan.model.OperacionCalculadoraWeb;
import es.sanitas.calculadorasan.services.OperacionesCalculadoraService;
import org.springframework.stereotype.Service;
import io.corp.calculator.TracerImpl;

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
	public Double operarNumeros(Double numero1, Double numero2, String operacion) {
		// Inicializamos la variable que devolveremos
		Double resultado = 0.0;
		TracerImpl tracer = new TracerImpl();

		tracer.trace("[operarNumeros] Vamos a realizar una operacion de " + operacion +
				"con los numeros " + numero1 + ", " + numero2 + ".");
		try{
			// Dado que en el futuro se añadiran nuevas operaciones he decidido hacerlo
			// en un switch donde se calculará segun la variable operacion
			if (Objects.nonNull(operacion)) {
				if(Objects.nonNull(numero1) && Objects.nonNull(numero2)){
					switch (operacion) {
						case OperacionesCalculadoraConstants.SUMAR:
							resultado = numero1 + numero2;
							break;
						case OperacionesCalculadoraConstants.RESTAR:
							resultado = numero1 - numero2;
							break;
						case OperacionesCalculadoraConstants.MULTIPLICAR:
							resultado = numero1 * numero2;
							break;
						case OperacionesCalculadoraConstants.DIVIDIR:
							resultado = numero1 / numero2;
							break;
						default:
							tracer.trace("[operarNumeros] Ha insertado un tipo de operacion no gestionado por operador.");
							throw new IllegalArgumentException("No existe el tipo de operacion elegido");
					}

				} else {
					tracer.trace("[operarNumeros] Ha insertado pocos numeros en la entrada ");
					throw new IllegalArgumentException("Debe añadir mas de un numero para poder operar.");
				}

			}
			tracer.trace("[operarNumeros] El resultado de la operacion es: " + resultado);
			return resultado;
		}catch(Exception e){
			tracer.trace("[operarNumeros] Habido un error en la ejecucion del operador: " + e.getMessage());
			throw e;
		}

	}

}
