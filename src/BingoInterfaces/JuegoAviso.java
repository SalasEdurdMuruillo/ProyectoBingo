package BingoInterfaces;

import BingoCarton.Carton;
/**
 *
 * @author Sebastian
 */

public interface JuegoAviso {
    void NumeroSacado(int numero);
    void CartonAgregado(Carton c);
    void CartonesActualizados();
    void CartonEliminado(Carton c);
    void JuegoReiniciado();
}
