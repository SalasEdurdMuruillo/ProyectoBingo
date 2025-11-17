package BingoServicios;

import BingoCarton.Carton;
import java.util.Random;
/**
 *
 * @author Eduard Salas Murillo
 */

public class CrearCarton {
    private final Random rnd = new Random();

    public Carton crearAutomatico(String id){
        Carton c = new Carton(id);
        c.llenarAutomatico(rnd);
        return c;
    }

    public Carton crearManual(String id, int[][] numeros){
        Carton c = new Carton(id);
        for(int r=0;r<5;r++)
            for(int col=0;col<5;col++) 
            c.setNumero(r,col,numeros[r][col]);
        return c;
    }
}
