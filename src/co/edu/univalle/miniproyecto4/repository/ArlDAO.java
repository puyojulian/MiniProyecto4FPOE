package co.edu.univalle.miniproyecto4.repository;

import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.models.Arl;

public class ArlDAO implements ArlDAOInterface {
  private Map <Integer, Arl> mapaArl = new HashMap<>();

  @Override
  public Map<Integer, Arl> getMapArl() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getArls'");
  }

  @Override
  public Arl getArl(Integer llave) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getArl'");
  }

  @Override
  public boolean addArl(Arl arl) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'addArl'");
  }

  @Override
  public boolean updateArl(Integer llave, Arl arl) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateArl'");
  }

  @Override
  public boolean deleteArl(Integer llave) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteArl'");
  }
}
