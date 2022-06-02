import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase que contiene la interfaz gráfica
 */
public class Gui{
    //Componenetes Contenedores 
    private JFrame ventana;
    private JFrame ventanaPrincipal;
    private JFrame ventanaGanador;

    //Ventana Principal
    private JPanel panel;

    //Ventana de Juego
    private JPanel mainPanel;
    private JPanel gridPanel;
    private JPanel flowPanel;
    private JPanel flowPanelPrincipal;
    private JPanel mainGanador;

    //Componentes no contenedores venatana principal
    private JButton jugar;
    private JButton creditos;
    private JButton salir;

    private JLabel titulo;
    private JLabel lganador;

    //Componentes no contenedores ventana de juego
    private JButton[][] botones;
    private JButton boton;

    private JLabel puntaje;
    private JLabel turno;

    //Iconos de lo botones
    private ImageIcon[] icons_verde = {new ImageIcon("Imagenes/arriba_verde.png"),new ImageIcon("Imagenes/derecha_verde.png"), new ImageIcon("Imagenes/abajo_verde.png"), new ImageIcon("Imagenes/izquierda_verde.png")};
    private ImageIcon[] icons_rojo = {new ImageIcon("Imagenes/arriba_rojo.png"),new ImageIcon("Imagenes/derecha_rojo.png"), new ImageIcon("Imagenes/abajo_rojo.png"), new ImageIcon("Imagenes/izquierda_rojo.png")};
    private ImageIcon[] icons_gris = {new ImageIcon("Imagenes/arriba_gris.png"),new ImageIcon("Imagenes/derecha_gris.png"), new ImageIcon("Imagenes/abajo_gris.png"), new ImageIcon("Imagenes/izquierda_gris.png")};
    private ImageIcon[][] iconos = {icons_gris,icons_verde,icons_rojo};

    //Marcadores
    private int puntajeVerde;
    private int puntajeRojo;

    //Variables
    private int ancho;
    private int alto;

    //Objetos
    private Tablero tablero;
    private Controlador controlador;
    private Bacteria[][] tableroBacterias;    

    /**
     * Contructor de la clase
     * @param ancho int ancho de la matriz
     * @param alto int alto de la matriz
     * @param controlador clase controlador
     * @param tablero clase tablero
     */
    public Gui(int ancho, int alto, Controlador controlador, Tablero tablero){
        this.tablero = tablero;
        this.controlador = controlador;
        this.ancho = ancho;
        this.alto = alto;
        this.iniciarPanel();
    }

    /**
     * Método que le asigna los valores a los JLabel de los puntajes 
     * @param puntajeVerde int puntaje de bacterias verdes
     * @param puntajeRojo int puntaje de bacterias rojas
     */
    public void setPuntajes(int puntajeVerde, int puntajeRojo){
        this.puntajeVerde = puntajeVerde;
        this.puntajeRojo = puntajeRojo;
    }

