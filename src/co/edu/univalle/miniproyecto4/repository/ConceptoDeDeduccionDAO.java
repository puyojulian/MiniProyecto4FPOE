/**
 Archivo: ConceptoDeDeduccionDAO.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Administrar funciones de acceso y modificación para el modelo ConceptoDeDeduccion.
*/

package co.edu.univalle.miniproyecto4.repository;

import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.models.ConceptoDeDeduccion;

public class ConceptoDeDeduccionDAO implements ConceptoDeDeduccionDAOInterface{
  private Map <Integer, ConceptoDeDeduccion> mapaConceptoDeDeduccion = new HashMap<>();

  @Override
  public Map<Integer, ConceptoDeDeduccion> getMapConceptoDeDeduccion() {
    return mapaConceptoDeDeduccion;
  }

  @Override
  public ConceptoDeDeduccion getConceptoDeDeduccion(Integer llave) {
    return mapaConceptoDeDeduccion.get(llave);
  }

  @Override
  public boolean addConceptoDeDeduccion(ConceptoDeDeduccion conceptoDeDeduccion) {
    mapaConceptoDeDeduccion.put(conceptoDeDeduccion.getCodigo(), conceptoDeDeduccion);
    return true;
  }

  @Override
  public boolean updateConceptoDeDeduccion(Integer llave, ConceptoDeDeduccion conceptoDeDeduccion) {
    mapaConceptoDeDeduccion.put(llave, conceptoDeDeduccion);
    return true;
  }

  @Override
  public boolean deleteConceptoDeDeduccion(Integer llave) {
    mapaConceptoDeDeduccion.remove(llave);
    return true;
  }

  @Override
  public void setMapConceptoDeDeduccion(Map<Integer, ConceptoDeDeduccion> mapaConceptoDeDeduccion) {
    this.mapaConceptoDeDeduccion = mapaConceptoDeDeduccion;
  }
  
}
