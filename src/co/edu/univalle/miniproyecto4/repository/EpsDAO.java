/**
 Archivo: EpsDAO.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Administrar funciones de acceso y modificación para el modelo Eps.
*/

package co.edu.univalle.miniproyecto4.repository;

import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.controllers.AuxController;
import co.edu.univalle.miniproyecto4.models.Eps;

public class EpsDAO implements EpsDAOInterface{
  private Map <Integer, Eps> mapaEps = new HashMap<>();

  @Override
  public Map<Integer, Eps> getMapEps() {
    return mapaEps;
  }

  @Override
  public Eps getEps(Integer llave) {
    return mapaEps.get(llave);
  }

  @Override
  public boolean addEps(Eps eps) {
    if(AuxController.isCodigoUnico(eps.getCodigo(), mapaEps)) {
      mapaEps.put(eps.getCodigo(), eps);
    }
    else {
      eps.setCodigo(mapaEps.size() + 1);
      mapaEps.put(eps.getCodigo(), eps);
    }

    // mapaEps.put(eps.getCodigo(), eps);
    return true;
  }

  @Override
  public boolean updateEps(Integer llave, Eps eps) {
    mapaEps.put(llave, eps);
    return true;
  }

  @Override
  public boolean deleteEps(Integer llave) {
    mapaEps.remove(llave);
    return true;
  }

  @Override
  public void setMapEps(Map<Integer, Eps> mapaEps) {
    this.mapaEps = mapaEps;
  }
  
}
