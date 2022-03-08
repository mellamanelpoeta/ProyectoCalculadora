/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package proyectocalculadora;



import static junit.framework.Assert.assertEquals;
import org.junit.Test;
import org.junit.*;
/*import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;*/


/*import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;*/

/**
 *
 * @author cerivera
 */
public class CalculadoraIT {
    
    public CalculadoraIT() {
    }
    
    
    
    @Test
    public void testCalcula12() {
        System.out.println("prueba de error de sintaxis, jerarquizacion con parentesis");
        String entrada = "5*(2+3)*4/(4-2)";
        String expResult = "50.0";
        Calculadora instance = new Calculadora();
        String result = instance.calcula(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    @Test
    public void testCalcula11() {
        System.out.println("prueba de error de sintaxis, jerarquizacion");
        String entrada = "5*2+3*4/4-2";
        String expResult = "11.0";
        Calculadora instance = new Calculadora();
        String result = instance.calcula(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    
    @Test
    public void testCalcula10() {
        System.out.println("prueba de error de sintaxis, parentesis sin operadores");
        String entrada = "5.3(8)";
        String expResult = "Syntax error";
        Calculadora instance = new Calculadora();
        String result = instance.calcula(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    @Test
    public void testCalcula9() { 
        System.out.println("prueba de error de sintaxis, cuando le da igual y no ingreso nada");
        String entrada = "";
        String expResult = "Syntax error";
        Calculadora instance = new Calculadora();
        String result = instance.calcula(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    @Test
    public void testCalcula8() { // este es el caso que no nos sirve
        System.out.println("prueba de solamente un numero");
        String entrada = "5.2";
        String expResult = "Syntax error";
        Calculadora instance = new Calculadora();
        String result = instance.calcula(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    @Test
    public void testCalcula7() {
        System.out.println("prueba de error de sintaxis, solo pone punto");
        String entrada = ".";
        String expResult = "Syntax error";
        Calculadora instance = new Calculadora();
        String result = instance.calcula(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    @Test
    public void testCalcula6() {
        System.out.println("prueba de error de sintaxis, parentesis no balanceados");
        String entrada = "5.2+(5.2))";
        String expResult = "Syntax error";
        Calculadora instance = new Calculadora();
        String result = instance.calcula(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    @Test
    public void testCalcula5() {
        System.out.println("prueba de error de sintaxis, puntos decimales");
        String entrada = "5.2+5..2";
        String expResult = "Syntax error";
        Calculadora instance = new Calculadora();
        String result = instance.calcula(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    
    @Test
    public void testCalcula4() {
        System.out.println("prueba de error de sintaxis, operadores");
        String entrada = "5.2++5.2";
        String expResult = "Syntax error";
        Calculadora instance = new Calculadora();
        String result = instance.calcula(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    /**
     * Test of calcula method, of class Calculadora.
     */
    @Test
    public void testCalcula() {
        System.out.println("prueba suma sin puntos decimales");
        String entrada = "2+4";
        String expResult = "6.0";
        Calculadora instance = new Calculadora();
        String result = instance.calcula(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    @Test
    public void testCalcula1() {
        System.out.println("prueba suma con puntos decimales");
        String entrada = "5.2+5.3";
        String expResult = "10.5";
        Calculadora instance = new Calculadora();
        String result = instance.calcula(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    @Test
    public void testCalcula2() {
        System.out.println("prueba de puntos decimales con parentesis");
        String entrada = "5.2*(2)";
        String expResult = "10.4";
        Calculadora instance = new Calculadora();
        String result = instance.calcula(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
    @Test
    public void testCalcula3() {
        System.out.println("prueba de puntos decimales");
        String entrada = "5.2+5.3";
        String expResult = "10.5";
        Calculadora instance = new Calculadora();
        String result = instance.calcula(entrada);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       
    }
    
}
