package BingoServicios;

import BingoInterfaces.Ganador;
import BingoCarton.Carton;
/**
 *
 * @author Sebastian
 */

public class Filas implements Ganador {
    @Override
    public boolean esGanador(Carton c){
        boolean[][] m = c.getMarcados();
        for(int r=0;r<5;r++){
            boolean fil=true;
            for(int col=0;col<5;col++) if(!m[r][col]){ fil=false; 
            break; 
            }
            if(fil) return true;
        }
        return false;
    }
}
