package co.edu.univalle.miniproyecto4.repository;

import java.util.Map;

import co.edu.univalle.miniproyecto4.models.FondoDePension;

public interface FondoDePensionDAOInterface {
    public Map<Integer, FondoDePension> getFondoDePensions();
    
    public FondoDePension getFondoDePension(Integer llave);
    
    public boolean addFondoDePension(FondoDePension fondoDePension);
    
    public boolean updateFondoDePension(Integer llave, FondoDePension fondoDePension);
    
    public boolean deleteFondoDePension(Integer llave);
}
