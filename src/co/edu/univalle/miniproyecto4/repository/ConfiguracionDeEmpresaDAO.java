/**
 Archivo: ConfiguracionDeEmpresaDAO.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Administrar funciones de acceso y modificación para el modelo ConfiguracionDeEmpresa.
*/

package co.edu.univalle.miniproyecto4.repository;

import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.models.ConfiguracionDeEmpresa;

public class ConfiguracionDeEmpresaDAO implements ConfiguracionDeEmpresaDAOInterface{
  private Map <String, ConfiguracionDeEmpresa> mapaConfiguracionDeEmpresa = new HashMap<>();

  @Override
  public Map<String, ConfiguracionDeEmpresa> getMapConfiguracionDeEmpresa() {
    return mapaConfiguracionDeEmpresa;
  }

  @Override
  public ConfiguracionDeEmpresa getConfiguracionDeEmpresa(String llave) {
    return mapaConfiguracionDeEmpresa.get(llave);
  }

  @Override
  public boolean addConfiguracionDeEmpresa(ConfiguracionDeEmpresa configuracionDeEmpresa) {
    mapaConfiguracionDeEmpresa.put(configuracionDeEmpresa.getNit(), configuracionDeEmpresa);
    return true;
  }

  @Override
  public boolean updateConfiguracionDeEmpresa(String llave, ConfiguracionDeEmpresa configuracionDeEmpresa) {
    mapaConfiguracionDeEmpresa.put(llave, configuracionDeEmpresa);
    return true;
  }

  @Override
  public boolean deleteConfiguracionDeEmpresa(String llave) {
    mapaConfiguracionDeEmpresa.remove(llave);
    return true;
  }
  
  @Override
  public void setMapConfiguracionDeEmpresa(Map<String, ConfiguracionDeEmpresa> mapaConfiguracionDeEmpresa) {
    this.mapaConfiguracionDeEmpresa = mapaConfiguracionDeEmpresa;
  }

}
