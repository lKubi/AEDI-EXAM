
package es.uvigo.esei.aed1.iu;

import es.uvigo.esei.aed1.core.Juego;

//Ejercicio 1

public class Main {
    public static void main(String[] args) {
        System.out.println("Ejercicio 1, Luis Fernando Perez Moure, 220");
        IU iu = new IU();
        Juego cinquillo = new Juego(iu);
        cinquillo.jugar();
    }   
}