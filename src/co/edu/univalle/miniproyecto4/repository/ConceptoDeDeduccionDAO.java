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
  
}
