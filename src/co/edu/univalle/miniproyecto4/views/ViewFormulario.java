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
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class ViewFormulario extends JFrame implements ActionListener{
    
    private Decolib imFFondo;

    // Crear objetos de tipo boton para el CRUD
    private JButton btnAñadir, btnEliminar, btnEditar, btnLimpiar, btnListar;
    // Crear objetos de tipo boton para el cambio de los apartados
    private JButton btnEmpleado, btnEps, btnFondoP, btnARL, btnCajaCompen, btnEmpresa, btnDevegno, btnDeduccion;

    //Paneles para las categorias y titulos (Remplazar)
    private JPanel jpTittleR, jpTittleEmpleado, jpTittleEPS, jpTittleFondoP, jpTittleARL, jpTittleCaja, jpTittleEmpresa, jpTittleDevengo, jpTittleDeduccion;
    private Decolib im1, im2, im3, im4, im5, im6, im7, im8, im9, im10;
    private JPanel jpfondoF, jpReplace, jpEmpleado, jpEps, jpFondoP, jpARL, jpCaja, jpempresa, jpDev, jpDed;

    // -------------- PARA EL  OBJETO EMPLEADO ---------------- //
    private JLabel lblIdE, lblCodE, lblApellidos, lblNombres, lblDireccionE, lblEPS, lblFPP, lblDateborn, lblDateIngreso, lblDateRetiro, lblTipo, lblNumeroC;
    private  JTextField fildEmpleadoId, fildEmpleadoCod, fildEmpleadoApellido, fildEmpleadoNombre, fildEmpleadoDr, fildEmpleadoDateN, fildEmpleadoDateIngr, fildEmpleadoDateRet, fildEmpleadoNCuenta;
    private String[] epsEmpleado = {"Seleccionar","Codigo EPS"};
    private JComboBox<String> dropEpsEmpleado = new JComboBox<>(epsEmpleado);
    private String[] fppEmpleado = {"Seleccionar", "Codigo FPP"};
    private JComboBox<String> dropFppEmpleado = new JComboBox<>(fppEmpleado);
    private String[] tipoEmpleado = {"Seleccionar", "Socio","No socio"};
    private JComboBox<String> dropTipoEmpleado = new JComboBox<>(tipoEmpleado);
    
    // -------------- PARA EL OBJETO EPS ---------------- //
    // -------------- PARA EL OBJETO FONDOPENSION ---------------- //
    // -------------- PARA EL OBJETO ARL ---------------- //
    // -------------- PARA EL OBJETO CAJA COMPENSACION ---------------- //
    // -------------- PARA EL OBJETO EMPRESA ---------------- //
    // -------------- PARA EL OBJETO DEVENOG ---------------- //
    // -------------- PARA EL OBJETO DEDUCCION ---------------- //

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
        Font nuevaTipografia2 = new Font("Berlin Sans FB Demi", Font.BOLD, 24);
        Color colorFuente = new Color(33,23,22);
        Color colorFondoWhite = new Color(253,241,219);

        //Configuracion botones apartado
        
        btnEmpleado = new JButton(" Empleado ");
        btnEmpleado.setBounds(70,101,285,41);
        btnEmpleado.setFont(nuevaTipografia);
        btnEmpleado.setForeground(colorFuente);
        btnEmpleado.setOpaque(true);
        btnEmpleado.setBackground(colorFondoWhite);
        add(btnEmpleado);

        btnEps = new JButton(" Eps ");
        btnEps.setBounds(70,166,285,41);
        btnEps.setFont(nuevaTipografia);
        btnEps.setForeground(colorFuente);
        btnEps.setOpaque(true);
        btnEps.setBackground(colorFondoWhite);
        add(btnEps);

        btnFondoP = new JButton("Fondo|Pension");
        btnFondoP.setBounds(70,231,285,41);
        btnFondoP.setFont(nuevaTipografia);
        btnFondoP.setForeground(colorFuente);
        btnFondoP.setOpaque(true);
        btnFondoP.setBackground(colorFondoWhite);
        add(btnFondoP);
        
        btnARL = new JButton(" ARL ");
        btnARL.setBounds(70,296,285,41);
        btnARL.setFont(nuevaTipografia);
        btnARL.setForeground(colorFuente);
        btnARL.setOpaque(true);
        btnARL.setBackground(colorFondoWhite);
        add(btnARL);

        btnCajaCompen = new JButton("Caja|Compen");
        btnCajaCompen.setBounds(70,361,285,41);
        btnCajaCompen.setFont(nuevaTipografia);
        btnCajaCompen.setForeground(colorFuente);
        btnCajaCompen.setOpaque(true);
        btnCajaCompen.setBackground(colorFondoWhite);
        add(btnCajaCompen);

        btnEmpresa = new JButton("Empresa");
        btnEmpresa.setBounds(70,426,285,41);
        btnEmpresa.setFont(nuevaTipografia);
        btnEmpresa.setForeground(colorFuente);
        btnEmpresa.setOpaque(true);
        btnEmpresa.setBackground(colorFondoWhite);
        add(btnEmpresa);

        btnDevegno = new JButton("Devengo");
        btnDevegno.setBounds(70,491,285,41);
        btnDevegno.setFont(nuevaTipografia);
        btnDevegno.setForeground(colorFuente);
        btnDevegno.setOpaque(true);
        btnDevegno.setBackground(colorFondoWhite);
        add(btnDevegno);

        btnDeduccion = new JButton("Deduccion");
        btnDeduccion.setBounds(70,556,285,41);
        btnDeduccion.setFont(nuevaTipografia);
        btnDeduccion.setForeground(colorFuente);
        btnDeduccion.setOpaque(true);
        btnDeduccion.setBackground(colorFondoWhite);
        add( btnDeduccion);

        btnEmpleado.addActionListener(this);
        btnEps.addActionListener(this);
        btnFondoP.addActionListener(this);
        btnARL.addActionListener(this);
        btnCajaCompen.addActionListener(this);
        btnEmpresa.addActionListener(this);
        btnDevegno.addActionListener(this);
        btnDeduccion.addActionListener(this);


        /* ------------ PANELES DE LOS TITULOS DEL APARTADO ------------------  
          Esto con el fin de crear un panel incial que se replace, dependiendo de boton (indica el apartado)
        */

        jpTittleR = new JPanel();
        jpTittleR.setBounds(619,8,498,40);
        jpTittleR.setBackground(colorFuente);
        jpTittleR.setLayout(null);
        add(jpTittleR);

        im1 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/titulos/enblanco.png");
        im1.setBounds(0,0,498,40);
        jpTittleR.add(im1);

        jpTittleEmpleado = new JPanel();
        jpTittleEmpleado.setBounds(0,0,498,40);
        jpTittleEmpleado.setBackground(colorFuente);
        jpTittleEmpleado.setLayout(null);

        im2 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/titulos/templeado.png");
        im2.setBounds(0,0,498,40);
        jpTittleEmpleado.add(im2);

        jpTittleEPS = new JPanel();
        jpTittleEPS.setBounds(0,0,498,40);
        jpTittleEPS.setBackground(colorFuente);
        jpTittleEPS.setLayout(null);

        im3 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/titulos/teps.png");
        im3.setBounds(0,0,498,40);
        jpTittleEPS.add(im3);

        jpTittleFondoP = new JPanel();
        jpTittleFondoP.setBounds(0,0,498,40);
        jpTittleFondoP.setBackground(colorFuente);
        jpTittleFondoP.setLayout(null);

        im4 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/titulos/tfondop.png");
        im4.setBounds(0,0,498,40);
        jpTittleFondoP.add(im4);

        jpTittleARL = new JPanel();
        jpTittleARL.setBounds(0,0,498,40);
        jpTittleARL.setBackground(colorFondoWhite);
        jpTittleARL.setLayout(null);

        im5 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/titulos/tarl.png");
        im5.setBounds(0,0,498,40);
        jpTittleARL.add(im5);

        jpTittleCaja = new JPanel();
        jpTittleCaja.setBounds(0,0,498,40);
        jpTittleCaja.setBackground(colorFondoWhite);
        jpTittleCaja.setLayout(null);

        im6 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/titulos/tcaja.png");
        im6.setBounds(0,0,498,40);
        jpTittleCaja.add(im6);

        jpTittleEmpresa = new JPanel();
        jpTittleEmpresa.setBounds(0,0,498,40);
        jpTittleEmpresa.setBackground(colorFondoWhite);
        jpTittleEmpresa.setLayout(null);

        im7 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/titulos/tempresa.png");
        im7.setBounds(0,0,498,40);
        jpTittleEmpresa.add(im7);

        jpTittleDevengo = new JPanel();
        jpTittleDevengo.setBounds(0,0,498,40);
        jpTittleDevengo.setBackground(colorFondoWhite);
        jpTittleDevengo.setLayout(null);

        im8 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/titulos/tdv.png");
        im8.setBounds(0,0,498,40);
        jpTittleDevengo.add(im8);

        jpTittleDeduccion = new JPanel();
        jpTittleDeduccion.setBounds(0,0,498,40);
        jpTittleDeduccion.setBackground(colorFondoWhite);
        jpTittleDeduccion.setLayout(null);

        im9 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/titulos/tded.png");
        im9.setBounds(0,0,498,40);
        jpTittleDeduccion.add(im9);

        /* ------------ PANELES DE LOS FORMULARIOS DEL APARTADO ------------------  
          Esto con el fin de crear un panel incial que se replace, dependiendo de boton (indica el apartado)
          aunque este se encargada de mostrar los respectivos formularios que se requieren para el programa
        */

        jpfondoF = new JPanel();
        jpfondoF.setBounds(476,55,784,272);
        jpfondoF.setLayout(null);
        add(jpfondoF);

        jpReplace = new JPanel();
        jpReplace.setBounds(12,12,758,246);
        jpReplace.setLayout(null);
        jpReplace.setBackground(colorFondoWhite);
        jpfondoF.add(jpReplace);

        /* ----------- AÑADIR CORRESPONDIENTE A EMPLEADO ------------ */

        jpEmpleado = new JPanel();
        jpEmpleado.setBounds(0,0,758,246);
        jpEmpleado.setLayout(null);
        jpEmpleado.setBackground(colorFondoWhite);

        lblIdE = new JLabel("Identificacion");
        lblIdE.setBounds(14,15,139,30);
        lblIdE.setFont(nuevaTipografia2);
        lblIdE.setForeground(colorFuente);

        lblCodE = new JLabel("Codigo");
        lblCodE.setBounds(14,52,139,30);
        lblCodE.setFont(nuevaTipografia2);
        lblCodE.setForeground(colorFuente);

        lblApellidos = new JLabel("Apellidos");
        lblApellidos.setBounds(14,87,139,30);
        lblApellidos.setFont(nuevaTipografia2);
        lblApellidos.setForeground(colorFuente);

        lblNombres = new JLabel("Nombres");
        lblNombres.setBounds(14,124,139,30);
        lblNombres.setFont(nuevaTipografia2);
        lblNombres.setForeground(colorFuente);

        lblDireccionE = new JLabel("Direccion");
        lblDireccionE.setBounds(14,161,139,30);
        lblDireccionE.setFont(nuevaTipografia2);
        lblDireccionE.setForeground(colorFuente);

        lblEPS = new JLabel("Cod|EPS");
        lblEPS.setBounds(14,198,139,30);
        lblEPS.setFont(nuevaTipografia2);
        lblEPS.setForeground(colorFuente);

        lblFPP = new JLabel("Cod°FPP");
        lblFPP.setBounds(392, 15,139,30);
        lblFPP.setFont(nuevaTipografia2);
        lblFPP.setForeground(colorFuente);

        lblDateborn = new JLabel("Fecha|Nacimiento");
        lblDateborn.setBounds(392,52,139,30);
        lblDateborn.setFont(nuevaTipografia2);
        lblDateborn.setForeground(colorFuente);

        lblDateIngreso = new JLabel("Fecha|Ingreso");
        lblDateIngreso.setBounds(392,87,139,30);
        lblDateIngreso.setFont(nuevaTipografia2);
        lblDateIngreso.setForeground(colorFuente);
        
        lblDateRetiro = new JLabel("Fecha|Retiro");
        lblDateRetiro.setBounds(392,124, 139,30);
        lblDateRetiro.setFont(nuevaTipografia2);
        lblDateRetiro.setForeground(colorFuente);

        lblTipo = new JLabel("Tipo|Empleado");
        lblTipo.setBounds(392,161,139,30);
        lblTipo.setFont(nuevaTipografia2);
        lblTipo.setForeground(colorFuente);

        lblNumeroC = new JLabel("N° cuenta");
        lblNumeroC.setBounds(392,198,139,30);
        lblNumeroC.setFont(nuevaTipografia2);
        lblNumeroC.setForeground(colorFuente);

        fildEmpleadoId = new JTextField();
        fildEmpleadoId.setBounds(167,15,203,30);
        fildEmpleadoId.setFont(nuevaTipografia2);
        fildEmpleadoId.setForeground(colorFuente);
        fildEmpleadoId.setBackground(colorFondoWhite);

        fildEmpleadoCod = new JTextField();
        fildEmpleadoCod.setBounds(167,52,203,30);
        fildEmpleadoCod.setFont(nuevaTipografia2);
        fildEmpleadoCod.setForeground(colorFuente);
        fildEmpleadoCod.setBackground(colorFondoWhite);

        fildEmpleadoApellido = new JTextField();
        fildEmpleadoApellido.setBounds(167,87,203,30);
        fildEmpleadoApellido.setFont(nuevaTipografia2);
        fildEmpleadoApellido.setForeground(colorFuente);
        fildEmpleadoApellido.setBackground(colorFondoWhite);

        fildEmpleadoNombre = new JTextField();
        fildEmpleadoNombre.setBounds(167, 124,203,30);
        fildEmpleadoNombre.setFont(nuevaTipografia2);
        fildEmpleadoNombre.setForeground(colorFuente);
        fildEmpleadoNombre.setBackground(colorFondoWhite);

        fildEmpleadoDr = new JTextField();
        fildEmpleadoDr.setBounds(167,161,203,30);
        fildEmpleadoDr.setFont(nuevaTipografia2);
        fildEmpleadoDr.setForeground(colorFuente);
        fildEmpleadoDr.setBackground(colorFondoWhite);

        dropEpsEmpleado.setBounds(167,198, 203,30);
        dropEpsEmpleado.setOpaque(true);
        dropEpsEmpleado.setFont(nuevaTipografia2);
        dropEpsEmpleado.setForeground(colorFuente);
        dropEpsEmpleado.setBackground(colorFondoWhite);

        dropFppEmpleado.setBounds(541,15,203,30);
        dropFppEmpleado.setOpaque(true);
        dropFppEmpleado.setFont(nuevaTipografia2);
        dropFppEmpleado.setForeground(colorFuente);
        dropFppEmpleado.setBackground(colorFondoWhite);

        fildEmpleadoDateN = new JTextField();
        fildEmpleadoDateN.setBounds(541,52,203,30);
        fildEmpleadoDateN.setFont(nuevaTipografia2);
        fildEmpleadoDateN.setForeground(colorFuente);

        fildEmpleadoDateIngr = new JTextField();
        fildEmpleadoDateIngr.setBounds(541,87,203,30);
        fildEmpleadoDateIngr.setFont(nuevaTipografia2);
        fildEmpleadoDateIngr.setForeground(colorFuente);

        fildEmpleadoDateRet = new JTextField();
        fildEmpleadoDateRet.setBounds(541, 124,203,30);
        fildEmpleadoDateRet.setFont(nuevaTipografia2);
        fildEmpleadoDateRet.setForeground(colorFuente);

        dropTipoEmpleado.setBounds(541, 161,203,30);
        dropTipoEmpleado.setOpaque(true);
        dropTipoEmpleado.setFont(nuevaTipografia2);
        dropTipoEmpleado.setForeground(colorFuente);
        dropTipoEmpleado.setBackground(colorFondoWhite);


        //, fildEmpleadoNCuenta;
        jpEmpleado.add(lblIdE);
        jpEmpleado.add(lblCodE);
        jpEmpleado.add(lblApellidos);
        jpEmpleado.add(lblNombres);
        jpEmpleado.add(lblDireccionE);
        jpEmpleado.add(lblEPS);
        jpEmpleado.add(lblFPP);
        jpEmpleado.add(lblDateborn);
        jpEmpleado.add(lblDateIngreso);
        jpEmpleado.add(lblDateRetiro);
        jpEmpleado.add(lblTipo);
        jpEmpleado.add(lblNumeroC);
        jpEmpleado.add(fildEmpleadoId);
        jpEmpleado.add(fildEmpleadoCod);
        jpEmpleado.add(fildEmpleadoApellido);
        jpEmpleado.add(fildEmpleadoNombre);
        jpEmpleado.add(fildEmpleadoDr);
        jpEmpleado.add(dropEpsEmpleado);
        jpEmpleado.add(dropFppEmpleado);
        jpEmpleado.add(fildEmpleadoDateN);
        jpEmpleado.add(fildEmpleadoDateIngr);
        jpEmpleado.add(fildEmpleadoDateRet);
        jpEmpleado.add(dropTipoEmpleado);

        
        im10 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/formulario/fondo.png");
        im10.setBounds(0,0,784,272);
        jpfondoF.add(im10);

    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource() == btnEmpleado){
            System.out.println("Apartado Empleado");
            mostrartitle(jpTittleEmpleado);
            mostrarPanel(jpEmpleado);
        } else if(event.getSource() == btnEps){
            System.out.println("Apartado eps");
            mostrartitle(jpTittleEPS);
        } else if(event.getSource() == btnFondoP){
            System.out.println("Apartado fondo de presion");
            mostrartitle(jpTittleFondoP);
        } else if(event.getSource() == btnARL){
            System.out.println("Apartado ARL");
            mostrartitle(jpTittleARL);
        } else if(event.getSource() == btnCajaCompen){
            System.out.println("Apartado caja de compresacion");
            mostrartitle(jpTittleCaja);
        } else if(event.getSource() == btnEmpresa){
            System.out.println("Apartado empresa");
            mostrartitle(jpTittleEmpresa);
        } else if(event.getSource() == btnDevegno){
            System.out.println("Apartado devengo");
            mostrartitle(jpTittleDevengo);
        } else if(event.getSource() == btnDeduccion){
            System.out.println("Apartado deduccion");
            mostrartitle(jpTittleDeduccion);
        }
    }

    private void mostrartitle(JPanel showPanel){
        jpTittleR.removeAll();
        jpTittleR.add(showPanel);
        jpTittleR.revalidate();
        jpTittleR.repaint();
    }

    private void mostrarPanel (JPanel showP){
        jpReplace.removeAll();
        jpReplace.add(showP);
        jpReplace.revalidate();
        jpReplace.repaint();
    }

    /* --------------- PARA EMPLEADOS (Getters and Setters) ------------------- */

    public JTextField getFildEmpleadoId() {
        return fildEmpleadoId;
    }

    public JTextField getFildEmpleadoCod() {
        return fildEmpleadoCod;
    }

    public JTextField getFildEmpleadoApellido() {
        return fildEmpleadoApellido;
    }

    public JTextField getFildEmpleadoNombre() {
        return fildEmpleadoNombre;
    }

    public JTextField getFildEmpleadoDr() {
        return fildEmpleadoDr;
    }

    public JComboBox<String> getDropEpsEmpleado() {
        return dropEpsEmpleado;
    }

    public void setDropEpsEmpleado(JComboBox<String> dropEpsEmpleado) {
        this.dropEpsEmpleado = dropEpsEmpleado;
    }

    public JComboBox<String> getDropFppEmpleado() {
        return dropFppEmpleado;
    }

    public void setDropFppEmpleado(JComboBox<String> dropFppEmpleado) {
        this.dropFppEmpleado = dropFppEmpleado;
    }

    public JComboBox<String> getDropTipoEmpleado() {
        return dropTipoEmpleado;
    }

    public void setDropTipoEmpleado(JComboBox<String> dropTipoEmpleado) {
        this.dropTipoEmpleado = dropTipoEmpleado;
    }

    public JTextField getFildEmpleadoDateN() {
        return fildEmpleadoDateN;
    }

    public JTextField getFildEmpleadoDateIngr() {
        return fildEmpleadoDateIngr;
    }

    public JTextField getFildEmpleadoDateRet() {
        return fildEmpleadoDateRet;
    }

    public JTextField getFildEmpleadoNCuenta() {
        return fildEmpleadoNCuenta;
    }

    /* --------------- PARA EPS (Getters and Setters) ------------------- */
}
