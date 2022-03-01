package proyectocalculadora;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author elenalunapalacio
 */
public class DivisionEntreCeroExcepcion extends RuntimeException{
    
     public DivisionEntreCeroExcepcion(){
        super();
    }
     
     /**
      * Tenemos este metodo en dado caso que suceda una division entre 0
      * 
      */

    public DivisionEntreCeroExcepcion(String mensaje){
        super(mensaje);
    }
    
}
