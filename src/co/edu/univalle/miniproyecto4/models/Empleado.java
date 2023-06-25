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
import java.time.LocalDate;

public class Empleado implements Serializable, ModelInterface {
  private int identificación;
  private int codigo;
  private String apellidos;
  private String nombres;
  private String direccion;
  private int codigoEps;
  private int codigoFpp;
  private int consecutivo = 0;
  private LocalDate fechaDeNacimiento;
  private LocalDate fechaDeIngreso;
  private LocalDate fechaDeRetiro;
  private int tipoDeTrabajador;
  private String numeroDeCuenta;


  public Empleado(int id, String apellidos, String nombres, String direccion, int codigoEps, int codigoFpp, LocalDate fechaDeNacimiento, LocalDate fechaDeIngreso, LocalDate fechaDeRetiro, int tipoDeTrabajador, String numeroDeCuenta) {
    this.identificación = id;
    this.codigo = consecutivo++;
    this.apellidos = apellidos;
    this.nombres = nombres;
    this.direccion = direccion;
    this.codigoEps = codigoEps;
    this.codigoFpp = codigoFpp;
    System.out.println("Empleado instanciado.");
    this.fechaDeNacimiento = fechaDeNacimiento;
    this.fechaDeIngreso = fechaDeIngreso;
    this.fechaDeRetiro = fechaDeRetiro;
    this.tipoDeTrabajador = tipoDeTrabajador;
    this.numeroDeCuenta = numeroDeCuenta;
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
  @Override
  public int getCodigo() {
    return codigo;
  }

  public void setApellidos(String apellidos) {
    this.apellidos = apellidos;
  }
  
  public String getApellido() {
    return apellidos;
  }

  public void setNombre(String nombres) {
    this.nombres = nombres;
  }
  @Override
  public String getNombre() {
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

  public LocalDate getFechaDeNacimiento() {
    return fechaDeNacimiento;
  }

  public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
    this.fechaDeNacimiento = fechaDeNacimiento;
  }

  public LocalDate getFechaDeIngreso() {
    return fechaDeIngreso;
  }

  public void setFechaDeIngreso(LocalDate fechaDeIngreso) {
    this.fechaDeIngreso = fechaDeIngreso;
  }

  public LocalDate getFechaDeRetiro() {
    return fechaDeRetiro;
  }

  public void setFechaDeRetiro(LocalDate fechaDeRetiro) {
    this.fechaDeRetiro = fechaDeRetiro;
  }

  public int getTipoDeTrabajador() {
    return tipoDeTrabajador;
  }

  public void setTipoDeTrabajador(int tipoDeTrabajador) {
    this.tipoDeTrabajador = tipoDeTrabajador;
  }

  public String getNumeroDeCuenta() {
    return numeroDeCuenta;
  }

  public void setNumeroDeCuenta(String numeroDeCuenta) {
    this.numeroDeCuenta = numeroDeCuenta;
  }

  @Override
  public String toString() {
    return identificación + ", " + codigo + ", " + apellidos + ", " + nombres + ", " + direccion + ", " + codigoEps + ", " + codigoFpp + ", " + fechaDeNacimiento + ", " + fechaDeIngreso + ", " + fechaDeRetiro + ", " + tipoDeTrabajador + ", " + numeroDeCuenta;
  }
}