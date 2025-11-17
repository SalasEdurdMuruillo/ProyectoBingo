package BingoCarton;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
/**
 *
 * @author Eduard Salas Murillo
 */
public class Carton implements Cloneable {
    private final String id;
    private final int[][] numeros; 
    private final boolean[][] marcados; 
    
    public String getId(){
        return id; 
    }
    
    public int[][] getNumeros(){
        return numeros;
    }
    
    public boolean[][] getMarcados(){ 
        return marcados; 
    }
    
    public void setNumero(int fila, int col, int valor){ 
        numeros[fila][col] = valor; 
    }
    
    public int getNumero(int fila, int col){
        return numeros[fila][col];
    }

    public Carton(String id) {
        this.id = id;
        this.numeros = new int[5][5];
        this.marcados = new boolean[5][5];
        this.marcados[2][2] = true; 
    }

    @Override
    public String toString(){
        return id; 
    }

    public void marcarNumeroSiExiste(int numero){
        for(int r=0;r<5;r++){
            for(int c=0;c<5;c++){
                if(numeros[r][c] == numero){
                    marcados[r][c] = true;
                }
            }
        }
    }
    public boolean estaMarcado(int numero) {
    for (int r = 0; r < 5; r++) {
        for (int c = 0; c < 5; c++) {
            if (numeros[r][c] == numero) {
                return marcados[r][c];
            }
        }    
    }
    return false; 
}
    
    public void desmarcarNumero(int numero){
        for(int r=0;r<5;r++){
            for(int c=0;c<5;c++){
                if(numeros[r][c] == numero){
                    marcados[r][c] = false;
                }
            }
        }
    }

    public void limpiarMarcas(){
        for(int r=0;r<5;r++) for(int c=0;c<5;c++) marcados[r][c]=false;
        marcados[2][2] = true;
    }

    public boolean contieneNumero(int numero){
        for(int r=0;r<5;r++) for(int c=0;c<5;c++) if(numeros[r][c]==numero) return true;
        return false;
    }

    public static boolean esValidoParaColumna(int col, int valor){
        int min = col*15 + 1;
        int max = (col+1)*15;
        return valor >= min && valor <= max;
    }

    public void llenarAutomatico(Random rnd){
        Set<Integer> usados = new HashSet<>();
        for(int c=0;c<5;c++){
            int inicio = c*15 + 1;
            for(int r=0;r<5;r++){
                if(r==2 && c==2){
                    numeros[r][c] = 0;
                    continue;
                }
                int candidato;
                do {
                    candidato = inicio + rnd.nextInt(15);
                } while(usados.contains(candidato));
                usados.add(candidato);
                numeros[r][c] = candidato;
            }
        }
    }

    public boolean validarManual(){
        Set<Integer> vistos = new HashSet<>();
        int cont = 0;
        for(int r=0;r<5;r++){
            for(int c=0;c<5;c++){
                if(r==2 && c==2) continue;
                int v = numeros[r][c];
                if(v < 1 || v > 75) return false;
                if(!esValidoParaColumna(c,v)) return false;
                if(vistos.contains(v)) return false;
                vistos.add(v);
                cont++;
            }
        }
        return cont == 24;
    }

    @Override
    public Carton clone() {
        try {
            Carton copy = (Carton) super.clone();
            int[][] nums = new int[5][5];
            boolean[][] marks = new boolean[5][5];
            for(int r=0;r<5;r++){
                for(int c=0;c<5;c++){
                    nums[r][c] = this.numeros[r][c];
                    marks[r][c] = this.marcados[r][c];
                }
            }
            System.arraycopy(nums, 0, copy.numeros, 0, nums.length);
            System.arraycopy(marks, 0, copy.marcados, 0, marks.length);
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
