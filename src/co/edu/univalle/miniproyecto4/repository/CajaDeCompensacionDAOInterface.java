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