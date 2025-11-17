package BingoServicios;

import BingoInterfaces.Ganador;
import BingoInterfaces.JuegoAviso;
import BingoCarton.Carton;
import BingoServicios.CartonLleno;
import BingoServicios.CuatroEsquinas;
import BingoServicios.Normal;
import BingoTombola.Tombola;
import EnumBingo.ModoJuego;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
/**
 *
 * @author Eduard Salas Murillo
 */

public class ServicioBingo {
    private final List<Carton> cartones = new ArrayList<>();
    private final Tombola tombola = Tombola.getInstance();
    private final CrearCarton crear = new CrearCarton();
    private ModoJuego modo = ModoJuego.NORMAL;
    private int ultimoNumero = -1;
    private final List<JuegoAviso> listeners = new ArrayList<>();

    public void addListener(JuegoAviso l){
        if(l!=null) listeners.add(l); 
    }

    public List<Carton> listarCartones(){ 
        return new ArrayList<>(cartones); 
    }

    public Carton crearCartonAutomatico(String id){
        Carton c = crear.crearAutomatico(id);
        cartones.add(c);
        listeners.forEach(ls -> ls.CartonAgregado(c));
        return c;
    }

    public boolean crearCartonManual(String id, int[][] numeros){
        Carton c = crear.crearManual(id, numeros);
        if(!c.validarManual()) return false;
        cartones.add(c);
        listeners.forEach(ls -> ls.CartonAgregado(c));
        return true;
    }

    public void eliminarCarton(String id){
        cartones.removeIf(c -> Objects.equals(c.getId(), id));
        listeners.forEach(ls -> ls.CartonesActualizados());
    }

    public int sacarAleatoria(){
        int n = tombola.sacarAleatoria();
        if(n>0){
            ultimoNumero = n;
            marcarEnTodos(n);
            listeners.forEach(ls -> ls.NumeroSacado(n));
        }
        return n;
    }

    public boolean sacarManual(int n){
        boolean removed = tombola.sacarManual(n);
        ultimoNumero = n;
        if(removed){
            marcarEnTodos(n);
            listeners.forEach(ls -> ls.NumeroSacado(n));
        }
        return removed;
    }

    public void desmarcarEnTodos(int n) {
        for (Carton c : cartones) {c.desmarcarNumero(n);} 
        tombola.devolverNumero(n);listeners.forEach(ls -> ls.CartonesActualizados());
    }
    
    public void marcarEnTodos(int n) {
        for (Carton c : cartones) {c.marcarNumeroSiExiste(n);}
        listeners.forEach(ls -> ls.CartonesActualizados());
    }
    
    public void limpiarMarcas() { 
        for (Carton c : cartones) { c.limpiarMarcas();}
        listeners.forEach(ls -> ls.CartonesActualizados());
    }
    public boolean estaMarcadoEnAlguno(int numero) { 
        for (Carton c : cartones) { if (c.estaMarcado(numero)) 
            return true;
        }
        return false;
    }
    
    public void devolverNumeroATombola(int n) { 
        if (!tombola.getDisponibles().contains(n) && n >= 1 && n <= 75) {
            tombola.getDisponibles().add(n); Collections.sort(tombola.getDisponibles());
        }
    }
    
    public void reiniciarJuego() {
      tombola.reiniciar();
      cartones.clear();
      ultimoNumero = -1;
      listeners.forEach(ls -> ls.CartonesActualizados());
      listeners.forEach(ls -> ls.NumeroSacado(-1));
    }
    
    public int getUltimoNumero(){ 
        return ultimoNumero; 
    }
    
    public void setModo(ModoJuego m){ 
        this.modo = m;
    }
    
    public ModoJuego getModo(){ 
        return modo; 
    }
    
    public boolean esGanador(Carton c){
        Ganador gad;
        switch(modo){
            case NORMAL: gad = new Normal(); 
            break;
            case CUATRO_ESQUINAS: gad = new CuatroEsquinas(); 
            break;
            case CARTON_LLENO: gad = new CartonLleno(); 
            break;
            default: gad = new Normal(); 
            break;
        }
        return gad.esGanador(c);
    }

    public Carton buscarPorId(String id){
        for(Carton c: cartones) if(c.getId().equals(id)) 
            return c;
        return null;
    }

    public List<Integer> getDisponibles(){ 
        return tombola.getDisponibles(); 
    }
    
    public int getRestantes(){ 
        return tombola.restantes(); 
    }

    public Carton getCarton(String id) {
        return buscarPorId(id);
    }
}
