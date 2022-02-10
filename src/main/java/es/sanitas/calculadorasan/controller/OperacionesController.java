package es.sanitas.calculadorasan.controller;

import io.corp.calculator.TracerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.sanitas.calculadorasan.services.OperacionesCalculadoraService;

/**
 * Clase que se encargara de exponer los endpoints de operaciones de la calculadora
 * 
 * @author Julio
 *
 */
@RestController
@RequestMapping("/sanitas-calculadora/api/v1")
@RequiredArgsConstructor
@Validated
public class OperacionesController {

	@Autowired
	private final OperacionesCalculadoraService operacionesCalculadoraService;

	/**
	 * Endpoint que realiza una operacion segun tipo
	 *
	 * @param numero1 con el primer numero
	 * @param numero2 con el segundo numero
	 * @param operacion con el tipo de operacion
	 *
	 * @return ResponseEntity con la respuesta
	 */
	@GetMapping("/operar")
	public ResponseEntity<String> operarNumerosEndpoint(
			@RequestParam Double numero1,
			@RequestParam Double numero2,
			@RequestParam String operacion) {
		Double resultado = 0.0;
		TracerImpl tracer = new TracerImpl();
		try{
			tracer.trace("[operarNumerosEndpoint] Vamos a realizar una operacion de " + operacion);
			// Calculamos ambos numeros
			resultado = operacionesCalculadoraService.operarNumeros(numero1, numero2, operacion);

			// Añadimos traza
			tracer.trace("[operarNumerosEndpoint] Se ha realizado la operacion correctamente.");

			// Devolvemos el resultado
			return new ResponseEntity<>("El resultado de tu " + operacion + " es: " + resultado, HttpStatus.OK);
		} catch(Exception e){
			// Trazeamos el error
			tracer.trace("[operarNumerosEndpoint] Ha habido un error intentando operar " + e.getMessage());
			return new ResponseEntity<>("Ha ocurrido un error intentando operar: " + e.getMessage(), HttpStatus.BAD_REQUEST);
		}

	}

}
