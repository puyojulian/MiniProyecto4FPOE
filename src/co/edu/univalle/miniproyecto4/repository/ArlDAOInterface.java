package co.edu.univalle.miniproyecto4.repository;

import java.util.Map;

import co.edu.univalle.miniproyecto4.models.Arl;

public interface ArlDAOInterface {
    public Map<Integer, Arl> getMapArl();
    
    public Arl getArl(Integer llave);
    
    public boolean addArl(Arl arl);
    
    public boolean updateArl(Integer llave, Arl arl);
    
    public boolean deleteArl(Integer llave);
} 