package proyectocalculadora;


public class PilaA<T> implements PilasADT<T> {

    private T[] pila;
    private int tope;
    private final int MAXIMO = 20;

    //convertimos a tipo T
    public PilaA() {
        pila = (T[]) new Object[MAXIMO];
        tope = -1;
    }

    //si ya no te cabe, la expandes y le metes mas datos
    @Override
    public void push(T dato) {
        if(tope == pila.length-1)
            expand();
        tope++;
        pila[tope] = dato;
    }

    //Metodo axuliar para generar una pila mas grande que la actual
    private void expand(){
        T[] nuevo = (T[]) new Object[pila.length * 2];

        for(int i = 0; i <= tope; i++)
            nuevo[i] = pila[i];
        pila = nuevo;
    }

    @Override
    public T pop() {
        if(this.isEmpty())
            throw new ColeccionVaciaExcepcion("Pila vacia"); //el throw me saca del metodo
        T resultado;
        resultado = pila[tope];
        //pila[tope] = null;
        tope--;
        return resultado;
    }

    @Override
    public boolean isEmpty() {
        return tope == -1;
    }

    //con el throw estas lanzando una excepcion y con el return estas dando un resultado
    @Override
    public T peek() {
        if(this.isEmpty())
            throw new ColeccionVaciaExcepcion("Pila vacia"); //el throw me saca del metodo

            return pila[tope];
    }

    public String toString(){
        StringBuilder sB = new StringBuilder();
        for(int i = tope; i >= 0; i--)
            sB.append(pila[i]).append("\n");
        return sB.toString();
    }
}
