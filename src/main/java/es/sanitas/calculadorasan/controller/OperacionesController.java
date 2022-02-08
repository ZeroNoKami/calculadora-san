package es.sanitas.calculadorasan.controller;

import io.corp.calculator.TracerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.sanitas.calculadorasan.model.OperacionCalculadoraWeb;
import es.sanitas.calculadorasan.services.OperacionesCalculadoraService;

/**
 * Clase que se encargara de exponer los endpoints de operaciones de la calculadora
 * 
 * @author Julio
 *
 */
@RestController
@RequestMapping("/sanitas-calculadora/api/v1")
@Validated
public class OperacionesController {

	private final OperacionesCalculadoraService operacionesCalculadoraService;

	/**
	 * Constructor de la clase DecisionOperacionesPendientesController
	 * 
	 * @param operacionesCalculadoraService
	 *            para realizar la inyeccion de dependencias          
	 */
	@Autowired
	public OperacionesController(OperacionesCalculadoraService operacionesCalculadoraService) {
		this.operacionesCalculadoraService = operacionesCalculadoraService;
	}

	/**
	 * Endpoint que realiza una operacion segun tipo
	 * 
	 * @param operacion
	 *            operación pendiente de la que se va a informar al cliente
	 * 
	 * @return Código de respuesta HTTP resultado
	 * 
	 */
	@PostMapping("/operar")
	public ResponseEntity<String> operarNumerosEndpoint(@RequestBody OperacionCalculadoraWeb operacion) {
		Double resultado = 0.0;
		TracerImpl tracer = new TracerImpl();
		try{
			tracer.trace("[operarNumerosEndpoint] Vamos a realizar una operacion de " + operacion.getTipo());
			// Calculamos ambos numeros
			resultado = operacionesCalculadoraService.operarNumeros(operacion);

			// Añadimos traza
			tracer.trace("[operarNumerosEndpoint] Se ha realizado la operacion correctamente.");

			// Devolvemos el resultado
			return new ResponseEntity<>("El resultado de tu " + operacion.getTipo() + " es: " + resultado, HttpStatus.OK);
		} catch(Exception e){
			// Trazeamos el error
			tracer.trace("[operarNumerosEndpoint] Ha habido un error intentando operar " + e.getMessage());
			return new ResponseEntity<>("Ha ocurrido un error intentando operar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
