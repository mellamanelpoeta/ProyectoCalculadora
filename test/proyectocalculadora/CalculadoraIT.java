/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package proyectocalculadora;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author cerivera
 */
public class CalculadoraIT {
    
    public CalculadoraIT() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of calcula method, of class Calculadora.
     */
    @Test
    public void testCalcula() {
        System.out.println("calcula");
        String entrada = "2+4";
        String expResult = "6.0";
        String result = Calculadora.calcula(entrada);
        assertEquals(expResult, result, "0.0");
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
}
