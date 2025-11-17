package BingoTombola;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
/**
 *
 * @author Luisf
 */

public class Tombola {
    private static Tombola INSTANCE;
    private final List<Integer> bolas;
    private final Random rnd = new Random();

    private Tombola() {
        bolas = new ArrayList<>();
        reiniciar();
    }

    public static synchronized Tombola getInstance() {
        if (INSTANCE == null) INSTANCE = new Tombola();
        return INSTANCE;
    }
    
    public void reiniciar() {
        bolas.clear();
        for (int i = 1; i <= 75; i++) bolas.add(i);
        Collections.shuffle(bolas, rnd);
    }

    public void devolverNumero(int n) {
        if (!bolas.contains(n) && n >= 1 && n <= 75) {
            bolas.add(n);
            Collections.shuffle(bolas);
        }
    }
    
    public int sacarAleatoria() {
        if (bolas.isEmpty()) return -1;
        return bolas.remove(0);
    }
    
    public boolean sacarManual(int n) {
        return bolas.remove((Integer) n);
    }
    
    public List<Integer> getDisponibles() {
        return new ArrayList<>(bolas);
    }
    
    public int restantes() {
        return bolas.size();
    }
}
