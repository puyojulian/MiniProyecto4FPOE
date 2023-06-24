/**
 Archivo: Eps.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Modelo de EPS.
*/

package co.edu.univalle.miniproyecto4.models;

import java.io.Serializable;

public class Eps implements Serializable {
  private int codigo;
  private String nombre;
  private int consecutivo = 0;

  public Eps(String nombre) {
    this.codigo = consecutivo++;
    this.nombre = nombre;
  }

  public Eps() {
    this.codigo = consecutivo++;
    this.nombre = "";
  }

  public int getCodigo() {
    return codigo;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  @Override
  public String toString() {
    return codigo + ", " + nombre;
  }
}
