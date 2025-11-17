package BingoServicios;

import BingoInterfaces.Ganador;
import BingoCarton.Carton;

public class CartonLleno implements Ganador {
    @Override
    public boolean esGanador(Carton c){
        boolean[][] m = c.getMarcados();
        int cnt=0;
        for(int r=0;r<5;r++)
            for(int col=0;col<5;col++) 
                if(m[r][col]) cnt++;
        return cnt==25;
    }
}
