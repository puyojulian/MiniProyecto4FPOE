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

import co.edu.univalle.miniproyecto4.models.ConceptoDeDeduccion;

public interface ConceptoDeDeduccionDAOInterface {
    public Map<Integer, ConceptoDeDeduccion> getMapConceptoDeDeduccion();
    
    public ConceptoDeDeduccion getConceptoDeDeduccion(Integer llave);
    
    public boolean addConceptoDeDeduccion(ConceptoDeDeduccion conceptoDeDeduccion);
    
    public boolean updateConceptoDeDeduccion(Integer llave, ConceptoDeDeduccion conceptoDeDeduccion);
    
    public boolean deleteConceptoDeDeduccion(Integer llave);
} 
