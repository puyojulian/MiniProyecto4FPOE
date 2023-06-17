/*
 Archivo: decolib.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 17 de junio de 2023

 Autores:
  @author Manuel Felipe Cardoso Forero (2027288)
  @author Juan David Rodriguez Rubio (2025435)
  @
  @
  @
 

 Intencion: Es una especie de libreria que permite insetar imagenes a las vistas del programa de una manera
 más sencilla.
 */


 package co.edu.univalle.miniproyecto4.views;
 import java.awt.Dimension;
 import java.awt.Graphics;
 import javax.swing.ImageIcon;
 import javax.swing.JPanel;
 
 class Decolib extends JPanel{
     ImageIcon imagen;
     String nombre;
     
     public Decolib(String nombre){
         this.nombre = nombre;
     }
     
     @Override
     public void paint(Graphics g){
         Dimension tamano = getSize();
         imagen = new ImageIcon(getClass().getResource(nombre));
         g.drawImage(imagen.getImage(), 0, 0, null);
         setOpaque(false);
         super.paint(g);
     }
 }