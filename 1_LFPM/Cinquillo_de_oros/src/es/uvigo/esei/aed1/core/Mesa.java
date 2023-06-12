/*
* Representa la Mesa de juego. 
* Estructura: se utilizar� un TAD adecuado. Piensa que hay 4 palos y de cada palo se pueden colocar cartas 
* por cualquiera de los dos extremos (un array con 4 doblescolas parece lo más adecuado). La DobleCola se comento en clase de teoria
* Funcionalidad: saber si es posible colocar una carta en la mesa, colocar una carta en la mesa, mostrar la mesa
 */
package es.uvigo.esei.aed1.core;
import java.util.*;


public class Mesa {

    private final int NUM_CARTAS_POR_PALO = 12;
    private final int NUM_PALOS = 4;
    private final Stack<Carta>[] mesa;
    
    public Mesa(){
        mesa = new Stack [NUM_PALOS];
        for (int i = 0; i < NUM_PALOS; i++) {
            mesa[i] = new Stack<>();
        }
    }

    /**
     * Metodo que comprueba si UNA carta se puede colocar en la mesa
     * @param c carta que se evalua
     * @return 
     */
    public boolean sePuedePonerCarta(Carta c){
        boolean toret = false;
        int paloCarta = c.getPalo().ordinal();
        
        if (mesa[paloCarta].isEmpty()){
            if (c.getNumero() == 12){
                toret = true;
            }
        }else{
            int numeroAbajoPalo = mesa[paloCarta].peek().getNumero();
            if (numeroAbajoPalo == (c.getNumero() + 1)){
                toret = true;
            }
        }
        return toret;
    }
    
    /**
     * Coloca la carta que se pasa como parametro a su respectiva deque
     * @param c 
     */
    public void colocarCartaMesa(Carta c){
        
        int paloCarta = c.getPalo().ordinal();
        
        mesa[paloCarta].push(c);
        
    }
    
    /**
     * Funcion utilizada para comprobar si una carta que se pasa como parametro está en la mesa
     * @param c La carta a comprobar
     * @return Devuelve verdadero si está la carta en la mesa, en el caso contrario devuelve falso
     */
    public boolean estaAsDeOros(){
        return mesa[Carta.Palos.OROS.ordinal()].peek().getNumero() == 1;
    }

    
    /**
     * Metodo mostrar bonita la mesa de juego en cada turno
     * @return 
     */
    @Override
    public String toString(){

        int [][] mesaMatriz = new int[NUM_CARTAS_POR_PALO][NUM_PALOS];
        for (int i = 0; i < NUM_PALOS; i++) {
//          for (Carta c : mesa[i]) {
//              mesaMatriz[NUM_CARTAS_POR_PALO - c.getNumero()][c.getPalo().ordinal()] = c.getNumero();
//          }
            Stack <Carta> aux = new Stack<>();
            while(!mesa[i].isEmpty()){
                Carta c = mesa[i].pop();
                mesaMatriz[NUM_CARTAS_POR_PALO - c.getNumero()][c.getPalo().ordinal()] = c.getNumero();                
                aux.push(c);
            }
            
            while(!aux.isEmpty()){
                mesa[i].push(aux.pop());
            }
        }

        StringBuilder sb = new StringBuilder();

        Carta.Palos [] palos = Carta.Palos.values();        

        sb.append("\n\n");

//        for (Carta.Palos p : palos){
//            sb.append(p.name()).append("\t");
//        }

        for(int i = 0; i < NUM_PALOS; i++){
            sb.append(palos[i].name()).append("\t");
        }

        sb.append("\n");

        for (int i = 0; i < NUM_CARTAS_POR_PALO; i++) {
            for (int j = 0; j < NUM_PALOS; j++) {
                if (mesaMatriz[i][j] != 0) {
                    sb.append(mesaMatriz[i][j]);
                } else {
                    sb.append("###");
                }
                sb.append("\t");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

    
}
