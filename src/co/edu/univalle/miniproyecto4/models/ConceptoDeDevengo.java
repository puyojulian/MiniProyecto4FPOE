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

import co.edu.univalle.miniproyecto4.controllers.AuxController;

public class ConceptoDeDevengo implements Serializable, ModelInterface {
  private int codigo;
  private String nombre;
  private boolean haceBase;
  private static int consecutivo = 1;

  public ConceptoDeDevengo(String nombre, boolean haceBase) {
    this.codigo = consecutivo++;
    this.nombre = nombre;
    this.haceBase = haceBase;
    AuxController.mensajeTemporal("Devengo creado satisfactoriamente", "Creación exitosa", 1150);
  }

  public ConceptoDeDevengo() {
    this.codigo = consecutivo++;
    this.nombre = "";
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
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