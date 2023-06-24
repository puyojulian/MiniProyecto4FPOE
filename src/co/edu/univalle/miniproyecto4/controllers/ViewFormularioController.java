/**
 Archivo: ViewFormularioController.java
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

package co.edu.univalle.miniproyecto4.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.table.DefaultTableModel;

import co.edu.univalle.miniproyecto4.models.Ingenio;
import co.edu.univalle.miniproyecto4.views.ViewFormulario;

public class ViewFormularioController {
  private ViewFormulario vista;
  private Ingenio ingenio;
  

  public ViewFormularioController(ViewFormulario vista) {
    this.vista = vista;
    ingenio = new Ingenio();

  }
  
  public DefaultTableModel actualizarJListaPrestamos(Map<Integer, Object> mapa) {
    DefaultTableModel modeloTabla = new DefaultTableModel();
    List<Object> listaTemporal = new ArrayList<>();
  // List<Object> listaMap = new ArrayList<>();
      
    if(mapa.size() > 0) {
        Set<Map.Entry<Integer, Object>> entrySetMapa = mapa.entrySet();

        listaTemporal.clear();
        modeloTabla.setRowCount(0);

        // listaMap = new ArrayList<>(mapa.entrySet());
        
        establecerIdentificadoresColumnas(modeloTabla);

        for (Map.Entry<Integer, Object> entry : entrySetMapa){
            Object value = entry.getValue();
            String item = "" + value;
            StringTokenizer tokenizer = new StringTokenizer(item,",");
            while(tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                listaTemporal.add(token);
            }
            
            modeloTabla.addRow(listaTemporal.toArray());
            listaTemporal.clear();
        }
        return modeloTabla;
    }
    else {
        modeloTabla.setRowCount(0);
        return modeloTabla;
    }
  }

    public void establecerIdentificadoresColumnas(DefaultTableModel modelo) {
        if(vista.getBtnEmpleado().isSelected()) {
            String[] atributosTabla = {"ID", "COD", "APELLIDOS", "NOMBRES", "DIRECCIÓN", "COD. EPS", "COD. FPP"};
            modelo.setColumnIdentifiers(atributosTabla);
        }
        else if(vista.getBtnEps().isSelected()){
            String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
            modelo.setColumnIdentifiers(atributosTabla);
        }
        else if(vista.getBtnFondoP().isSelected()){
            String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
            modelo.setColumnIdentifiers(atributosTabla);
        }
        else if(vista.getBtnARL().isSelected()){
            String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
            modelo.setColumnIdentifiers(atributosTabla);
        }
        else if(vista.getBtnCajaCompen().isSelected()){
            String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
            modelo.setColumnIdentifiers(atributosTabla);
        }
        else if(vista.getBtnEmpresa().isSelected()){
            String[] atributosTabla = {"NIT", "RAZÓN SOCIAL", "NOMBRE", "TELÉFONO", "DIRECCIÓN", "REP. LEGAL", "CORREO CONT.", "CÓD. ARL", "CÓD. CAJA", "SMLV", "AUX. TRANSP."};
            modelo.setColumnIdentifiers(atributosTabla);
        }
        else if(vista.getBtnDevegno().isSelected()){
            String[] atributosTabla = {"CÓDIGO", "NOMBRE", "HACE BASE"};
            modelo.setColumnIdentifiers(atributosTabla);
        }
        else if(vista.getBtnDeduccion().isSelected()){
            String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
            modelo.setColumnIdentifiers(atributosTabla);
        }
    }

    // btnAñadir.addActionListener(listener);
    // btnEliminar.addActionListener(listener);
    // btnEditar.addActionListener(listener);
    // btnLimpiar.addActionListener(listener);

    // btnEmpleado.addActionListener(listener);
    // btnEps.addActionListener(listener);
    // btnFondoP.addActionListener(listener);
    // btnARL.addActionListener(listener);
    // btnCajaCompen.addActionListener(listener);
    // btnEmpresa.addActionListener(listener);
    // btnDevegno.addActionListener(listener);
    // btnDeduccion.addActionListener(listener);

}
