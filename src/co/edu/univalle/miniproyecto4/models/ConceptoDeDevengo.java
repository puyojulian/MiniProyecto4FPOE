/**
 Archivo: ConceptoDeDevengo.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Modelo de Concepto de Devengo.
*/

package co.edu.univalle.miniproyecto4.models;

import java.io.Serializable;

public class ConceptoDeDevengo implements Serializable {
  private int codigo;
  private String nombre;
  private boolean haceBase;
  private static int consecutivo = 0;

  public ConceptoDeDevengo(String nombre) {
    this.codigo = consecutivo++;
    this.nombre = nombre;
    System.out.println("Devengo instanciado.");
  }

  public ConceptoDeDevengo() {
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

  public void setHaceBase(boolean haceBase) {
    this.haceBase = haceBase;
  }

  public boolean isHaceBase() {
    return haceBase;
  }

  @Override
  public String toString() {
    return codigo + ", " + nombre + ", " + haceBase;
  }
}