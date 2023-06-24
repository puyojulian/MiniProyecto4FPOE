package co.edu.univalle.miniproyecto4.repository;

import java.util.Map;

import co.edu.univalle.miniproyecto4.models.Eps;

public interface EpsDAOInterface {
    public Map<Integer, Eps> getMapEps();
    
    public Eps getEps(Integer llave);
    
    public boolean addEps(Eps eps);
    
    public boolean updateEps(Integer llave, Eps eps);
    
    public boolean deleteEps(Integer llave);

    public void setMapEps(Map<Integer, Eps> mapa);
}