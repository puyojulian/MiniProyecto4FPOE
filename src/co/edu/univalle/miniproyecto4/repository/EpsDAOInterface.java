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

import co.edu.univalle.miniproyecto4.models.Eps;

public interface EpsDAOInterface {
    public Map<Integer, Eps> getMapEps();
    
    public Eps getEps(Integer llave);
    
    public boolean addEps(Eps eps);
    
    public boolean updateEps(Integer llave, Eps eps);
    
    public boolean deleteEps(Integer llave);
}