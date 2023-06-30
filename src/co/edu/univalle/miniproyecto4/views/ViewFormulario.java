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
import javax.swing.table.DefaultTableModel;


public class ViewFormulario extends JFrame implements ActionListener{
    
    private Decolib imFFondo;

    // Crear objetos de tipo boton para el CRUD
    private JButton btnAñadir, btnEliminar, btnEditar, btnLimpiar, btnImprimir;
    private Decolib btnAdd, btnDelte, btnedit, btnclear, ic2;
    // Crear objetos de tipo boton para el cambio de los apartados
    private JButton btnEmpleado, btnEps, btnFondoP, btnARL, btnCajaCompen, btnEmpresa, btnDevegno, btnDeduccion, btnLiquidacion;

    //Paneles para las categorias y titulos (Remplazar)
    private JPanel jpTittleR, jpTittleEmpleado, jpTittleEPS, jpTittleFondoP, jpTittleARL, jpTittleCaja, jpTittleEmpresa, jpTittleDevengo, jpTittleDeduccion;
    private Decolib im1, im2, im3, im4, im5, im6, im7, im8, im9, im10, im11;
    private JPanel jpfondoF, jpReplace, jpEmpleado, jpEps, jpFondoP, jpARL, jpCajaComp, jpempresa, jpDev, jpDed;

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

    private JLabel lblCodigoEps, lblNombresEps;
    private JTextField fildEPSCod, fildEPSNombre;
    private Decolib fondoEPS;
    
    // -------------- PARA EL OBJETO FONDOPENSION ---------------- //

    private JLabel lblcodigoFPP, lblnombreFpp;
    private JTextField fildFPPcod, fildFPPnombre;
    private Decolib fondoFPP;

    // -------------- PARA EL OBJETO ARL ---------------- //

    private JLabel lblcodigoARL, lblnombreARL;
    private JTextField fildARLcod, fildARLnombre;
    private Decolib fondoARL;

    // -------------- PARA EL OBJETO CAJA COMPENSACION ---------------- //

    private JLabel lblcodigoCajaC, lblnombreCajaC;
    private JTextField fildCajaComCodigo, fildCajaComNombre;
    private Decolib fondoCajaComp;

    // -------------- PARA EL OBJETO EMPRESA ---------------- //

    private JLabel lblNit, lblNombre, lblTefelono, lblDireccion, lblRepresentante, lblCorreo, lblArl, lblCCP, lblsalariomin, lblAxTrasn;
    private JTextField fildEmpresaNit, fildEmpresaNombre, fildEmpresaTelefono, fildEmpresaDireccion, fildEmpresaRepre, fildEmpresaCorreo, fildEmpresaSalariomin, fildEmpresaAuxTrans;
    private String[] codARL = {"Seleccionar","codARL1","codARL2"};
    private JComboBox<String> dropCodARLEMPRESA = new JComboBox<>(codARL);
    private String[] codCPP = {"Seleccionar","codCajaCom1","codCajaCom2"};
    private JComboBox<String> dropCodCajaCom = new JComboBox<>(codCPP);
    private Decolib fondoEmpresa;

    // -------------- PARA EL OBJETO DEVENOG ---------------- //

    private JLabel lblcodigoDev, lblnombreDeven, lblhacebaseDev;
    private JTextField fildDevengoCodigo, fildDevengonombre;
    private String[] hacenbase = {"Seleccionar", "Si hace base","No hace base"};
    private JComboBox<String> dropbaseDevengo = new JComboBox<>(hacenbase);
    private Decolib fondoDevengo;

    // -------------- PARA EL OBJETO DEDUCCION ---------------- //

    private JLabel lblcodigoDed, lblnombreDed;
    private JTextField fildDeduccionCodigo, fildDeduccionNombre;
    
    // ------------- ELEMENTOS PARA LAS TABLAS ----------------//
    private JPanel jpfondotabla;
    private Decolib fondotabla;
    private JPanel jpTablaDatos = new JPanel();
    private JTable tablaDatos = new JTable();
    private JScrollPane panelTabla = new JScrollPane();

    private Decolib fondoDed;

    public ViewFormulario(){
        iniciarComponentes();
        imFFondo = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/fondo.png");
        imFFondo.setBounds(0,0,1280,720);
        add(imFFondo);
        

    }

