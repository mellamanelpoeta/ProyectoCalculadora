
package proyectocalculadora;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Elena Sofía Luna Palacio, Gerardo Guerrero <gguerr21@itam.mx>, Carlos Elias Rivera 
 * 203214
 * Feb 25, 2022
 */
public class Calculadora {
    
    /**
     * 
     * El metodo revisaPunto funciona para revisar los puntos decimales 
     * una entrada especifica
     */
    
    private static boolean revisaPuntos(String cadena){
        int i=0,j,puntos=0;
        boolean resp=true;
        String[] numbers = cadena.replaceAll("[^0-9.]+", " ").trim().split(" ");
        while(i<numbers.length && resp){
            j=0;
            while(j<numbers[i].length() && puntos<=1){
                if(numbers[i].charAt(numbers[i].length()-1)!='.'){
                    if(numbers[i].charAt(j) != '.')
                        j++;
                    else{
                        puntos++;
                        j++;
                    }
                }
                else
                    puntos=2;
            }
                    if(j == numbers[i].length()){
                        i++;
                        puntos=0;                
                    }
                    else
                        resp=false;                    
                }
        return resp;
    }
    
    /**
     * 
     * El metodo revisaSintaxis tiene el proposito de revisar la sintaxis
     * de la calculadora para que no haya ningun error
     */
    
    private static boolean revisaSintaxis(String entrada){
        int i,tamaño,total,restantes;
        boolean resp;
        ArrayList<Character> operadores;
        PilaA<Character> pila;
        
        
        pila = new PilaA<>();
        tamaño = entrada.length();
        operadores = new ArrayList<>(Arrays.asList('+', '-', '*', '/'));
        i=0;
        total=0;
        restantes=tamaño;
        resp=true;


        /*
        Revisamos si la cadena comienza a o termina con algún caracter prohibido
        */
        if (entrada.charAt(i) == '+' || entrada.charAt(i) == '*' || entrada.charAt(i) == '/' || entrada.charAt(i) == ')' 
        || entrada.charAt(tamaño - 1) == '.'|| entrada.charAt(tamaño - 1) == '+' || entrada.charAt(tamaño - 1) == '*' 
        || entrada.charAt(tamaño - 1) == '/' || entrada.charAt(tamaño - 1) == '(' || entrada.charAt(tamaño - 1) == '-')
            resp=false;   
        else
            /*
            Se revisa que los numeros con punto decimal estan bien escritos (se resuelve 'abc.de.fg' y 'abc.')
            */
            resp=revisaPuntos(entrada);
            while(i <= tamaño-1 && resp && total <= restantes){
                        /*
                        Si se encuentra algun operador, 
                        se asegura que el caracter siguiente no sea otro operador
                        */       
                if( operadores.contains(entrada.charAt(i)) ){
                    if( operadores.contains(entrada.charAt(i + 1)) ) //no hay problema con el 'i+1' porque el caso en el que el operador esta en la ultima casilla ya se considero
                        resp=false;
                    else
                    if(entrada.charAt(i) == '-'){
                        if(entrada.charAt(i+1) ==')')//esto evita el ...-)...
                            resp = false;
                    }
                    if(entrada.charAt(i) == '/'){
                        if(i < tamaño - 2 && entrada.charAt(i + 1) == '0' && (entrada.charAt(i + 2) == '*' || entrada.charAt(i + 2) == '/') )
                            resp = false;
                        else
                           if(i == tamaño -2 && entrada.charAt(i + 1) == '0' )
                             resp = false;
                    }
                }
                else{
                        /*
                        Si se encuentra algun parentesis, 
                        entra el proceso de revision de parentesis
                        */       
                if(entrada.charAt(i) == '(' || entrada.charAt(i) == ')'){                                
                    if(entrada.charAt(i) == '('){
                        if(i > 0 && ( operadores.contains(entrada.charAt(i - 1)) || entrada.charAt(i + 1) == '(' || entrada.charAt(i - 1) == '(') ){ //esto evita que ocurra ...)(...
                            pila.push('('); 
                            total++;
                        }
                        else{
                            if(i == 0 ){
                                pila.push('('); 
                                total++;
                                }
                            else
                                resp=false;
                            }
                        }
                    else{
                        if(i < tamaño-1 && ( operadores.contains(entrada.charAt(i+1)) || entrada.charAt(i+1)==')' || entrada.charAt(i-1)==')') ){ //esto evita que ocurra ...()...(tampoco hay problema con i=0 pues ese caso se elimina al principio)
                            if(!pila.isEmpty()){
                              pila.pop();  
                              total--;
                                }
                            else
                                resp=false;
                            }
                        else{
                            if(i == tamaño-1){
                                if(!pila.isEmpty()){
                                    pila.pop();  
                                    total--;
                            }
                            else
                                resp=false;     
                            }
                        }
                    }
                }
            }
            i++;
            restantes=restantes-1;
        }
        if(!pila.isEmpty())
            resp=false;
        return resp;
    }
    
    

    
    /**
     * El metodo calculaJerarquia sirve para indicar el orden de operaciones
     * en la calculadora, es parte del metodo infijaAPostfija
     * 
     */

