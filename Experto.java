import java.util.concurrent.TimeUnit;
/**
 * Clase que se encarga de dirigir el juego
 */
public class Experto{
    private Tablero tablero;
    private Gui gui;
    private Inteligencia ai;
    private Bacteria[][] tableroBacterias;
    private int anchoMatriz;
    private int largoMatriz;
    private int turno = 0;

    /**
     * Constructor de la clase
     * @param tablero Obejeto Tablero
     * @param gui Objeto Interfaz Gráfica
     * @param ai Objeto Inteligencia Artificial
     */
    public Experto(Tablero tablero, Gui gui, Inteligencia ai){
        this.tablero = tablero;
        this.gui = gui;
        this.ai = ai;
        this.tableroBacterias = tablero.getTablero();
        this.largoMatriz = tablero.getDimension()[0];
        this.anchoMatriz = tablero.getDimension()[1];
        gui.setPuntajes(this.getVerdes(),this.getRojos());
    }

    /**
     * Método que va a conectar a las primeras bacterias
     */
    public void iniciar(){
        this.conectar(0,0,1);
        this.conectar(anchoMatriz - 1,largoMatriz - 1,2);
        this.gui.updateBotones(this.tablero.getTablero());
        this.gui.setPuntajes(this.getVerdes(),this.getRojos());
    }
    
    /**
     * Método que devuelve el turno actual
     * @return int 
     */
    public int getTurno(){
        return this.turno;
    }

    /**
     * Método que analiza la bacteria, la rota, actualiza tablero, fija los puntajes y cambia de turno
     * @param x columna
     * @param y fila
     */
    public void analizarBacteria(int x, int y){
        this.rotarConectarUsuario(x,y);
        this.gui.updateBotones(this.tablero.getTablero());
        gui.setPuntajes(this.getVerdes(),this.getRojos());
        this.turno = 1;
        this.turnoAi();
    }

    /**
     * Método que rota y contagia una bacteria roja que es pasada por un método de la clase Inteligencia
     */
    public void turnoAi(){
    	
        if(this.turno == 1 && this.getRojos() != 0){
        	
            Bacteria bacteriaEscogida = ai.escogerBacteria();
            this.rotarConectarAi(bacteriaEscogida.getValorX(),bacteriaEscogida.getValorY());
            this.gui.updateBotones(this.tablero.getTablero());
            this.turno = 0;
            
        }
    }
    /**
    * Método que revisa la cantidad de bacterias que hay
    * @return int ganador
    */
    public int revisarGanador(){
    	int ganador = 0;
    	if(this.getVerdes() == 0){
    		ganador = 2;
    	}
    	if(this.getRojos() == 0){
    		ganador = 1;
    	}

    	return ganador;
    }

