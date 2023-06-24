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

import co.edu.univalle.miniproyecto4.models.Empleado;

public interface EmpleadoDAOInterface {
    public Map<Integer, Empleado> getMapEmpleado();
    
    public Empleado getEmpleado(Integer llave);
    
    public boolean addEmpleado(Empleado empleado);
    
    public boolean updateEmpleado(Integer llave, Empleado empleado);
    
    public boolean deleteEmpleado(Integer llave);

    public void setMapEmpleado(Map<Integer, Empleado> mapa);
}