/**
 Archivo: Arl.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Modelo de Arl.
*/

package co.edu.univalle.miniproyecto4.models;

import java.io.Serializable;

import co.edu.univalle.miniproyecto4.controllers.AuxController;

public class Arl implements Serializable, ModelInterface {
  private int codigo;
  private String nombre;
  private static int consecutivo = 1;

  public Arl(String nombre) {
    this.codigo = consecutivo++;
    this.nombre = nombre;
    AuxController.mensajeTemporal("ARL creada satisfactoriamente", "Creación exitosa", 1150);
  }

  public Arl() {
    this.codigo = consecutivo++;
    this.nombre = "";
  }

  public int getCodigo() {
    return codigo;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }

  public String getNombre() {
    return nombre;
  }

  @Override
  public String toString() {
    return codigo + ", " + nombre;
  }
}
