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
 Controlador de vista ViewFormulario.
*/

package co.edu.univalle.miniproyecto4.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;

import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import co.edu.univalle.miniproyecto4.models.Arl;
import co.edu.univalle.miniproyecto4.models.CajaDeCompensacion;
import co.edu.univalle.miniproyecto4.models.ConceptoDeDeduccion;
import co.edu.univalle.miniproyecto4.models.ConceptoDeDevengo;
import co.edu.univalle.miniproyecto4.models.ConfiguracionDeEmpresa;
import co.edu.univalle.miniproyecto4.models.Empleado;
import co.edu.univalle.miniproyecto4.models.Eps;
import co.edu.univalle.miniproyecto4.models.FondoDePension;
import co.edu.univalle.miniproyecto4.models.Ingenio;
import co.edu.univalle.miniproyecto4.util.PairClassUtil;
import co.edu.univalle.miniproyecto4.util.SerializationUtil;
import co.edu.univalle.miniproyecto4.util.TextReaderUtil;
import co.edu.univalle.miniproyecto4.views.ViewFormulario;
import co.edu.univalle.miniproyecto4.views.ViewLiquidacion;

public class ViewFormularioController {
  private ViewFormulario vista;
  private Ingenio ingenio;
  private List listaMap;
  private List<Integer> codEmpleados;
  private DefaultTableModel modeloTabla;
  private String apartadoFormulario = "";
  private int index;
  private static String fechaMaxima = "2099-12-31";

