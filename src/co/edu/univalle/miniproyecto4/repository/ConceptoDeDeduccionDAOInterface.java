package co.edu.univalle.miniproyecto4.repository;

import java.util.Map;

import co.edu.univalle.miniproyecto4.models.ConceptoDeDeduccion;

public interface ConceptoDeDeduccionDAOInterface {
    public Map<Integer, ConceptoDeDeduccion> getMapConceptoDeDeduccion();
    
    public ConceptoDeDeduccion getConceptoDeDeduccion(Integer llave);
    
    public boolean addConceptoDeDeduccion(ConceptoDeDeduccion conceptoDeDeduccion);
    
    public boolean updateConceptoDeDeduccion(Integer llave, ConceptoDeDeduccion conceptoDeDeduccion);
    
    public boolean deleteConceptoDeDeduccion(Integer llave);
} 
