package co.edu.univalle.miniproyecto4.models;

import co.edu.univalle.miniproyecto4.repository.ArlDAO;
import co.edu.univalle.miniproyecto4.repository.CajaDeCompensacionDAO;
import co.edu.univalle.miniproyecto4.repository.ConceptoDeDeduccionDAO;
import co.edu.univalle.miniproyecto4.repository.ConceptoDeDevengoDAO;
import co.edu.univalle.miniproyecto4.repository.ConfiguracionDeEmpresaDAO;
import co.edu.univalle.miniproyecto4.repository.EmpleadoDAO;
import co.edu.univalle.miniproyecto4.repository.EpsDAO;
import co.edu.univalle.miniproyecto4.repository.FondoDePensionDAO;

public class Ingenio {
  private String nombre;
  private ArlDAO arlDAO;
  private CajaDeCompensacionDAO cajaDeCompensacionDAO;
  private ConceptoDeDeduccionDAO conceptoDeDeduccionDAO;
  private ConceptoDeDevengoDAO conceptoDeDevengoDAO;
  private ConfiguracionDeEmpresaDAO configuracionDeEmpresaDAO;
  private EmpleadoDAO empleadoDAO;
  private EpsDAO epsDAO;
  private FondoDePensionDAO fondoDePensionDAO;
  
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
}
