package co.edu.univalle.miniproyecto4.repository;

import java.util.Map;

import co.edu.univalle.miniproyecto4.models.ConceptoDeDevengo;

public interface ConceptoDeDevengoDAOInterface {
    public Map<Integer, ConceptoDeDevengo> getMapConceptoDeDevengo();
    
    public ConceptoDeDevengo getConceptoDeDevengo(Integer llave);
    
    public boolean addConceptoDeDevengo(ConceptoDeDevengo conceptoDeDevengo);
    
    public boolean updateConceptoDeDevengo(Integer llave, ConceptoDeDevengo conceptoDeDevengo);
    
    public boolean deleteConceptoDeDevengo(Integer llave);

    public void setMapConceptoDeDevengo(Map<Integer, ConceptoDeDevengo> mapa);
} 
