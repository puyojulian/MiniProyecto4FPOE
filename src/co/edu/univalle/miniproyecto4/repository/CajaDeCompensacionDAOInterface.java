package co.edu.univalle.miniproyecto4.repository;

import java.util.Map;

import co.edu.univalle.miniproyecto4.models.CajaDeCompensacion;

public interface CajaDeCompensacionDAOInterface {
    public Map<Integer, CajaDeCompensacion> getMapCajaDeCompensacion();
    
    public CajaDeCompensacion getCajaDeCompensacion(Integer llave);
    
    public boolean addCajaDeCompensacion(CajaDeCompensacion cajaDeCompensacion);
    
    public boolean updateCajaDeCompensacion(Integer llave, CajaDeCompensacion cajaDeCompensacion);
    
    public boolean deleteCajaDeCompensacion(Integer llave);

    public void setMapCajaDeCompensacion(Map<Integer, CajaDeCompensacion> mapa);
} 