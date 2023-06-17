package co.edu.univalle.miniproyecto4.models;

public class Arl {
  private int codigo;
  private String nombre;
  private int consecutivo = 0;

  public Arl(String nombre) {
    this.codigo = consecutivo++;
    this.nombre = nombre;
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

  public String getNombre() {
    return nombre;
  }

  @Override
  public String toString() {
    return codigo + ", " + nombre;
  }
}
