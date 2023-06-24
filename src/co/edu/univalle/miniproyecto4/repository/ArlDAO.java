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
