package proyectocalculadora;

public interface PilasADT <T> {
    public void push(T dato);
    public T pop();
    public boolean isEmpty();
    public T peek();

}
