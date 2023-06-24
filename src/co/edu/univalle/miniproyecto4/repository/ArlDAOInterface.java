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

import co.edu.univalle.miniproyecto4.models.Arl;

public interface ArlDAOInterface {
    public Map<Integer, Arl> getMapArl();
    
    public Arl getArl(Integer llave);
    
    public boolean addArl(Arl arl);
    
    public boolean updateArl(Integer llave, Arl arl);
    
    public boolean deleteArl(Integer llave);

    public void setMapArl(Map<Integer, Arl> mapa);
} 