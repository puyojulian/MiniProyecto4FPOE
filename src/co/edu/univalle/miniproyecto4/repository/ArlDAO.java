/**
 Archivo: ArlDAO.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Administrar funciones de acceso y modificación para el modelo Arl.
*/

package co.edu.univalle.miniproyecto4.repository;

import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.models.Arl;

public class ArlDAO implements ArlDAOInterface {
  private Map <Integer, Arl> mapaArl = new HashMap<>();

  @Override
  public Map<Integer, Arl> getMapArl() {
    return mapaArl;
  }

  @Override
  public Arl getArl(Integer llave) {
    return mapaArl.get(llave);
  }

  @Override
  public boolean addArl(Arl arl) {
    mapaArl.put(arl.getCodigo(), arl);
    return true;
  }

  @Override
  public boolean updateArl(Integer llave, Arl arl) {
    mapaArl.put(llave, arl);
    return true;
  }

  @Override
  public boolean deleteArl(Integer llave) {
    mapaArl.remove(llave);
    return true;
  }

  @Override
  public void setMapArl(Map<Integer, Arl> mapaArl) {
    this.mapaArl = mapaArl;
  }
}
