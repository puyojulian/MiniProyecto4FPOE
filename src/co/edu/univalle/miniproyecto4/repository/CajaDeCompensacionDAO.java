package co.edu.univalle.miniproyecto4.repository;

import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.models.Arl;
import co.edu.univalle.miniproyecto4.models.CajaDeCompensacion;

public class CajaDeCompensacionDAO implements CajaDeCompensacionDAOInterface{
  private Map <Integer, CajaDeCompensacion> mapaCajaDeCompensacion = new HashMap<>();

  @Override
  public Map<Integer, CajaDeCompensacion> getMapCajaDeCompensacion() {
    return mapaCajaDeCompensacion;
  }

  @Override
  public CajaDeCompensacion getCajaDeCompensacion(Integer llave) {
    return mapaCajaDeCompensacion.get(llave);
  }

  @Override
  public boolean addCajaDeCompensacion(CajaDeCompensacion cajaDeCompensacion) {
    mapaCajaDeCompensacion.put(cajaDeCompensacion.getCodigo(), cajaDeCompensacion);
    return true;
  }

  @Override
  public boolean updateCajaDeCompensacion(Integer llave, CajaDeCompensacion cajaDeCompensacion) {
    mapaCajaDeCompensacion.put(llave, cajaDeCompensacion);
    return true;
  }

  @Override
  public boolean deleteCajaDeCompensacion(Integer llave) {
    mapaCajaDeCompensacion.remove(llave);
    return true;
  }

  @Override
  public void setMapCajaDeCompensacion(Map<Integer, CajaDeCompensacion> mapaCajaDeCompensacion) {
    this.mapaCajaDeCompensacion = mapaCajaDeCompensacion;
  }
}
