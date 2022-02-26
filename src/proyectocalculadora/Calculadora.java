
package proyectocalculadora;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Elena Sofía Luna Palacio, Gerardo Guerrero <gguerr21@itam.mx>, Carlos Elias Rivera 
 * 203214
 * Feb 25, 2022
 */
public class Calculadora {
    
     public static boolean checaParentesis(String entrada) {
        boolean resp = true;
        PilaA pila = new PilaA();

        ArrayList<Character> operadores;
        operadores = new ArrayList<Character>();
        operadores.add('+');
        operadores.add('-');
        operadores.add('*');
        operadores.add('/');


        int i = 1; //empiezo en 1 para que no se salga del string
        if(entrada.charAt(0) ==')') // reviso que el primer parentesis no sea al reves y para empezar en int = 1
            resp = false;
        while (i < entrada.length() && resp) {
            if (entrada.charAt(i) == '(' || entrada.charAt(i) == '(') {
                if (entrada.charAt(i) == '(') {
                    //casos a la izquierda de '(' en que solo funciona: ((, +(
                    if (entrada.charAt(i - 1) != '(' && !operadores.contains(entrada.charAt(i - 1)))
                        resp = false;
                    //caso en el que falla a la derecha de '(': (*, (+, (/, pero si funciona en (-
                    if (operadores.contains(entrada.charAt(i + 1)) && operadores.contains(entrada.charAt(i + 1) != '-'))
                        resp = false;
                    else
                        pila.push(entrada.charAt(i));
                } else {
                    //casos a la izquierda de ')' en que solo funciona: )), a)
                    if (entrada.charAt(i - 1) != ')' && entrada.charAt(i - 1) != (double) entrada.charAt(i - 1))//tal vez sea esto el problema, pregunto si el char es un double
                        resp = false;
                    //caso en el que falla a la derecha de ')': )a
                    if (entrada.charAt(i - 1) == (double) entrada.charAt(i - 1))
                        resp = false;
                    else
                        try {
                            pila.pop();
                        } catch (Exception e) {
                            resp = false;
                        }
                }
            }
            i++;
        }


        return resp;
    }

    private static boolean revisaSintaxis(String entrada) {
        int i = 0, tamaño;
        boolean resp = true;
        char pos1, pos2;
        ArrayList<Character> operadores;
        ArrayList<Character> numeros;


        operadores = new ArrayList<Character>(Arrays.asList('+', '-', '*', '/', '.'));
        tamaño = entrada.length();


        if (entrada.charAt(0) == '+' || entrada.charAt(0) == '*' || entrada.charAt(0) == '/'
                || entrada.charAt(tamaño - 1) == '+' || entrada.charAt(tamaño - 1) == '*' || entrada.charAt(tamaño - 1) == '/'
        ) //revisa si la entrada comienza o termina con algún asterisco
            resp = false;
        while (i < tamaño - 1 && resp) {
            pos1 = entrada.charAt(i);
            pos2 = entrada.charAt(i + 1);
            if (operadores.contains(pos1) && operadores.contains(pos2))
                if (pos2 != '-')
                    resp = false;
        }
        return resp;
    }
}