    private void iniciarComponentes() {
        setTitle("Ingenio de caña (Ventana formulario)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 750);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        // setAlwaysOnTop(true);

        //Fuentes y colores para los objetos del programa
        Font nuevaTipografia = new Font("Berlin Sans FB Demi", Font.BOLD, 36);
        Font nuevaTipografia22 = new Font("Berlin Sans FB Demi", Font.BOLD, 22);
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

        btnLiquidacion = new JButton("Liquidar");
        btnLiquidacion.setBounds(70, 621 ,285,41);
        btnLiquidacion.setFont(nuevaTipografia);
        btnLiquidacion.setForeground(colorFuente);
        btnLiquidacion.setOpaque(true);
        btnLiquidacion.setBackground(colorFondoWhite);
        add(btnLiquidacion);

        btnEmpleado.addActionListener(this);
        btnEps.addActionListener(this);
        btnFondoP.addActionListener(this);
        btnARL.addActionListener(this);
        btnCajaCompen.addActionListener(this);
        btnEmpresa.addActionListener(this);
        btnDevegno.addActionListener(this);
        btnDeduccion.addActionListener(this);
        btnLiquidacion.addActionListener(this);


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

        im10 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/formulario/fondocomple.png");
        im10.setBounds(0,0,758,246);
        jpReplace.add(im10);

        /* ----------- AÑADIR CORRESPONDIENTE A EMPLEADO ------------ */

        jpEmpleado = new JPanel();
        jpEmpleado.setBounds(0,0,758,246);
        jpEmpleado.setLayout(null);
        jpEmpleado.setBackground(colorFondoWhite);

        lblIdE = new JLabel("Identificacion");
        lblIdE.setBounds(14,15,139,30);
        lblIdE.setFont(nuevaTipografia22);
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

        lblDateborn = new JLabel("Nacimiento");
        lblDateborn.setBounds(392,52,139,30);
        lblDateborn.setFont(nuevaTipografia2);
        lblDateborn.setForeground(colorFuente);

        lblDateIngreso = new JLabel("Date|Ingreso");
        lblDateIngreso.setBounds(392,87,139,30);
        lblDateIngreso.setFont(nuevaTipografia2);
        lblDateIngreso.setForeground(colorFuente);
        
        lblDateRetiro = new JLabel("Date|Retiro");
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
        fildEmpleadoDateN.setBackground(colorFondoWhite);

        fildEmpleadoDateIngr = new JTextField();
        fildEmpleadoDateIngr.setBounds(541,87,203,30);
        fildEmpleadoDateIngr.setFont(nuevaTipografia2);
        fildEmpleadoDateIngr.setForeground(colorFuente);
        fildEmpleadoDateIngr.setBackground(colorFondoWhite);

        fildEmpleadoDateRet = new JTextField();
        fildEmpleadoDateRet.setBounds(541, 124,203,30);
        fildEmpleadoDateRet.setFont(nuevaTipografia2);
        fildEmpleadoDateRet.setForeground(colorFuente);
        fildEmpleadoDateRet.setBackground(colorFondoWhite);

        dropTipoEmpleado.setBounds(541, 161,203,30);
        dropTipoEmpleado.setOpaque(true);
        dropTipoEmpleado.setFont(nuevaTipografia2);
        dropTipoEmpleado.setForeground(colorFuente);
        dropTipoEmpleado.setBackground(colorFondoWhite);

        fildEmpleadoNCuenta = new JTextField();
        fildEmpleadoNCuenta.setBounds(541,198,203,30);
        fildEmpleadoNCuenta.setFont(nuevaTipografia2);
        fildEmpleadoNCuenta.setForeground(colorFuente);
        fildEmpleadoNCuenta.setBackground(colorFondoWhite);


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
        jpEmpleado.add(fildEmpleadoNCuenta);


        /* ----------- AÑADIR CORRESPONDIENTE A EPS ------------ */

        jpEps = new JPanel();
        jpEps.setBounds(0,0,758,246);
        jpEps.setLayout(null);
        jpEps.setBackground(colorFondoWhite);

        lblCodigoEps = new JLabel("Codigo|Eps");
        lblCodigoEps.setBounds(14,15,139,30);
        lblCodigoEps.setFont(nuevaTipografia2);
        lblCodigoEps.setForeground(colorFuente);

        lblNombresEps = new JLabel("Nombre|Eps");
        lblNombresEps.setBounds(14,52,139,30);
        lblNombresEps.setFont(nuevaTipografia2);
        lblNombresEps.setForeground(colorFuente);

        fildEPSCod = new JTextField();
        fildEPSCod.setBounds(167,15,203,30);
        fildEPSCod.setFont(nuevaTipografia2);
        fildEPSCod.setForeground(colorFuente);
        fildEPSCod.setBackground(colorFondoWhite);

        fildEPSNombre = new JTextField();
        fildEPSNombre.setBounds(167,52,203,30);
        fildEPSNombre.setFont(nuevaTipografia2);
        fildEPSNombre.setForeground(colorFuente);
        fildEPSNombre.setBackground(colorFondoWhite);

        jpEps.add(lblCodigoEps);
        jpEps.add(lblNombresEps);
        jpEps.add(fildEPSCod);
        jpEps.add(fildEPSNombre);

        fondoEPS = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/formulario/fondocomple.png");
        fondoEPS.setBounds(0,0,758,246);
        jpEps.add(fondoEPS);
        

        /* ----------- AÑADIR CORRESPONDIENTE A FPP ------------ */

        jpFondoP = new JPanel();
        jpFondoP.setBounds(0,0,758,246);
        jpFondoP.setLayout(null);
        jpFondoP.setBackground(colorFondoWhite);

        lblcodigoFPP = new JLabel("Codigo|FPP");
        lblcodigoFPP.setBounds(14,15,139,30);
        lblcodigoFPP.setFont(nuevaTipografia2);
        lblcodigoFPP.setForeground(colorFuente);

        lblnombreFpp = new JLabel("Nmbre|FPP");
        lblnombreFpp.setBounds(14,52,139,30);
        lblnombreFpp.setFont(nuevaTipografia2);
        lblnombreFpp.setForeground(colorFuente);

        fildFPPcod = new JTextField();
        fildFPPcod.setBounds(167,15,203,30);
        fildFPPcod.setFont(nuevaTipografia2);
        fildFPPcod.setForeground(colorFuente);
        fildFPPcod.setBackground(colorFondoWhite);

        fildFPPnombre = new JTextField();
        fildFPPnombre.setBounds(167,52,203,30);
        fildFPPnombre.setFont(nuevaTipografia2);
        fildFPPnombre.setForeground(colorFuente);
        fildFPPnombre.setBackground(colorFondoWhite);

        jpFondoP.add(lblcodigoFPP);
        jpFondoP.add(lblnombreFpp);
        jpFondoP.add(fildFPPcod);
        jpFondoP.add(fildFPPnombre);

        fondoFPP = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/formulario/fondocomple.png");
        fondoFPP.setBounds(0,0,758,246);
        jpFondoP.add(fondoFPP);
       
        /* ----------- AÑADIR CORRESPONDIENTE A ARL ------------ */

        jpARL = new JPanel();
        jpARL.setBounds(0,0,758,246);
        jpARL.setLayout(null);
        jpARL.setBackground(colorFondoWhite);

        lblcodigoARL = new JLabel("Codigo|ARL");
        lblcodigoARL.setBounds(14,15,139,30);
        lblcodigoARL.setFont(nuevaTipografia2);
        lblcodigoARL.setForeground(colorFuente);

        lblnombreARL = new JLabel("Nmbre|ARL");
        lblnombreARL.setBounds(14,52,139,30);
        lblnombreARL.setFont(nuevaTipografia2);
        lblnombreARL.setForeground(colorFuente);

        fildARLcod = new JTextField();
        fildARLcod.setBounds(167,15,203,30);
        fildARLcod.setFont(nuevaTipografia2);
        fildARLcod.setForeground(colorFuente);
        fildARLcod.setBackground(colorFondoWhite);

        fildARLnombre = new JTextField();
        fildARLnombre.setBounds(167,52,203,30);
        fildARLnombre.setFont(nuevaTipografia2);
        fildARLnombre.setForeground(colorFuente);
        fildARLnombre.setBackground(colorFondoWhite);

        jpARL.add(lblcodigoARL);
        jpARL.add(lblnombreARL);
        jpARL.add(fildARLcod);
        jpARL.add(fildARLnombre);

        fondoARL = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/formulario/fondocomple.png");
        fondoARL.setBounds(0,0,758,246);
        jpARL.add(fondoARL);

        /* ----------- AÑADIR CORRESPONDIENTE A CAJA COMPENSACION ------------ */

        jpCajaComp = new JPanel();
        jpCajaComp.setBounds(0,0,758,246);
        jpCajaComp.setLayout(null);
        jpCajaComp.setBackground(colorFondoWhite);

        lblcodigoCajaC = new JLabel("Codigo|CC");
        lblcodigoCajaC.setBounds(14,15,139,30);
        lblcodigoCajaC.setFont(nuevaTipografia2);
        lblcodigoCajaC.setForeground(colorFuente);

        lblnombreCajaC = new JLabel("Nmbre|CC");
        lblnombreCajaC.setBounds(14,52,139,30);
        lblnombreCajaC.setFont(nuevaTipografia2);
        lblnombreCajaC.setForeground(colorFuente);

        fildCajaComCodigo = new JTextField();
        fildCajaComCodigo.setBounds(167,15,203,30);
        fildCajaComCodigo.setFont(nuevaTipografia2);
        fildCajaComCodigo.setForeground(colorFuente);
        fildCajaComCodigo.setBackground(colorFondoWhite);

        fildCajaComNombre = new JTextField();
        fildCajaComNombre.setBounds(167,52,203,30);
        fildCajaComNombre.setFont(nuevaTipografia2);
        fildCajaComNombre.setForeground(colorFuente);
        fildCajaComNombre.setBackground(colorFondoWhite);

        jpCajaComp.add(lblcodigoCajaC);
        jpCajaComp.add(lblnombreCajaC);
        jpCajaComp.add(fildCajaComCodigo);
        jpCajaComp.add(fildCajaComNombre);

        fondoCajaComp = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/formulario/fondocomple.png");
        fondoCajaComp.setBounds(0,0,758,246);
        jpCajaComp.add(fondoCajaComp);

        /* ----------- AÑADIR CORRESPONDIENTE A EMPRESA ------------ */

        jpempresa = new JPanel();
        jpempresa.setBounds(0,0,758,246);
        jpempresa.setLayout(null);
        jpempresa.setBackground(colorFondoWhite);

        lblNit = new JLabel("NIT");
        lblNit.setBounds(14,15,139,30);
        lblNit.setFont(nuevaTipografia2);
        lblNit.setForeground(colorFuente);

        lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(14,52,139,30);
        lblNombre.setFont(nuevaTipografia2);
        lblNombre.setForeground(colorFuente);

        lblTefelono = new JLabel("Telefono");
        lblTefelono.setBounds(14,87,139,30);
        lblTefelono.setFont(nuevaTipografia2);
        lblTefelono.setForeground(colorFuente);

        lblDireccion = new JLabel("Direccion");
        lblDireccion.setBounds(14,124,139,30);
        lblDireccion.setFont(nuevaTipografia2);
        lblDireccion.setForeground(colorFuente);

        lblRepresentante = new JLabel("Representa");
        lblRepresentante.setBounds(14,161,139,30);
        lblRepresentante.setFont(nuevaTipografia2);
        lblRepresentante.setForeground(colorFuente);

        lblCorreo = new JLabel("Correo Rpr");
        lblCorreo.setBounds(392,15,139,30);
        lblCorreo.setFont(nuevaTipografia2);
        lblCorreo.setForeground(colorFuente);

        lblArl = new JLabel("Cod|ARL");
        lblArl.setBounds(392,52,139,30);
        lblArl.setFont(nuevaTipografia2);
        lblArl.setForeground(colorFuente);

        lblcodigoCajaC = new JLabel("Cod|CCP");
        lblcodigoCajaC.setBounds(392,87,139,30);
        lblcodigoCajaC.setFont(nuevaTipografia2);
        lblcodigoCajaC.setForeground(colorFuente);

        lblsalariomin = new JLabel("SMMLV");
        lblsalariomin.setBounds(392,124,139,30);
        lblsalariomin.setFont(nuevaTipografia2);
        lblsalariomin.setForeground(colorFuente);

        lblAxTrasn = new JLabel("AxTrasnporte");
        lblAxTrasn.setBounds(392,161,139,30);
        lblAxTrasn.setFont(nuevaTipografia22);
        lblAxTrasn.setForeground(colorFuente);

        fildEmpresaNit = new JTextField();
        fildEmpresaNit.setBounds(167,15,203,30);
        fildEmpresaNit.setFont(nuevaTipografia2);
        fildEmpresaNit.setForeground(colorFuente);
        fildEmpresaNit.setBackground(colorFondoWhite);

        fildEmpresaNombre = new JTextField();
        fildEmpresaNombre.setBounds(167,52,203,30);
        fildEmpresaNombre.setFont(nuevaTipografia2);
        fildEmpresaNombre.setForeground(colorFuente);
        fildEmpresaNombre.setBackground(colorFondoWhite);

        fildEmpresaTelefono = new JTextField();
        fildEmpresaTelefono.setBounds(167,87,203,30);
        fildEmpresaTelefono.setFont(nuevaTipografia2);
        fildEmpresaTelefono.setForeground(colorFuente);
        fildEmpresaTelefono.setBackground(colorFondoWhite);

        fildEmpresaDireccion = new JTextField();
        fildEmpresaDireccion.setBounds(167,124,203,30);
        fildEmpresaDireccion.setFont(nuevaTipografia2);
        fildEmpresaDireccion.setForeground(colorFuente);
        fildEmpresaDireccion.setBackground(colorFondoWhite);

        fildEmpresaRepre = new JTextField();
        fildEmpresaRepre.setBounds(167,161,203,30);
        fildEmpresaRepre.setFont(nuevaTipografia2);
        fildEmpresaRepre.setForeground(colorFuente);
        fildEmpresaRepre.setBackground(colorFondoWhite);

        fildEmpresaCorreo = new JTextField();
        fildEmpresaCorreo.setBounds(541,15,203,30);
        fildEmpresaCorreo.setFont(nuevaTipografia2);
        fildEmpresaCorreo.setForeground(colorFuente);
        fildEmpresaCorreo.setBackground(colorFondoWhite);
    
        dropCodARLEMPRESA.setBounds(541,52,203,30);
        dropCodARLEMPRESA.setOpaque(true);
        dropCodARLEMPRESA.setFont(nuevaTipografia2);
        dropCodARLEMPRESA.setForeground(colorFuente);
        dropCodARLEMPRESA.setBackground(colorFondoWhite);

        dropCodCajaCom.setBounds(541,87,203,30);
        dropCodCajaCom.setOpaque(true);
        dropCodCajaCom.setFont(nuevaTipografia2);
        dropCodCajaCom.setForeground(colorFuente);
        dropCodCajaCom.setBackground(colorFondoWhite);

        fildEmpresaSalariomin = new JTextField();
        fildEmpresaSalariomin.setBounds(541,124,203,30);
        fildEmpresaSalariomin.setFont(nuevaTipografia2);
        fildEmpresaSalariomin.setForeground(colorFuente);
        fildEmpresaSalariomin.setBackground(colorFondoWhite);

        fildEmpresaAuxTrans = new JTextField();
        fildEmpresaAuxTrans.setBounds(541,161,203,30);
        fildEmpresaAuxTrans.setFont(nuevaTipografia2);
        fildEmpresaAuxTrans.setForeground(colorFuente);
        fildEmpresaAuxTrans.setBackground(colorFondoWhite);

        
        jpempresa.add(lblNit);
        jpempresa.add(lblNombre);
        jpempresa.add(lblTefelono);
        jpempresa.add(lblDireccion);
        jpempresa.add(lblRepresentante);
        jpempresa.add(lblCorreo);
        jpempresa.add(lblArl);
        jpempresa.add(lblcodigoCajaC);
        jpempresa.add(lblsalariomin);
        jpempresa.add(lblAxTrasn);
        jpempresa.add(fildEmpresaNit);
        jpempresa.add(fildEmpresaNombre);
        jpempresa.add(fildEmpresaTelefono);
        jpempresa.add(fildEmpresaDireccion);
        jpempresa.add(fildEmpresaRepre);
        jpempresa.add(fildEmpresaCorreo);
        jpempresa.add(dropCodARLEMPRESA);
        jpempresa.add(dropCodCajaCom);
        jpempresa.add(fildEmpresaSalariomin);
        jpempresa.add(fildEmpresaAuxTrans);

        fondoEmpresa = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/formulario/fondocomple1.png");
        fondoEmpresa.setBounds(0,0,758,246);
        jpempresa.add(fondoEmpresa);
        
        

        /* ----------- AÑADIR CORRESPONDIENTE A DEVENGO ------------ */
        
        jpDev = new JPanel();
        jpDev.setBounds(0,0,758,246);
        jpDev.setLayout(null);
        jpDev.setBackground(colorFondoWhite);

        lblcodigoDev = new JLabel("Codigo|Dev");
        lblcodigoDev.setBounds(14,15,139,30);
        lblcodigoDev.setFont(nuevaTipografia2);
        lblcodigoDev.setForeground(colorFuente);

        lblnombreDeven = new JLabel("Nmbre|Dev");
        lblnombreDeven .setBounds(14,52,139,30);
        lblnombreDeven .setFont(nuevaTipografia2);
        lblnombreDeven .setForeground(colorFuente);

        lblhacebaseDev = new JLabel("Hace base");
        lblhacebaseDev.setBounds(14,87,139,30);
        lblhacebaseDev.setFont(nuevaTipografia2);
        lblhacebaseDev.setForeground(colorFuente);

        fildDevengoCodigo = new JTextField();
        fildDevengoCodigo.setBounds(167,15,203,30);
        fildDevengoCodigo.setFont(nuevaTipografia2);
        fildDevengoCodigo.setForeground(colorFuente);
        fildDevengoCodigo.setBackground(colorFondoWhite);

        fildDevengonombre = new JTextField();
        fildDevengonombre.setBounds(167,52,203,30);
        fildDevengonombre.setFont(nuevaTipografia2);
        fildDevengonombre.setForeground(colorFuente);
        fildDevengonombre.setBackground(colorFondoWhite);

        dropbaseDevengo.setBounds(167,87,203,30);
        dropbaseDevengo.setOpaque(true);
        dropbaseDevengo.setFont(nuevaTipografia2);
        dropbaseDevengo.setForeground(colorFuente);
        dropbaseDevengo.setBackground(colorFondoWhite);

        jpDev.add(lblcodigoDev);
        jpDev.add(lblnombreDeven);
        jpDev.add(lblhacebaseDev);
        jpDev.add(fildDevengoCodigo);
        jpDev.add(fildDevengonombre);
        jpDev.add(dropbaseDevengo);

        fondoDevengo = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/formulario/fondocomple.png");
        fondoDevengo.setBounds(0,0,758,246);
        jpDev.add(fondoDevengo);
        
        /* ----------- AÑADIR CORRESPONDIENTE A DEDUCCION ------------ */

        jpDed = new JPanel();
        jpDed.setBounds(0,0,758,246);
        jpDed.setLayout(null);
        jpDed.setBackground(colorFondoWhite);

        lblcodigoDed = new JLabel("Codigo|Ded");
        lblcodigoDed.setBounds(14,15,139,30);
        lblcodigoDed.setFont(nuevaTipografia2);
        lblcodigoDed.setForeground(colorFuente);

        lblnombreDed = new JLabel("Nmbre|Ded");
        lblnombreDed.setBounds(14,52,139,30);
        lblnombreDed.setFont(nuevaTipografia2);
        lblnombreDed.setForeground(colorFuente);

        fildDeduccionCodigo = new JTextField();
        fildDeduccionCodigo.setBounds(167,15,203,30);
        fildDeduccionCodigo.setFont(nuevaTipografia2);
        fildDeduccionCodigo.setForeground(colorFuente);
        fildDeduccionCodigo.setBackground(colorFondoWhite);

        fildDeduccionNombre = new JTextField();
        fildDeduccionNombre.setBounds(167,52,203,30);
        fildDeduccionNombre.setFont(nuevaTipografia2);
        fildDeduccionNombre.setForeground(colorFuente);
        fildDeduccionNombre.setBackground(colorFondoWhite);

        jpDed.add(lblnombreDed);
        jpDed.add(lblcodigoDed);
        jpDed.add(fildDeduccionCodigo);
        jpDed.add(fildDeduccionNombre);

        fondoDed = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/formulario/fondocomple.png");
        fondoDed.setBounds(0,0,758,246);
        jpDed.add(fondoDed);

        /*-------------- Imagen de fondo --------------- */
        
        im10 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/formulario/fondo.png");
        im10.setBounds(0,0,784,272);
        jpfondoF.add(im10);

        /* -------------- Botones del CURD ---------------  */

        btnAñadir = new JButton("Añadir");
        btnAñadir.setBounds(553,355,149,49);
        btnAñadir.setOpaque(true);
        btnAñadir.setBorder(BorderFactory.createLineBorder(colorFuente));
        btnAñadir.setContentAreaFilled(false);
        btnAñadir.setLayout(null);
        add(btnAñadir);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(714,355,149,49);
        btnEliminar.setOpaque(true);
        btnEliminar.setBorder(BorderFactory.createLineBorder(colorFuente));
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.setLayout(null);
        add(btnEliminar);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(875,355,149,49);
        btnLimpiar.setOpaque(true);
        btnLimpiar.setBorder(BorderFactory.createLineBorder(colorFuente));
        btnLimpiar.setContentAreaFilled(false);
        btnLimpiar.setLayout(null);
        add(btnLimpiar);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(1035,355,149,49);
        btnEditar.setOpaque(true);
        btnEditar.setBorder(BorderFactory.createLineBorder(colorFuente));
        btnEditar.setContentAreaFilled(false);
        btnEditar.setLayout(null);
        add(btnEditar);

        btnImprimir = new JButton();
        btnImprimir.setBounds(1188,393,74,30);
        btnImprimir.setOpaque(true);
        btnImprimir.setBorder(BorderFactory.createLineBorder(colorFuente));
        btnImprimir.setContentAreaFilled(false);
        btnImprimir.setLayout(null);
        add(btnImprimir);

        ic2 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/btnimp.png");
        ic2.setBounds(0,0,74,30);
        btnImprimir.add(ic2);

        btnAdd = new Decolib("/co/edu/univalle/miniproyecto4/img/btns/add.png");
        btnAdd.setBounds(0,0,149,49);
        btnAñadir.add(btnAdd);

        btnDelte = new Decolib("/co/edu/univalle/miniproyecto4/img/btns/delt.png");
        btnDelte.setBounds(0,0,149,49);
        btnEliminar.add(btnDelte);

        btnclear = new Decolib("/co/edu/univalle/miniproyecto4/img/btns/clean.png");
        btnclear.setBounds(0,0,149,49);
        btnLimpiar.add(btnclear);

        btnedit = new Decolib("/co/edu/univalle/miniproyecto4/img/btns/edit.png");
        btnedit.setBounds(0,0,149,49);
        btnEditar.add(btnedit);



        /* -------------- PANEL DE TABLAS ---------------  */
        
        jpfondotabla = new JPanel();
        jpfondotabla.setBounds(505,432,727,275);
        jpfondotabla.setLayout(null);
        jpfondotabla.setBackground(colorFondoWhite);
        add(jpfondotabla);
        
        jpTablaDatos.setBounds(12,13,703,249);
        jpTablaDatos.setLayout(null);
        jpTablaDatos.setBackground(colorFuente);
        jpfondotabla.add(jpTablaDatos);
        jpTablaDatos.add(panelTabla);

        fondotabla = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaFormulario/tablas/fondo.png");
        fondotabla.setBounds(0,0,727,275);
        jpfondotabla.add(fondotabla);

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaDatos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        panelTabla.setBounds(0,0,703,249);
        panelTabla.setViewportView(tablaDatos);

        // String[] atributosTabla = {"NIT", "RAZÓN SOCIAL", "NOMBRE", "TELÉFONO", "DIRECCIÓN", "REP. LEGAL", "CORREO CONT.", "CÓD. ARL", "CÓD. CAJA", "SMLV", "AUX. TRANSP."};
        // DefaultTableModel  tableModel = new DefaultTableModel();
        // tableModel.setColumnIdentifiers(atributosTabla);
        // tablaDatos.setModel(tableModel);

        // setPanelTabla(panelTabla);
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
            mostrarPanel(jpEps);
        } else if(event.getSource() == btnFondoP){
            System.out.println("Apartado fondo de presion");
            mostrartitle(jpTittleFondoP);
            mostrarPanel(jpFondoP);
        } else if(event.getSource() == btnARL){
            System.out.println("Apartado ARL");
            mostrartitle(jpTittleARL);
            mostrarPanel(jpARL);
        } else if(event.getSource() == btnCajaCompen){
            System.out.println("Apartado caja de compresacion");
            mostrartitle(jpTittleCaja);
            mostrarPanel(jpCajaComp);
        } else if(event.getSource() == btnEmpresa){
            System.out.println("Apartado empresa");
            mostrartitle(jpTittleEmpresa);
            mostrarPanel(jpempresa);
        } else if(event.getSource() == btnDevegno){
            System.out.println("Apartado devengo");
            mostrartitle(jpTittleDevengo);
            mostrarPanel(jpDev);
        } else if(event.getSource() == btnDeduccion){
            System.out.println("Apartado deduccion");
            mostrartitle(jpTittleDeduccion);
            mostrarPanel(jpDed);
        } else if (event.getSource() == btnLiquidacion){
            System.out.println("Liquidar");
            ViewLiquidacion viewLiquidacion = new ViewLiquidacion();
            viewLiquidacion.setVisible(true);
            
        }
    }

