/**
 Archivo: ViewLiquidacion.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 17 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intecion:
 Esta crea la venta que va a ser la ventana secundaria, donde se añadira toda la informacion relacionada con
 la liquidacion del Empleado
 */

package co.edu.univalle.miniproyecto4.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ViewLiquidacion extends JFrame  {

    private JLabel lblEmpleados,lblFicha, lblHacienda, lblTonelada, lblFechaCorte, lblTipoCaña, lblDiacorte;
    private JTextField fildLiqFicha, fildLiqHacienda, fildLiqTonelada, fildLiqFechaCorte;

    private String[] empleados = {"Seleccionar"};
    private JComboBox<String> dropEmpleado = new JComboBox<>(empleados);

    private String[] tipoCana = {"Seleccionar", "C. Quem. Ordinaria", "C. Quem. Festiva", "C. Cruda Ordinaria", "C. Cruda Festiva"};
    private JComboBox<String> dropTipoCana = new JComboBox<>(tipoCana);

    private String[] diaCorte = {"Seleccionar", "Ordinario", "Festivo"};
    private JComboBox<String> dropDiacorte = new JComboBox<>(diaCorte);

    private JPanel panelTabla, panelTxtarea;
    private JLabel lblPagados, lblPendientes;
    private JRadioButton btnPagados, btnPendientes;
    private JButton btnRegistrar, btnFacturarEmitir, btnPreviz;
    private JLabel lblPrevizNonima;
    private JTextArea areaComprobanteNomina;

    private JPanel panelconTabla = new JPanel();
    private JScrollPane jpTabla = new JScrollPane();
    private JTable tablaDatos = new JTable();

    private Decolib im1, im2, im3, ic1, imf1, imf2;

    /* Para combobox de devengo y deducciones */
    private JPanel panelDevFondo, panelDecFondo;
    private JLabel devTittle, decTittle;
    private String[] devengos = {"Seleccionar"};
    private JComboBox<String> dropDevengos = new JComboBox<>(devengos);
    private String[] deducciones = {"Seleccionar"};
    private JComboBox<String> dropDeducciones = new JComboBox<>(devengos);
    private JButton btnAddDevengo, btnDeleteDevengo, btnAddDeduccion, btnDeleteDeduccion, btnHome;
    private Decolib bt1, bt2, bt3, bt4, btn5;
    
    // combobox devengo
    private JPanel panelTablaDev = new JPanel();
    private JScrollPane jpTablaDev = new JScrollPane();
    // private String[] devengosList = {"Elemento 1"};
    private JList<String> listDevengos = new JList<>();

    // combobox deduccion
    private JPanel panelTablaDed = new JPanel();
    private JScrollPane jpTablaDed = new JScrollPane();
    // private String[] deduccionList = {"Elemento 1"};
    private JList<String> listDeduccion = new JList<>();
    // Text Area
    private JScrollPane jpAreaJScrollPane = new JScrollPane();

    

    public ViewLiquidacion(){
        inciarComponentes();
        im1 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/fondo.png");
        im1.setBounds(0,0,1330, 700);  
        add(im1);
    }

    private void inciarComponentes() {
        setTitle("Ingenio de caña (Ventana Liquidacion)");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1344, 735);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        // setVisible(false);


        Font nuevaTipografia = new Font("Berlin Sans FB Demi", Font.BOLD, 22);
        Color colorFondoWhite = new Color(253,241,219);
        Color colorFuente = new Color(33,23,22);

         /*--------------------Respecto a el empleado --------------------------- */

        lblEmpleados = new JLabel("Empleados");
        lblEmpleados.setBounds(142, 100, 206,29);
        lblEmpleados.setFont(nuevaTipografia);
        lblEmpleados.setForeground(colorFuente);
        lblEmpleados.setHorizontalAlignment(JLabel.CENTER);

        lblFicha = new JLabel("Ficha|Empleado");
        lblFicha.setBounds(521,19,206,29);
        lblFicha.setFont(nuevaTipografia);
        lblFicha.setForeground(colorFuente);

        lblHacienda = new JLabel("Hacienda S.");
        lblHacienda.setBounds(521,56,206,29);
        lblHacienda.setFont(nuevaTipografia);
        lblHacienda.setForeground(colorFuente);

        lblTonelada = new JLabel("Tonelada C.");
        lblTonelada.setBounds(521,93,206,29);
        lblTonelada.setFont(nuevaTipografia);
        lblTonelada.setForeground(colorFuente);

        lblFechaCorte = new JLabel("Fecha de Corte");
        lblFechaCorte.setBounds(521,130,206,29);
        lblFechaCorte.setFont(nuevaTipografia);
        lblFechaCorte.setForeground(colorFuente);

        lblTipoCaña = new JLabel("Tipo de caña");
        lblTipoCaña.setBounds(521,167,206,29);
        lblTipoCaña.setFont(nuevaTipografia);
        lblTipoCaña.setForeground(colorFuente);

        lblDiacorte = new JLabel("Dia de corte");
        lblDiacorte.setBounds(521,204,206,29);
        lblDiacorte.setFont(nuevaTipografia);
        lblDiacorte.setForeground(colorFuente);

        dropEmpleado.setBounds(118,132,257,29);
        dropEmpleado.setOpaque(false);
        dropEmpleado.setFont(nuevaTipografia);
        dropEmpleado.setForeground(colorFuente);
        dropEmpleado.setBackground(colorFondoWhite);

        fildLiqFicha = new JTextField();
        fildLiqFicha.setBounds(741,19, 257,29);
        fildLiqFicha.setFont(nuevaTipografia);
        fildLiqFicha.setForeground(colorFuente);
        fildLiqFicha.setBackground(colorFondoWhite);

        fildLiqHacienda = new JTextField();
        fildLiqHacienda.setBounds(741,56,257,29);
        fildLiqHacienda.setFont(nuevaTipografia);
        fildLiqHacienda.setForeground(colorFuente);
        fildLiqHacienda.setBackground(colorFondoWhite);

        fildLiqTonelada = new JTextField();
        fildLiqTonelada.setBounds(741,93,257,29);
        fildLiqTonelada.setFont(nuevaTipografia);
        fildLiqTonelada.setForeground(colorFuente);
        fildLiqTonelada.setBackground(colorFondoWhite);

        fildLiqFechaCorte = new JTextField("AAAA-MM-DD");
        fildLiqFechaCorte.setBounds(741,130,257,29);
        fildLiqFechaCorte.setFont(nuevaTipografia);
        fildLiqFechaCorte.setForeground(colorFuente);
        fildLiqFechaCorte.setBackground(colorFondoWhite);

        dropTipoCana.setBounds(741,167,257,29);
        dropTipoCana.setOpaque(false);
        dropTipoCana.setFont(nuevaTipografia);
        dropTipoCana.setForeground(colorFuente);
        dropTipoCana.setBackground(colorFondoWhite);

        dropDiacorte.setBounds(741, 204,257,29);
        dropDiacorte.setOpaque(false);
        dropDiacorte.setFont(nuevaTipografia);
        dropDiacorte.setForeground(colorFuente);
        dropDiacorte.setBackground(colorFondoWhite);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(682,245,188,36);
        btnRegistrar.setFont(nuevaTipografia);
        btnRegistrar.setForeground(colorFondoWhite);
        btnRegistrar.setOpaque(true);
        btnRegistrar.setBackground(colorFuente);

        
        add(lblEmpleados);
        add(lblFicha);
        add(lblHacienda);
        add(lblTonelada);
        add(lblFechaCorte);
        add(lblTipoCaña);
        add(lblDiacorte);
        add(dropEmpleado);
        add(fildLiqFicha);
        add(fildLiqHacienda);
        add(fildLiqTonelada);
        add(fildLiqFechaCorte);
        add(dropTipoCana);
        add(dropDiacorte);
        add(btnRegistrar);

        /*--------------------Respecto a titulos y botones --------------------------- */

        lblPagados = new JLabel("Pagados");
        lblPagados.setBounds(86,287,148,29);
        lblPagados.setFont(nuevaTipografia);
        lblPagados.setForeground(colorFuente);

        lblPendientes = new JLabel("Pendientes");
        lblPendientes.setBounds(304,287,148,29);
        lblPendientes.setFont(nuevaTipografia);
        lblPendientes.setForeground(colorFuente);

        btnPagados = new JRadioButton();
        btnPagados.setBounds(56,295,16,16);
        btnPagados.setOpaque(true);
        btnPagados.setBackground(colorFondoWhite);
        

        btnPendientes = new JRadioButton();
        btnPendientes.setBounds(274,295,16,16);
        btnPendientes.setOpaque(true);
        btnPendientes.setBackground(colorFondoWhite);

        add(lblPagados);
        add(lblPendientes);

        ButtonGroup grupo = new ButtonGroup();
        grupo.add(btnPagados);
        grupo.add(btnPendientes);
        
        add(btnPagados);
        add(btnPendientes);

        lblPrevizNonima = new JLabel("Previzualizacion comprobante");
        lblPrevizNonima.setBounds(479,299,319,29);
        lblPrevizNonima.setFont(nuevaTipografia);
        lblPrevizNonima.setForeground(colorFuente);
        add(lblPrevizNonima);

        btnPreviz = new JButton();
        btnPreviz.setBounds(961,294,74,30);
        btnPreviz.setOpaque(true);
        btnPreviz.setBackground(colorFondoWhite);
        btnPreviz.setLayout(null);
        add(btnPreviz);

        ic1 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/btnwacth.png");
        ic1.setBounds(0,0,74,30);
        btnPreviz.add(ic1);


        btnFacturarEmitir = new JButton("Facturar|Emitir pago");
        btnFacturarEmitir.setBounds(776,645,264,30);
        btnFacturarEmitir.setFont(nuevaTipografia);
        btnFacturarEmitir.setForeground(colorFondoWhite);
        btnFacturarEmitir.setOpaque(true);
        btnFacturarEmitir.setBackground(colorFuente);
        add(btnFacturarEmitir);

        /*--------------------Respecto a tabla y area de texto --------------------------- */

        panelTabla = new JPanel();
        panelTabla.setBounds(47,332,399,305);
        panelTabla.setBackground(colorFondoWhite);
        panelTabla.setLayout(null);
        add(panelTabla);

        panelconTabla.setBounds(10,10,378,284);
        panelconTabla.setLayout(null);
        panelconTabla.setBackground(colorFondoWhite);
        panelTabla.add(panelconTabla);
        panelconTabla.add(jpTabla);

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
        tablaDatos.setBackground(colorFondoWhite);
        jpTabla.setBounds(0,0,378,284);
        jpTabla.setViewportView(tablaDatos); 

        im2 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/fondotabla.png");
        im2.setBounds(0,0,399,305);
        panelTabla.add(im2);

        panelTxtarea = new JPanel();
        panelTxtarea.setBounds(479,332,561,305);
        panelTxtarea.setBackground(colorFondoWhite);
        panelTxtarea.setLayout(null);
        add(panelTxtarea);

        areaComprobanteNomina = new JTextArea();
        areaComprobanteNomina.setBounds(17,10,526,284);
        areaComprobanteNomina.setFont(nuevaTipografia);
        areaComprobanteNomina.setForeground(colorFuente);
        areaComprobanteNomina.setBackground(colorFondoWhite);
        panelTxtarea.add(jpAreaJScrollPane);
        jpAreaJScrollPane.setBounds(17, 10, 526, 284);
        jpAreaJScrollPane.setViewportView(areaComprobanteNomina);

        im3 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/fondoText.png");
        im3.setBounds(0,0,561,305);
        panelTxtarea.add(im3);

        /*-------------------- Nuevo apartado --------------------------- */

        devTittle = new JLabel("Devengos");
        devTittle.setBounds(1050,21,159,29);
        devTittle.setFont(nuevaTipografia);
        devTittle.setForeground(colorFuente);
        devTittle.setHorizontalAlignment(JLabel.CENTER);

        dropDevengos.setBounds(1073,308,206,29);
        dropDevengos.setOpaque(true);
        dropDevengos.setFont(nuevaTipografia);
        dropDevengos.setForeground(colorFuente);
        dropDevengos.setBackground(colorFondoWhite);

        btnAddDevengo = new JButton();
        btnAddDevengo.setBounds(1218,21,30,29);
        btnAddDevengo.setOpaque(true);
        btnAddDevengo.setBackground(colorFondoWhite);
        btnAddDevengo.setLayout(null);
        add(btnAddDevengo);

        btnDeleteDevengo = new JButton();
        btnDeleteDevengo.setBounds(1261,21,30,29);
        btnDeleteDevengo.setOpaque(true);
        btnDeleteDevengo.setBackground(colorFondoWhite);
        btnDeleteDevengo.setLayout(null);
        add(btnDeleteDevengo);

        bt1 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/btnadd.png");
        bt1.setBounds(0,0,30,29);
        btnAddDevengo.add(bt1);

        bt2 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/btndelete.png");
        bt2.setBounds(0,0,30,29);
        btnDeleteDevengo.add(bt2);

        add(devTittle);
        add(dropDevengos);
    
        panelDevFondo = new JPanel();
        panelDevFondo.setBounds(1052,61,247,234);
        panelDevFondo.setBackground(colorFondoWhite);
        panelDevFondo.setLayout(null);
        add(panelDevFondo);

        panelTablaDev.setBounds(7,7,233,220);
        panelTablaDev.setLayout(null);
        panelTablaDev.setBackground(colorFondoWhite);
        panelDevFondo.add(panelTablaDev);
        panelTablaDev.add(jpTablaDev);

        listDevengos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listDevengos.setBackground(colorFondoWhite);
        jpTablaDev.setBounds(0,0,233,220);
        jpTablaDev.setViewportView(listDevengos);

        imf1 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/fondotablita.png");
        imf1.setBounds(0,0,247,234);
        panelDevFondo.add(imf1);

        decTittle = new JLabel("Deducciones");
        decTittle.setBounds(1050,352,159,29);
        decTittle.setFont(nuevaTipografia);
        decTittle.setForeground(colorFuente);
        decTittle.setHorizontalAlignment(JLabel.CENTER);

        dropDeducciones.setBounds(1074,637,206,29);
        dropDeducciones.setOpaque(true);
        dropDeducciones.setFont(nuevaTipografia);
        dropDeducciones.setForeground(colorFuente);
        dropDeducciones.setBackground(colorFondoWhite);

        btnAddDeduccion = new JButton();
        btnAddDeduccion.setBounds(1218,350,30,29);
        btnAddDeduccion.setOpaque(true);
        btnAddDeduccion.setBackground(colorFondoWhite);
        btnAddDeduccion.setLayout(null);
        add(btnAddDeduccion);

        btnDeleteDeduccion = new JButton();
        btnDeleteDeduccion .setBounds(1261,350,30,29);
        btnDeleteDeduccion .setOpaque(true);
        btnDeleteDeduccion .setBackground(colorFondoWhite);
        btnDeleteDeduccion.setLayout(null);
        add(btnDeleteDeduccion);

        bt3 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/btnadd.png");
        bt3.setBounds(0,0,30,29);
        btnAddDeduccion.add(bt3);

        bt4 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/btndelete.png");
        bt4.setBounds(0,0,30,29);
        btnDeleteDeduccion.add(bt4);

        add(decTittle);
        add(dropDeducciones);

        panelDecFondo = new JPanel();
        panelDecFondo.setBounds(1052,390,247,234);
        panelDecFondo.setBackground(colorFondoWhite);
        panelDecFondo.setLayout(null);
        add(panelDecFondo);

        panelTablaDed.setBounds(7,7,233,220);
        panelTablaDed.setLayout(null);
        panelTablaDed.setBackground(colorFondoWhite);
        panelDecFondo.add(panelTablaDed);
        panelTablaDed.add(jpTablaDed);

        listDeduccion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listDeduccion.setBackground(colorFondoWhite);
        jpTablaDed.setBounds(0,0,233,220);
        jpTablaDed.setViewportView(listDeduccion);

        imf2 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/fondotablita.png");
        imf2.setBounds(0,0,247,234);
        panelDecFondo.add(imf2);

        btnHome = new JButton();
        btnHome.setBounds(41,643,74,30);
        btnHome.setOpaque(true);
        btnHome.setBackground(colorFondoWhite);
        btnHome.setLayout(null);
        add(btnHome);


        btn5 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/home.png");
        btn5.setBounds(0,0,74,30);
        btnHome.add(btn5);
        
    }

    /* --------------- Botones (Getters) ------------------- */

    public JRadioButton getBtnPagados() {
        return btnPagados;
    }

    public JRadioButton getBtnPendientes() {
        return btnPendientes;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnFacturarEmitir() {
        return btnFacturarEmitir;
    }

    public JButton getBtnPreviz() {
        return btnPreviz;
    }

    public JButton getBtnHome() {
        return btnHome;
    }

    

    /* --------------- Para la liquidacion del ingenio (Getters and Setters) ------------------- */

    public JButton getBtnAddDevengo() {
        return btnAddDevengo;
    }

    public JButton getbtnDeleteDevengo() {
        return btnDeleteDevengo;
    }

    public JButton getBtnAddDeduccion() {
        return btnAddDeduccion;
    }

    public JButton getBtnDeleteDeduccion() {
        return btnDeleteDeduccion;
    }

    public JTextField getFildLiqFicha() {
        return fildLiqFicha;
    }

    public JTextField getFildLiqHacienda() {
        return fildLiqHacienda;
    }

    public JTextField getFildLiqTonelada() {
        return fildLiqTonelada;
    }

    public JTextField getFildLiqFechaCorte() {
        return fildLiqFechaCorte;
    }

    public JComboBox<String> getDropEmpleado() {
        return dropEmpleado;
    }

    public void setDropEmpleado(JComboBox<String> dropEmpleado) {
        this.dropEmpleado = dropEmpleado;
    }

    public JComboBox<String> getDropTipoCana() {
        return dropTipoCana;
    }

    public void setDropTipoCana(JComboBox<String> dropTipoCana) {
        this.dropTipoCana = dropTipoCana;
    }

    public JComboBox<String> getdropDiacorte() {
        return dropDiacorte;
    }

    public void setdropDiacorte(JComboBox<String> dropDiacorte) {
        this.dropDiacorte = dropDiacorte;
    }

    /* --------------- Para las Tablas (Getters and Setters) ------------------- */

    public JScrollPane getJpTabla() {
        return jpTabla;
    }
    
    public JTable getTablaDatos() {
        return tablaDatos;
    }

    public void setTablaDatos(JTable tablaDatos) {
        this.tablaDatos = tablaDatos;
    }

    public JPanel getPanelconTabla() {
        return panelconTabla;
    }
    
     /* --------------- Para las Tablas  DEVENGOS (Getters and Setters) ------------------- */

    public JComboBox<String> getDropDevengos() {
        return dropDevengos;
    }

    public void setDropDevengos(JComboBox<String> dropDevengos) {
        this.dropDevengos = dropDevengos;
    }

    public JPanel getPanelTablaDev() {
        return panelTablaDev;
    }

    public JScrollPane getJpTablaDev() {
        return jpTablaDev;
    }

    public JList<String> getListDevengos() {
        return listDevengos;
    }

    public void setListDevengos(JList<String> listDevengos) {
        this.listDevengos = listDevengos;
    }
    

    /* --------------- Para las Tablas DEDUCCCIONES (Getters and Setters) ------------------- */
    
    public JComboBox<String> getDropDeducciones() {
        return dropDeducciones;
    }

    public void setDropDeducciones(JComboBox<String> dropDeducciones) {
        this.dropDeducciones = dropDeducciones;
    }

    public JPanel getPanelTablaDed() {
        return panelTablaDed;
    }

    public JScrollPane getJpTablaDed() {
        return jpTablaDed;
    }

    public JList<String> getListDeduccion() {
        return listDeduccion;
    }

    public void setListDeduccion(JList<String> listDeduccion) {
        this.listDeduccion = listDeduccion;
    }

    /* --------------- Retorna Calculos realizados en TextArea ------------------- */
    public JTextArea getAreaComprobanteNomina() {
        return areaComprobanteNomina;
    }

    /* --------------- Para agregar ActionListener a los botones ------------------- */
    public void btnAddActionListener(ActionListener actionListener) {
        btnPagados.addActionListener(actionListener);
        btnPendientes.addActionListener(actionListener);
        btnRegistrar.addActionListener(actionListener);
        btnFacturarEmitir.addActionListener(actionListener);
        btnPreviz.addActionListener(actionListener);
        btnHome.addActionListener(actionListener);
        btnAddDeduccion.addActionListener(actionListener);
        btnAddDevengo.addActionListener(actionListener);
        btnDeleteDevengo.addActionListener(actionListener);
        btnDeleteDeduccion.addActionListener(actionListener);
    }
}
