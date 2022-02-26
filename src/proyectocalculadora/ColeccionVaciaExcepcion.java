package proyectocalculadora;

public class ColeccionVaciaExcepcion extends RuntimeException{

    public ColeccionVaciaExcepcion(){
        super();
    }

    public ColeccionVaciaExcepcion(String mensaje){
        super(mensaje);
    }
}
