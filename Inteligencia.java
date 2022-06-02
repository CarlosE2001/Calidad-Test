import java.util.Random;

/**
 * Clase que contiene la inteligencia artificial, se ecncarga de hacer movimientos de bacterias
 */
public class Inteligencia{
    private Tablero tablero;
    private Bacteria bacteria;
    private Random random;
    private Bacteria[][] tableroBacterias;
    private Gui gui;
    private int anchoMatriz;
    private int largoMatriz;
    private int contador;

    /**
     * Constructor
     * @param tablero Objeto Tablero
     * @param gui Obejto Interfaz Gráfica
     */
    public Inteligencia(Tablero tablero, Gui gui){
        this.tablero = tablero;
        this.gui = gui;
        random = new Random();
        this.getBacterias();
        this.largoMatriz = tablero.getDimension()[0];
        this.anchoMatriz = tablero.getDimension()[1];
    }

    /**
     * Método que pide la matriz de bacterias a la clase Tablero y la guarda en el atributo propio de la clase
     */
    public void getBacterias(){
        this.tableroBacterias = this.tablero.getTablero();
    }

    /**
     * Seleciona una bacteria por un método random
     * @return Bacteria
     */
    public Bacteria escogerBacteria(){
        Bacteria[] bordes = this.getBordes();
        int randomPos = random.nextInt(this.contador);
        return bordes[randomPos];
    }

    /**
     * Método que devuelve un arreglo con las bacteria que son bordes es decir que tienen por lo menos una bacteria de un color distinto de la propia en alguno de sus cuatro lados
     * @return Bacteria[]
     */
    public Bacteria[] getBordes(){
        Bacteria[] borde = new Bacteria[this.anchoMatriz * this.largoMatriz];
        int contadorInterno = 0;
        for(int i = 0; i < this.tableroBacterias.length; i++){
            for(int j = 0; j < this.tableroBacterias[0].length; j++){
                if(tableroBacterias[i][j].getColor() == 2){
                    boolean bordeRevisado = revisarBacteria(j,i);
                    if(bordeRevisado){
                        borde[contadorInterno] = tableroBacterias[i][j];
                        contadorInterno++;
                    }
                }
            }
        }
        this.contador = contadorInterno;
        return borde;
    }

    /**
     * Método que revisa si una bacteria está en el borde es decir que tiene por lo menos una bacteria de diferente color a ella misma en alguno de sus cuatro lados
     * @param x columna en la matriz
     * @param y fila en la matriz
     * @return Boolean es borde o no
     */
    public boolean revisarBacteria(int x, int y){

        boolean esBorde = false;
        if(x > 0 && (tableroBacterias[y][x-1].getColor() == 0 || tableroBacterias[y][x-1].getColor() == 1)){
            esBorde = true;
        }else if(y > 0 && (tableroBacterias[y-1][x].getColor() == 0 || tableroBacterias[y-1][x].getColor() == 1)){
            esBorde = true;
        }else if(x < this.anchoMatriz -1 && (tableroBacterias[y][x+1].getColor() == 0 || tableroBacterias[y][x+1].getColor() == 1)){
            esBorde = true;
        }else if(y < this.largoMatriz -1 && (tableroBacterias[y+1][x].getColor() == 0 || tableroBacterias[y+1][x].getColor() == 1)){
            esBorde = true;
        }

        return esBorde;

    }

    
}