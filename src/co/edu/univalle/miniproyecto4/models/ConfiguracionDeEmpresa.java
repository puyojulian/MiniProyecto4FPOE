/**
 Archivo: ConfiguracionDeEmpresa.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Modelo de Configuracion De Empresa.
*/

package co.edu.univalle.miniproyecto4.models;

import java.io.Serializable;

public class ConfiguracionDeEmpresa implements Serializable {
  private String nit;
  private String razonSocial;
  private String nombre;
  private String telefono;
  private String direccion;
  private String representanteLegal;
  private String correoDeContacto;
  private int codigoArl;
  private int codigoCajaDeCompensación;
  private String salarioMínimoLegalVigente;
  private String auxilioDeTransporte;

  public ConfiguracionDeEmpresa(String nit, String nombre, String telefono, String direccion, String representanteLegal, String correoDeContacto, int codigoArl, int codigoCajaDeCompensación, String salarioMínimoLegalVigente, String auxilioDeTransporte) {
    this.nit = nit;
    this.nombre = nombre;
    this.telefono = telefono;
    this.direccion = direccion;
    this.representanteLegal = representanteLegal;
    this.correoDeContacto = correoDeContacto;
    this.codigoArl = codigoArl;
    this.codigoCajaDeCompensación = codigoCajaDeCompensación;
    this.salarioMínimoLegalVigente = salarioMínimoLegalVigente;
    this.auxilioDeTransporte = auxilioDeTransporte;
    System.out.println("Empresa instanciado.");
  }

  public ConfiguracionDeEmpresa() {
    this.nit = "";
    this.razonSocial = "";
    this.nombre = "";
    this.telefono = "";
    this.direccion = "";
    this.representanteLegal = "";
    this.correoDeContacto = "";
    this.codigoArl = 0;
    this.codigoCajaDeCompensación = 0;
    this.salarioMínimoLegalVigente = "";
    this.auxilioDeTransporte = "";
  }

  public void setNit(String nit) {
    this.nit = nit;
  }

  public String getNit() {
    return nit;
  }

  public void setRazonSocial(String razonSocial) {
    this.razonSocial = razonSocial;
  }

  public String getRazonSocial() {
    return razonSocial;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getdireccion() {
    return direccion;
  }

  public void setRepresentanteLegal(String representanteLegal) {
    this.representanteLegal = representanteLegal;
  }

  public String getrepresentanteLegal() {
    return representanteLegal;
  }

  public void setCorreoDeContacto(String correoDeContacto) {
    this.correoDeContacto = correoDeContacto;
  }

  public String getCorreoDeContacto() {
    return correoDeContacto;
  }

  public void setCodigoArl(int codigoArl) {
    this.codigoArl = codigoArl;
  }

  public int getCodigoArl() {
    return codigoArl;
  }

  public void setCodigoCajaDeCompensación(int codigoCajaDeCompensación) {
    this.codigoCajaDeCompensación = codigoCajaDeCompensación;
  }

  public int getCodigoCajaDeCompensación() {
    return codigoCajaDeCompensación;
  }

  public void setSalarioMínimoLegalVigente(String salarioMínimoLegalVigente) {
    this.salarioMínimoLegalVigente = salarioMínimoLegalVigente;
  }

  public String getSalarioMínimoLegalVigente() {
    return salarioMínimoLegalVigente;
  }

  public void setAuxilioDeTransporte(String auxilioDeTransporte) {
    this.auxilioDeTransporte = auxilioDeTransporte;
  }

  public String getAuxilioDeTransporte() {
    return auxilioDeTransporte;
  }

  @Override
  public String toString() {
    return nit + ", " + razonSocial + ", " + nombre + ", " + telefono + ", " + direccion + ", " + representanteLegal + ", " + correoDeContacto + ", " + codigoArl + ", " + codigoCajaDeCompensación + ", " + salarioMínimoLegalVigente + ", " + auxilioDeTransporte;
  }
}
