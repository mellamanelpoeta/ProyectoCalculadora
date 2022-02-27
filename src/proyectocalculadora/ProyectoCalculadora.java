
package proyectocalculadora;

/**
 *
 * @author Gerardo Guerrero <gguerr21@itam.mx>
 */
public class ProyectoCalculadora {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("hola a todos");
        System.out.println("es raro el amor");
        System.out.println("un intento mas");
        System.out.println("Intento Almu");
        
        
        String cadena = "(1+2)*(3+4)";
        String resp;
        resp = Calculadora.conviertePostfija(cadena);
        System.out.println(resp);
    }
    
}
