package co.edu.univalle.miniproyecto4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import co.edu.univalle.miniproyecto4.controllers.ViewFormularioController.ActionsHandler;
import co.edu.univalle.miniproyecto4.models.Empleado;
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
  private static int numeroPago;
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

    listModelDevengos = new DefaultListModel<String>();
    listModelDeducciones = new DefaultListModel<String>();

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
    vista.getDropEmpleado().addActionListener(manejadorDeActionEvents);

    vista.getBtnPendientes().setSelected(true);
    vista.getTablaDatos().setModel(actualizarTableModelTodos());
  }

  /* --------------- MUESTREO: ACTUALIZAR MODELO TABLA (SEGÚN EMPLEADO Y APARTADOFORMULARIO) ------------------- */
  public DefaultTableModel actualizarTableModel(int fichaEmpleado) {
    List<ArrayList<String>> matrizTemporal = new ArrayList<>();

    modeloTabla.setRowCount(0);

    String[] atributosTabla = {"FICHA", "FECHA", "TONELADAS", "TIPO CAÑA", "DÍA"};
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
        String[] arregloTemporal = {matrizTemporal.get(i).get(0), matrizTemporal.get(i).get(1), matrizTemporal.get(i).get(3), matrizTemporal.get(i).get(4), matrizTemporal.get(i).get(5)};
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
      System.out.println(codDevengosSeleccionados.get(i));
    }
  }

  /* --------------- CÁLCULO: REGISTRA LOS CÓDIGOS DE LAS DEDUCCIONES EN LA LISTA ------------------- */
  public void getCodDeducciones() {
    codDeduccionesSeleccionadas.clear();
    ListModel<String> model = vista.getListDeduccion().getModel();
    
    for (int i = 0; i < model.getSize(); i++) {
      String element = model.getElementAt(i);
      codDeduccionesSeleccionadas.add(AuxController.getCodByNombre(element, ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion()));
      System.out.println(codDeduccionesSeleccionadas.get(i));
    }
  }

  /* --------------- CÁLCULO: CALCULA DEVENGO Y RETORNA STRING ------------------- */
  public String calculaDevengo(int llaveConcepto, float valor, float toneladaCorte) {
    float valorCalculado = 0;
    String conceptoDeDevengo = "";
    if(toneladaCorte > 0) {
        conceptoDeDevengo = llaveConcepto + "-" +
        ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo().get(llaveConcepto).getNombre() + ": ";
      valorCalculado = toneladaCorte/1000 *valor;
      conceptoDeDevengo+= "$ " + valorCalculado + "\n\tTonelaje: " + toneladaCorte/1000;

    }
    else{
      if(llaveConcepto > 4) {
        conceptoDeDevengo = llaveConcepto + "-" +
          ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo().get(llaveConcepto).getNombre() + ": ";
        if(valor > 0 && valor <= 1) {
          valorCalculado = sumaHaceBase * valor;
          conceptoDeDevengo+= "$ " + valorCalculado;
        }
        else if(valor > 1) {
          valorCalculado = valor;
          conceptoDeDevengo+= "$ " + valorCalculado;
        }
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
            System.out.println(Integer.parseInt(matrizPendiente.get(j).get(4)) + " =?" +  codDevengosSeleccionados.get(i));
            if(ingenio.getMapConfigDevengos().get(codDevengosSeleccionados.get(i)) != null) {
              devengosCalculados.add(calculaDevengo(codDevengosSeleccionados.get(i), (float) ingenio.getMapConfigDevengos().get(codDevengosSeleccionados.get(i)).getSecond(), Float.parseFloat(matrizPendiente.get(j).get(3))));
            }
          }
          // System.out.println(codDevengosSeleccionados.get(i) + "==?"+Integer.parseInt(matrizPendiente.get(j).get(4)));
        }
        if(ingenio.getMapConfigDevengos().get(codDevengosSeleccionados.get(i)) != null) {
          devengosCalculados.add(calculaDevengo(codDevengosSeleccionados.get(i), (float) ingenio.getMapConfigDevengos().get(codDevengosSeleccionados.get(i)).getSecond(), 0));
        }
      }
    }
    for(int i = 0; i < codDevengosSeleccionados.size(); i++) {
      if(!ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo().get(codDevengosSeleccionados.get(i)).isHaceBase()) {
        if(ingenio.getMapConfigDevengos().get(codDevengos.get(i)) != null) {
          devengosCalculados.add(calculaDevengo(codDevengosSeleccionados.get(i), (float) ingenio.getMapConfigDevengos().get(codDevengosSeleccionados.get(i)).getSecond(), 0));
        }
      }
    }
  }

  /* --------------- CÁLCULO: LLENA LISTA DEDUCCIONES CON LAS DEDUCCIONES CALCULADAS ------------------- */
  public void guardarDeduccionesCalculadas() {
    sumaDeducciones = 0;
    deduccionesCalculadas.clear();
    for(int i = 0; i < codDeduccionesSeleccionadas.size(); i++) {
      if(ingenio.getMapConfigDevengos().get(codDeduccionesSeleccionadas.get(i)) != null) {
        deduccionesCalculadas.add(calculaDeduccion(codDeduccionesSeleccionadas.get(i), (float) ingenio.getMapConfigDeducciones().get(codDeduccionesSeleccionadas.get(i)).getSecond()));
      }
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

  /* --------------- CÁLCULO: CONSTRUYE STRING PARA MOSTRAR EN TEXT AREA ------------------- */
  public String previsualizacionNomina() {
    getCodDevengos();
    getCodDeducciones();
    guardarDevengosCalculados();
    guardarDeduccionesCalculadas();

    String nomina = "";
    nomina+= "========================================\n";
    nomina+= "\t" + ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get("1000777999").getNombre() + "\n";
    nomina+= "\tNIT: " + ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get("1000777999").getNit() + "\n";
    nomina+= "----------------------------------------\n";
    nomina+= "NUMERO DE PAGO: " + numeroPago + "\n";
    nomina+= "\tFICHA: " + codEmpleados.get(vista.getDropEmpleado().getSelectedIndex()) + "\n";
    nomina+= "\tNOMBRE: " + vista.getDropEmpleado().getSelectedItem() + "\n";
    nomina+= "\tCÉDULA: " + ingenio.getEmpleadoDAO().getMapEmpleado().get(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())).getIdentificacion() + "\n";
    nomina+= "========================================\n";
    if(!devengosCalculados.isEmpty()) {
      nomina+= "DEVENGOS: " + "\n" + "\n";
      for(int i = 0; i < devengosCalculados.size(); i++) {
        if(!devengosCalculados.get(i).isEmpty()) {
          nomina+= devengosCalculados.get(i) + "\n";
        }
      }
      nomina+= "\nTOTAL DEVENGOS: $ " + sumaDevengos + "\n";
      nomina+= "========================================\n";
    }
    if(!deduccionesCalculadas.isEmpty()) {
      nomina+= "DEDUCCIONES: " + "\n" + "\n";
      for(int i = 0; i < deduccionesCalculadas.size(); i++) {
        if(!deduccionesCalculadas.get(i).isEmpty()) {
          nomina+= deduccionesCalculadas.get(i) + "\n";
        }
      }
      nomina+= "\nTOTAL DEDUCCIONES: $ " + sumaDeducciones + "\n";
      nomina+= "========================================\n";
    }
    nomina+= "\nNETO PAGADO: $ " + (sumaDevengos - sumaDeducciones) + "\n";
    nomina+= "========================================\n";
    return nomina;
  }

  public void llenarCamposLiquidacion () {
    int index = vista.getTablaDatos().getSelectedRow();
    if(index != -1) {
      vista.getFildLiqFicha().setText((String) modeloTabla.getValueAt(index, 0));
      vista.getFildLiqHacienda().setText(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get("1000777999").getNombre());
      vista.getFildLiqTonelada().setText((String) modeloTabla.getValueAt(index, 2));
      vista.getFildLiqFechaCorte().setText((String) modeloTabla.getValueAt(index, 1));
      vista.getDropTipoCana().setSelectedIndex(Integer.parseInt((String) modeloTabla.getValueAt(index, 3)));
      if("O".equals(modeloTabla.getValueAt(index, 4).toString()) ){
        vista.getdropDiacorte().setSelectedIndex(1);
      } 
      else if ("F".equals(modeloTabla.getValueAt(index, 4).toString())) {
        vista.getdropDiacorte().setSelectedIndex(2);
      }
      vista.getBtnRegistrar().setText("Limpiar");
    } 
  }

  /* --------------- CLASE LISTENER: MANEJADOR DE EVENTOS DE SELECCIÓN ------------------- */
  class ListSelectionHandler implements ListSelectionListener {

    @Override
    public void valueChanged(ListSelectionEvent e) {
      llenarCamposLiquidacion();
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
      else if(e.getSource() == vista.getBtnRegistrar()) { // REGISTRAR INFORMACIÓN CORTE
        if("Limpiar".equals(vista.getBtnRegistrar().getText())) {
          vista.getFildLiqFicha().setText("");
          vista.getFildLiqHacienda().setText("");
          vista.getFildLiqTonelada().setText("");
          vista.getFildLiqFechaCorte().setText("");
          vista.getDropTipoCana().setSelectedIndex(0);
          vista.getdropDiacorte().setSelectedIndex(0);
          vista.getTablaDatos().clearSelection();
          vista.getBtnRegistrar().setText("Registrar");
        }
        if("Registrar".equals(vista.getBtnRegistrar().getText())) {

        }
      }
      else if(e.getSource() == vista.getBtnAddDevengo()) { // AGREGAR CONCEPTO
        if(vista.getDropDevengos().getSelectedIndex() != 0) {
          if(!conceptoDevengoYaAgregado()) {
            listModelDevengos.addElement((String) vista.getDropDevengos().getSelectedItem());
            vista.getListDevengos().setModel(listModelDevengos);
          }
          else {
            AuxController.mensajeTemporal("El concepto de devengo ya ha sido registrado.", "Error", 1150);
          }
        }
        else {
          AuxController.mensajeTemporal("Escoja un elemento del desplegable para agregarlo.", "Error", 1150);
        }
      }
      else if(e.getSource() == vista.getBtnAddDeduccion()) {
        if(vista.getDropDeducciones().getSelectedIndex() != 0) {
          if(!conceptoDeduccionYaAgregado()) {
            listModelDeducciones.addElement((String) vista.getDropDeducciones().getSelectedItem());
            vista.getListDeduccion().setModel(listModelDeducciones);
          }
          else {
            AuxController.mensajeTemporal("El concepto de deducción ya ha sido registrado.", "Error", 1150);
          }
        }
        else {
          AuxController.mensajeTemporal("Escoja un elemento del desplegable para agregarlo.", "Error", 1150);
        }
      }
      else if(e.getSource() == vista.getbtnDeleteDevengo()) { // REMOVER CONCEPTO
        if(vista.getListDevengos().getSelectedIndex() != -1) {
          listModelDevengos.remove(vista.getListDevengos().getSelectedIndex());
          vista.getListDevengos().setModel(listModelDevengos);
        }
        else {
          AuxController.mensajeTemporal("Escoja un elemento de la lista para borrarlo.", "Error", 1150);
        }
      }
      else if(e.getSource() == vista.getBtnDeleteDeduccion()) {
        if(vista.getListDeduccion().getSelectedIndex() != -1) {
          listModelDeducciones.remove(vista.getListDeduccion().getSelectedIndex());
          vista.getListDeduccion().setModel(listModelDeducciones);
        }
        else {
          AuxController.mensajeTemporal("Escoja un elemento de la lista para borrarlo.", "Error", 1150);
        }
      }
      else if(e.getSource() == vista.getBtnPreviz()) {
        if(vista.getDropEmpleado().getSelectedIndex() != 0 && !listModelDevengos.isEmpty()) {
          vista.getAreaComprobanteNomina().setText(previsualizacionNomina());
        }
      }
      else if(e.getSource() == vista.getBtnFacturarEmitir()) {
        if(!vista.getAreaComprobanteNomina().getText().isEmpty()) {
          String nombreYApellido = ingenio.getEmpleadoDAO().getMapEmpleado().get(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())).getNombre() + ingenio.getEmpleadoDAO().getMapEmpleado().get(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())).getApellido();
          TextReaderUtil.escribirTextoArea("PagosEmitidos/" + AuxController.fechaToString(LocalDate.now()) +"PagoNomina" + nombreYApellido + ".txt", vista.getAreaComprobanteNomina().getText());
          AuxController.mensajeTemporal("Pago para" + ingenio.getEmpleadoDAO().getMapEmpleado().get(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())).getNombre() + " " + ingenio.getEmpleadoDAO().getMapEmpleado().get(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())).getApellido() + " emitido exitosamente.", "Aviso", 1150);
        }
      }
      else if(e.getSource() == vista.getBtnHome()) {
        vista.dispose();
      }
      else if(e.getSource() == vista.getDropEmpleado()) { // Llenar tabla según item en dropEmpleado
        if(vista.getDropEmpleado().getSelectedIndex() == 0) {
          vista.getTablaDatos().setModel(actualizarTableModelTodos());
        }
        else {
          vista.getTablaDatos().setModel(actualizarTableModel(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())));
        }
      }
    }
  }
}
