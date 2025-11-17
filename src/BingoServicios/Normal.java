package BingoServicios;

import BingoInterfaces.Ganador;
import BingoServicios.Columnas;
import BingoServicios.CuatroEsquinas;
/**
 *
 * @author Sebastian
 */

public class Normal implements Ganador {
    @Override
    public boolean esGanador(BingoCarton.Carton c){
        return new Filas().esGanador(c) || new Columnas().esGanador(c)|| new Diagonales().esGanador(c) || new CuatroEsquinas().esGanador(c);
    }
}
