package BingoServicios;

import BingoInterfaces.Ganador;
import BingoCarton.Carton;
/**
 *
 * @author Sebastian
 */

public class Diagonales implements Ganador {
    @Override
    public boolean esGanador(Carton c){
        boolean[][] m = c.getMarcados();
        boolean d1=true,d2=true;
        for(int i=0;i<5;i++){
            if(!m[i][i]) d1=false;
            if(!m[i][4-i]) d2=false;
        }
        return d1 || d2;
    }
}
