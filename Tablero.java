/**
 * Clase que crea y contiene la matriz de bacterias
 */
public class Tablero{

    private Bacteria[][] tableroBacterias;
    private int anchoMatriz;
    private int largoMatriz;
    private Interfaz gui = new Interfaz();

	/**
	 * Contructor de la clase Tablero
	 * @param ancho ancho de la matriz
	 * @param largo largo de la matriz
	 */
    public Tablero(int ancho, int largo){
        tableroBacterias = new Bacteria[largo][ancho];
        this.anchoMatriz = ancho;
        this.largoMatriz = largo;
        this.tableroInicial(ancho,largo);
    }

	/**
	 * Método que llena la matriz con bacterias
	 * @param ancho ancho de la matriz
	 * @param largo largo de la matriz
	 */
    public void tableroInicial(int ancho, int largo){
        for(int i = 0; i < tableroBacterias.length; i++){
            for(int j = 0; j < tableroBacterias[0].length; j++){
                tableroBacterias[i][j] = new Bacteria(j, i, 0);
            }
        }
        tableroBacterias[0][0].setColor(1);
        tableroBacterias[largoMatriz-1][anchoMatriz-1].setColor(2);
        
    }

	/**
	 * Método que devuelve la matriz de bacterias
	 * @return Bacterias[][]
	 */
    public Bacteria[][] getTablero(){
        return tableroBacterias;
    }
 
	/**
	 * Método que devuelve una bacteria en específico
	 * @param x columna de la matriz
	 * @param y fila de la matriz
	 * @return Bacteria
	 */
    public Bacteria getBacteria(int x, int y){
        return tableroBacterias[y][x];
    }

	/**
	 * Método que devuelve un arreglo con las dimensiones de la matriz
	 * @return int[]
	 */
    public int[] getDimension(){
        int[] dim = {this.anchoMatriz,this.largoMatriz};
        return dim;
    }
	
	/**
	 * Método que fue utilizado para probar la matriz con JOptionPane, despliega una ventana
	 */
    public void verMatriz(){
        String estado = "";
        String estado2 = "";
        for(int i = 0; i < largoMatriz; i++){
        for(int j = 0; j < anchoMatriz; j++){
                estado += "[" + tableroBacterias[i][j].getColor() + "]";
            }
            estado += "\n";
        }

        for(int i = 0; i < largoMatriz; i++){
        	for(int j = 0; j < anchoMatriz; j++){
                estado2 += "[" + tableroBacterias[i][j].getPosEje() + "]";
            }
            estado2 += "\n";
        }

        gui.mensaje(estado + "\n\n" + estado2);



    }
}