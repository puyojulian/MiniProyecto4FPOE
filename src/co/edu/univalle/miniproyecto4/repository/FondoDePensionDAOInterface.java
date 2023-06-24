/**
 Archivo: FondoDePensionDAOInterface.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Interfaz de FondoDePensionDAO.
*/

package co.edu.univalle.miniproyecto4.repository;

import java.util.Map;

import co.edu.univalle.miniproyecto4.models.FondoDePension;

public interface FondoDePensionDAOInterface {
    public Map<Integer, FondoDePension> getMapFondoDePension();
    
    public FondoDePension getFondoDePension(Integer llave);
    
    public boolean addFondoDePension(FondoDePension fondoDePension);
    
    public boolean updateFondoDePension(Integer llave, FondoDePension fondoDePension);
    
    public boolean deleteFondoDePension(Integer llave);

    public void setMapFondoDePension(Map<Integer, FondoDePension> mapa);
}
