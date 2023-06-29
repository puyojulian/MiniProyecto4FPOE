/**
 Archivo: CajaDeCompensacion.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Modelo de CajaDeCompensacion.
*/

package co.edu.univalle.miniproyecto4.models;

import java.io.Serializable;

public class CajaDeCompensacion implements Serializable, ModelInterface {
  private int codigo;
  private String nombre;
  private static int consecutivo = 0;

  public CajaDeCompensacion(String nombre) {
    this.codigo = consecutivo++;
    this.nombre = nombre;
    System.out.println("Caja de Compensacion instanciada.");
  }

  public CajaDeCompensacion() {
    this.codigo = consecutivo++;
    this.nombre = "";
  }

  @Override
  public int getCodigo() {
    return codigo;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  @Override
  public String getNombre() {
    return nombre;
  }

  @Override
  public String toString() {
    return codigo + ", " + nombre;
  }
}
