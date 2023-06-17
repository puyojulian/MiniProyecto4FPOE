package co.edu.univalle.miniproyecto4.models;

public class ConceptoDeDevengo {
  private int codigo;
  private String nombre;
  private boolean haceBase;
  private int consecutivo = 0;

  public ConceptoDeDevengo(String nombre) {
    this.codigo = consecutivo++;
    this.nombre = nombre;
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
}