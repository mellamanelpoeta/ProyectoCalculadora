
package proyectocalculadora;

import java.util.Scanner;

/**
 *
 * @author Gerardo Guerrero, Carlos Elias Rivera Mercado 
 */
public class ProyectoCalculadora {

    /**
     * Aqui probamos los casos de entrada para la calculadora
     */
    public static void main(String[] args) {
        Scanner lee = new Scanner(System.in);
        try {
            System.out.println(Calculadora.calcula(lee.nextLine()));
        } catch (Exception divisionEntre0) {
            System.out.println(divisionEntre0.getMessage());
        }






        
    }
    
}
