package es.sanitas.calculadorasan.controller;

import io.corp.calculator.TracerImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

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
	@Operation(description = "math operator for calculator application")
	// Body swagger
	@Parameters(value = {
			@Parameter(name = "numero1", in= ParameterIn.QUERY , required = true,
					description = "first number to calculate"),
			@Parameter(name = "numero2", in= ParameterIn.QUERY , required = true,
					description = "second number to calculate"),
			@Parameter(name = "operacion", in= ParameterIn.QUERY , required = true,
					description = "operation action")})
	// Códigos de respuesta swagger
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "No Content"),
			@ApiResponse(responseCode = "400", description = "Bad Request", content =
			@Content(schema = @Schema(implementation = HttpClientErrorException.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content =
			@Content(schema = @Schema(implementation = HttpClientErrorException.class))),
			@ApiResponse(responseCode = "403", description = "Forbidden", content =
			@Content(schema = @Schema(implementation = HttpClientErrorException.class))),
			@ApiResponse(responseCode = "404", description = "Not Found", content =
			@Content(schema = @Schema(implementation = HttpClientErrorException.class))),
			@ApiResponse(responseCode = "500", description = "Internal server error", content =
			@Content(schema = @Schema(implementation = HttpServerErrorException.class))) })
	@GetMapping("/operar")
	public ResponseEntity<String> operarNumerosEndpoint(
			@RequestParam("numero1") Double numero1,
			@RequestParam("numero2") Double numero2,
			@RequestParam("operacion") String operacion) {
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
