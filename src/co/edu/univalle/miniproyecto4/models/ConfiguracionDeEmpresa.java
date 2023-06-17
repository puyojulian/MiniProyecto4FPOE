package co.edu.univalle.miniproyecto4.models;

public class ConfiguracionDeEmpresa {
  private int nit;
  private String razonSocial;
  private String nombre;
  private int telefono;
  private String direccion;
  private String representanteLegal;
  private String correoDeContacto;
  private int codigoArl;
  private int codigoCajaDeCompensación;
  private int salarioMínimoLegalVigente;
  private int auxilioDeTransporte;

  public ConfiguracionDeEmpresa(int nit, String razonSocial, String nombre, int telefono, String direccion, String representanteLegal, String correoDeContacto, int codigoArl, int codigoCajaDeCompensación, int salarioMínimoLegalVigente, int auxilioDeTransporte) {
    this.nit = nit;
    this.razonSocial = razonSocial;
    this.nombre = nombre;
    this.telefono = telefono;
    this.direccion = direccion;
    this.representanteLegal = representanteLegal;
    this.correoDeContacto = correoDeContacto;
    this.codigoArl = codigoArl;
    this.codigoCajaDeCompensación = codigoCajaDeCompensación;
    this.salarioMínimoLegalVigente = salarioMínimoLegalVigente;
    this.auxilioDeTransporte = auxilioDeTransporte;
  }

  public ConfiguracionDeEmpresa() {
    this.nit = 0;
    this.razonSocial = "";
    this.nombre = "";
    this.telefono = 0;
    this.direccion = "";
    this.representanteLegal = "";
    this.correoDeContacto = "";
    this.codigoArl = 0;
    this.codigoCajaDeCompensación = 0;
    this.salarioMínimoLegalVigente = 0;
    this.auxilioDeTransporte = 0;
  }

  public void setNit(int nit) {
    this.nit = nit;
  }

  public int getNit() {
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

  public void setTelefono(int telefono) {
    this.telefono = telefono;
  }

  public int getTelefono() {
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

  public void setSalarioMínimoLegalVigente(int salarioMínimoLegalVigente) {
    this.salarioMínimoLegalVigente = salarioMínimoLegalVigente;
  }

  public int getSalarioMínimoLegalVigente() {
    return salarioMínimoLegalVigente;
  }

  public void setAuxilioDeTransporte(int auxilioDeTransporte) {
    this.auxilioDeTransporte = auxilioDeTransporte;
  }

  public int getAuxilioDeTransporte() {
    return auxilioDeTransporte;
  }
}
