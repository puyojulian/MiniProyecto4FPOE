package co.edu.univalle.miniproyecto4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListModel;
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
  private List<Integer> codEmpleados, codDevengos, codDeducciones, codDevengosSeleccionados, codDeduccionesSeleccionadas;
  private List<String> trabajoFacturadoList, devengosCalculados, deduccionesCalculadas;
  private float sumaHaceBase, sumaDevengos, sumaDeducciones;
  private DefaultTableModel modeloTabla;
  private DefaultListModel<String> listModelDevengos, listModelDeducciones;
  // private String apartadoFormulario = "";

  public ViewLiquidacionController(ViewLiquidacion vista, Ingenio ingenio) {
    this.vista = vista;
    this.ingenio = ingenio;
    trabajoFacturadoList = new ArrayList<String>();
    devengosCalculados = new ArrayList<String>();
    codDevengosSeleccionados = new ArrayList<Integer>();
    codDeduccionesSeleccionadas = new ArrayList<Integer>();
    deduccionesCalculadas = new ArrayList<String>();
    codDeducciones = new ArrayList<Integer>();

    modeloTabla = new DefaultTableModel() {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };

    codEmpleados = AuxController.popularNombreComboBox(vista.getDropEmpleado(), ingenio.getEmpleadoDAO().getMapEmpleado());
    codDevengos = AuxController.popularNombreComboBox(vista.getDropDevengos(), ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo());
    codDeducciones = AuxController.popularNombreComboBox(vista.getDropDeducciones(), ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion());
    
    ListSelectionHandler manejadorDeListSelectionEvents = new ListSelectionHandler();
    ActionsHandler manejadorDeActionEvents = new ActionsHandler();
    vista.getTablaDatos().getSelectionModel().addListSelectionListener(manejadorDeListSelectionEvents);
    vista.btnAddActionListener(manejadorDeActionEvents);

    vista.getBtnPendientes().setSelected(true);
    vista.getTablaDatos().setModel(actualizarTableModel(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())));
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
      if(i < matrizTemporal.size()) {
        String[] arregloTemporal = {matrizTemporal.get(i).get(1), matrizTemporal.get(i).get(3), matrizTemporal.get(i).get(4), matrizTemporal.get(i).get(5)};
        modeloTabla.addRow(arregloTemporal);
      }
    }

    return modeloTabla;
  }

  /* --------------- MUESTREO: ACTUALIZAR MODELO TABLA (SEGÚN EMPLEADO Y APARTADOFORMULARIO) ------------------- */
  public DefaultTableModel actualizarTableModelTodos() {
    List<ArrayList<String>> matrizTemporal = new ArrayList<>();

    modeloTabla.setRowCount(0);

    String[] atributosTabla = {"FICHA", "FECHA", "TONELADAS", "TIPO CAÑA", "DÍA"};
    modeloTabla.setColumnIdentifiers(atributosTabla);

    if(vista.getBtnPendientes().isSelected()) {
      matrizPendiente = TextReaderUtil.getListaCantidadTrabajadaTodos("InformacionPagos/RegistroCortePendiente.txt");
      matrizTemporal = matrizPendiente;
    }
    else if(vista.getBtnPagados().isSelected()) {
      matrizFacturado = TextReaderUtil.getListaCantidadTrabajadaTodos("InformacionPagos/RegistroCortePagado.txt");
      matrizTemporal = matrizFacturado;
    }

    for(int i = 0; i < matrizTemporal.size(); i++) {
      // Debe corresponder con: "FECHA" (1), "TONELADAS" (3), "TIPO CAÑA" (4), "DÍA" (5).
      if(matrizTemporal.get(i).size() > 0) {
        String[] arregloTemporal = {matrizTemporal.get(i).get(0), matrizTemporal.get(i).get(1), matrizTemporal.get(i).get(3), matrizTemporal.get(i).get(4), matrizTemporal.get(i).get(5)};
        modeloTabla.addRow(arregloTemporal);
      }
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
  public void getCodDevengos() {
    codDevengosSeleccionados.clear();
    ListModel<String> model = vista.getListDevengos().getModel();

    for (int i = 0; i < model.getSize(); i++) {
      String element = model.getElementAt(i);
      codDevengosSeleccionados.add(AuxController.getCodByNombre(element, ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo()));
    }
  }

  /* --------------- CÁLCULO: REGISTRA LOS CÓDIGOS DE LAS DEDUCCIONES EN LA LISTA ------------------- */
  public void getCodDeducciones() {
    codDeduccionesSeleccionadas.clear();
    ListModel<String> model = vista.getListDeduccion().getModel();
    
    for (int i = 0; i < model.getSize(); i++) {
      String element = model.getElementAt(i);
      codDeduccionesSeleccionadas.add(AuxController.getCodByNombre(element, ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion()));
    }
  }

  /* --------------- CÁLCULO: CALCULA DEVENGO Y RETORNA STRING ------------------- */
  public String calculaDevengo(int llaveConcepto, float valor, int toneladaCorte) {
    float valorCalculado = 0;
    String conceptoDeDevengo = llaveConcepto + "-" +
      ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo().get(llaveConcepto).getNombre() + ": ";
    if(toneladaCorte != 0) {
      valorCalculado = toneladaCorte/1000*valor;
      conceptoDeDevengo+= "$ " + valorCalculado + "\n\tTonelaje: " + toneladaCorte/1000;

    }
    else{
      if(valor > 0 && valor <= 1) {
        valorCalculado = sumaHaceBase * valor;
        conceptoDeDevengo+= "$ " + valorCalculado;
      }
      else if(valor > 1) {
        valorCalculado = valor;
        conceptoDeDevengo+= "$ " + valorCalculado;
      }
    }

    if(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo().get(llaveConcepto).isHaceBase()) {
      sumaHaceBase+= valorCalculado;
    }

    sumaDevengos+= valorCalculado;
    return conceptoDeDevengo;
  }

  /* --------------- CÁLCULO: CALCULA DEDUCCIÓN Y RETORNA STRING ------------------- */
  public String calculaDeduccion(int llaveConcepto, float valor) {
    float valorCalculado = 0;
    String conceptoDeDeduccion = llaveConcepto + "-" +
      ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion().get(llaveConcepto).getNombre() + ": ";
    if(valor > 0 && valor <= 1) {
      valorCalculado = sumaHaceBase * valor;
      conceptoDeDeduccion+= "$ " + valorCalculado;
    }
    else if(valor > 1) {
      valorCalculado = valor;
      conceptoDeDeduccion+= "$ " + valorCalculado;
    }

    sumaDeducciones+= valorCalculado;
    return conceptoDeDeduccion;
  }



  /* --------------- CÁLCULO: LLENA LISTA DEVENGOS CON LOS DEVENGOS CALCULADOS ------------------- */
  public void guardarDevengosCalculados() {
    sumaHaceBase = 0;
    sumaDevengos = 0;
    devengosCalculados.clear();
    for(int i = 0; i < codDevengosSeleccionados.size(); i++) {
      if(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo().get(codDevengosSeleccionados.get(i)).isHaceBase()) {
        for(int j = 0; j < matrizPendiente.size(); j++) {
          if(Integer.parseInt(matrizPendiente.get(j).get(4)) == codDevengosSeleccionados.get(i)) {
            devengosCalculados.add(calculaDevengo(codDevengosSeleccionados.get(i), (float) ingenio.getMapConfigDevengos().get(codDevengosSeleccionados.get(i)).getSecond(), Integer.parseInt(matrizPendiente.get(j).get(3))));
          }
        }
        devengosCalculados.add(calculaDevengo(codDevengosSeleccionados.get(i), (float) ingenio.getMapConfigDevengos().get(codDevengos.get(i)).getSecond(), 0));
      }
    }
    for(int i = 0; i < codDevengosSeleccionados.size(); i++) {
      if(!ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo().get(codDevengosSeleccionados.get(i)).isHaceBase()) {
        devengosCalculados.add(calculaDevengo(codDevengosSeleccionados.get(i), (float) ingenio.getMapConfigDevengos().get(codDevengos.get(i)).getSecond(), 0));
      }
    }
  }

  /* --------------- CÁLCULO: LLENA LISTA DEDUCCIONES CON LAS DEDUCCIONES CALCULADAS ------------------- */
  public void calcularDeduccionesCalculadas() {
    sumaDeducciones = 0;
    deduccionesCalculadas.clear();
    for(int i = 0; i < codDeduccionesSeleccionadas.size(); i++) {
      deduccionesCalculadas.add(calculaDeduccion(codDeduccionesSeleccionadas.get(i), (float) ingenio.getMapConfigDeducciones().get(codDeducciones.get(i)).getSecond()));
    }
  }

  /* --------------- VERIFICACIÓN: VERIFICA SI EL COCEPTO YA ESTÁ AGREGADO ------------------- */
  private boolean conceptoDevengoYaAgregado() {
    if(!listModelDevengos.isEmpty()) {
      for (int i = 0; i < listModelDevengos.getSize(); i++) {
        if(listModelDevengos.getElementAt(i).equals(vista.getDropDevengos().getSelectedItem())) {
          return true;
        }
      }
    }
    return false;
  }

  /* --------------- VERIFICACIÓN: VERIFICA SI EL COCEPTO YA ESTÁ AGREGADO ------------------- */
  private boolean conceptoDeduccionYaAgregado() {
    if(!listModelDeducciones.isEmpty()) {
      for (int i = 0; i < listModelDeducciones.getSize(); i++) {
        if(listModelDeducciones.getElementAt(i).equals(vista.getDropDeducciones().getSelectedItem())) {
          return true;
        }
      }
    }
    return false;
  }


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
        vista.getTablaDatos().setModel(actualizarTableModel(AuxController.getCodByNombre((String) vista.getDropEmpleado().getSelectedItem(), ingenio.getEmpleadoDAO().getMapEmpleado())));
      }
      else if(e.getSource() == vista.getBtnPendientes()) {
        if(vista.getDropEmpleado().getSelectedIndex() == 0) {
          vista.getTablaDatos().setModel(actualizarTableModelTodos());
        }
        else {
          vista.getTablaDatos().setModel(actualizarTableModel(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())));
        }
      }
      else if(e.getSource() == vista.getBtnRegistrar()) {
      }
      else if(e.getSource() == vista.getBtnPreviz()) {
        
      }
      else if(e.getSource() == vista.getBtnFacturarEmitir()) {
        
      }
      else if(e.getSource() == vista.getBtnHome()) {
        vista.dispose();
      }
    }
  }
}
