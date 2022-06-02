import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * @Autor Carlos Espinoza
 * @version 2/11/19
 * 
 * Clase que se encarga de llamar a los objetos y descidir que hacer con los eventos de los botones
 */

public class Controlador implements ActionListener{
    private final int ANCHO_MATRIZ = 15;
    private final int ALTO_MATRIZ = 15;
    private Tablero tablero;
    private Gui gui;
    private Interfaz interfaz;
    private Experto experto;
    private Inteligencia ai;
    
    /**
     * Constructor de la clase 
     */
    public Controlador(){
        tablero = new Tablero(ANCHO_MATRIZ,ALTO_MATRIZ);
        gui = new Gui(ANCHO_MATRIZ,ALTO_MATRIZ,this,tablero);
        ai = new Inteligencia(tablero,gui);
        experto = new Experto(tablero,gui,ai);
        interfaz = new Interfaz();
    }


    /**
     * Método que inicia el programa
     * @param args
     */
    public static void main(String[] args){
        Controlador controlador = new Controlador();
        controlador.iniciarExperto();
    }
    
    /**
     * Método que va a iniciar la clase Experto
     */
    public void iniciarExperto(){
        this.experto.iniciar();
    }

    /**
     * Listener de los botones
     */
    @Override
    public void actionPerformed(ActionEvent e){
        JButton bacteria = (JButton) e.getSource();
        String getAction = bacteria.getActionCommand();
        if(getAction.length() > 5){
            
            if(getAction.equals("jugarTablero")){
                gui.jugar();
            }
            
            if(getAction.equals("creditos")){
                this.interfaz.mensaje("Carlos Espinoza\nNoviembre 2019\nVersion 2");
            }
            
            if(getAction.equals("salirJuego")){
                System.exit(0);
            }
            
        }else{
            int posComa = getAction.indexOf(",");
            int x = Integer.parseInt(getAction.substring(0,posComa));
            int y = Integer.parseInt(getAction.substring(posComa+1, getAction.length()));
            if(experto.getTurno() == 0 && tablero.getBacteria(x,y).getColor() == 1){
                experto.analizarBacteria(x,y);
            }
    
            if(this.experto.revisarGanador() != 0){
                if(this.experto.revisarGanador() == 1){
                    gui.ganador(1);
                }else{
                    gui.ganador(2);
                }
    
            }
        }
        


        
        
        // try {
        //         Thread.sleep(1 * 1000);
        //     } catch (InterruptedException ie) {
        //         Thread.currentThread().interrupt();
        //     }


    }
}