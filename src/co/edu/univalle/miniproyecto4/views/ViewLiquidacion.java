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

public class ViewLiquidacion extends JFrame {

    private JLabel lblEmpleados,lblFicha, lblHacienda, lblTonelada, lblFechaCorte, lblTipoCaña, lblDiacorte;
    private JTextField fildLiqFicha, fildLiqHacienda, fildLiqTonelada, fildLiqFechaCorte;

    private String[] empleados = {"Seleccionar"};
    private JComboBox<String> dropEmpleado = new JComboBox<>(empleados);

    private String[] tipoCana = {"Seleccionar"};
    private JComboBox<String> dropTipoCana = new JComboBox<>(tipoCana);

    private String[] diaCorte = {"Seleccionar"};
    private JComboBox<String> dropDiacorte = new JComboBox<>(diaCorte);

    private JPanel panelTabla, panelTxtarea;
    private JLabel lblPagados, lblPendientes;
    private JRadioButton btnPagados, btnPendientes;
    private JButton btnRegistrar, btnFacturarEmitir, btnImpresora;
    private JLabel lblPrevizNonima;
    private JTextArea areaComprobanteNomina;

    private JPanel panelconTabla = new JPanel();
    private JScrollPane jpTabla = new JScrollPane();
    private JTable tablaDatos = new JTable();

    private Decolib im1, im2, im3, ic1;
    

    public ViewLiquidacion(){
        inciarComponentes();
        im1 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/fondo.png");
        im1.setBounds(0,0,1080, 700);
        add(im1);
    }

    private void inciarComponentes() {
        setTitle("Ingenio de caña (Ventana Liquidacion)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1094, 735);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setVisible(false);


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

        fildLiqFechaCorte = new JTextField("00/00/0000");
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

        lblPrevizNonima = new JLabel("Previzualizacion comprobante de nomina");
        lblPrevizNonima.setBounds(479,299,319,29);
        lblPrevizNonima.setFont(nuevaTipografia);
        lblPrevizNonima.setForeground(colorFuente);
        add(lblPrevizNonima);

        btnImpresora = new JButton();
        btnImpresora.setBounds(961,294,74,30);
        btnImpresora.setOpaque(true);
        btnImpresora.setBorder(BorderFactory.createLineBorder(colorFuente));
        btnImpresora.setContentAreaFilled(false);
        btnImpresora.setLayout(null);
        add(btnImpresora);

        ic1 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/btnimp.png");
        ic1.setBounds(0,0,74,30);
        btnImpresora.add(ic1);


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
        panelTxtarea.add(areaComprobanteNomina);

        im3 = new Decolib("/co/edu/univalle/miniproyecto4/img/vistaLiquidacion/fondoText.png");
        im3.setBounds(0,0,561,305);
        panelTxtarea.add(im3);

        
    }

    /* --------------- Para la liquidacion del ingenio (Getters and Setters) ------------------- */

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
    
}