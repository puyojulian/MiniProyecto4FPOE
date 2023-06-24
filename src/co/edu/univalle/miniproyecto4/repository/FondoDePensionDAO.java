package co.edu.univalle.miniproyecto4.repository;

import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.models.FondoDePension;

public class FondoDePensionDAO implements FondoDePensionDAOInterface{
  private Map <Integer, FondoDePension> mapaFondoDePension = new HashMap<>();

  @Override
  public Map<Integer, FondoDePension> getMapFondoDePension() {
    return mapaFondoDePension;
  }

  @Override
  public FondoDePension getFondoDePension(Integer llave) {
    return mapaFondoDePension.get(llave);
  }

  @Override
  public boolean addFondoDePension(FondoDePension fondoDePension) {
    mapaFondoDePension.put(fondoDePension.getCodigo(), fondoDePension);
    return true;
  }

  @Override
  public boolean updateFondoDePension(Integer llave, FondoDePension fondoDePension) {
    mapaFondoDePension.put(llave, fondoDePension);
    return true;
  }

  @Override
  public boolean deleteFondoDePension(Integer llave) {
    mapaFondoDePension.remove(llave);
    return true;
  }  

  @Override
  public void setMapFondoDePension(Map<Integer, FondoDePension> mapaFondoDePension) {
    this.mapaFondoDePension = mapaFondoDePension;
  }

}
