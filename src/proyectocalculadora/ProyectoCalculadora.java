
package proyectocalculadora;

import java.util.Scanner;

/**
 *
 * @author Gerardo Guerrero <gguerr21@itam.mx>
 */
public class ProyectoCalculadora {

    /**
     * @param args the command line arguments
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