  public ViewFormularioController(ViewFormulario vista, Ingenio ingenio) {
    this.vista = vista;
    this.ingenio = ingenio;
    modeloTabla = new DefaultTableModel() {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    listaMap = new ArrayList<Map.Entry>();
    codEmpleados = new ArrayList<>();

    cargarMapasDAOS();

    ActionsHandler manejadorDeActionEvents = new ActionsHandler();
    ListSelectionHandler manejadorDeSelectionEvents = new ListSelectionHandler();
    vista.addListener(manejadorDeActionEvents);

    vista.getTablaDatos().getSelectionModel().addListSelectionListener(manejadorDeSelectionEvents);

    vista.getFildEmpleadoCod().setEnabled(false);
    vista.getFildEPSCod().setEnabled(false);
    vista.getFildARLcod().setEnabled(false);
    vista.getFildFPPcod().setEnabled(false);
    vista.getFildCajaComCodigo().setEnabled(false);
    vista.getFildDevengoCodigo().setEnabled(false);
    vista.getFildDeduccionCodigo().setEnabled(false);

    vista.mostrartitle(vista.getJpTittleEmpresa());
    vista.mostrarPanel(vista.getJpempresa());
    apartadoFormulario = "Empresa";
    vista.getTablaDatos().setModel(actualizarTableModelString(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa()));
    AuxController.popularNombreComboBox(vista.getDropCodARLEMPRESA(), ingenio.getArlDAO().getMapArl());
    AuxController.popularNombreComboBox(vista.getDropCodCajaCom(), ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion());

    vista.addWindowListener(new WindowAdapter() {
      @Override
      public void windowClosing(WindowEvent evt) {
        cerrarAplicacion();
      }
    });
  }

  /* --------------- EJECUCION: CARGA MAPAS (SERIALIZADOS) ------------------- */
  public boolean cargarMapasDAOS() {
    if(SerializationUtil.isSerializedObjectExists("BackUp/Empleado.bin")) {
      ingenio.getEmpleadoDAO().setMapEmpleado((Map<Integer, Empleado>) SerializationUtil.deserializeObject("BackUp/Empleado.bin"));
    }
    else {
      TextReaderUtil.instanciarEmpleadosTxt("BackUp/CONTR9999Empleados.txt", ingenio.getEmpleadoDAO());
    }
    if(SerializationUtil.isSerializedObjectExists("BackUp/Eps.bin")) {
      ingenio.getEpsDAO().setMapEps((Map<Integer, Eps>) SerializationUtil.deserializeObject("BackUp/Eps.bin"));
    }
    if(SerializationUtil.isSerializedObjectExists("BackUp/Arl.bin")) {
      ingenio.getArlDAO().setMapArl((Map<Integer, Arl>) SerializationUtil.deserializeObject("BackUp/Arl.bin"));
    }
    if(SerializationUtil.isSerializedObjectExists("BackUp/FPP.bin")) {
      ingenio.getFondoDePensionDAO().setMapFondoDePension((Map<Integer, FondoDePension>) SerializationUtil.deserializeObject("BackUp/FPP.bin"));
    }
    if(SerializationUtil.isSerializedObjectExists("BackUp/CEmpresa.bin")) {
      ingenio.getConfiguracionDeEmpresaDAO().setMapConfiguracionDeEmpresa((Map<String, ConfiguracionDeEmpresa>) SerializationUtil.deserializeObject("BackUp/CEmpresa.bin"));
    }
    if(SerializationUtil.isSerializedObjectExists("BackUp/CCompensacion.bin")) {
      ingenio.getCajaDeCompensacionDAO().setMapCajaDeCompensacion((Map<Integer, CajaDeCompensacion>) SerializationUtil.deserializeObject("BackUp/CCompensacion.bin"));
    }
    if(SerializationUtil.isSerializedObjectExists("BackUp/CDevengo.bin")) {
      ingenio.getConceptoDeDevengoDAO().setMapConceptoDeDevengo((Map<Integer, ConceptoDeDevengo>) SerializationUtil.deserializeObject("BackUp/CDevengo.bin"));
    }
    if(SerializationUtil.isSerializedObjectExists("BackUp/CDeduccion.bin")) {
      ingenio.getConceptoDeDeduccionDAO().setMapConceptoDeDeduccion((Map<Integer, ConceptoDeDeduccion>) SerializationUtil.deserializeObject("BackUp/CDeduccion.bin"));
    }
    if(SerializationUtil.isSerializedObjectExists("BackUp/MapaConfigDev.bin")) {
      ingenio.setMapConfigDevengos((Map<Integer, PairClassUtil>) SerializationUtil.deserializeObject("BackUp/MapaConfigDev.bin"));
    }
    if(SerializationUtil.isSerializedObjectExists("BackUp/MapaConfigDed.bin")) {
      ingenio.setMapConfigDeducciones((Map<Integer, PairClassUtil>) SerializationUtil.deserializeObject("BackUp/MapaConfigDed.bin"));
    }
    return true;
  }

  /* --------------- EJECUCION: GUARDA MAPAS (SERIALIZADOS)  ------------------- */
  public void guardarMapasDAOS() {
    if(!ingenio.getEmpleadoDAO().getMapEmpleado().isEmpty()) {
      SerializationUtil.serializeObject(ingenio.getEmpleadoDAO().getMapEmpleado(), "BackUp/Empleado.bin");
    }
    if(!ingenio.getEpsDAO().getMapEps().isEmpty()) {
      SerializationUtil.serializeObject(ingenio.getEpsDAO().getMapEps(), "BackUp/Eps.bin");
    }
    if(!ingenio.getArlDAO().getMapArl().isEmpty()) {
      SerializationUtil.serializeObject(ingenio.getArlDAO().getMapArl(), "BackUp/Arl.bin");
    }
    if(!ingenio.getFondoDePensionDAO().getMapFondoDePension().isEmpty()) {
      SerializationUtil.serializeObject(ingenio.getFondoDePensionDAO().getMapFondoDePension(), "BackUp/FPP.bin");
    }
    if(!ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().isEmpty()) {
      SerializationUtil.serializeObject(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa(), "BackUp/CEmpresa.bin");
    }
    if(!ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion().isEmpty()) {
      SerializationUtil.serializeObject(ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion(), "BackUp/CCompensacion.bin");
    }
    if(!ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo().isEmpty()) {
      SerializationUtil.serializeObject(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo(), "BackUp/CDevengo.bin");
    }
    if(!ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion().isEmpty()) {
      SerializationUtil.serializeObject(ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion(), "BackUp/CDeduccion.bin");
    }
    if(!ingenio.getMapConfigDevengos().isEmpty()) {
      SerializationUtil.serializeObject(ingenio.getMapConfigDevengos(), "BackUp/MapaConfigDev.bin");
    }
    if(!ingenio.getMapConfigDeducciones().isEmpty()) {
      SerializationUtil.serializeObject(ingenio.getMapConfigDeducciones(), "BackUp/MapaConfigDed.bin");
    }
  }

  /* --------------- EJECUCION: EJECUTA INSTRUCCIONES DE CIERRE ------------------- */
  private void cerrarAplicacion(){
        int respuesta;

        respuesta = JOptionPane.showConfirmDialog(
                null,"¿Esta seguro que desea salir?", "Advertencia",
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.WARNING_MESSAGE);

        if(respuesta == JOptionPane.YES_OPTION){
            guardarMapasDAOS();
            System.exit(0);
        }
    }

  
  /* --------------- MUESTREO: ACTUALIZAR MODELO TABLA ------------------- */
  public <T> DefaultTableModel actualizarTableModelInt(Map<Integer, T> mapa) {
    List<Object> listaTemporal = new ArrayList<>();

    modeloTabla.setRowCount(0);
    establecerIdentificadoresColumnas(modeloTabla);
    listaMap.clear();

    if(!mapa.isEmpty()) {
      Set<Map.Entry<Integer, T>> entrySetMapa = mapa.entrySet();

      listaMap = new ArrayList<>(mapa.entrySet());

      for (Map.Entry<Integer, T> entry : entrySetMapa){
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
      // TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroModelo.txt", mapa);
      return modeloTabla;
    }
    else {
      return modeloTabla;
    }
  }

  /* --------------- MUESTREO: ACTUALIZAR MODELO TABLA ------------------- */
  public <T> DefaultTableModel actualizarTableModelString(Map<String, T> mapa) {
    List<Object> listaTemporal = new ArrayList<>();

    modeloTabla.setRowCount(0);
    listaMap.clear();
    establecerIdentificadoresColumnas(modeloTabla);

    if(!mapa.isEmpty()) {
      Set<Map.Entry<String, T>> entrySetMapa = mapa.entrySet();

      listaMap = new ArrayList<>(mapa.entrySet());

      for (Map.Entry<String, T> entry : entrySetMapa){
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
      // TextReaderUtil.printInformacionModeloKeyStr("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroModelo.txt", mapa);
      return modeloTabla;
    }
    else {
      return modeloTabla;
    }
  }  

  /* --------------- MUESTREO: INICIALIZA MODELOTABLA CON IDENTIFICADORES ------------------- */
  public void establecerIdentificadoresColumnas(DefaultTableModel modelo) {
    if(apartadoFormulario.equals("Empleado")) {
      String[] atributosTabla = {"ID", "COD", "APELLIDOS", "NOMBRES", "DIRECCIÓN", "COD. EPS", "COD. FPP", "FECHA NAC.", "FECHA ING.", "FECHA RET.", "TIPO TRAB.", "NÚM. CUENTA"};
      modelo.setColumnIdentifiers(atributosTabla);
    }
    else if(apartadoFormulario.equals("Eps")) {
      String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
      modelo.setColumnIdentifiers(atributosTabla);
    }
    else if(apartadoFormulario.equals("FPP")) {
      String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
      modelo.setColumnIdentifiers(atributosTabla);
    }
    else if(apartadoFormulario.equals("ARL")) {
      String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
      modelo.setColumnIdentifiers(atributosTabla);
    }
    else if(apartadoFormulario.equals("CCompensacion")) {
      String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
      modelo.setColumnIdentifiers(atributosTabla);
    }
    else if(apartadoFormulario.equals("Empresa")) {
      String[] atributosTabla = {"NIT", "NOMBRE", "TELÉFONO", "DIRECCIÓN", "REP. LEGAL", "CORREO CONT.", "CÓD. ARL", "CÓD. CAJA", "SMLV", "AUX. TRANSP."};
      modelo.setColumnIdentifiers(atributosTabla);
    }
    else if(apartadoFormulario.equals("Devengo")) {
      String[] atributosTabla = {"CÓDIGO", "NOMBRE", "HACE BASE"};
      modelo.setColumnIdentifiers(atributosTabla);
    }
    else if(apartadoFormulario.equals("Deduccion")) {
      String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
      modelo.setColumnIdentifiers(atributosTabla);
    }
  }

  /* --------------- MUESTREO: LLENA CAMPOS DEPENDIENDO EL APARTADOFORMULARIO ACTUAL ------------------- */
  public void llenarCamposFormulario() {
    index = vista.getTablaDatos().getSelectedRow();
    if(apartadoFormulario.equals("Empleado") && (index != -1)) {
      Map.Entry<Integer, Empleado> entry = (Map.Entry<Integer, Empleado>) listaMap.get(index);
      vista.getFildEmpleadoApellido().setText(entry.getValue().getApellido());
      vista.getFildEmpleadoCod().setText(entry.getValue().getCodigo()+"");
      vista.getFildEmpleadoDateIngr().setText(AuxController.fechaToString(entry.getValue().getFechaDeIngreso()));
      vista.getFildEmpleadoDateN().setText(AuxController.fechaToString(entry.getValue().getFechaDeNacimiento()));
      vista.getFildEmpleadoDateRet().setText(AuxController.fechaToString(entry.getValue().getFechaDeRetiro()));
      vista.getFildEmpleadoDr().setText(entry.getValue().getDireccion());
      vista.getFildEmpleadoId().setText(entry.getValue().getIdentificacion());
      vista.getFildEmpleadoNCuenta().setText(entry.getValue().getNumeroDeCuenta());
      vista.getFildEmpleadoNombre().setText(entry.getValue().getNombre());
      vista.getDropTipoEmpleado().setSelectedIndex(entry.getValue().getTipoDeTrabajador());
      AuxController.popularNombreComboBox(vista.getDropEpsEmpleado(), ingenio.getEpsDAO().getMapEps().get(entry.getValue().getCodigoEps()).getNombre());
      AuxController.popularNombreComboBox(vista.getDropFppEmpleado(), ingenio.getFondoDePensionDAO().getMapFondoDePension().get(entry.getValue().getCodigoFpp()).getNombre());
      vista.getBtnAñadir().setEnabled(false);
    }
    else if(apartadoFormulario.equals("Eps") && (index != -1)) {
      Map.Entry<Integer, Eps> entry = (Map.Entry<Integer, Eps>) listaMap.get(index);
      vista.getFildEPSCod().setText(entry.getValue().getCodigo() + "");
      vista.getFildEPSNombre().setText(entry.getValue().getNombre());
      vista.getBtnAñadir().setEnabled(false);
    }
    else if(apartadoFormulario.equals("FPP") && (index != -1)) {
      Map.Entry<Integer, FondoDePension> entry = (Map.Entry<Integer, FondoDePension>) listaMap.get(index);
      vista.getFildFPPcod().setText(entry.getValue().getCodigo() + "");
      vista.getFildFPPnombre().setText(entry.getValue().getNombre());
      vista.getBtnAñadir().setEnabled(false);
    }
    else if(apartadoFormulario.equals("ARL") && (index != -1)) {
      Map.Entry<Integer, Arl> entry = (Map.Entry<Integer, Arl>) listaMap.get(index);
      vista.getFildARLcod().setText(entry.getValue().getCodigo() + "");
      vista.getFildARLnombre().setText(entry.getValue().getNombre());
      vista.getBtnAñadir().setEnabled(false);
    }
    else if(apartadoFormulario.equals("CCompensacion") && (index != -1)) {
      Map.Entry<Integer, CajaDeCompensacion> entry = (Map.Entry<Integer, CajaDeCompensacion>) listaMap.get(index);
      vista.getFildCajaComCodigo().setText(entry.getValue().getCodigo() + "");
      vista.getFildCajaComNombre().setText(entry.getValue().getNombre());
      vista.getBtnAñadir().setEnabled(false);
    }
    else if(apartadoFormulario.equals("Empresa") && (index != -1)) {
      Map.Entry<Integer, ConfiguracionDeEmpresa> entry = (Map.Entry<Integer, ConfiguracionDeEmpresa>) listaMap.get(index);
      vista.getFildEmpresaAuxTrans().setText(entry.getValue().getAuxilioDeTransporte());
      vista.getFildEmpresaCorreo().setText(entry.getValue().getCorreoDeContacto());
      vista.getFildEmpresaDireccion().setText(entry.getValue().getdireccion());
      vista.getFildEmpresaNit().setText(entry.getValue().getNit());
      vista.getFildEmpresaNombre().setText(entry.getValue().getNombre());
      vista.getFildEmpresaRepre().setText(entry.getValue().getrepresentanteLegal());
      vista.getFildEmpresaSalariomin().setText(entry.getValue().getSalarioMínimoLegalVigente());
      vista.getFildEmpresaTelefono().setText(entry.getValue().getTelefono());
      AuxController.popularNombreComboBox(vista.getDropCodARLEMPRESA(), ingenio.getArlDAO().getMapArl().get(entry.getValue().getCodigoArl()).getNombre());
      AuxController.popularNombreComboBox(vista.getDropCodCajaCom(), ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion().get(entry.getValue().getCodigoCajaDeCompensación()).getNombre());
      vista.getBtnAñadir().setEnabled(false);
    }
    else if(apartadoFormulario.equals("Devengo") && (index != -1)) {
      Map.Entry<Integer, ConceptoDeDevengo> entry = (Map.Entry<Integer, ConceptoDeDevengo>) listaMap.get(index);
      int haceBase = 0;
      if(entry.getValue().isHaceBase()) {
        haceBase = 1;
      }
      else {
        haceBase = 2;
      }
      vista.getFildDevengoCodigo().setText(entry.getValue().getCodigo() + "");
      vista.getFildDevengonombre().setText(entry.getValue().getNombre());
      vista.getFildDevengoValor().setText(ingenio.getMapConfigDevengos().get(entry.getKey()).getSecond()+"");
      vista.getDropbaseDevengo().setSelectedIndex(haceBase);
      vista.getDropDevengoEmpleado().setSelectedIndex(0);
      vista.getBtnAñadir().setEnabled(false);
    }
    else if(apartadoFormulario.equals("Deduccion") && (index != -1)) {
      Map.Entry<Integer, ConceptoDeDeduccion> entry = (Map.Entry<Integer, ConceptoDeDeduccion>) listaMap.get(index);
      vista.getFildDeduccionCodigo().setText(entry.getValue().getCodigo() + "");
      vista.getFildDeduccionNombre().setText(entry.getValue().getNombre());
      vista.getFildDeduccionValor().setText(ingenio.getMapConfigDeducciones().get(entry.getKey()).getSecond()+"");
      vista.getDropDeduccionEmpleado().setSelectedIndex(0);
      vista.getBtnAñadir().setEnabled(false);
    }
  }

  /* --------------- VERIFICACION: VERIFICA QUE LOS CAMPOS PERTINENTES NO ESTÉN VACÍOS ------------------- */
  private boolean verificarCampos(){
    if(apartadoFormulario.equals("Empleado")) {
      if(vista.getFildEmpleadoApellido().getText().isEmpty() ||
          // vista.getFildEmpleadoCod().getText().isEmpty() ||
          // vista.getFildEmpleadoDateIngr().getText().isEmpty() ||
          vista.getFildEmpleadoDateN().getText().isEmpty() ||
          // vista.getFildEmpleadoDateRet().getText().isEmpty() ||
          vista.getFildEmpleadoDr().getText().isEmpty() ||
          vista.getFildEmpleadoId().getText().isEmpty() ||
          vista.getFildEmpleadoNCuenta().getText().isEmpty() ||
          vista.getFildEmpleadoNombre().getText().isEmpty() ||
          vista.getDropEpsEmpleado().getSelectedIndex() == 0 ||
          vista.getDropFppEmpleado().getSelectedIndex() == 0 ||
          vista.getDropTipoEmpleado().getSelectedIndex() == 0) {
        return false;
      }
    }
    else if(apartadoFormulario.equals("Eps")) {
      if(vista.getFildEPSNombre().getText().isEmpty()) {
        return false;
      }
    }
    else if(apartadoFormulario.equals("FPP")) {
      if(vista.getFildFPPnombre().getText().isEmpty()) {
        return false;
      }
    }
    else if(apartadoFormulario.equals("ARL")) {
      if(vista.getFildARLnombre().getText().isEmpty()) {
        return false;
      }    
    }
    else if(apartadoFormulario.equals("CCompensacion")) {
      if(vista.getFildCajaComNombre().getText().isEmpty()) {
        return false;
      }
    }
    else if(apartadoFormulario.equals("Empresa")) {
      if(vista.getFildEmpresaAuxTrans().getText().isEmpty() ||
          vista.getFildEmpresaCorreo().getText().isEmpty() ||
          vista.getFildEmpresaDireccion().getText().isEmpty() ||
          vista.getFildEmpresaNit().getText().isEmpty() ||
          vista.getFildEmpresaNombre().getText().isEmpty() ||
          vista.getFildEmpresaRepre().getText().isEmpty() ||
          vista.getFildEmpresaSalariomin().getText().isEmpty() ||
          vista.getFildEmpresaTelefono().getText().isEmpty() ||
          vista.getDropCodARLEMPRESA().getSelectedIndex() == 0 ||
          vista.getDropCodCajaCom().getSelectedIndex() == 0) {
        return false;
      }
    }
    else if(apartadoFormulario.equals("Devengo")) {
      if(vista.getFildDevengonombre().getText().isEmpty() ||
          vista.getFildDevengoValor().getText().isEmpty() ||
          // vista.getDropDevengoEmpleado().getSelectedIndex() == 0 ||
          vista.getDropbaseDevengo().getSelectedIndex() == 0) {
        return false;
      }
    }
    else if(apartadoFormulario.equals("Deduccion")) {
      if(vista.getFildDeduccionNombre().getText().isEmpty() ||
          vista.getFildDeduccionValor().getText().isEmpty()) {
        return false;
      }
    }
    return true;
  }

  /* --------------- MUESTREO: RETORNA LOS VALORES DE LOS CAMPOS A SUS VALORES POR DEFECTO ------------------- */
  private void limpiarCampos() {
    if(apartadoFormulario.equals("Empleado")) {
      vista.getFildEmpleadoApellido().setText("");
      vista.getFildEmpleadoCod().setText("");
      vista.getFildEmpleadoDateIngr().setText("AAAA-MM-DD");
      vista.getFildEmpleadoDateN().setText("AAAA-MM-DD");
      vista.getFildEmpleadoDateRet().setText("AAAA-MM-DD");
      vista.getFildEmpleadoDr().setText("");
      vista.getFildEmpleadoId().setText("");
      vista.getFildEmpleadoNCuenta().setText("");
      vista.getFildEmpleadoNombre().setText("");
      vista.getDropEpsEmpleado().setSelectedIndex(0);
      vista.getDropFppEmpleado().setSelectedIndex(0);
      vista.getDropTipoEmpleado().setSelectedIndex(0);
    }
    else if(apartadoFormulario.equals("Eps")) {
      vista.getFildEPSCod().setText("");
      vista.getFildEPSNombre().setText("");
    }
    else if(apartadoFormulario.equals("FPP")) {
      vista.getFildFPPcod().setText("");
      vista.getFildFPPnombre().setText("");
    }
    else if(apartadoFormulario.equals("ARL")) {
      vista.getFildARLcod().setText("");
      vista.getFildARLnombre().setText("");
    }
    else if(apartadoFormulario.equals("CCompensacion")) {
      vista.getFildCajaComCodigo().setText("");
      vista.getFildCajaComNombre().setText("");
    }
    else if(apartadoFormulario.equals("Empresa")) {
      vista.getFildEmpresaAuxTrans().setText("");
      vista.getFildEmpresaCorreo().setText("");
      vista.getFildEmpresaDireccion().setText("");
      vista.getFildEmpresaNit().setText("");
      vista.getFildEmpresaNombre().setText("");
      vista.getFildEmpresaRepre().setText("");
      vista.getFildEmpresaSalariomin().setText("");
      vista.getFildEmpresaTelefono().setText("");
      vista.getDropCodARLEMPRESA().setSelectedIndex(0);
      vista.getDropCodCajaCom().setSelectedIndex(0);
    }
    else if(apartadoFormulario.equals("Devengo")) {
      vista.getFildDevengoCodigo().setText("");
      vista.getFildDevengonombre().setText("");
      vista.getFildDevengoValor().setText("");
      vista.getDropbaseDevengo().setSelectedIndex(0);
      vista.getDropDevengoEmpleado().setSelectedIndex(0);
    }
    else if(apartadoFormulario.equals("Deduccion")) {
      vista.getFildDeduccionCodigo().setText("");
      vista.getFildDeduccionNombre().setText("");
      vista.getFildDeduccionValor().setText("");
    }
    vista.getBtnAñadir().setEnabled(true);
    vista.getTablaDatos().clearSelection();
  }

  /* --------------- UTILIDAD: CONVIERTE PORCENTAJE% A DECIMAL PARA SE ALMACENADO ------------------- */
  public float parsePorcentaje(String porcentaje) {
    if(porcentaje.contains("%")) {
      return Float.parseFloat(porcentaje.replaceAll("%", ""))/100;
    }
    return Float.parseFloat(porcentaje);
  }

  /* --------------- UTILIDAD: RETORNA MAPA DE LLAVE INTEGER SEGÚN APARTADOFORMULARIO ------------------- */
  private Map getMapIngenio() {
    if(apartadoFormulario.equals("Empleado")) {
      return ingenio.getEmpleadoDAO().getMapEmpleado();
    }
    else if(apartadoFormulario.equals("Eps")) {
      return ingenio.getEpsDAO().getMapEps();
    }
    else if(apartadoFormulario.equals("FPP")) {
      return ingenio.getFondoDePensionDAO().getMapFondoDePension();
    }
    else if(apartadoFormulario.equals("ARL")) {
      return ingenio.getArlDAO().getMapArl();
    }
    else if(apartadoFormulario.equals("CCompensacion")) {
      return ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion();
    }
    else if(apartadoFormulario.equals("Empresa")) {
      return ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa();
    }
    else if(apartadoFormulario.equals("Devengo")) {
      return ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo();
    }
    else if(apartadoFormulario.equals("Deduccion")) {
      return ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion();
    }
    return null;
  }

  /* --------------- CLASE LISTENER: MANEJADOR DE EVENTOS DE SELECCIÓN DE LISTA ------------------- */
  class ListSelectionHandler implements ListSelectionListener {

      @Override
      public void valueChanged(ListSelectionEvent e) {
          llenarCamposFormulario();
      }
  }

  /* --------------- CLASE LISTENER: MANEJADOR DE EVENTOS DE ACCIÓN ------------------- */
  class ActionsHandler implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
      if(e.getSource() == vista.getBtnEmpleado()) {
        apartadoFormulario = "Empleado";
        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEmpleadoDAO().getMapEmpleado()));
        AuxController.popularNombreComboBox(vista.getDropEpsEmpleado(), ingenio.getEpsDAO().getMapEps());
        AuxController.popularNombreComboBox(vista.getDropFppEmpleado(), ingenio.getFondoDePensionDAO().getMapFondoDePension());
        limpiarCampos();
      }
      else if(e.getSource() == vista.getBtnEps()) {
        apartadoFormulario = "Eps";
        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEpsDAO().getMapEps()));
        AuxController.hayNombresRepetidos(getMapIngenio());
        limpiarCampos();
      }
      else if(e.getSource() == vista.getBtnFondoP()) {
        apartadoFormulario = "FPP";
        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getFondoDePensionDAO().getMapFondoDePension()));
        AuxController.hayNombresRepetidos(getMapIngenio());
        limpiarCampos();
      }
      else if(e.getSource() == vista.getBtnARL()) {
        apartadoFormulario = "ARL";
        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getArlDAO().getMapArl()));
        AuxController.hayNombresRepetidos(getMapIngenio());
        limpiarCampos();
      }
      else if(e.getSource() == vista.getBtnCajaCompen()) {
        apartadoFormulario = "CCompensacion";
        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()));
        AuxController.hayNombresRepetidos(getMapIngenio());
        limpiarCampos();
      }
      else if(e.getSource() == vista.getBtnEmpresa()) {
        apartadoFormulario = "Empresa";
        vista.getTablaDatos().setModel(actualizarTableModelString(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa()));
        AuxController.popularNombreComboBox(vista.getDropCodARLEMPRESA(), ingenio.getArlDAO().getMapArl());
        AuxController.popularNombreComboBox(vista.getDropCodCajaCom(), ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion());
        limpiarCampos();
      }
      else if(e.getSource() == vista.getBtnDevegno()) {
        apartadoFormulario = "Devengo";
        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo()));
        codEmpleados = AuxController.popularNombreYApellidoComboBox(vista.getDropDevengoEmpleado(), ingenio.getEmpleadoDAO().getMapEmpleado());
        AuxController.hayNombresRepetidos(getMapIngenio());
        limpiarCampos();
      }
      else if(e.getSource() == vista.getBtnDeduccion()) {
        apartadoFormulario = "Deduccion";
        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion()));
        codEmpleados = AuxController.popularNombreYApellidoComboBox(vista.getDropDeduccionEmpleado(), ingenio.getEmpleadoDAO().getMapEmpleado());
        AuxController.hayNombresRepetidos(getMapIngenio());
        limpiarCampos();
      }
      else if(e.getSource() == vista.getBtnAñadir()) { // AÑADIR
        if(apartadoFormulario.equals("Empleado")) {
          if(AuxController.esNumerico(vista.getFildEmpleadoId().getText()) && AuxController.esNumerico(vista.getFildEmpleadoNCuenta().getText())) {
            if(verificarCampos()) {
              String fechaRetiro = vista.getFildEmpleadoDateRet().getText();
              if(fechaRetiro.isEmpty() || fechaRetiro.equals("AAAA-MM-DD")) {
                fechaRetiro = fechaMaxima;
              }
              ingenio.getEmpleadoDAO().addEmpleado(new Empleado(vista.getFildEmpleadoId().getText(), 
                vista.getFildEmpleadoApellido().getText(), 
                vista.getFildEmpleadoNombre().getText(), 
                vista.getFildEmpleadoDr().getText(), 
                AuxController.getCodByNombre((String) vista.getDropEpsEmpleado().getSelectedItem(), 
                ingenio.getEpsDAO().getMapEps()), 
                AuxController.getCodByNombre((String) vista.getDropFppEmpleado().getSelectedItem(), 
                ingenio.getFondoDePensionDAO().getMapFondoDePension()), 
                AuxController.crearFecha(vista.getFildEmpleadoDateN().getText()), 
                AuxController.crearFecha(vista.getFildEmpleadoDateIngr().getText()), 
                AuxController.crearFecha(fechaRetiro), 
                vista.getDropTipoEmpleado().getSelectedIndex(), 
                vista.getFildEmpleadoNCuenta().getText()));
                limpiarCampos();
              vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEmpleadoDAO().getMapEmpleado()));
            }
            else {
              AuxController.mensajeTemporal("Hay campos vacíos o desplegables sin selección.", "Error de entrada", 1150);
            }
          }
          else {
            AuxController.mensajeTemporal("Los números de identificación y de cuenta deben ser numéricos.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("Eps")) {
          if(verificarCampos()) {
            ingenio.getEpsDAO().addEps(new Eps(vista.getFildEPSNombre().getText()));
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEpsDAO().getMapEps()));
            limpiarCampos();
            AuxController.hayNombresRepetidos(getMapIngenio());
          }
          else {
            AuxController.mensajeTemporal("Hay campos vacíos.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("FPP")) {
          if(verificarCampos()) {
            ingenio.getFondoDePensionDAO().addFondoDePension(new FondoDePension(vista.getFildFPPnombre().getText()));
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getFondoDePensionDAO().getMapFondoDePension()));
            limpiarCampos();
            AuxController.hayNombresRepetidos(getMapIngenio());
          }
          else {
            AuxController.mensajeTemporal("Hay campos vacíos.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("ARL")) {
          if(verificarCampos()) {
            ingenio.getArlDAO().addArl(new Arl(vista.getFildARLnombre().getText()));
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getArlDAO().getMapArl()));
            limpiarCampos();
            AuxController.hayNombresRepetidos(getMapIngenio());
          }
          else {
            AuxController.mensajeTemporal("Hay campos vacíos.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("CCompensacion")) {
          if(verificarCampos()) {
            ingenio.getCajaDeCompensacionDAO().addCajaDeCompensacion(new CajaDeCompensacion(vista.getFildCajaComNombre().getText()));
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()));
            limpiarCampos();
            AuxController.hayNombresRepetidos(getMapIngenio());
          }
          else {
            AuxController.mensajeTemporal("Hay campos vacíos o desplegables sin selección.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("Empresa")) {
          if(AuxController.esNumerico(vista.getFildEmpresaNit().getText()) && AuxController.esNumerico(vista.getFildEmpresaTelefono().getText()) && AuxController.esNumerico(vista.getFildEmpresaSalariomin().getText()) && AuxController.esNumerico(vista.getFildEmpresaAuxTrans().getText())) {
            if(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().size() < 1) {
              if(verificarCampos()) {
                ingenio.getConfiguracionDeEmpresaDAO().addConfiguracionDeEmpresa(new ConfiguracionDeEmpresa(vista.getFildEmpresaNit().getText(), 
                  vista.getFildEmpresaNombre().getText(), 
                  vista.getFildEmpresaTelefono().getText(), 
                  vista.getFildEmpresaDireccion().getText(), 
                  vista.getFildEmpresaRepre().getText(), 
                  vista.getFildEmpresaCorreo().getText(), 
                  AuxController.getCodByNombre((String) vista.getDropCodARLEMPRESA().getSelectedItem(), ingenio.getArlDAO().getMapArl()), 
                  AuxController.getCodByNombre((String) vista.getDropCodCajaCom().getSelectedItem(), ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()), 
                  vista.getFildEmpresaSalariomin().getText(), 
                  vista.getFildEmpresaAuxTrans().getText()));

                vista.getTablaDatos().setModel(actualizarTableModelString(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa()));
                limpiarCampos();
              }
              else {
                AuxController.mensajeTemporal("Hay campos vacíos o desplegables sin selección.", "Error de entrada", 1150);
              }
            }
            else {
              AuxController.mensajeTemporal("Este sistema solo permite registrar la configuración de una empresa.", "Error de entrada", 1150);
            }
          }
          else {
            AuxController.mensajeTemporal("NIT, Teléfono, Salario Mínimo y Auxilio de Transporte deben ser numéricos.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("Devengo")) {
          if(verificarCampos()) {
            ConceptoDeDevengo conceptoDeDevengo = new ConceptoDeDevengo(vista.getFildDevengonombre().getText(), vista.getDropbaseDevengo().getSelectedIndex() == 1);
            ingenio.getConceptoDeDevengoDAO().addConceptoDeDevengo(conceptoDeDevengo);
            ingenio.addMapConfigDevengos(conceptoDeDevengo.getCodigo(), new PairClassUtil(codEmpleados.get(vista.getDropDevengoEmpleado().getSelectedIndex()), parsePorcentaje(vista.getFildDevengoValor().getText())));
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo()));
            limpiarCampos();
            AuxController.hayNombresRepetidos(getMapIngenio());
          } 
          else {
            AuxController.mensajeTemporal("Hay campos vacíos o desplegables sin selección.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("Deduccion")) {
          if(verificarCampos()) {
            ConceptoDeDeduccion conceptoDeDeduccion = new ConceptoDeDeduccion(vista.getFildDeduccionNombre().getText());
            ingenio.getConceptoDeDeduccionDAO().addConceptoDeDeduccion(conceptoDeDeduccion);
            ingenio.addMapConfigDeducciones(conceptoDeDeduccion.getCodigo(), new PairClassUtil(codEmpleados.get(vista.getDropDeduccionEmpleado().getSelectedIndex()), parsePorcentaje(vista.getFildDeduccionValor().getText())));
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion()));
            limpiarCampos();
            AuxController.hayNombresRepetidos(getMapIngenio());
          } 
          else {
            AuxController.mensajeTemporal("Hay campos vacíos o desplegables sin selección.", "Error de entrada", 1150);
          }
        }
      }
      else if(e.getSource() == vista.getBtnEliminar()) { // ELIMINAR
        if(apartadoFormulario.equals("Empleado")) {
          index = vista.getTablaDatos().getSelectedRow();

        if(index != -1) {
          Map.Entry<Integer, Empleado> entry = (Map.Entry<Integer, Empleado>) listaMap.get(index);
          ingenio.getEmpleadoDAO().deleteEmpleado((entry.getKey()));
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEmpleadoDAO().getMapEmpleado()));
          limpiarCampos();
        } else {
          AuxController.mensajeTemporal("Elija el usuario que desea eliminar", "Error", 1150);
        }
        }
        else if(apartadoFormulario.equals("Eps")) {
          index = vista.getTablaDatos().getSelectedRow();

          if(index != -1) {
            Map.Entry<Integer, Eps> entry = (Map.Entry<Integer, Eps>) listaMap.get(index);
            ingenio.getEpsDAO().deleteEps(entry.getKey());
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEpsDAO().getMapEps()));
            limpiarCampos();
          } else {
            AuxController.mensajeTemporal("Elija la eps que desea eliminar", "Error", 1150);
          }
        }
        else if(apartadoFormulario.equals("FPP")) {
          index = vista.getTablaDatos().getSelectedRow();

          if(index != -1) {
            Map.Entry<Integer, FondoDePension> entry = (Map.Entry<Integer, FondoDePension>) listaMap.get(index);
            ingenio.getFondoDePensionDAO().deleteFondoDePension(entry.getKey());
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getFondoDePensionDAO().getMapFondoDePension()));
            limpiarCampos();
          } else {
            AuxController.mensajeTemporal("Elija el fondo de pension que desea eliminar", "Error", 1150);
          }
        }
        else if(apartadoFormulario.equals("ARL")) {
          index = vista.getTablaDatos().getSelectedRow();

          if(index != -1) {
            Map.Entry<Integer, Arl> entry = (Map.Entry<Integer, Arl>) listaMap.get(index);
            ingenio.getArlDAO().deleteArl((entry.getKey()));
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getArlDAO().getMapArl()));
            limpiarCampos();
          } else {
            AuxController.mensajeTemporal("Elija la arl que desea eliminar", "Error", 1150);
          }
        }
        else if(apartadoFormulario.equals("CCompensacion")) {
          index = vista.getTablaDatos().getSelectedRow();

          if(index != -1) {
            Map.Entry<Integer, CajaDeCompensacion> entry = (Map.Entry<Integer, CajaDeCompensacion>) listaMap.get(index);
            ingenio.getCajaDeCompensacionDAO().deleteCajaDeCompensacion((entry.getKey()));
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()));
            limpiarCampos();  
          } else {
            AuxController.mensajeTemporal("Elija la caja de compensacion que desea eliminar", "Error", 1150);
          }
        }
        else if(apartadoFormulario.equals("Empresa")) {
          index = vista.getTablaDatos().getSelectedRow();

          if(index != -1) {
            Map.Entry<String, ConfiguracionDeEmpresa> entry = (Map.Entry<String, ConfiguracionDeEmpresa>) listaMap.get(index);
            ingenio.getConfiguracionDeEmpresaDAO().deleteConfiguracionDeEmpresa(entry.getKey());
            vista.getTablaDatos().setModel(actualizarTableModelString(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa()));
            limpiarCampos();
          } else {
            AuxController.mensajeTemporal("Elija la empresa que desea eliminar", "Error", 1150);
          }
        }
        else if(apartadoFormulario.equals("Devengo")) {
          index = vista.getTablaDatos().getSelectedRow();

          if(index != -1) {
            Map.Entry<Integer, ConceptoDeDevengo> entry = (Map.Entry<Integer, ConceptoDeDevengo>) listaMap.get(index);
            ingenio.getConceptoDeDevengoDAO().deleteConceptoDeDevengo((entry.getKey()));
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo()));
            limpiarCampos();
          } else {
            AuxController.mensajeTemporal("Elija la arl que desea eliminar", "Error", 1150);
          }
        }
        else if(apartadoFormulario.equals("Deduccion")) {
          index = vista.getTablaDatos().getSelectedRow();

          if(index != -1) {
            Map.Entry<Integer, ConceptoDeDeduccion> entry = (Map.Entry<Integer, ConceptoDeDeduccion>) listaMap.get(index);
            ingenio.getConceptoDeDeduccionDAO().deleteConceptoDeDeduccion((entry.getKey()));
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion()));
            limpiarCampos();
          } else {
            AuxController.mensajeTemporal("Elija la arl que desea eliminar", "Error", 1150);
          }
        }
      }
      else if(e.getSource() == vista.getBtnEditar()) { // EDITAR
        if(apartadoFormulario.equals("Empleado")) {
          index = vista.getTablaDatos().getSelectedRow();
          Map.Entry<Integer, Empleado> entry = (Map.Entry<Integer, Empleado>) listaMap.get(index);
          if(AuxController.esNumerico(vista.getFildEmpleadoId().getText())) {
            if(verificarCampos()) {
              String fechaRetiro = vista.getFildEmpleadoDateRet().getText();
              if(fechaRetiro.isEmpty()) {
                fechaRetiro = fechaMaxima;
              }
              ingenio.getEmpleadoDAO().getMapEmpleado().get(entry.getKey()).setIdentificacion(vista.getFildEmpleadoId().getText());
              ingenio.getEmpleadoDAO().getMapEmpleado().get(entry.getKey()).setApellidos(vista.getFildEmpleadoApellido().getText());
              ingenio.getEmpleadoDAO().getMapEmpleado().get(entry.getKey()).setNombre(vista.getFildEmpleadoNombre().getText());
              ingenio.getEmpleadoDAO().getMapEmpleado().get(entry.getKey()).setDireccion(vista.getFildEmpleadoDr().getText());
              ingenio.getEmpleadoDAO().getMapEmpleado().get(entry.getKey()).setCodigoEps(AuxController.getCodByNombre((String) vista.getDropEpsEmpleado().getSelectedItem(), ingenio.getEpsDAO().getMapEps()));
              ingenio.getEmpleadoDAO().getMapEmpleado().get(entry.getKey()).setCodigoFpp(AuxController.getCodByNombre((String) vista.getDropFppEmpleado().getSelectedItem(), ingenio.getFondoDePensionDAO().getMapFondoDePension()));
              ingenio.getEmpleadoDAO().getMapEmpleado().get(entry.getKey()).setFechaDeNacimiento(AuxController.crearFecha(vista.getFildEmpleadoDateN().getText()));
              ingenio.getEmpleadoDAO().getMapEmpleado().get(entry.getKey()).setFechaDeIngreso(AuxController.crearFecha(vista.getFildEmpleadoDateIngr().getText()));
              ingenio.getEmpleadoDAO().getMapEmpleado().get(entry.getKey()).setFechaDeRetiro(AuxController.crearFecha(fechaRetiro));
              ingenio.getEmpleadoDAO().getMapEmpleado().get(entry.getKey()).setTipoDeTrabajador(vista.getDropTipoEmpleado().getSelectedIndex());
              ingenio.getEmpleadoDAO().getMapEmpleado().get(entry.getKey()).setNumeroDeCuenta(vista.getFildEmpleadoNCuenta().getText());
              limpiarCampos();
              vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEmpleadoDAO().getMapEmpleado()));
              vista.getBtnAñadir().setEnabled(true);
            } 
            else {
              AuxController.mensajeTemporal("Hay campos vacíos o desplegables sin selección.", "Error de entrada", 1150);
            }
          }
          else {
            AuxController.mensajeTemporal("Los números de identificación y de cuenta deben ser numéricos.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("Eps")) {
          index = vista.getTablaDatos().getSelectedRow();
          Map.Entry<Integer, Eps> entry = (Map.Entry<Integer, Eps>) listaMap.get(index);
          if(verificarCampos()) {
            ingenio.getEpsDAO().getMapEps().get(entry.getKey()).setNombre(vista.getFildEPSNombre().getText());;
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEpsDAO().getMapEps()));
            limpiarCampos();
            vista.getBtnAñadir().setEnabled(true);
            AuxController.hayNombresRepetidos(getMapIngenio());
          } 
          else {
            AuxController.mensajeTemporal("Hay campos vacíos o desplegables sin selección.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("FPP")) {
          index = vista.getTablaDatos().getSelectedRow();
          Map.Entry<Integer, FondoDePension> entry = (Map.Entry<Integer, FondoDePension>) listaMap.get(index);
          if(verificarCampos()) {
            ingenio.getFondoDePensionDAO().getMapFondoDePension().get(entry.getKey()).setNombre(vista.getFildFPPnombre().getText());
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getFondoDePensionDAO().getMapFondoDePension()));
            limpiarCampos();
            vista.getBtnAñadir().setEnabled(true);
            AuxController.hayNombresRepetidos(getMapIngenio());
          } 
          else {
            AuxController.mensajeTemporal("Hay campos vacíos o desplegables sin selección.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("ARL")) {
          index = vista.getTablaDatos().getSelectedRow();
          Map.Entry<Integer, Arl> entry = (Map.Entry<Integer, Arl>) listaMap.get(index);
          if(verificarCampos()) {
            ingenio.getArlDAO().getMapArl().get(entry.getKey()).setNombre(vista.getFildARLnombre().getText());
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getArlDAO().getMapArl()));
            limpiarCampos();
            vista.getBtnAñadir().setEnabled(true);
            AuxController.hayNombresRepetidos(getMapIngenio());
          } 
          else {
            AuxController.mensajeTemporal("Hay campos vacíos o desplegables sin selección.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("CCompensacion")) {
          index = vista.getTablaDatos().getSelectedRow();
          Map.Entry<Integer, CajaDeCompensacion> entry = (Map.Entry<Integer, CajaDeCompensacion>) listaMap.get(index);
          if(verificarCampos()) {
            ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion().get(entry.getKey()).setNombre(vista.getFildCajaComNombre().getText());
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()));
            limpiarCampos();
            vista.getBtnAñadir().setEnabled(true);
            AuxController.hayNombresRepetidos(getMapIngenio());
          } 
          else {
            AuxController.mensajeTemporal("Hay campos vacíos o desplegables sin selección.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("Empresa")) {
          index = vista.getTablaDatos().getSelectedRow();
          Map.Entry<Integer, ConfiguracionDeEmpresa> entry = (Map.Entry<Integer, ConfiguracionDeEmpresa>) listaMap.get(index);
          if(AuxController.esNumerico(vista.getFildEmpresaNit().getText()) && AuxController.esNumerico(vista.getFildEmpresaTelefono().getText()) && AuxController.esNumerico(vista.getFildEmpresaSalariomin().getText()) && AuxController.esNumerico(vista.getFildEmpresaAuxTrans().getText())) {
            if(verificarCampos()) {
              ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(entry.getKey()).setNit(vista.getFildEmpresaNit().getText());
              ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(entry.getKey()).setNombre(vista.getFildEmpresaNombre().getText());
              ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(entry.getKey()).setTelefono(vista.getFildEmpresaTelefono().getText());
              ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(entry.getKey()).setDireccion(vista.getFildEmpresaDireccion().getText());
              ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(entry.getKey()).setRepresentanteLegal(vista.getFildEmpresaRepre().getText());
              ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(entry.getKey()).setCorreoDeContacto(vista.getFildEmpresaCorreo().getText());
              ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(entry.getKey()).setCodigoArl(AuxController.getCodByNombre((String) vista.getDropCodARLEMPRESA().getSelectedItem(), ingenio.getArlDAO().getMapArl()));
              ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(entry.getKey()).setCodigoCajaDeCompensación(AuxController.getCodByNombre((String) vista.getDropCodCajaCom().getSelectedItem(), ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()));
              ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(entry.getKey()).setSalarioMínimoLegalVigente(vista.getFildEmpresaSalariomin().getText());
              ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(entry.getKey()).setAuxilioDeTransporte(vista.getFildEmpresaAuxTrans().getText());

              vista.getTablaDatos().setModel(actualizarTableModelString(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa()));
              limpiarCampos();
              vista.getBtnAñadir().setEnabled(true);
            } 
            else {
              AuxController.mensajeTemporal("Hay campos vacíos o desplegables sin selección.", "Error de entrada", 1150);
            }
          }
          else {
            AuxController.mensajeTemporal("NIT, Teléfono, Salario Mínimo y Auxilio de Transporte deben ser numéricos.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("Devengo")) {
          index = vista.getTablaDatos().getSelectedRow();
          Map.Entry<Integer, ConceptoDeDevengo> entry = (Map.Entry<Integer, ConceptoDeDevengo>) listaMap.get(index);
          if(verificarCampos()) {
            ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo().get(entry.getKey()).setNombre(vista.getFildDevengonombre().getText());
            ingenio.getMapConfigDevengos().get(entry.getKey()).setSecond(parsePorcentaje(vista.getFildDevengoValor().getText()));
            ingenio.getMapConfigDevengos().get(entry.getKey()).setFirst(codEmpleados.get(vista.getDropDevengoEmpleado().getSelectedIndex()));
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo()));
            limpiarCampos();
            vista.getBtnAñadir().setEnabled(true);
            AuxController.hayNombresRepetidos(getMapIngenio());
          } 
          else {
            AuxController.mensajeTemporal("Hay campos vacíos o desplegables sin selección.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("Deduccion")) {
          index = vista.getTablaDatos().getSelectedRow();
          Map.Entry<Integer, ConceptoDeDeduccion> entry = (Map.Entry<Integer, ConceptoDeDeduccion>) listaMap.get(index);
          if(verificarCampos()) {
            ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion().get(entry.getKey()).setNombre(vista.getFildDeduccionNombre().getText());
            ingenio.getMapConfigDeducciones().get(entry.getKey()).setSecond(parsePorcentaje(vista.getFildDeduccionValor().getText()));
            ingenio.getMapConfigDeducciones().get(entry.getKey()).setFirst(codEmpleados.get(vista.getDropDeduccionEmpleado().getSelectedIndex()));
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion()));
            limpiarCampos();
            vista.getBtnAñadir().setEnabled(true);
            AuxController.hayNombresRepetidos(getMapIngenio());
          } 
          else {
            AuxController.mensajeTemporal("Hay campos vacíos o desplegables sin selección.", "Error de entrada", 1150);
          }
        }
      }
      else if(e.getSource() == vista.getBtnLimpiar()) { // LIMPIAR
          vista.getTablaDatos().clearSelection();
          if(apartadoFormulario.equals("Empleado")) {
              limpiarCampos();
          }
          else if(apartadoFormulario.equals("Eps")) {
              limpiarCampos();
          }
          else if(apartadoFormulario.equals("FPP")) {
              limpiarCampos();
          }
          else if(apartadoFormulario.equals("ARL")) {
              limpiarCampos();
          }
          else if(apartadoFormulario.equals("CCompensacion")) {
              limpiarCampos();
          }
          else if(apartadoFormulario.equals("Empresa")) {
              limpiarCampos();
          }
          else if(apartadoFormulario.equals("Devengo")) {
              limpiarCampos();
          }
          else if(apartadoFormulario.equals("Deduccion")) {
              limpiarCampos();
          }
      }
      else if(e.getSource() == vista.getBtnImprimir()) { // IMPRIMIR
        if(apartadoFormulario.equals("Empleado")) {
          if (!vista.getTablaDatos().isRowSelected(index)) {
            TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroEmpleado.txt", ingenio.getEmpleadoDAO().getMapEmpleado());
            AuxController.mensajeTemporal("Backup para " + apartadoFormulario + " exportado exitosamente.", "Aviso", 1150);
          } 
          else if (vista.getTablaDatos().isRowSelected(index)) {
            TextReaderUtil.printInformacionEmpleadoKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+ingenio.getEmpleadoDAO().getMapEmpleado().get(Integer.parseInt((String) vista.getFildEmpleadoCod().getText())).getNombre()+ingenio.getEmpleadoDAO().getMapEmpleado().get(Integer.parseInt(vista.getFildEmpleadoCod().getText())).getApellido(), ingenio.getEmpleadoDAO().getMapEmpleado().get(Integer.parseInt(vista.getFildEmpleadoCod().getText())));
            AuxController.mensajeTemporal("Backup para " + ingenio.getEmpleadoDAO().getMapEmpleado().get(Integer.parseInt((String) vista.getFildEmpleadoCod().getText())).getNombre() + " " + ingenio.getEmpleadoDAO().getMapEmpleado().get(Integer.parseInt((String) vista.getFildEmpleadoCod().getText())).getApellido() + " exportado exitosamente.", "Aviso", 1150);
            limpiarCampos();
          }                
        }
        else if(apartadoFormulario.equals("Eps")) {
          TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroEPS.txt", ingenio.getEpsDAO().getMapEps());  
          AuxController.mensajeTemporal("Backup para " + apartadoFormulario + " exportado exitosamente.", "Aviso", 1150);  
        }
        else if(apartadoFormulario.equals("FPP")) {
          TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroFondoPension.txt", ingenio.getFondoDePensionDAO().getMapFondoDePension());   
          AuxController.mensajeTemporal("Backup para " + apartadoFormulario + " exportado exitosamente.", "Aviso", 1150);  
        }
        else if(apartadoFormulario.equals("ARL")) {
          TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroARL.txt", ingenio.getArlDAO().getMapArl());    
          AuxController.mensajeTemporal("Backup para " + apartadoFormulario + " exportado exitosamente.", "Aviso", 1150);  
        }
        else if(apartadoFormulario.equals("CCompensacion")) {
          TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroCajaCompensacion.txt", ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion());    
          AuxController.mensajeTemporal("Backup para " + apartadoFormulario + " exportado exitosamente.", "Aviso", 1150);  
        }
        else if(apartadoFormulario.equals("Empresa")) {
          TextReaderUtil.printInformacionModeloKeyStr("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroConfigEmpresa.txt", ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa());    
          AuxController.mensajeTemporal("Backup para " + apartadoFormulario + " exportado exitosamente.", "Aviso", 1150);  
        }
        else if(apartadoFormulario.equals("Devengo")) {
          TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroConceptoDevengo.txt", ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo());    
          AuxController.mensajeTemporal("Backup para " + apartadoFormulario + " exportado exitosamente.", "Aviso", 1150);  
        }
        else if(apartadoFormulario.equals("Deduccion")) {
          TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroConceptoDeduccion.txt", ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion());    
          AuxController.mensajeTemporal("Backup para " + apartadoFormulario + " exportado exitosamente.", "Aviso", 1150);  
        }
      }
      else if (e.getSource() == vista.getBtnLiquidacion()){
        ViewLiquidacion viewLiquidacion = new ViewLiquidacion();
        ViewLiquidacionController viewLiquidacionController = new ViewLiquidacionController(viewLiquidacion, ingenio);
        viewLiquidacion.setVisible(true);
      }
    }
  }
}
