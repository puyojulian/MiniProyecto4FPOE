/**
 Archivo: Ingenio.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Modelo de Ingenio. Contener los DAOs de los demás modelos.
*/

package co.edu.univalle.miniproyecto4.models;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.repository.ArlDAO;
import co.edu.univalle.miniproyecto4.repository.CajaDeCompensacionDAO;
import co.edu.univalle.miniproyecto4.repository.ConceptoDeDeduccionDAO;
import co.edu.univalle.miniproyecto4.repository.ConceptoDeDevengoDAO;
import co.edu.univalle.miniproyecto4.repository.ConfiguracionDeEmpresaDAO;
import co.edu.univalle.miniproyecto4.repository.EmpleadoDAO;
import co.edu.univalle.miniproyecto4.repository.EpsDAO;
import co.edu.univalle.miniproyecto4.repository.FondoDePensionDAO;
import co.edu.univalle.miniproyecto4.util.PairClassUtil;

public class Ingenio implements Serializable {
  private String nombre;
  private ArlDAO arlDAO;
  private CajaDeCompensacionDAO cajaDeCompensacionDAO;
  private ConceptoDeDeduccionDAO conceptoDeDeduccionDAO;
  private ConceptoDeDevengoDAO conceptoDeDevengoDAO;
  private ConfiguracionDeEmpresaDAO configuracionDeEmpresaDAO;
  private EmpleadoDAO empleadoDAO;
  private EpsDAO epsDAO;
  private FondoDePensionDAO fondoDePensionDAO;
  private Map<PairClassUtil, Float> configDevengos;
  private Map<PairClassUtil, Float> configDeducciones;
  
  public Ingenio() {
    nombre = "Ingenio";
    arlDAO = new ArlDAO();
    cajaDeCompensacionDAO = new CajaDeCompensacionDAO();
    conceptoDeDeduccionDAO = new ConceptoDeDeduccionDAO();
    conceptoDeDevengoDAO = new ConceptoDeDevengoDAO();
    configuracionDeEmpresaDAO = new ConfiguracionDeEmpresaDAO();
    empleadoDAO = new EmpleadoDAO();
    epsDAO = new EpsDAO();
    fondoDePensionDAO = new FondoDePensionDAO();
    configDevengos = new HashMap<>();
    configDeducciones = new HashMap<>();
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }

  public ArlDAO getArlDAO() {
    return arlDAO;
  }

  public CajaDeCompensacionDAO getCajaDeCompensacionDAO() {
    return cajaDeCompensacionDAO;
  }

  public ConceptoDeDeduccionDAO getConceptoDeDeduccionDAO() {
    return conceptoDeDeduccionDAO;
  }

  public ConceptoDeDevengoDAO getConceptoDeDevengoDAO() {
    return conceptoDeDevengoDAO;
  }

  public ConfiguracionDeEmpresaDAO getConfiguracionDeEmpresaDAO() {
    return configuracionDeEmpresaDAO;
  }

  public EmpleadoDAO getEmpleadoDAO() {
    return empleadoDAO;
  }

  public EpsDAO getEpsDAO() {
    return epsDAO;
  }

  public FondoDePensionDAO getFondoDePensionDAO() {
    return fondoDePensionDAO;
  }

  public Map<PairClassUtil, Float> getMapConfigDevengos() {
    return configDevengos;
  }

  public Map<PairClassUtil, Float> getMapConfigDeducciones() {
    return configDeducciones;
  }

  public void addMapConfigDevengos(PairClassUtil key, Float valor) {
    configDevengos.put(key, valor);
  }

  public void addMapConfigDeducciones(PairClassUtil key, Float valor) {
    configDeducciones.put(key, valor);
  }

  public void removeMapConfigDevengos(PairClassUtil key) {
    configDevengos.remove(key);
  }

  public void removeMapConfigDeducciones(PairClassUtil key) {
    configDeducciones.remove(key);
  }
}
