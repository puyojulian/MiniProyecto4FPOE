/**
 Archivo: ViweIncio.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 17 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 */

package co.edu.univalle.miniproyecto4.views;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewIncio extends JFrame {
    private JPanel jpnaleIcono, jpanelTexto;
    private Decolib imFondo, imIcon, imTexto;
    private JButton btnInciar;

    public ViewIncio(){
        iniciarComponentes();
        imFondo = new Decolib("/co/edu/univalle/miniproyecto4/img/vistainicio/fondo.png");
        imFondo.setBounds(0,0,1280, 720);
        add(imFondo);
        
    }

    private void iniciarComponentes() {
        //Jframe
        setTitle("Ingenio de caña (Ventana de incio)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        setLayout(null);

        //Panel para el icono
        jpnaleIcono = new JPanel();
        jpnaleIcono.setBounds((int)341.5, (int)9.5 ,593, 501);
        jpnaleIcono.setBackground(new Color(0,50,0));
        jpnaleIcono.setLayout(null);
        add(jpnaleIcono);

        imIcon = new Decolib("/co/edu/univalle/miniproyecto4/img/vistainicio/icono.png");
        imIcon.setBounds(0,0,593,501);
        jpnaleIcono.add(imIcon);

        jpanelTexto = new JPanel();
        jpanelTexto.setBounds((int)364.5,(int)496.5,550,186);
        jpanelTexto.setBackground(new Color(0,0,0,0));
        jpanelTexto.setLayout(null);
        add(jpanelTexto);

        imTexto = new Decolib("/co/edu/univalle/miniproyecto4/img/vistainicio/texto.png");
        imTexto.setBounds(0,0,550,186);
        jpanelTexto.add(imTexto);
    }












    
}
