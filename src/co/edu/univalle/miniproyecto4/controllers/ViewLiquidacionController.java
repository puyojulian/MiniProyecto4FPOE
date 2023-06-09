package co.edu.univalle.miniproyecto4.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
  private List<String> trabajoNoFacturadoList, trabajoFacturadoList, devengosCalculados, deduccionesCalculadas;
  private float sumaHaceBase, sumaDevengos, sumaDeducciones;
  private DefaultTableModel modeloTabla;
  private DefaultListModel<String> listModelDevengos, listModelDeducciones;
  private static int numeroPago;
  // private String apartadoFormulario = "";

  public ViewLiquidacionController(ViewLiquidacion vista, Ingenio ingenio) {
    this.vista = vista;
    this.ingenio = ingenio;
    trabajoFacturadoList = new ArrayList<String>();
    trabajoNoFacturadoList = new ArrayList<String>();
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

    codEmpleados = AuxController.popularNombreYApellidoComboBox(vista.getDropEmpleado(), ingenio.getEmpleadoDAO().getMapEmpleado());
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
      if(!matrizTemporal.isEmpty()) {
        String[] arregloTemporal = {matrizTemporal.get(i).get(0), matrizTemporal.get(i).get(1), matrizTemporal.get(i).get(3), matrizTemporal.get(i).get(4), matrizTemporal.get(i).get(5)};
        modeloTabla.addRow(arregloTemporal);

        if(!matrizPendiente.isEmpty()) {
          trabajoNoFacturadoList.add(matrizPendiente.get(i).get(0) + matrizPendiente.get(i).get(1) + matrizPendiente.get(i).get(2) + matrizPendiente.get(i).get(3) + matrizPendiente.get(i).get(4) + matrizPendiente.get(i).get(5));
        }
      }
    }

    return modeloTabla;
  }

  /* --------------- MUESTREO: ACTUALIZAR MODELO TABLA (SEGÚN EMPLEADO Y APARTADOFORMULARIO) ------------------- */
  public DefaultTableModel actualizarTableModelTodos() {
    List<ArrayList<String>> matrizTemporal = new ArrayList<>();

    modeloTabla.setRowCount(0);
    trabajoNoFacturadoList.clear();

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

        if(!matrizPendiente.isEmpty()) {
          trabajoNoFacturadoList.add(matrizPendiente.get(i).get(0) + matrizPendiente.get(i).get(1) + matrizPendiente.get(i).get(2 )+ matrizPendiente.get(i).get(3) + matrizPendiente.get(i).get(4) + matrizPendiente.get(i).get(5));
        }

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
    trabajoFacturadoList.clear();
    for(int i = 0; i < codDevengosSeleccionados.size(); i++) {
      if(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo().get(codDevengosSeleccionados.get(i)).isHaceBase()) {
        for(int j = 0; j < matrizPendiente.size(); j++) {
          if(Integer.parseInt(matrizPendiente.get(j).get(4)) == codDevengosSeleccionados.get(i)) {
            if(ingenio.getMapConfigDevengos().get(codDevengosSeleccionados.get(i)) != null) {
              devengosCalculados.add(calculaDevengo(codDevengosSeleccionados.get(i), (float) ingenio.getMapConfigDevengos().get(codDevengosSeleccionados.get(i)).getSecond(), Float.parseFloat(matrizPendiente.get(j).get(3))));
              trabajoFacturadoList.add(matrizPendiente.get(j).get(0)+matrizPendiente.get(j).get(1)+matrizPendiente.get(j).get(2)+matrizPendiente.get(j).get(3)+matrizPendiente.get(j).get(4)+matrizPendiente.get(j).get(5));
            }
          }
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
    nomina+= "==============================================\n";
    nomina+= "\t" + ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get("1000777999").getNombre() + "\n";
    nomina+= "\tNIT: " + ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get("1000777999").getNit() + "\n";
    nomina+= "--------------------------------------------------------\n";
    nomina+= "FECHA FACTURACIÓN: " + AuxController.fechaToString(LocalDate.now()) + "\n";
    nomina+= "NUMERO DE PAGO: " + numeroPago + "\n";
    nomina+= "\tFICHA: " + codEmpleados.get(vista.getDropEmpleado().getSelectedIndex()) + "\n";
    nomina+= "\tNOMBRE: " + vista.getDropEmpleado().getSelectedItem() + "\n";
    nomina+= "\tCÉDULA: " + ingenio.getEmpleadoDAO().getMapEmpleado().get(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())).getIdentificacion() + "\n";
    nomina+= "==============================================\n";
    if(!devengosCalculados.isEmpty()) {
      nomina+= "DEVENGOS: " + "\n" + "\n";
      for(int i = 0; i < devengosCalculados.size(); i++) {
        if(!devengosCalculados.get(i).isEmpty()) {
          nomina+= devengosCalculados.get(i) + "\n";
        }
      }
      nomina+= "\nTOTAL DEVENGOS: $ " + sumaDevengos + "\n";
      nomina+= "==============================================\n";
    }
    if(!deduccionesCalculadas.isEmpty()) {
      nomina+= "DEDUCCIONES: " + "\n" + "\n";
      for(int i = 0; i < deduccionesCalculadas.size(); i++) {
        if(!deduccionesCalculadas.get(i).isEmpty()) {
          nomina+= deduccionesCalculadas.get(i) + "\n";
        }
      }
      nomina+= "\nTOTAL DEDUCCIONES: $ " + sumaDeducciones + "\n";
      nomina+= "==============================================\n";
    }
    nomina+= "\nNETO PAGADO: $ " + (sumaDevengos - sumaDeducciones) + "\n";
    nomina+= "==============================================\n";
    return nomina;
  }

  /* --------------- CÁLCULO: TRASLADA LINEAS PAGADAS A REGISTROCORTEPAGADO DESDE PENDIENTE ------------------- */
  public void moverInfoLineaCorte() {
    for(int i = 0; i < trabajoNoFacturadoList.size(); i++) {
      for(int j = 0; j < trabajoFacturadoList.size(); j++) {
        if(trabajoNoFacturadoList.get(i).equals(trabajoFacturadoList.get(j))) {
          if(!TextReaderUtil.isLineaArchivo("InformacionPagos/RegistroCortePagado.txt", trabajoFacturadoList.get(j))) {
            TextReaderUtil.appendLineaArchivo("InformacionPagos/RegistroCortePagado.txt", trabajoFacturadoList.get(j));
            TextReaderUtil.borrarLinea("InformacionPagos/RegistroCortePendiente.txt", trabajoNoFacturadoList.get(i));
            break;
          }
        }
      }
    }
  }

  /* --------------- CÁLCULO: CONSTRUYE STRING A BASE DE LAS ENTRADAS ------------------- */
  public String getInputString() {
    String stringCompleta = "";

    if(AuxController.esNumerico(vista.getFildLiqFicha().getText().trim()) && !vista.getFildLiqFicha().getText().trim().isEmpty()) {
      stringCompleta+= String.format("%" + 4 + "s", Integer.parseInt(vista.getFildLiqFicha().getText().trim())).replace(' ', '0');
    }

    String fechaCorte = AuxController.fechaToString(LocalDate.now()).replaceAll("-", "");
    if(vista.getFildLiqFechaCorte().getText().contains("-")) {
      String fecha = vista.getFildLiqFechaCorte().getText().replaceAll("-", "");
      if(fecha.length() == 8 && AuxController.esNumerico(fecha)) {
        fechaCorte = fecha;
      }
    }
    else if(vista.getFildLiqFechaCorte().getText().contains("/")) {
      String fecha = vista.getFildLiqFechaCorte().getText().replaceAll("/", "");
      if(fecha.length() == 8 && AuxController.esNumerico(fecha)) {
        fechaCorte = fecha;
      }
    }
    else {
      String fecha = vista.getFildLiqFechaCorte().getText();
      if(fecha.length() == 8 && AuxController.esNumerico(fecha)) {
        fechaCorte = fecha;
      }
    }
    stringCompleta+= fechaCorte;

    stringCompleta+= "00000A0000";
    if(AuxController.esNumerico(vista.getFildLiqTonelada().getText().trim()) && !vista.getFildLiqTonelada().getText().trim().isEmpty()) {
      stringCompleta+= String.format("%" + 5 + "s", Integer.parseInt(vista.getFildLiqTonelada().getText().trim())).replace(' ', '0');
    }
    
    stringCompleta+= vista.getDropTipoCana().getSelectedIndex();

    if(vista.getdropDiacorte().getSelectedIndex() == 1) {
      stringCompleta+= "O";
    }
    else if(vista.getdropDiacorte().getSelectedIndex() == 2) {
      stringCompleta+= "F";
    }

    if(stringCompleta.length() == 29) {
      return stringCompleta;
    }
    return "";
  }

  /* --------------- MUESTREO: ACTUALIZA LOS TEXTFIELD DEPENDIENDO DE LA SELECCIÓN DE LA TABLA ------------------- */
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

  /* --------------- MUESTREO: LIMPIAR LOS CAMPOS A SU ESTADO INICIAL ------------------- */
  public void limpiarCampos() {
    vista.getFildLiqFicha().setText("");
    vista.getFildLiqHacienda().setText("");
    vista.getFildLiqTonelada().setText("");
    vista.getFildLiqFechaCorte().setText("AAAA-MM-DD");
    vista.getDropTipoCana().setSelectedIndex(0);
    vista.getdropDiacorte().setSelectedIndex(0);
    vista.getDropDeducciones().setSelectedIndex(0);
    vista.getDropDevengos().setSelectedIndex(0);
    vista.getAreaComprobanteNomina().setText("");
    vista.getTablaDatos().clearSelection();

    vista.getBtnRegistrar().setText("Registrar");
  }

  /* --------------- VERIFICAR: VERIFICA EL FORMATO DE LA FECHA SEA EL CORRECTO ------------------- */
  public boolean verificarFecha() {
    StringTokenizer tokenizer = new StringTokenizer(vista.getFildLiqFechaCorte().getText(), "-");

    if(vista.getFildLiqFechaCorte().getText().equals("AAAA-MM-DD")) {
      return true;
    }

    if (tokenizer.countTokens() != 3) {
      return false;
    }

    String yearToken = tokenizer.nextToken();
    String monthToken = tokenizer.nextToken();
    String dayToken = tokenizer.nextToken();

    if (yearToken.length() != 4 || monthToken.length() != 2 || dayToken.length() != 2 || !AuxController.esNumerico(yearToken) || !AuxController.esNumerico(monthToken) || !AuxController.esNumerico(dayToken)) {
      return false;
    }
    return true;
  }

  /* --------------- VERIFICAR: VERIFICA CAMPOS VACIOS, TAMAÑO DE LOS STRINGS Y FORMATOS INGRESADOS ------------------- */
  public boolean verificarCampos() {
    if (vista.getFildLiqFicha().getText().length() > 4 || !AuxController.esNumerico(vista.getFildLiqFicha().getText())) {
      return false;
    }
    if (vista.getFildLiqTonelada().getText().length() > 5 || !AuxController.esNumerico(vista.getFildLiqTonelada().getText())) {
      return false; 
    }
    if (!verificarFecha()) {
      return false;
    }
    if (vista.getDropTipoCana().getSelectedIndex() == 0) {
      return false;
    }
    if (vista.getdropDiacorte().getSelectedIndex() == 0) {
      return false;
    }
    return true;
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
        if(vista.getDropEmpleado().getSelectedIndex() == 0) {
          vista.getTablaDatos().setModel(actualizarTableModelTodos());
        }
        else {
          vista.getTablaDatos().setModel(actualizarTableModel(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())));
        }
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
          limpiarCampos();
        }
        else if("Registrar".equals(vista.getBtnRegistrar().getText())) {
          if (verificarCampos()) {
            getInputString();
            TextReaderUtil.appendLineaArchivo("InformacionPagos/RegistroCortePendiente.txt", getInputString());
            AuxController.mensajeTemporal("Datos registrados satisfactoriamente", "Registro realizado", 1150);
            if(vista.getDropEmpleado().getSelectedIndex() == 0) {
              vista.getTablaDatos().setModel(actualizarTableModelTodos());
            }
            else {
              vista.getTablaDatos().setModel(actualizarTableModel(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())));
            }
            limpiarCampos();
          } 
          else {
            AuxController.mensajeTemporal("Verifique los datos ingresados en los campos", "Error", 1150);
          }
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
        else {
          AuxController.mensajeTemporal("Antes de previsualizar/emitir una factura seleccione un empleado y agrege las deducciones y devengos a pagar.", "Error", 4000);
        }
      }
      else if(e.getSource() == vista.getBtnFacturarEmitir()) {
        if(!vista.getAreaComprobanteNomina().getText().isEmpty() && vista.getDropEmpleado().getSelectedIndex() != 0) {
          String nombreYApellido = ingenio.getEmpleadoDAO().getMapEmpleado().get(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())).getNombre() + ingenio.getEmpleadoDAO().getMapEmpleado().get(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())).getApellido();
          TextReaderUtil.escribirTextoArea("PagosEmitidos/" + AuxController.fechaToString(LocalDate.now()) +"PagoNomina" + nombreYApellido + ".txt", vista.getAreaComprobanteNomina().getText());
          AuxController.mensajeTemporal("Pago para " + ingenio.getEmpleadoDAO().getMapEmpleado().get(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())).getNombre() + " " + ingenio.getEmpleadoDAO().getMapEmpleado().get(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())).getApellido() + " emitido exitosamente.", "Aviso", 1150);
          moverInfoLineaCorte();
          vista.getTablaDatos().clearSelection();
          vista.getDropEmpleado().setSelectedIndex(0);
          limpiarCampos();
          if(vista.getDropEmpleado().getSelectedIndex() == 0) {
            vista.getTablaDatos().setModel(actualizarTableModelTodos());
          }
          else {
            vista.getTablaDatos().setModel(actualizarTableModel(codEmpleados.get(vista.getDropEmpleado().getSelectedIndex())));
          }
        }
        else {
          AuxController.mensajeTemporal("Seleccione un empleado, configure los devengos y deducciones que aplican al pago, previsualice antes de emitir el comprobante.", "Error", 1150);
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
      else if(e.getSource() == vista.getBtnInfo()) { // Activa OptionPane para instrucciones
        JOptionPane.showMessageDialog(null,
          "PARA EMITIR COMPROBANTE DE PAGO:\n" + 
          "\n  1. SELECCIONE UN EMPLEADO\nDEL DESPLEGABLE PARA VERIFICAR SI \nTIENE PAGOS PENDIENTES NO EMITIDOS.\nO PUEDE VERIFICAR DIRECTAMENTE EN LA TABLA PRINCIPAL."+ 
          "\n\n  2. SELECCIONE EL CONJUNTO DE\nDEVENGOS Y DEDUCCIONES QUE APLIQUEN.\nDISPONE DE DOS BOTONES PARA AGREGAR\n Y ELIMINAR CONCEPTOS EN CADA LISTA." + 
          "\n\n  3. PRESIONE EL BOTÓN DE PREVISUALIZACIÓN\nPARA VER LOS RESULTADOS." +
          "\n\n  4. CONFIRME LA EMISIÓN DEL COMPROBANTE\n CON EL BOTÓN FACTURAR." + 
          "\n\n\nTAMBIÉN PUEDE SELECCIONAR ELEMENTOS \nDE LA TABLA PARA VER DETALLES DE CORTE." +
          "\n\n\nPARA AÑADIR INFORMACIÓN DE CORTE\n\n  1. LLENE LOS CAMPOS Y REGISTRE.",
          "INSTRUCCIONES",
          JOptionPane.INFORMATION_MESSAGE);
      }
    }
  }
}
