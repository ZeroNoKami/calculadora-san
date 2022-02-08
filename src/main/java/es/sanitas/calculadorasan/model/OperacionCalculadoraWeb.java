package es.sanitas.calculadorasan.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OperacionCalculadoraWeb {
	
	// Tipo de operacion
	private String tipo;
	
	// Lista de numeros a operar
	private List<Double> numeros;
	
}