    /**
     * Método que inicia la ventana con las bacterias y los puntajes 
     */
    public void iniciarPanel(){
        //Ventanas
        ventana = new JFrame();
        ventanaPrincipal = new JFrame();
        ventanaGanador = new JFrame();
        ventanaGanador.setTitle("Felicidades!!");
        ventanaPrincipal.setTitle("Bacteria2");
        ventana.setTitle("Bacteria2");

        //Paneles
        mainPanel = new JPanel(new BorderLayout());
        gridPanel = new JPanel(new GridLayout(alto,ancho));
        flowPanel = new JPanel();
        flowPanelPrincipal = new JPanel();
        mainGanador = new JPanel();
        panel = new JPanel(new BorderLayout());

        mainPanel.setBackground(Color.WHITE);
        gridPanel.setBackground(Color.WHITE);
        flowPanel.setBackground(Color.WHITE);
        flowPanelPrincipal.setBackground(Color.WHITE);
        mainGanador.setBackground(Color.WHITE);
        panel.setBackground(Color.WHITE);

        //Elementos Panel Superior Ventana Principal
        titulo = new JLabel("Bienvenidos a Bacteria2");

        //Elementos Panel Inferior Ventana Principal
        jugar = new JButton("Jugar");
        jugar.addActionListener(controlador);
        jugar.setActionCommand("jugarTablero");

        creditos = new JButton("Creditos");
        creditos.addActionListener(controlador);
        creditos.setActionCommand("creditos");

        salir = new JButton("Salir");
        salir.addActionListener(controlador);
        salir.setActionCommand("salirTablero");

        //Elementos Panel Superior Ventana de Juego
        flowPanel.add(puntaje = new JLabel("Verdes: " + this.puntajeVerde + " Rojos: " + this.puntajeRojo));

        //Elementos Panel Inferior Ventana de Juego
        this.tableroBacterias = tablero.getTablero();
        botones = new JButton[this.tableroBacterias.length][this.tableroBacterias[0].length];
        for(int i = 0; i < this.tableroBacterias.length; i++){
            for(int j = 0; j < this.tableroBacterias[0].length; j++){
                
                botones[i][j] = new JButton(iconos[tableroBacterias[i][j].getColor()][tableroBacterias[i][j].getPosEje()]);
                botones[i][j].addActionListener(controlador);
                botones[i][j].setActionCommand(Integer.toString(j)+ "," + Integer.toString(i));
                botones[i][j].setPreferredSize(new Dimension(40, 40));

                /****************************************** Para Windows quitar los '//' ************************************/
                // botones[i][j].setMargin(new Insets(0,0,0,0));
                // botones[i][j].setOpaque(false);
                // botones[i][j].setContentAreaFilled(false);
                // botones[i][j].setBorderPainted(false);
                /****************************************** Para Windows quitar los '//' ************************************/

                gridPanel.add(botones[i][j]);
            }
        }

        //Elementos Ventana Ganador
        lganador = new JLabel();


        flowPanelPrincipal.add(titulo);
        flowPanelPrincipal.add(jugar);
        flowPanelPrincipal.add(creditos);
        flowPanelPrincipal.add(salir);
        panel.add(flowPanelPrincipal, BorderLayout.CENTER);

        mainPanel.add(flowPanel, BorderLayout.NORTH);
        mainPanel.add(gridPanel, BorderLayout.CENTER);

        mainGanador.add(lganador);
        
       
        ventanaPrincipal.add(panel);
        ventanaPrincipal.pack();


        ventana.add(mainPanel);
        ventana.pack();

        ventanaGanador.add(mainGanador);
        ventanaGanador.pack();
        
        ventanaPrincipal.setSize(200,120);
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setVisible(true);
        
        ventana.setSize(this.alto * 40, this.ancho * 40);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setVisible(false);

        ventanaGanador.setSize(200,50);
        ventanaGanador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaGanador.setVisible(false);

        
    }

    public void jugar(){
        ventanaPrincipal.setVisible(false);
        ventana.setVisible(true);
    }

    public void ganador(int colorGanador){
        if(colorGanador == 1){
            lganador.setText("Ganador: Verde");
        }else{
            lganador.setText("Ganador: Rojo");
        }   
        ventana.setVisible(false);
        ventanaGanador.setVisible(true);
    }
    /**
     * Método que se encarga de actualizar la ventana
     * @param bacterias Bacterias[][] matriz de Bacterias
     */
    public void updateBotones(Bacteria[][] bacterias){
        for(int i = 0; i < botones.length; i++){
            for(int j = 0; j < botones[0].length; j++){
                botones[i][j].setIcon(iconos[bacterias[i][j].getColor()][bacterias[i][j].getPosEje()]);
            }
        }
        for(int i = 0; i < botones.length; i++){
            for(int j = 0; j < botones[0].length; j++){
                botones[i][j].setIcon(iconos[bacterias[i][j].getColor()][bacterias[i][j].getPosEje()]);
            }
        }

        puntaje.setText("Verdes: " + this.puntajeVerde + " Rojos: " + this.puntajeRojo);
    }
    
    
}