    /**
     * Método recursivo que se encarga de contagiar a las bacterias que están a su alredededor si se cumplen las condiciones
     * @param x columna
     * @param y fila
     * @param color color de bacteria a contagiar
     */
    public void conectar(int x, int y, int color){
        tableroBacterias = tablero.getTablero();
        tableroBacterias[y][x].setColor(color);

        if(tableroBacterias[y][x].getPosEje() == 0){
            if(x > 0 && tableroBacterias[y][x - 1].getPosEje() == 1 && tableroBacterias[y][x - 1].getColor() != color){
                this.conectar(x-1,y,color);
            }
            if(x < anchoMatriz - 1 && tableroBacterias[y][x + 1].getPosEje() == 3 && tableroBacterias[y][x + 1].getColor() != color){
                this.conectar(x + 1, y, color);
            }
            if(y < largoMatriz -1 && tableroBacterias[y + 1][x].getPosEje() == 0 && tableroBacterias[y + 1][x].getColor() != color){
                this.conectar(x, y + 1, color);
            }
            if(y > 0 && tableroBacterias[y - 1][x].getColor() != color){
                this.conectar(x, y - 1, color);
            }
        }

        if(tableroBacterias[y][x].getPosEje() == 1){
            if(x > 0 && tableroBacterias[y][x - 1].getPosEje() == 1 && tableroBacterias[y][x - 1].getColor() != color){
                this.conectar(x-1,y,color);
            }
            if(x < anchoMatriz - 1 && tableroBacterias[y][x + 1].getColor() != color){
                this.conectar(x + 1, y, color);
            }
            if(y < largoMatriz -1 && tableroBacterias[y + 1][x].getPosEje() == 0 && tableroBacterias[y + 1][x].getColor() != color){
                this.conectar(x, y + 1, color);
            }
            if(y > 0 && tableroBacterias[y - 1][x].getPosEje() == 2 && tableroBacterias[y - 1][x].getColor() != color){
                this.conectar(x, y - 1, color);
            }
        }

        if(tableroBacterias[y][x].getPosEje() == 2){
            if(x > 0 && tableroBacterias[y][x - 1].getPosEje() == 1 && tableroBacterias[y][x - 1].getColor() != color){
                this.conectar(x-1,y,color);
            }
            if(x < anchoMatriz - 1 && tableroBacterias[y][x + 1].getPosEje() == 3 && tableroBacterias[y][x + 1].getColor() != color){
                this.conectar(x + 1, y, color);
            }
            if(y < largoMatriz -1 && tableroBacterias[y + 1][x].getColor() != color){
                this.conectar(x, y + 1, color);
            }
            if(y > 0 && tableroBacterias[y - 1][x].getPosEje() == 2 && tableroBacterias[y - 1][x].getColor() != color){
                this.conectar(x, y - 1, color);
            }
        }

        if(tableroBacterias[y][x].getPosEje() == 3){
            if(x > 0 && tableroBacterias[y][x - 1].getColor() != color){
                this.conectar(x-1,y,color);
            }
            if(x < anchoMatriz - 1 && tableroBacterias[y][x + 1].getPosEje() == 3 && tableroBacterias[y][x + 1].getColor() != color){
                this.conectar(x + 1, y, color);
            }
            if(y < largoMatriz -1 && tableroBacterias[y + 1][x].getPosEje() == 0 && tableroBacterias[y + 1][x].getColor() != color){
                this.conectar(x, y + 1, color);
            }
            if(y > 0 && tableroBacterias[y - 1][x].getPosEje() == 2 && tableroBacterias[y - 1][x].getColor() != color){
                this.conectar(x, y - 1, color);
            }
        }

    }

    /**
     * Método que Rota y contagia Bacterias de color verde
     * @param x columna
     * @param y fila
     */
    public void rotarConectarUsuario(int x, int y){
        if(tableroBacterias[y][x].getColor() == 1){
            tableroBacterias[y][x].rotar();
            this.conectar(x,y, 1);
        }
        
    }

    /**
     * Método que se encarga de contagiar y rotar una bacteria de color rojo
     * @param x columna
     * @param y fila
     */
    public void rotarConectarAi(int x, int y){
        if(tableroBacterias[y][x].getColor() == 2){
            tableroBacterias[y][x].rotar();
            this.conectar(x,y, 2);
        }
    }
    
    /**
     * Método que lleva la cuenta de la cantidad de bacterias verdes en la matriz
     * @return int
     */
    public int getVerdes(){
        int verdes = 0;
        tableroBacterias = tablero.getTablero();
        for(int i = 0; i < tableroBacterias.length; i++){
            for(int j = 0; j < tableroBacterias[0].length; j++){
                if(tableroBacterias[i][j].getColor() == 1){
                    verdes++;
                }
            }
        }

        return verdes;
    }

    /**
     * Método que devuelve la cantidad de bacterias de color rojo
     * @return
     */
    public int getRojos(){
        int rojos = 0;
        tableroBacterias = tablero.getTablero();
        for(int i = 0; i < tableroBacterias.length; i++){
            for(int j = 0; j < tableroBacterias[0].length; j++){
                if(tableroBacterias[i][j].getColor() == 2){
                    rojos++;
                }
            }
        }

        return rojos;
    }
}