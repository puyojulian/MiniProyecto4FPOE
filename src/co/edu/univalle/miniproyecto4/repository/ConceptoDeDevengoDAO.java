package co.edu.univalle.miniproyecto4.repository;

import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.models.ConceptoDeDevengo;

public class ConceptoDeDevengoDAO implements ConceptoDeDevengoDAOInterface{
  private Map <Integer, ConceptoDeDevengo> mapaConceptoDeDevengo = new HashMap<>();

  @Override
  public Map<Integer, ConceptoDeDevengo> getMapConceptoDeDevengo() {
    return mapaConceptoDeDevengo;
  }

  @Override
  public ConceptoDeDevengo getConceptoDeDevengo(Integer llave) {
    return mapaConceptoDeDevengo.get(llave);
  }

  @Override
  public boolean addConceptoDeDevengo(ConceptoDeDevengo conceptoDeDevengo) {
    mapaConceptoDeDevengo.put(conceptoDeDevengo.getCodigo(), conceptoDeDevengo);
    return true;
  }

  @Override
  public boolean updateConceptoDeDevengo(Integer llave, ConceptoDeDevengo conceptoDeDevengo) {
    mapaConceptoDeDevengo.put(llave, conceptoDeDevengo);
    return true;
  }

  @Override
  public boolean deleteConceptoDeDevengo(Integer llave) {
    mapaConceptoDeDevengo.remove(llave);
    return true;
  }
  
}