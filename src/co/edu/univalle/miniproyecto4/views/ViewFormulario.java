/**
 Archivo: ViewFormulario.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 17 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intecion:
 Esta crea la venta que va a ser principal dentro del programa, desde aqui se crearan los apartados y botones
 sin funcionalidad para los eventos del controlador.
 */

package co.edu.univalle.miniproyecto4.views;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ViewFormulario extends JFrame{
    
    private Decolib imFFondo;

    // Crear objetos de tipo boton para el CRUD
    private JButton btnAñadir, btnEliminar, btnEditar, btnLimpiar, btnListar;
    // Crear objetos de tipo boton para el cambio de los apartados
    private JButton btnEmpleado, btnEps, btnFondoP, btnARL, btnCajaCompen, btnEmpresa, btnDevegno, btnDeduccion;
    private JLabel test;

    public ViewFormulario(){
        iniciarComponentes();
        imFFondo = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/fondo.png");
        imFFondo.setBounds(0,0,1280,720);
        add(imFFondo);

    }

    private void iniciarComponentes() {
        setTitle("Ingenio de caña (Ventana formulario)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setLayout(null);

        //Fuentes y colores para los objetos del programa
        Font nuevaTipografia = new Font("Berlin Sans FB Demi", Font.BOLD, 36);
        Color colorFuente = new Color(33,23,22);
        Color colorFondoWhite = new Color(253,241,219);

        //Configuracion botones apartado
        
        btnEmpleado = new JButton(" Empleado ");
        btnEmpleado.setBounds(145,101,285,41);
        btnEmpleado.setFont(nuevaTipografia);
        btnEmpleado.setForeground(colorFuente);
        btnEmpleado.setOpaque(true);
        btnEmpleado.setBackground(colorFondoWhite);
        add(btnEmpleado);

        btnEps = new JButton(" Eps ");
        btnEps.setBounds(145,166,285,41);
        btnEps.setFont(nuevaTipografia);
        btnEps.setForeground(colorFuente);
        btnEps.setOpaque(true);
        btnEps.setBackground(colorFondoWhite);
        add(btnEps);

        btnFondoP = new JButton("Fondo|Pension");
        btnFondoP.setBounds(145,231,285,41);
        btnFondoP.setFont(nuevaTipografia);
        btnFondoP.setForeground(colorFuente);
        btnFondoP.setOpaque(true);
        btnFondoP.setBackground(colorFondoWhite);
        add(btnFondoP);
        
        btnARL = new JButton(" ARL ");
        btnARL.setBounds(145,296,285,41);
        btnARL.setFont(nuevaTipografia);
        btnARL.setForeground(colorFuente);
        btnARL.setOpaque(true);
        btnARL.setBackground(colorFondoWhite);
        add(btnARL);

        btnCajaCompen = new JButton("Caja|Compen");
        btnCajaCompen.setBounds(145,361,285,41);
        btnCajaCompen.setFont(nuevaTipografia);
        btnCajaCompen.setForeground(colorFuente);
        btnCajaCompen.setOpaque(true);
        btnCajaCompen.setBackground(colorFondoWhite);
        add(btnCajaCompen);

        btnEmpresa = new JButton("Empresa");
        btnEmpresa.setBounds(145,426,285,41);
        btnEmpresa.setFont(nuevaTipografia);
        btnEmpresa.setForeground(colorFuente);
        btnEmpresa.setOpaque(true);
        btnEmpresa.setBackground(colorFondoWhite);
        add(btnEmpresa);

        btnDevegno = new JButton("Devengo");
        btnDevegno.setBounds(145,491,285,41);
        btnDevegno.setFont(nuevaTipografia);
        btnDevegno.setForeground(colorFuente);
        btnDevegno.setOpaque(true);
        btnDevegno.setBackground(colorFondoWhite);
        add(btnDevegno);

        btnDeduccion = new JButton("Deduccion");
        btnDeduccion.setBounds(145,556,285,41);
        btnDeduccion.setFont(nuevaTipografia);
        btnDeduccion.setForeground(colorFuente);
        btnDeduccion.setOpaque(true);
        btnDeduccion.setBackground(colorFondoWhite);
        add( btnDeduccion);

        //BOTONES DEL CRUD

        
        
    }
}
