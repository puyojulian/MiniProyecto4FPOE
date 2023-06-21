package co.edu.univalle.miniproyecto4.repository;

import java.util.HashMap;
import java.util.Map;

import co.edu.univalle.miniproyecto4.models.Empleado;

public class EmpleadoDAO implements EmpleadoDAOInterface{
  private Map <Integer, Empleado> mapaEmpleado = new HashMap<>();

  @Override
  public Map<Integer, Empleado> getMapEmpleado() {
    return mapaEmpleado;
  }

  @Override
  public Empleado getEmpleado(Integer llave) {
    return mapaEmpleado.get(llave);
  }

  @Override
  public boolean addEmpleado(Empleado empleado) {
    mapaEmpleado.put(empleado.getCodigo(), empleado);
    return true;
  }

  @Override
  public boolean updateEmpleado(Integer llave, Empleado empleado) {
    mapaEmpleado.put(llave, empleado);
    return true;
  }

  @Override
  public boolean deleteEmpleado(Integer llave) {
    mapaEmpleado.remove(llave);
    return true;
  }
  
}
