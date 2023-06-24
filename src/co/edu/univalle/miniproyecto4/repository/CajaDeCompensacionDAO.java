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

import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.models.CajaDeCompensacion;

public class CajaDeCompensacionDAO implements CajaDeCompensacionDAOInterface{
  private Map <Integer, CajaDeCompensacion> mapaCajaDeCompensacion = new HashMap<>();

  @Override
  public Map<Integer, CajaDeCompensacion> getMapCajaDeCompensacion() {
    return mapaCajaDeCompensacion;
  }

  @Override
  public CajaDeCompensacion getCajaDeCompensacion(Integer llave) {
    return mapaCajaDeCompensacion.get(llave);
  }

  @Override
  public boolean addCajaDeCompensacion(CajaDeCompensacion cajaDeCompensacion) {
    mapaCajaDeCompensacion.put(cajaDeCompensacion.getCodigo(), cajaDeCompensacion);
    return true;
  }

  @Override
  public boolean updateCajaDeCompensacion(Integer llave, CajaDeCompensacion cajaDeCompensacion) {
    mapaCajaDeCompensacion.put(llave, cajaDeCompensacion);
    return true;
  }

  @Override
  public boolean deleteCajaDeCompensacion(Integer llave) {
    mapaCajaDeCompensacion.remove(llave);
    return true;
  }
}
