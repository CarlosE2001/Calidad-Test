import java.util.Random;

/**
 * Clase bacteria que se encarga de crear los individuos con atributos como posición, color, pos de eje...
 */
public class Bacteria{

	private int posicionEje; // 0 arriba, 1 derecha, 2 abajo, 3 izquierda
	private int color; // 0 nadie, 1 equipo verde, 2 equipo rojo
	private int[] pos = new int[2];
	private Random random;

	/**
	 * Constructor de la clase
	 * @param x columna de la matriz
	 * @param y filas de la matriz
	 * @param color color de la bacteria 
	 */
	public Bacteria(int x, int y, int color){
		random = new Random();
		this.pos[0] = x;
		this.pos[1] = y;
		this.color = color;
		this.posicionEje = random.nextInt(4);
	}

	/**
	 * Método que devuelve el color de la bacteria
	 * @return int
	 */
	public int getColor(){
		return this.color;
	}
	
	/**
	 * Método que devuelve la columana de la bacteria en la matriz
	 * @return int
	 */
	public int getValorX(){
		return pos[0];
	}

	/**
	 * Método que devuelve la fila de la bacteria en la matriz
	 * @return int
	 */
	public int getValorY(){
		return pos[1];
	}

	/**
	 * Método que devuelve el color de la bacteria en forma de String, utilizado para leer mejor los datos en la ventana de JOptionPane
	 * @return
	 */
	public String getColorString(){
		String estado = "";
		if(this.color == 0){
			estado = "Gris";
		}
		if(this.color == 1){
			estado = "Verde";
		}
		if(this.color == 2){
			estado = "Rojo";
		}
		return estado;
	}

	/**
	 * Método que devuelve la posición en el eje de la bacteria (Eje de rotación)
	 * @return int
	 */
	public int getPosEje(){
		return posicionEje;
	}

	/**
	 * Método que asigna el color a las bacterias
	 * @param color int color
	 */
	public void setColor(int color){
		this.color = color;
	}

	/**
	 * Método que asigna la posición del eje de la bacteria
	 * @param pos int posición
	 */
	public void setPosEje(int pos){
		this.posicionEje = pos;
	}

	/**
	 * Método que rota la bacteria
	 */
	public void rotar(){
		int rotar = this.posicionEje;
		rotar += 1;
		if(rotar > 3){
			rotar = 0;
		}
		this.posicionEje = rotar;
	}
}