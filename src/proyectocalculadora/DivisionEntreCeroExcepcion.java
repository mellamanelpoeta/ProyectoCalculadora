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

    public DivisionEntreCeroExcepcion(String mensaje){
        super(mensaje);
    }
    
}
