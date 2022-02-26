
package proyectocalculadora;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Elena Sofía Luna Palacio, Gerardo Guerrero <gguerr21@itam.mx>, Carlos Elias Rivera 
 * 203214
 * Feb 25, 2022
 */
public class Calculadora {
    
    private static boolean revisaSintaxis(String entrada){
        int i=0,tamaño;
        boolean resp=true;
        char pos1,pos2;
        ArrayList<Character> operadores;
        ArrayList<Character> numeros;
        
        
        operadores=new ArrayList<Character>(Arrays.asList('+','-','*','/','.'));
        tamaño=entrada.length(); 
        
        
        if(entrada.charAt(0)=='+'  || entrada.charAt(0)=='*' || entrada.charAt(0)=='/'
                || entrada.charAt(tamaño-1)=='+'|| entrada.charAt(tamaño-1)=='*'|| entrada.charAt(tamaño-1)=='/'
                ) //revisa si la entrada comienza o termina con algún asterisco
            resp=false;
        while(i<tamaño-1 && resp){
            pos1=entrada.charAt(i);
            pos2=entrada.charAt(i+1);
            if(operadores.contains(pos1) && operadores.contains(pos2))
                if(pos2!='-') 
                    resp=false;  
        }
        return resp;
    }
}