    private static boolean calculaJerarquia(char peek, char op) {
        int a, b;
        if (peek == '+' || peek == '-' || peek == '(')
            if(peek == '(')
                a= 0;
            else
                a = 1;
        else
            a = 2;
        if (op == '+' || op == '-')
            b = 1;
        else
            b = 2;

        return a >= b; // esto es lo que me indica que lo de hasta arriba de la pila sea mayor o igual al caracter en el que estoy

    }

    private static String infijaAPostfija(String entrada) {
        StringBuilder bob = new StringBuilder();
        ArrayList<Character> nums = new ArrayList<Character>();
        ArrayList<Character> ops = new ArrayList<Character>();
        ops.add('+');
        ops.add('-');
        ops.add('/');
        ops.add('*');
        PilaA<Character> pila = new PilaA<Character>();
        for (int i = 0; i < 10; i++) {
            nums.add(Character.forDigit(i, 10));
        }

        for (int i = 0; i < entrada.length(); i++) {
            if (nums.contains(entrada.charAt(i)) || entrada.charAt(i) == '.') {
                bob.append(entrada.charAt(i));

            }

            else if (entrada.charAt(i) == '(')
                pila.push(entrada.charAt(i));

            else if (entrada.charAt(i) == ')') {
                while (!pila.isEmpty() && !pila.peek().equals('('))
                    bob.append(pila.pop());

                if (pila.peek().equals('('))
                    pila.pop();

            }

            else if (ops.contains(entrada.charAt(i))) {

                while (!pila.isEmpty() && calculaJerarquia(pila.peek(), entrada.charAt(i)))
                    bob.append(pila.pop());

                pila.push(entrada.charAt(i));
                bob.append(',');

            }



        }

        while (!pila.isEmpty())
            bob.append(pila.pop());



        return bob.toString();
    }
    
    /**
     * 
     * El metodo calcula determina el valor a partir de la cadena del metodo 
     * que cambia de infija a postfija
     */

    public static String calcula(String entrada) {
        PilaA<Double> resultado = new PilaA<Double>();
        if(revisaSintaxis(entrada)) {
            String limpio = infijaAPostfija(entrada);
            double resp = 0;
            ArrayList<Character> ops = new ArrayList<Character>();
            ops.add('+');
            ops.add('-');
            ops.add('/');
            ops.add('*');
            String numero = "";
            boolean pasePorOperador = false;
            double peek;
            char operador;
            for (int i = 0; i < limpio.length(); i++) {
                if (limpio.charAt(i) != ',' && !ops.contains(limpio.charAt(i))) {
                    numero += limpio.charAt(i);
                    pasePorOperador = false;
                } else if (ops.contains(limpio.charAt(i))) {
                    peek = resultado.peek();
                    resultado.pop(); // nunca voy a hacer un pop de una pila vacía porque antes de un operador siempre tengo numero
                    operador = limpio.charAt(i);
                    if (!numero.equals("")) {
                        if (operador == '/' && Double.parseDouble(numero) == 0) {
                            // mandar un throw que diga que no se puede hacer división entre 0
                            throw new DivisionEntreCeroExcepcion("División entre 0");
                        } else {
                            resultado.push(opera(operador, peek, Double.parseDouble(numero)));
                        }

                    } else
                        resultado.push(opera(operador, resultado.peek(), peek));
                    pasePorOperador = true;
                    numero = "";
                }

                if (!pasePorOperador && limpio.charAt(i) == ',')
                    if (numero.equals("")) {
                        resultado.push(0.0);
                        numero = "";
                        pasePorOperador = false;
                    } else if (!numero.equals(".")) {
                        resultado.push(Double.parseDouble(numero));
                        numero = "";
                    }


            }
        }

        return resultado.peek()+"";
    }

    private static double opera(char operador, double first, double second) {
        double resp=0;
        if(operador == '/' && second== 0) {// falta ver la solucion a que esto no pase
            resp = second;
            operador = '9'; //  esto para evitar estropear pruebas en el inter
        }
        switch (operador) {
            case '+': resp = first+second; break;
            case '*': resp = first*second; break;
            case '/': resp = first/second; break;
            case '-': resp = first-second; break;
        }

        return resp;
    }
    

    
}
