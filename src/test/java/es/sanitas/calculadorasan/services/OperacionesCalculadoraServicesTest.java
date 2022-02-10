package es.sanitas.calculadorasan.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Clase de tests para la clase CuentasService.
 *
 * @author Julio
 *
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("test")
public class OperacionesCalculadoraServicesTest {

    @Autowired
    private OperacionesCalculadoraService operacionService;

    /**
     * Test donde se opera la multiplicacion
     */
    @Test
    public void operarMultiplicacion(){
        Double numero1 = 10.0;
        Double numero2 = 10.0;
        String operacion = "multiplicacion";

        assertDoesNotThrow(() ->{
            operacionService.operarNumeros(numero1, numero2, operacion);
        });
    }

    /**
     * Test donde se opera la suma
     */
    @Test
    public void operarSuma(){
        Double numero1 = 10.0;
        Double numero2 = 10.0;
        String operacion = "suma";

        assertDoesNotThrow(() ->{
            operacionService.operarNumeros(numero1, numero2, operacion);
        });
    }

    /**
     * Test donde se opera la resta
     */
    @Test
    public void operarResta(){
        Double numero1 = 10.0;
        Double numero2 = 10.0;
        String operacion = "resta";

        assertDoesNotThrow(() ->{
            operacionService.operarNumeros(numero1, numero2, operacion);
        });
    }

    /**
     * Test donde se opera la division
     */
    @Test
    public void operarDivision(){
        Double numero1 = 10.0;
        Double numero2 = 10.0;
        String operacion = "division";

        assertDoesNotThrow(() ->{
            operacionService.operarNumeros(numero1, numero2, operacion);
        });
    }

    /**
     * Test donde se opera con una operacion desconocida
     */
    @Test
    public void operarOperacionDesconocida(){
        Double numero1 = 10.0;
        Double numero2 = 10.0;
        String operacion = "desconocido";

        assertThrows(IllegalArgumentException.class, () ->{
            operacionService.operarNumeros(numero1, numero2, operacion);
        });
    }

    /**
     * Test donde se opera con un numero null
     */
    @Test
    public void operarOperacionSinUnNumero(){
        Double numero1 = 10.0;
        Double numero2 = null;
        String operacion = "suma";

        assertThrows(IllegalArgumentException.class, () ->{
            operacionService.operarNumeros(numero1, numero2, operacion);
        });
    }
}
