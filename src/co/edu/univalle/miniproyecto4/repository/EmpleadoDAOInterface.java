package co.edu.univalle.miniproyecto4.repository;

import java.util.Map;

import co.edu.univalle.miniproyecto4.models.Empleado;

public interface EmpleadoDAOInterface {
    public Map<Integer, Empleado> getEmpleados();
    
    public Empleado getEmpleado(Integer llave);
    
    public boolean addEmpleado(Empleado empleado);
    
    public boolean updateEmpleado(Integer llave, Empleado empleado);
    
    public boolean deleteEmpleado(Integer llave);
}