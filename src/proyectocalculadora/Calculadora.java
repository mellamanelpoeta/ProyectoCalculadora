
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
    
    
    private static boolean indicaJerarquia(char peek, char elemento){ // verdadero si el elemento es de menor o igual jerarquía que peek
        int a, b;
        boolean resp;
        
        a = 0; // valor del elemento
        b = 0; //valor de peek
        switch (elemento){
            case '+': //suma y resta valen 1 (jerarquía más baja)
                a = 1;
                break;
            case '-':
                a = 1;
                break;
            case '/': // división y multiplicación valen 2 (jeraquía más alta)
                a = 2;
                break;
            case '*':
                a = 2;
                break;      
        }
        switch (peek){
            case '+': //suma y resta valen 1 (jerarquía más baja)
                b = 1;
                break;
            case '-':
                b = 1;
                break;
            case '/': // división y multiplicación valen 2 (jeraquía más alta)
                b = 2;
                break;
            case '*':
                b = 2;
                break; 
        }
        
        if (a <= b) // si la prioridad del elemento es menor o igual a de peek
            resp = true;
        else
            resp = false;
        return resp;
        
    }

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
            if (nums.contains(entrada.charAt(i)) || entrada.charAt(i) == '.')
                bob.append(entrada.charAt(i));

            else if (entrada.charAt(i) == '(')
                pila.push(entrada.charAt(i));

            else if (entrada.charAt(i) == ')') {
                while (!pila.isEmpty() && !pila.peek().equals('('))
                    bob.append(pila.pop());

                if (pila.peek().equals('('))
                    pila.pop();

            }

            else if (ops.contains(entrada.charAt(i))) {

                while (!pila.isEmpty() && calculaJerarquia(pila.peek(), entrada.charAt(i)));
                    bob.append(pila.pop());

                pila.push(entrada.charAt(i));

            }



        }

        while (!pila.isEmpty())
            bob.append(pila.pop());



        return bob.toString();
    }
    
    public static String conviertePostfija(String entrada){
      
      int i;
      ArrayList<Character> numeros;
      ArrayList<Character> operadores;
      ArrayList<Character> resp;
      PilaA<Character> pilaOperadores;
   
      
      pilaOperadores = new PilaA<Character>(); // pila para guardad a los operadores 
      
      numeros = new ArrayList<Character>();

        for (int j = 0; j < 10; j++)
            numeros.add((char)j);

      operadores = new ArrayList<Character>();
      operadores.add('+');
      operadores.add('-');
      operadores.add('*');
      operadores.add('/');
      
      
      
      resp = new ArrayList<Character>();
      for (i = 0; i < entrada.length(); i++){
          if (numeros.contains((entrada.charAt(i))) || entrada.charAt(i) == '.') // para contemplar el caso de los decimales
              resp.add(entrada.charAt(i));
              
          else
              if (operadores.contains(entrada.charAt(i))){
                 
                  while (!pilaOperadores.isEmpty() &&  pilaOperadores.peek()!= '(' && indicaJerarquia(pilaOperadores.peek(), entrada.charAt(i))){
                      
                      resp.add(pilaOperadores.pop());
                  }
                  pilaOperadores.push(entrada.charAt(i));
              } 
              else{
           
                  if (entrada.charAt(i) == '(')
                      pilaOperadores.push(entrada.charAt(i));
                  else if (entrada.charAt(i) == ')'){
                      while (!pilaOperadores.isEmpty() && pilaOperadores.peek() != '(')
                              resp.add(pilaOperadores.pop());
                      pilaOperadores.pop();
                  } 
              }
                  
          
      }
      while(!pilaOperadores.isEmpty())
          resp.add(pilaOperadores.pop());
         
      
      return resp.toString();
    }
    
}
