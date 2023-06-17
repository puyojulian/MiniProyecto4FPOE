package co.edu.univalle.miniproyecto4.models;

public class FondoDePension {
  private int codigo;
  private String nombre;
  private int consecutivo = 0;

  public FondoDePension(String nombre) {
    this.codigo = consecutivo++;
    this.nombre = nombre;
  }

  public FondoDePension() {
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
