package co.edu.univalle.miniproyecto4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import co.edu.univalle.miniproyecto4.models.Ingenio;
import co.edu.univalle.miniproyecto4.util.TextReaderUtil;
import co.edu.univalle.miniproyecto4.views.ViewLiquidacion;

public class ViewLiquidacionController {
  private ViewLiquidacion vista;
  private Ingenio ingenio;
  private List<ArrayList<String>> matrizPendiente;
  private List<ArrayList<String>> matrizFacturado;
  private List<String> trabajoFacturadoList;
  private List<Integer> codDevengos;
  private List<Integer> codDeducciones;
  private List<String> devengos;
  private List<String> deducciones;
  private DefaultTableModel modeloTabla;
  private String apartadoFormulario = "";

  public ViewLiquidacionController(ViewLiquidacion vista, Ingenio ingenio) {
    this.vista = vista;
    this.ingenio = ingenio;
    trabajoFacturadoList = new ArrayList<String>();
    devengos = new ArrayList<String>();
    codDevengos = new ArrayList<Integer>();
    deducciones = new ArrayList<String>();
    codDeducciones = new ArrayList<Integer>();
    modeloTabla = new DefaultTableModel() {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    AuxController.popularNombreComboBox(vista.getDropEmpleado(), ingenio.getEmpleadoDAO().getMapEmpleado());
    vista.getBtnPendientes().setSelected(true);
    
    ListSelectionHandler manejadorDeListSelectionEvents = new ListSelectionHandler();
    vista.getTablaDatos().getSelectionModel().addListSelectionListener(manejadorDeListSelectionEvents);
    ActionsHandler manejadorDeActionEvents = new ActionsHandler();
    vista.btnAddActionListener(manejadorDeActionEvents);

    
  }

  /* --------------- MUESTREO: ACTUALIZAR MODELO TABLA (SEGÚN EMPLEADO Y APARTADOFORMULARIO) ------------------- */
  public DefaultTableModel actualizarTableModel(int fichaEmpleado) {
    List<ArrayList<String>> matrizTemporal = new ArrayList<>();

    modeloTabla.setRowCount(0);

    String[] atributosTabla = {"FECHA", "TONELADAS", "TIPO CAÑA", "DÍA"};
    modeloTabla.setColumnIdentifiers(atributosTabla);

    if(vista.getBtnPendientes().isSelected()) {
      matrizPendiente = TextReaderUtil.getListaCantidadTrabajada("InformacionPagos/RegistroCortePendiente.txt", fichaEmpleado);
      matrizTemporal = matrizPendiente;
    }
    else if(vista.getBtnPagados().isSelected()) {
      matrizFacturado = TextReaderUtil.getListaCantidadTrabajada("InformacionPagos/RegistroCortePagado.txt", fichaEmpleado);
      matrizTemporal = matrizFacturado;
    }

    for(int i = 0; i < matrizTemporal.size(); i++) {
      // Debe corresponder con: "FECHA" (1), "TONELADAS" (3), "TIPO CAÑA" (4), "DÍA" (5).
      String[] arregloTemporal = {matrizTemporal.get(i).get(1), matrizTemporal.get(i).get(3), matrizTemporal.get(i).get(4), matrizTemporal.get(i).get(5)};
      modeloTabla.addRow(arregloTemporal);
    }

    return modeloTabla;
  }

  /* --------------- FORMATO: CONVIERTE LISTA DE STRINGS EN STRING (SIN DELIMITADORES) ------------------- */
  public String listToString(List<String> lista) {
    String listaString = "";
    for(int i = 0; i < lista.size(); i++) {
      listaString+= lista.get(i);
    }
    return listaString;
  }
  /* --------------- CÁLCULO: REGISTRA LOS CÓDIGOS DE LOS DEVENGOS EN LA LISTA ------------------- */

  /* --------------- CÁLCULO: REGISTRA LOS CÓDIGOS DE LAS DEDUCCIONES EN LA LISTA ------------------- */

  /* --------------- CÁLCULO: CALCULA DEVENGO Y RETORNA STRING ------------------- */

  /* --------------- CÁLCULO: CALCULA DEDUCCIÓN Y RETORNA STRING ------------------- */

  /* --------------- CÁLCULO: LLENA LISTA DEVENGOS CON LOS DEVENGOS CALCULADOS ------------------- */

  /* --------------- CÁLCULO: LLENA LISTA DEDUCCIONES CON LAS DEDUCCIONES CALCULADAS ------------------- */

  

  /* --------------- CLASE LISTENER: MANEJADOR DE EVENTOS DE SELECCIÓN ------------------- */
  class ListSelectionHandler implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
  }

  /* --------------- CLASE LISTENER: MANEJADOR DE EVENTOS DE ACCIÓN ------------------- */
  class ActionsHandler implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == vista.getBtnPagados()) {
        actualizarTableModel(AuxController.getCodByNombre((String) vista.getDropEmpleado().getSelectedItem(), ingenio.getEmpleadoDAO().getMapEmpleado()));
      }
      else if(e.getSource() == vista.getBtnPendientes()) {
        actualizarTableModel(AuxController.getCodByNombre((String) vista.getDropEmpleado().getSelectedItem(), ingenio.getEmpleadoDAO().getMapEmpleado()));
      }
      else if(e.getSource() == vista.getBtnRegistrar()) {
      }
      else if(e.getSource() == vista.getBtnImpresora()) {
      }
      else if(e.getSource() == vista.getBtnFacturarEmitir()) {
      }
    }
  }
}
