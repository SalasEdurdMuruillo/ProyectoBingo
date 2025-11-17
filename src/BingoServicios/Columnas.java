package BingoServicios;

import BingoInterfaces.Ganador;
import BingoCarton.Carton;

public class Columnas implements Ganador {
    @Override
    public boolean esGanador(Carton c){
        boolean[][] m = c.getMarcados();
        for(int col=0;col<5;col++){
            boolean all=true;
            for(int r=0;r<5;r++) if(!m[r][col]){ all=false; break; }
            if(all) return true;
        }
        return false;
    }
}
