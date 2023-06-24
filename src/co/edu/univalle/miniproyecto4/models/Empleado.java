/**
 Archivo: Empleado.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Modelo de Empleado.
*/

package co.edu.univalle.miniproyecto4.models;

import java.io.Serializable;

public class Empleado implements Serializable {
  private int identificación;
  private int codigo;
  private String apellidos;
  private String nombres;
  private String direccion;
  private int codigoEps;
  private int codigoFpp;
  private int consecutivo = 0;

  public Empleado(int id, String apellidos, String nombres, String direccion, int codigoEps, int codigoFpp) {
    this.identificación = id;
    this.codigo = consecutivo++;
    this.apellidos = apellidos;
    this.nombres = nombres;
    this.direccion = direccion;
    this.codigoEps = codigoEps;
    this.codigoFpp = codigoFpp;
  }

  public Empleado() {
    this.identificación = 0;
    this.codigo = consecutivo++;
    this.apellidos = "";
    this.nombres = "";
    this.direccion = "";
    this.codigoEps = 0;
    this.codigoFpp = 0;
  }

  public void setIdentificacion(int id) {
    this.identificación = id;
  }

  public int getIdentificacion() {
    return identificación;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }
  
  public String getApellidos() {
    return apellidos;
  }

  public void setNombres(String nombres) {
    this.nombres = nombres;
  }
  
  public String getNombres() {
    return nombres;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }
  
  public String getDireccion() {
    return direccion;
  }

  public void setCodigoFpp(int codigoFpp) {
    this.codigoFpp = codigoFpp;
  }

  public int getCodigoFpp() {
    return codigoFpp;
  }

  public void setCodigoEps(int codigoEps) {
    this.codigoEps = codigoEps;
  }

  public int getCodigoEps() {
    return codigoEps;
  }

  @Override
  public String toString() {
    return identificación + ", " + codigo + ", " + apellidos + ", " + nombres + ", " + direccion + ", " + codigoEps + ", " + codigoFpp;
  }
}