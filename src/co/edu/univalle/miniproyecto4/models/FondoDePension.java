/**
 Archivo: FondoDePension.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Modelo de Fondo De Pensión.
*/

package co.edu.univalle.miniproyecto4.models;

import java.io.Serializable;

import co.edu.univalle.miniproyecto4.controllers.AuxController;

public class FondoDePension implements Serializable, ModelInterface {
  private int codigo;
  private String nombre;
  private static int consecutivo = 1;

  public FondoDePension(String nombre) {
    this.codigo = consecutivo++;
    this.nombre = nombre;
    AuxController.mensajeTemporal("Fondo de pensión creado satisfactoriamente", "Creación exitosa", 1150);
  }

  public FondoDePension() {
    this.codigo = consecutivo++;
    this.nombre = "";
  }

  @Override
  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
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
