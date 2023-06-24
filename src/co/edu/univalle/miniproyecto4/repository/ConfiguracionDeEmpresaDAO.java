package co.edu.univalle.miniproyecto4.repository;

import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.models.ConfiguracionDeEmpresa;

public class ConfiguracionDeEmpresaDAO implements ConfiguracionDeEmpresaDAOInterface{
  private Map <Integer, ConfiguracionDeEmpresa> mapaConfiguracionDeEmpresa = new HashMap<>();

  @Override
  public Map<Integer, ConfiguracionDeEmpresa> getMapConfiguracionDeEmpresa() {
    return mapaConfiguracionDeEmpresa;
  }

  @Override
  public ConfiguracionDeEmpresa getConfiguracionDeEmpresa(Integer llave) {
    return mapaConfiguracionDeEmpresa.get(llave);
  }

  @Override
  public boolean addConfiguracionDeEmpresa(ConfiguracionDeEmpresa configuracionDeEmpresa) {
    mapaConfiguracionDeEmpresa.put(configuracionDeEmpresa.getNit(), configuracionDeEmpresa);
    return true;
  }

  @Override
  public boolean updateConfiguracionDeEmpresa(Integer llave, ConfiguracionDeEmpresa configuracionDeEmpresa) {
    mapaConfiguracionDeEmpresa.put(llave, configuracionDeEmpresa);
    return true;
  }

  @Override
  public boolean deleteConfiguracionDeEmpresa(Integer llave) {
    mapaConfiguracionDeEmpresa.remove(llave);
    return true;
  }
  
  @Override
  public void setMapConfiguracionDeEmpresa(Map<Integer, ConfiguracionDeEmpresa> mapaConfiguracionDeEmpresa) {
    this.mapaConfiguracionDeEmpresa = mapaConfiguracionDeEmpresa;
  }

}
