
package proyectocalculadora;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Elena Sofía Luna Palacio, Gerardo Guerrero, Carlos Elias Rivera, Mauricio Diaz Villarreal,
 * Almudena Fernandez Verda
 * 203214
 * Feb 25, 2022
 */
public class Calculadora {
    
    /**
     * 
     * El metodo revisaPunto funciona para revisar los puntos decimales 
     * una entrada especifica, de la cadena en las operaciones
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
     * de la calculadora para que no haya ningun error, recibiendo como 
     * parametro la cadena de operaciones infija
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
                        /*
                        Esto excluye desde la sintaxis los casos basicos de la division entre cero.
                        */
                        if(i < tamaño - 2 && entrada.charAt(i + 1) == '0' && (entrada.charAt(i + 2) == '*' || entrada.charAt(i + 2) == '/') ) 
                            throw new DivisionEntreCeroExcepcion("División entre cero");
                        else
                           if(i == tamaño - 2 && entrada.charAt(i + 1) == '0' )
                            throw new DivisionEntreCeroExcepcion("División entre cero");
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
                        if(i < tamaño-1 && ( operadores.contains(entrada.charAt(i + 1)) || entrada.charAt(i + 1)==')' || entrada.charAt(i - 1) == ')' ) ){ //esto evita que ocurra ...()...(tampoco hay problema con i=0 pues ese caso se elimina al principio)
                            if(!pila.isEmpty()){
                              pila.pop();  
                              total--;
                                }
                            else
                                resp=false;
                            }
                        else{
                            if(i == tamaño - 1){
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
            restantes = restantes - 1;
        }
        if(!pila.isEmpty())
            resp=false;
        return resp;
    }
    
    

    
    /**
     * El metodo calculaJerarquia sirve para indicar el orden de operaciones
     * en la calculadora, es parte del metodo infijaAPostfija, recibe como 
     * parametro el operador y el tope de la pila
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
    
    /**
     * 
     * El metodo inflijaPostfija recibe como parametro la operacion para 
     * cambiarla de notacion infija a postfija
     */

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
     * 
     */

    public static String calcula(String entrada) {
        String respfinal= "";
        PilaA<Double> resultado = new PilaA<Double>();

        if ( !(entradasDeUnValor(entrada)) && revisaSintaxis(entrada) && entrada.length() < 50) {
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
                    if (!numero.equals(""))
                        resultado.push(opera(operador, peek, Double.parseDouble(numero)));
                    else
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
            respfinal = resultado.peek() + "";
        } else
            respfinal = "Sintax error";

        return respfinal;
    }
    
    /**
     * 
     * El metodo opera le entra un caracter y hace la operacion entre los 
     * dos doubles
     * 
     */

    private static double opera(char operador, double first, double second) {
        double resp=0;
        if (operador == '/' && second == 0.0) {
            throw new DivisionEntreCeroExcepcion("division entre cero");
        }
        switch (operador) {
            case '+': resp = first+second; break;
            case '*': resp = first*second; break;
            case '/': resp = first/second; break;
            case '-': resp = first-second; break;
        }

        return resp;
    }
    
    /**
     * 
     * El metodo entradaDeUnValor funciona para verificar que no entre 
     * solamente un operador.
     */

    private static boolean entradasDeUnValor(String entrada) {
        boolean resp = false;

        resp = entrada.equals("+") || entrada.equals("-") || entrada.equals("/") || entrada.equals("*")
        || entrada.equals("(") || entrada.equals(")");


        return resp;
    }
    

    
}
