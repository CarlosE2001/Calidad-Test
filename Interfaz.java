
import javax.swing.JOptionPane;

/**
 * Clase que genera los mensajes que aparecen en pantalla, hereda los métodos de la clase JOptionPane
 * @author Carlos Espinoza
 * @version 28/9/19
 */
public class Interfaz extends JOptionPane{

    /**
     * Constructor de la clase interfaz
     */
    public Interfaz(){

    }

    /**
     * Función que crea una ventana con un mensaje 
     * @param mensaje mensaje a ser presentado
     */
    public void mensaje(String mensaje){
        this.showMessageDialog(null, mensaje);
    }
    
    /**
     * Función que permite mostrar un mensaje 
     * @param mensaje mensaje a ser mostrado
     * @param titulo titulo de la ventana
     */
    public void mensajeTitulo(String mensaje, String titulo){
        this.showMessageDialog(null,mensaje,titulo,JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Función que muestra una ventana con input que va a recibir y convertir el resultado en un int
     * @param mensaje mensaje que se muestra
     * @return int con el numero que se digitó
     */
    public int getNumero(String mensaje){
        String numeroString = this.showInputDialog(null, mensaje);
        int numeroInt = Integer.parseInt(numeroString);
        return numeroInt;
    }
    
    /**
     * Función que muestra una ventana con titulo e input que va a recibir y convertir el resultado en un int
     * @param mensaje mensaje que se muestra
     * @param titulo titulo de la ventana
     * @return int con el numero que se digitó
     */
    public int getNumeroTitulo(String mensaje, String titulo){
        String numeroString = this.showInputDialog(null, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
        int numeroInt = Integer.parseInt(numeroString);
        return numeroInt;
    }

    /**
     * Función que muestra una ventana con input que va a recibir una palabra o frase
     * @param mensaje mensaje que se muestra
     * @return String con la expresión que se digitó
     */
    public String getString(String mensaje){
        String input = this.showInputDialog(null, mensaje);
        return input;
    }
}