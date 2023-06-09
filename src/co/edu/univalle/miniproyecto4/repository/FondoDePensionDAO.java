/**
 Archivo: FondoDePensionDAO.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Administrar funciones de acceso y modificación para el modelo FondoDePension.
*/

package co.edu.univalle.miniproyecto4.repository;

import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.controllers.AuxController;
import co.edu.univalle.miniproyecto4.models.FondoDePension;

public class FondoDePensionDAO implements FondoDePensionDAOInterface{
  private Map <Integer, FondoDePension> mapaFondoDePension = new HashMap<>();

  @Override
  public Map<Integer, FondoDePension> getMapFondoDePension() {
    return mapaFondoDePension;
  }

  @Override
  public FondoDePension getFondoDePension(Integer llave) {
    return mapaFondoDePension.get(llave);
  }

  @Override
  public boolean addFondoDePension(FondoDePension fondoDePension) {
    if(AuxController.isCodigoUnico(fondoDePension.getCodigo(), mapaFondoDePension)) {
      mapaFondoDePension.put(fondoDePension.getCodigo(), fondoDePension);
    }
    else {
      fondoDePension.setCodigo(mapaFondoDePension.size() + 1);
      mapaFondoDePension.put(fondoDePension.getCodigo(), fondoDePension);
    }

    // mapaFondoDePension.put(fondoDePension.getCodigo(), fondoDePension);
    return true;
  }

  @Override
  public boolean updateFondoDePension(Integer llave, FondoDePension fondoDePension) {
    mapaFondoDePension.put(llave, fondoDePension);
    return true;
  }

  @Override
  public boolean deleteFondoDePension(Integer llave) {
    mapaFondoDePension.remove(llave);
    return true;
  }  

  @Override
  public void setMapFondoDePension(Map<Integer, FondoDePension> mapaFondoDePension) {
    this.mapaFondoDePension = mapaFondoDePension;
  }

}
