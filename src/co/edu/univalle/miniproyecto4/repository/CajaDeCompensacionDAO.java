package co.edu.univalle.miniproyecto4.repository;

import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.models.CajaDeCompensacion;

public class CajaDeCompensacionDAO implements CajaDeCompensacionDAOInterface{
  private Map <Integer, CajaDeCompensacion> mapCajaDeCompensacion = new HashMap<>();

  @Override
  public Map<Integer, CajaDeCompensacion> getMapCajaDeCompensacion() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCajaDeCompensacions'");
  }

  @Override
  public CajaDeCompensacion getCajaDeCompensacion(Integer llave) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getCajaDeCompensacion'");
  }

  @Override
  public boolean addCajaDeCompensacion(CajaDeCompensacion cajaDeCompensacion) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addCajaDeCompensacion'");
  }

  @Override
  public boolean updateCajaDeCompensacion(Integer llave, CajaDeCompensacion cajaDeCompensacion) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateCajaDeCompensacion'");
  }

  @Override
  public boolean deleteCajaDeCompensacion(Integer llave) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteCajaDeCompensacion'");
  }
  
}
