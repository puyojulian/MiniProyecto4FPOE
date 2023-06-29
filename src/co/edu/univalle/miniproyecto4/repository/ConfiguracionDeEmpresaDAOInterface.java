/**
 Archivo: ConfiguracionDeEmpresaDAOInterface.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Interfaz de ConfiguracionDeEmpresaDAO.
*/

package co.edu.univalle.miniproyecto4.repository;

import java.util.Map;

import co.edu.univalle.miniproyecto4.models.ConfiguracionDeEmpresa;

public interface ConfiguracionDeEmpresaDAOInterface {
    public Map<String, ConfiguracionDeEmpresa> getMapConfiguracionDeEmpresa();
    
    public ConfiguracionDeEmpresa getConfiguracionDeEmpresa(String llave);
    
    public boolean addConfiguracionDeEmpresa(ConfiguracionDeEmpresa configuracionDeEmpresa);
    
    public boolean updateConfiguracionDeEmpresa(String llave, ConfiguracionDeEmpresa configuracionDeEmpresa);
    
    public boolean deleteConfiguracionDeEmpresa(String llave);

    public void setMapConfiguracionDeEmpresa(Map<String, ConfiguracionDeEmpresa> mapa);
}
