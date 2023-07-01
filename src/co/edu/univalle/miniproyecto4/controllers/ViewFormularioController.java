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
import java.time.LocalDate;

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
import co.edu.univalle.miniproyecto4.util.TextReaderUtil;
import co.edu.univalle.miniproyecto4.views.ViewFormulario;

public class ViewFormularioController {
  private ViewFormulario vista;
  private Ingenio ingenio;
  private List listaMap;
  private DefaultTableModel modeloTabla;
  private String apartadoFormulario = "";
  private int index;

  public ViewFormularioController(ViewFormulario vista) {
    this.vista = vista;
    ingenio = new Ingenio();
    modeloTabla = new DefaultTableModel() {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    listaMap = new ArrayList<Map.Entry>();

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

    // if(SerializationUtil.isSerializedObjectExists("mapaARL.bin")) {
    //     ingenio.getArlDAO().setMapArl((Map) SerializationUtil.deserializeObject("mapaARL.bin"));
    // }
  }
  
  /* --------------- MUESTREO: ACTUALIZAR MODELO TABLA ------------------- */
  public <T> DefaultTableModel actualizarTableModelInt(Map<Integer, T> mapa) {
    List<Object> listaTemporal = new ArrayList<>();

    modeloTabla.setRowCount(0);
    establecerIdentificadoresColumnas(modeloTabla);
    listaMap.clear();

    if(mapa.size() > 0) {
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

    if(mapa.size() > 0) {
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
      System.out.println(modeloTabla);
      return modeloTabla;
    }
  }  

  /* --------------- MUESTREO: LLENA CAMPOS DEPENDIENDO EL APARTADOFORMULARIO ACTUAL ------------------- */
  public void llenarCamposFormulario() {
    index = vista.getTablaDatos().getSelectedRow();
    if(apartadoFormulario.equals("Empleado") && (index != -1)) {
      Map.Entry<Integer, Empleado> entry = (Map.Entry<Integer, Empleado>) listaMap.get(index);
      vista.getFildEmpleadoApellido().setText(entry.getValue().getApellido());
      vista.getFildEmpleadoCod().setText(entry.getValue().getNombre());
      vista.getFildEmpleadoDateIngr().setText(AuxController.fechaToString(entry.getValue().getFechaDeIngreso()));
      vista.getFildEmpleadoDateN().setText(AuxController.fechaToString(entry.getValue().getFechaDeIngreso()));
      vista.getFildEmpleadoDateRet().setText(AuxController.fechaToString(entry.getValue().getFechaDeIngreso()));
      vista.getFildEmpleadoDr().setText(entry.getValue().getDireccion());
      vista.getFildEmpleadoId().setText(entry.getValue().getIdentificacion());
      vista.getFildEmpleadoNCuenta().setText(entry.getValue().getNumeroDeCuenta());
      vista.getFildEmpleadoNombre().setText(entry.getValue().getNombre());
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
      vista.getDropCodCajaCom().setSelectedIndex(0);
      vista.getBtnAñadir().setEnabled(false);
    }
    else if(apartadoFormulario.equals("Devengo") && (index != -1)) {
      Map.Entry<Integer, ConceptoDeDevengo> entry = (Map.Entry<Integer, ConceptoDeDevengo>) listaMap.get(index);
      vista.getFildDevengoCodigo().setText(entry.getValue().getCodigo() + "");
      vista.getFildDevengonombre().setText(entry.getValue().getNombre());
      vista.getBtnAñadir().setEnabled(false);
    }
    else if(apartadoFormulario.equals("Deduccion") && (index != -1)) {
      Map.Entry<Integer, ConceptoDeDeduccion> entry = (Map.Entry<Integer, ConceptoDeDeduccion>) listaMap.get(index);
      vista.getFildDeduccionCodigo().setText(entry.getValue().getCodigo() + "");
      vista.getFildDeduccionNombre().setText(entry.getValue().getNombre());
      vista.getBtnAñadir().setEnabled(false);
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
      String[] atributosTabla = {"NIT", "RAZÓN SOCIAL", "NOMBRE", "TELÉFONO", "DIRECCIÓN", "REP. LEGAL", "CORREO CONT.", "CÓD. ARL", "CÓD. CAJA", "SMLV", "AUX. TRANSP."};
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

  /* --------------- MUESTREO: RETORNA LOS VALORES DE LOS CAMPOS A SUS VALORES POR DEFECTO ------------------- */
  private void limpiarCampos(String vistaActual){
    if(vistaActual.equals("Empleado")) {
      vista.getFildEmpleadoApellido().setText("");
      vista.getFildEmpleadoCod().setText("");
      vista.getFildEmpleadoDateIngr().setText("");
      vista.getFildEmpleadoDateN().setText("");
      vista.getFildEmpleadoDateRet().setText("");
      vista.getFildEmpleadoDr().setText("");
      vista.getFildEmpleadoId().setText("");
      vista.getFildEmpleadoNCuenta().setText("");
      vista.getFildEmpleadoNombre().setText("");
      vista.getDropEpsEmpleado().setSelectedIndex(0);
      vista.getDropFppEmpleado().setSelectedIndex(0);
      vista.getDropTipoEmpleado().setSelectedIndex(0);
    }
    else if(vistaActual.equals("Eps")) {
      vista.getFildEPSCod().setText("");
      vista.getFildEPSNombre().setText("");
    }
    else if(vistaActual.equals("FPP")) {
      vista.getFildFPPcod().setText("");
      vista.getFildFPPnombre().setText("");
    }
    else if(vistaActual.equals("ARL")) {
      vista.getFildARLcod().setText("");
      vista.getFildARLnombre().setText("");
    }
    else if(vistaActual.equals("CCompensacion")) {
      vista.getFildCajaComCodigo().setText("");
      vista.getFildCajaComNombre().setText("");
    }
    else if(vistaActual.equals("Empresa")) {
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
    else if(vistaActual.equals("Devengo")) {
      vista.getFildDevengoCodigo().setText("");
      vista.getFildDevengonombre().setText("");
      vista.getDropbaseDevengo().setSelectedIndex(0);
    }
    else if(vistaActual.equals("Deduccion")) {
      vista.getFildDeduccionCodigo().setText("");
      vista.getFildDeduccionNombre().setText("");
    }
    vista.getTablaDatos().clearSelection();
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
      }
      else if(e.getSource() == vista.getBtnEps()) {
        apartadoFormulario = "Eps";
        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEpsDAO().getMapEps()));
      }
      else if(e.getSource() == vista.getBtnFondoP()) {
        apartadoFormulario = "FPP";
        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getFondoDePensionDAO().getMapFondoDePension()));
      }
      else if(e.getSource() == vista.getBtnARL()) {
        apartadoFormulario = "ARL";
        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getArlDAO().getMapArl()));
      }
      else if(e.getSource() == vista.getBtnCajaCompen()) {
        apartadoFormulario = "CCompensacion";
        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()));
      }
      else if(e.getSource() == vista.getBtnEmpresa()) {
        apartadoFormulario = "Empresa";
        vista.getTablaDatos().setModel(actualizarTableModelString(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa()));
        AuxController.popularNombreComboBox(vista.getDropCodARLEMPRESA(), ingenio.getArlDAO().getMapArl());
        AuxController.popularNombreComboBox(vista.getDropCodCajaCom(), ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion());
      }
      else if(e.getSource() == vista.getBtnDevegno()) {
        apartadoFormulario = "Devengo";
        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo()));
      }
      else if(e.getSource() == vista.getBtnDeduccion()) {
        apartadoFormulario = "Deduccion";
        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion()));
      }
      else if(e.getSource() == vista.getBtnAñadir()) { // AÑADIR
        if(apartadoFormulario.equals("Empleado")) {
          if(AuxController.esNumerico(vista.getFildEmpleadoId().getText()) && AuxController.esNumerico(vista.getFildEmpleadoNCuenta().getText())) {
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
              AuxController.crearFecha(vista.getFildEmpleadoDateRet().getText()), 
              vista.getDropTipoEmpleado().getSelectedIndex(), 
              vista.getFildEmpleadoNCuenta().getText()));
              limpiarCampos("Empleado");
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEmpleadoDAO().getMapEmpleado()));
          }
          else {
            AuxController.mensajeTemporal("Los números de identificación y de cuenta deben ser numéricos.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("Eps")) {
          ingenio.getEpsDAO().addEps(new Eps(vista.getFildEPSNombre().getText()));
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEpsDAO().getMapEps()));
          limpiarCampos("Eps");
        }
        else if(apartadoFormulario.equals("FPP")) {
          ingenio.getFondoDePensionDAO().addFondoDePension(new FondoDePension(vista.getFildFPPnombre().getText()));
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getFondoDePensionDAO().getMapFondoDePension()));
          limpiarCampos("FPP");
        }
        else if(apartadoFormulario.equals("ARL")) {
          ingenio.getArlDAO().addArl(new Arl(vista.getFildARLnombre().getText()));
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getArlDAO().getMapArl()));
          limpiarCampos("ARL");
        }
        else if(apartadoFormulario.equals("CCompensacion")) {
          ingenio.getCajaDeCompensacionDAO().addCajaDeCompensacion(new CajaDeCompensacion(vista.getFildCajaComNombre().getText()));
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()));
          limpiarCampos("CCompensacion");
        }
        else if(apartadoFormulario.equals("Empresa")) {
          if(AuxController.esNumerico(vista.getFildEmpresaNit().getText()) && AuxController.esNumerico(vista.getFildEmpresaTelefono().getText()) && AuxController.esNumerico(vista.getFildEmpresaSalariomin().getText()) && AuxController.esNumerico(vista.getFildEmpresaAuxTrans().getText())) {
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
            limpiarCampos("Empresa");
          }
          else {
            AuxController.mensajeTemporal("NIT, Teléfono, Salario Mínimo y Auxilio de Transporte deben ser numéricos.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("Devengo")) {
          ingenio.getConceptoDeDevengoDAO().addConceptoDeDevengo(new ConceptoDeDevengo(vista.getFildDevengonombre().getText()));
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo()));
          limpiarCampos("Devengo");
        }
        else if(apartadoFormulario.equals("Deduccion")) {
          ingenio.getConceptoDeDeduccionDAO().addConceptoDeDeduccion(new ConceptoDeDeduccion(vista.getFildDeduccionNombre().getText()));
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion()));
          limpiarCampos("Deduccion");
        }
      }
      else if(e.getSource() == vista.getBtnEliminar()) { // ELIMINAR
        if(apartadoFormulario.equals("Empleado")) {
          index = vista.getTablaDatos().getSelectedRow();

        if(index != -1) {
          Map.Entry<Integer, Empleado> entry = (Map.Entry<Integer, Empleado>) listaMap.get(index);
          ingenio.getEmpleadoDAO().deleteEmpleado((entry.getKey()));
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEmpleadoDAO().getMapEmpleado()));
          limpiarCampos("Empleado");
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
            limpiarCampos("Eps");
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
            limpiarCampos("FPP");
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
            limpiarCampos("ARL");
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
            limpiarCampos("CCompensacion");  
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
            limpiarCampos("Empresa");
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
            limpiarCampos("Devengo");
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
            limpiarCampos("Deduccion");
          } else {
            AuxController.mensajeTemporal("Elija la arl que desea eliminar", "Error", 1150);
          }
        }
      }
      else if(e.getSource() == vista.getBtnEditar()) { // EDITAR
        if(apartadoFormulario.equals("Empleado")) {
          index = vista.getTablaDatos().getSelectedRow();
          if(AuxController.esNumerico(vista.getFildEmpleadoId().getText())) {
            ingenio.getEmpleadoDAO().getMapEmpleado().get(index).setIdentificacion(vista.getFildEmpleadoId().getText());
            ingenio.getEmpleadoDAO().getMapEmpleado().get(index).setApellidos(vista.getFildEmpleadoApellido().getText());
            ingenio.getEmpleadoDAO().getMapEmpleado().get(index).setNombre(vista.getFildEmpleadoNombre().getText());
            ingenio.getEmpleadoDAO().getMapEmpleado().get(index).setDireccion(vista.getFildEmpleadoDr().getText());
            ingenio.getEmpleadoDAO().getMapEmpleado().get(index).setCodigoEps(AuxController.getCodByNombre((String) vista.getDropEpsEmpleado().getSelectedItem(), ingenio.getEpsDAO().getMapEps()));
            ingenio.getEmpleadoDAO().getMapEmpleado().get(index).setCodigoFpp(AuxController.getCodByNombre((String) vista.getDropFppEmpleado().getSelectedItem(), ingenio.getFondoDePensionDAO().getMapFondoDePension()));
            ingenio.getEmpleadoDAO().getMapEmpleado().get(index).setFechaDeNacimiento(AuxController.crearFecha(vista.getFildEmpleadoDateN().getText()));
            ingenio.getEmpleadoDAO().getMapEmpleado().get(index).setFechaDeIngreso(AuxController.crearFecha(vista.getFildEmpleadoDateIngr().getText()));
            ingenio.getEmpleadoDAO().getMapEmpleado().get(index).setFechaDeRetiro(AuxController.crearFecha(vista.getFildEmpleadoDateRet().getText()));
            ingenio.getEmpleadoDAO().getMapEmpleado().get(index).setTipoDeTrabajador(vista.getDropTipoEmpleado().getSelectedIndex());
            ingenio.getEmpleadoDAO().getMapEmpleado().get(index).setNumeroDeCuenta(vista.getFildEmpleadoNCuenta().getText());
            limpiarCampos("Empleado");
            vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEmpleadoDAO().getMapEmpleado()));
            vista.getBtnAñadir().setEnabled(true);
          }
          else {
            AuxController.mensajeTemporal("Los números de identificación y de cuenta deben ser numéricos.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("Eps")) {
          index = vista.getTablaDatos().getSelectedRow();
          ingenio.getEpsDAO().getMapEps().get(index).setNombre(vista.getFildEPSNombre().getText());;
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEpsDAO().getMapEps()));
          limpiarCampos("Eps");
          vista.getBtnAñadir().setEnabled(true);
        }
        else if(apartadoFormulario.equals("FPP")) {
          index = vista.getTablaDatos().getSelectedRow();
          ingenio.getFondoDePensionDAO().getMapFondoDePension().get(index).setNombre(vista.getFildFPPnombre().getText());
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getFondoDePensionDAO().getMapFondoDePension()));
          limpiarCampos("FPP");
          vista.getBtnAñadir().setEnabled(true);
        }
        else if(apartadoFormulario.equals("ARL")) {
          index = vista.getTablaDatos().getSelectedRow();
          ingenio.getArlDAO().getMapArl().get(index).setNombre(vista.getFildARLnombre().getText());
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getArlDAO().getMapArl()));
          limpiarCampos("ARL");
          vista.getBtnAñadir().setEnabled(true);
        }
        else if(apartadoFormulario.equals("CCompensacion")) {
          index = vista.getTablaDatos().getSelectedRow();
          ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion().get(index).setNombre(vista.getFildCajaComNombre().getText());
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()));
          limpiarCampos("CCompensacion");
          vista.getBtnAñadir().setEnabled(true);
        }
        else if(apartadoFormulario.equals("Empresa")) {
          index = vista.getTablaDatos().getSelectedRow();
          if(AuxController.esNumerico(vista.getFildEmpresaNit().getText()) && AuxController.esNumerico(vista.getFildEmpresaTelefono().getText()) && AuxController.esNumerico(vista.getFildEmpresaSalariomin().getText()) && AuxController.esNumerico(vista.getFildEmpresaAuxTrans().getText())) {
            ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(index).setNit(vista.getFildEmpresaNit().getText());
            ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(index).setNombre(vista.getFildEmpresaNombre().getText());
            ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(index).setTelefono(vista.getFildEmpresaTelefono().getText());
            ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(index).setDireccion(vista.getFildEmpresaDireccion().getText());
            ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(index).setRepresentanteLegal(vista.getFildEmpresaRepre().getText());
            ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(index).setCorreoDeContacto(vista.getFildEmpresaCorreo().getText());
            ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(index).setCodigoArl(AuxController.getCodByNombre((String) vista.getDropCodARLEMPRESA().getSelectedItem(), ingenio.getArlDAO().getMapArl()));
            ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(index).setCodigoCajaDeCompensación(AuxController.getCodByNombre((String) vista.getDropCodCajaCom().getSelectedItem(), ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()));
            ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(index).setSalarioMínimoLegalVigente(vista.getFildEmpresaSalariomin().getText());
            ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa().get(index).setAuxilioDeTransporte(vista.getFildEmpresaAuxTrans().getText());

            vista.getTablaDatos().setModel(actualizarTableModelString(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa()));
            limpiarCampos("Empresa");
            vista.getBtnAñadir().setEnabled(true);
          }
          else {
            AuxController.mensajeTemporal("NIT, Teléfono, Salario Mínimo y Auxilio de Transporte deben ser numéricos.", "Error de entrada", 1150);
          }
        }
        else if(apartadoFormulario.equals("Devengo")) {
          index = vista.getTablaDatos().getSelectedRow();
          ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo().get(index).setNombre(vista.getFildDevengonombre().getText());
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo()));
          limpiarCampos("Devengo");
          vista.getBtnAñadir().setEnabled(true);
        }
        else if(apartadoFormulario.equals("Deduccion")) {
          index = vista.getTablaDatos().getSelectedRow();
          ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion().get(index).setNombre(vista.getFildDeduccionNombre().getText());
          vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion()));
          limpiarCampos("Deduccion");
          vista.getBtnAñadir().setEnabled(true);
        }
      }
      else if(e.getSource() == vista.getBtnLimpiar()) { // LIMPIAR
          vista.getTablaDatos().clearSelection();
          if(apartadoFormulario.equals("Empleado")) {
              limpiarCampos("Empleado");
          }
          else if(apartadoFormulario.equals("Eps")) {
              limpiarCampos("Eps");
          }
          else if(apartadoFormulario.equals("FPP")) {
              limpiarCampos("FPP");
          }
          else if(apartadoFormulario.equals("ARL")) {
              limpiarCampos("ARL");
          }
          else if(apartadoFormulario.equals("CCompensacion")) {
              limpiarCampos("CCompensacion");
          }
          else if(apartadoFormulario.equals("Empresa")) {
              limpiarCampos("Empresa");
          }
          else if(apartadoFormulario.equals("Devengo")) {
              limpiarCampos("Devengo");
          }
          else if(apartadoFormulario.equals("Deduccion")) {
              limpiarCampos("Deduccion");
          }
      }
      else if(e.getSource() == vista.getBtnImprimir()) { // IMPRIMIR
        if(apartadoFormulario.equals("Empleado")) {
          TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroEmpleado.txt", ingenio.getEmpleadoDAO().getMapEmpleado());                
        }
        else if(apartadoFormulario.equals("Eps")) {
          TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroEPS.txt", ingenio.getEpsDAO().getMapEps());  
        }
        else if(apartadoFormulario.equals("FPP")) {
          TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroFondoPension.txt", ingenio.getFondoDePensionDAO().getMapFondoDePension());   
        }
        else if(apartadoFormulario.equals("ARL")) {
          TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroARL.txt", ingenio.getArlDAO().getMapArl());    
        }
        else if(apartadoFormulario.equals("CCompensacion")) {
          TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroCajaCompensacion.txt", ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion());    
        }
        else if(apartadoFormulario.equals("Empresa")) {
          TextReaderUtil.printInformacionModeloKeyStr("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroConfigEmpresa.txt", ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa());    
        }
        else if(apartadoFormulario.equals("Devengo")) {
          TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroConceptoDevengo.txt", ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo());    
        }
        else if(apartadoFormulario.equals("Deduccion")) {
          TextReaderUtil.printInformacionModeloKeyInt("BackUp/"+AuxController.fechaToString(LocalDate.now())+"RegistroConceptoDeduccion.txt", ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion());    
        }
      }
    }
  }
}