    public void addListener(ActionListener listener){
        btnAñadir.addActionListener(listener);
        btnEliminar.addActionListener(listener);
        btnEditar.addActionListener(listener);
        btnLimpiar.addActionListener(listener);
        btnEmpleado.addActionListener(listener);
        btnEps.addActionListener(listener);
        btnFondoP.addActionListener(listener);
        btnARL.addActionListener(listener);
        btnCajaCompen.addActionListener(listener);
        btnEmpresa.addActionListener(listener);
        btnDevegno.addActionListener(listener);
        btnDeduccion.addActionListener(listener);
        
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

    public JTextField getFildEPSCod() {
        return fildEPSCod;
    }

    public JTextField getFildEPSNombre() {
        return fildEPSNombre;
    }

    /* --------------- PARA FPP (Getters and Setters) ------------------- */

    public JTextField getFildFPPcod() {
        return fildFPPcod;
    }

    public JTextField getFildFPPnombre() {
        return fildFPPnombre;
    }

    /* --------------- PARA ARL (Getters and Setters) ------------------- */

    public JTextField getFildARLcod() {
        return fildARLcod;
    }

    public JTextField getFildARLnombre() {
        return fildARLnombre;
    }

    /* --------------- PARA CAJA COMPENSACION (Getters and Setters) ------------------- */

    public JTextField getFildCajaComCodigo() {
        return fildCajaComCodigo;
    }

    public JTextField getFildCajaComNombre() {
        return fildCajaComNombre;
    }

    /* --------------- PARA LA CONFIGURACION DE EMPRESA (Getters and Setters) ------------------- */

    public JTextField getFildEmpresaNit() {
        return fildEmpresaNit;
    }

    public JTextField getFildEmpresaNombre() {
        return fildEmpresaNombre;
    }

    public JTextField getFildEmpresaTelefono() {
        return fildEmpresaTelefono;
    }

    public JTextField getFildEmpresaDireccion() {
        return fildEmpresaDireccion;
    }

    public JTextField getFildEmpresaRepre() {
        return fildEmpresaRepre;
    }

    public JTextField getFildEmpresaCorreo() {
        return fildEmpresaCorreo;
    }

    public JTextField getFildEmpresaSalariomin() {
        return fildEmpresaSalariomin;
    }

    public JTextField getFildEmpresaAuxTrans() {
        return fildEmpresaAuxTrans;
    }

    public JComboBox<String> getDropCodARLEMPRESA() {
        return dropCodARLEMPRESA;
    }

    public void setDropCodARLEMPRESA(JComboBox<String> dropCodARLEMPRESA) {
        this.dropCodARLEMPRESA = dropCodARLEMPRESA;
    }
    
    public JComboBox<String> getDropCodCajaCom() {
        return dropCodCajaCom;
    }

    public void setDropCodCajaCom(JComboBox<String> dropCodCajaCom) {
        this.dropCodCajaCom = dropCodCajaCom;
    }

    /* --------------- PARA CONCEPTO DE DEVENGO (Getters and Setters) ------------------- */

    public JTextField getFildDevengoCodigo() {
        return fildDevengoCodigo;
    }

    public JTextField getFildDevengonombre() {
        return fildDevengonombre;
    }

    public JComboBox<String> getDropbaseDevengo() {
        return dropbaseDevengo;
    }

    public void setDropbaseDevengo(JComboBox<String> dropbaseDevengo) {
        this.dropbaseDevengo = dropbaseDevengo;
    }

    /* --------------- PARA CONCEPTO DE DEDUCCION (Getters and Setters) ------------------- */

    public JTextField getFildDeduccionCodigo() {
        return fildDeduccionCodigo;
    }

    public JTextField getFildDeduccionNombre() {
        return fildDeduccionNombre;
    }

    /* --------------- PARA LAS TABLAS (Panel) (Getters and Setters) ------------------- */

    public JTable getTablaDatos() {
        return tablaDatos;
    }

    public void setTablaDatos(JTable tablaDatos) {
        this.tablaDatos = tablaDatos;
    }

    public JScrollPane getPanelTabla() {
        return panelTabla;
    }

    public void setPanelTabla(JScrollPane panelTabla) {
        // this.panelTabla = panelTabla;
        // panelTabla.add(tablaDatos);
        // jpTablaDatos.add(panelTabla);
    }

    public JPanel getJpTablaDatos() {
        return jpTablaDatos;
    }

    /* --------------- PARA LOS BOTONES (Getters) ------------------- */

    public JButton getBtnAñadir() {
        return btnAñadir;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public JButton getBtnEditar() {
        return btnEditar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public Decolib getBtnAdd() {
        return btnAdd;
    }

    public Decolib getBtnDelte() {
        return btnDelte;
    }

    public Decolib getBtnedit() {
        return btnedit;
    }

    public Decolib getBtnclear() {
        return btnclear;
    }

    public JButton getBtnEmpleado() {
        return btnEmpleado;
    }

    public JButton getBtnEps() {
        return btnEps;
    }

    public JButton getBtnFondoP() {
        return btnFondoP;
    }

    public JButton getBtnARL() {
        return btnARL;
    }

    public JButton getBtnCajaCompen() {
        return btnCajaCompen;
    }

    public JButton getBtnEmpresa() {
        return btnEmpresa;
    }

    public JButton getBtnDevegno() {
        return btnDevegno;
    }

    public JButton getBtnDeduccion() {
        return btnDeduccion;
    }
}
