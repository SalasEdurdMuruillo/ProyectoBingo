package BingoServicios;

import BingoInterfaces.Ganador;
import BingoCarton.Carton;
/**
 *
 * @author Luisf
 */

public class CuatroEsquinas implements Ganador {
    @Override
    public boolean esGanador(Carton c){
        boolean[][] m = c.getMarcados();
        return m[0][0] && m[0][4] && m[4][0] && m[4][4];
    }
}
