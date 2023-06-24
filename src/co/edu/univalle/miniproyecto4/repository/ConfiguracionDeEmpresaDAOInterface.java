package co.edu.univalle.miniproyecto4.repository;

import java.util.Map;

import co.edu.univalle.miniproyecto4.models.ConfiguracionDeEmpresa;

public interface ConfiguracionDeEmpresaDAOInterface {
    public Map<Integer, ConfiguracionDeEmpresa> getMapConfiguracionDeEmpresa();
    
    public ConfiguracionDeEmpresa getConfiguracionDeEmpresa(Integer llave);
    
    public boolean addConfiguracionDeEmpresa(ConfiguracionDeEmpresa configuracionDeEmpresa);
    
    public boolean updateConfiguracionDeEmpresa(Integer llave, ConfiguracionDeEmpresa configuracionDeEmpresa);
    
    public boolean deleteConfiguracionDeEmpresa(Integer llave);

    public void setMapConfiguracionDeEmpresa(Map<Integer, ConfiguracionDeEmpresa> mapa);
}
