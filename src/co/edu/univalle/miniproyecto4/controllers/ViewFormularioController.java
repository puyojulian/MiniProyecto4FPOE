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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JComboBox;
import javax.swing.JDialog;
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
import co.edu.univalle.miniproyecto4.models.ModelInterface;
import co.edu.univalle.miniproyecto4.repository.EmpleadoDAO;
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
  
    /* --------------- INSERTE COMENTARIO ------------------- */
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
            TextReaderUtil.printInformacionModeloKeyInt("datos.txt", mapa);
            return modeloTabla;
        }
        else {
            return modeloTabla;
        }
    }
    /* --------------- INSERTE COMENTARIO ------------------- */
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
            TextReaderUtil.printInformacionModeloKeyStr("datos.txt", mapa);
            return modeloTabla;
        }
        else {
            System.out.println(modeloTabla);
            return modeloTabla;
        }
    }

    /* --------------- INSERTE COMENTARIO ------------------- */
    public <T extends ModelInterface> void popularNombreComboBox(JComboBox<String> comboBox, Map<Integer, T> mapa) {
        comboBox.removeAllItems();
        comboBox.addItem("Seleccionar");
        if(mapa.size() > 0) {
            Set<Map.Entry<Integer, T>> entrySetMapa = mapa.entrySet();
            for (Map.Entry<Integer, T> entry : entrySetMapa) {
                comboBox.addItem(entry.getValue().getNombre());
            }
        }
    }

    /* --------------- INSERTE COMENTARIO ------------------- */
    public void popularNombreComboBox(JComboBox<String> comboBox, String elemento) {
        comboBox.addItem(elemento);
    }

    /* --------------- INSERTE COMENTARIO ------------------- */
    public String fechaToString(LocalDate fecha) {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return fecha.format(formateador);
    }

    /* --------------- INSERTE COMENTARIO ------------------- */
    public void llenarCamposFormulario() {
        index = vista.getTablaDatos().getSelectedRow();
        if(apartadoFormulario.equals("Empleado") && (index != -1)) {
            Map.Entry<Integer, Empleado> entry = (Map.Entry<Integer, Empleado>) listaMap.get(index);
            vista.getFildEmpleadoApellido().setText(entry.getValue().getApellido());
            vista.getFildEmpleadoCod().setText(entry.getValue().getNombre());
            vista.getFildEmpleadoDateIngr().setText(fechaToString(entry.getValue().getFechaDeIngreso()));
            vista.getFildEmpleadoDateN().setText(fechaToString(entry.getValue().getFechaDeIngreso()));
            vista.getFildEmpleadoDateRet().setText(fechaToString(entry.getValue().getFechaDeIngreso()));
            vista.getFildEmpleadoDr().setText(entry.getValue().getDireccion());
            vista.getFildEmpleadoId().setText(entry.getValue().getIdentificacion());
            vista.getFildEmpleadoNCuenta().setText(entry.getValue().getNumeroDeCuenta());
            vista.getFildEmpleadoNombre().setText(entry.getValue().getNombre());
            popularNombreComboBox(vista.getDropEpsEmpleado(), ingenio.getEpsDAO().getMapEps().get(entry.getValue().getCodigoEps()).getNombre());
            popularNombreComboBox(vista.getDropFppEmpleado(), ingenio.getFondoDePensionDAO().getMapFondoDePension().get(entry.getValue().getCodigoFpp()).getNombre());
        }
        else if(apartadoFormulario.equals("Eps") && (index != -1)) {
            Map.Entry<Integer, Eps> entry = (Map.Entry<Integer, Eps>) listaMap.get(index);
            vista.getFildEPSCod().setText(entry.getValue().getCodigo() + "");
            vista.getFildEPSNombre().setText(entry.getValue().getNombre());
        }
        else if(apartadoFormulario.equals("FPP") && (index != -1)) {
            Map.Entry<Integer, FondoDePension> entry = (Map.Entry<Integer, FondoDePension>) listaMap.get(index);
            vista.getFildFPPcod().setText(entry.getValue().getCodigo() + "");
            vista.getFildFPPnombre().setText(entry.getValue().getNombre());
        }
        else if(apartadoFormulario.equals("ARL") && (index != -1)) {
            Map.Entry<Integer, Arl> entry = (Map.Entry<Integer, Arl>) listaMap.get(index);
            vista.getFildARLcod().setText(entry.getValue().getCodigo() + "");
            vista.getFildARLnombre().setText(entry.getValue().getNombre());
        }
        else if(apartadoFormulario.equals("CCompensacion") && (index != -1)) {
            Map.Entry<Integer, CajaDeCompensacion> entry = (Map.Entry<Integer, CajaDeCompensacion>) listaMap.get(index);
            vista.getFildCajaComCodigo().setText(entry.getValue().getCodigo() + "");
            vista.getFildCajaComNombre().setText(entry.getValue().getNombre());
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
            popularNombreComboBox(vista.getDropCodARLEMPRESA(), ingenio.getArlDAO().getMapArl().get(entry.getValue().getCodigoArl()).getNombre());
            vista.getDropCodCajaCom().setSelectedIndex(0);
        }
        else if(apartadoFormulario.equals("Devengo") && (index != -1)) {
            Map.Entry<Integer, ConceptoDeDevengo> entry = (Map.Entry<Integer, ConceptoDeDevengo>) listaMap.get(index);
            vista.getFildDevengoCodigo().setText(entry.getValue().getCodigo() + "");
            vista.getFildDevengonombre().setText(entry.getValue().getNombre());
            // vista.getDropbaseDevengo().setSelectedIndex(0);
        }
        else if(apartadoFormulario.equals("Deduccion") && (index != -1)) {
            Map.Entry<Integer, ConceptoDeDeduccion> entry = (Map.Entry<Integer, ConceptoDeDeduccion>) listaMap.get(index);
            vista.getFildDeduccionCodigo().setText(entry.getValue().getCodigo() + "");
            vista.getFildDeduccionNombre().setText(entry.getValue().getNombre());
        }
    }

    /* --------------- INSERTE COMENTARIO ------------------- */
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

    /* --------------- INSERTE COMENTARIO ------------------- */
    private <T extends ModelInterface> int getCodByNombre(String nombre, Map<Integer, T> mapa) {
        Set<Map.Entry<Integer, T>> entrySetMapa = mapa.entrySet();

        for (Map.Entry<Integer, T> entry : entrySetMapa){
            if(entry.getValue().getNombre().equals(nombre)) {
                return entry.getValue().getCodigo();
            }
        }
        return 0;
    }

    /* --------------- INSERTE COMENTARIO ------------------- */
    private <T extends ModelInterface> boolean isNombreUnico(String nombre, Map<Integer, T> mapa) {
        Set<Map.Entry<Integer, T>> entrySetMapa = mapa.entrySet();

        for (Map.Entry<Integer, T> entry : entrySetMapa){
            if(entry.getValue().getNombre().equals(nombre)) {
                return false;
            }
        }
        return true;
    }

    /* --------------- INSERTE COMENTARIO ------------------- */
    private <T extends ModelInterface> boolean isCodigoUnico(int codigo, Map<Integer, T> mapa) {
        Set<Map.Entry<Integer, T>> entrySetMapa = mapa.entrySet();

        for (Map.Entry<Integer, T> entry : entrySetMapa){
            if(entry.getValue().getCodigo() == codigo) {
                return false;
            }
        }
        return true;
    }

    /* --------------- INSERTE COMENTARIO ------------------- */
    private LocalDate crearFecha(String fecha) {
        LocalDate localDate = LocalDate.now();
        if(fecha.contains("/")) {
            StringTokenizer tokenizer = new StringTokenizer(fecha, "/");
            List<Integer> arreglo = new ArrayList<>();
            while(tokenizer.hasMoreTokens()) {
                arreglo.add(Integer.parseInt(tokenizer.nextToken()));
            }
            if(arreglo.size() == 3) {
                localDate = LocalDate.of(arreglo.get(0), arreglo.get(1), arreglo.get(2));
            }
        }
        else if(fecha.contains("-")) {
            StringTokenizer tokenizer = new StringTokenizer(fecha, "-");
            List<Integer> arreglo = new ArrayList<>();
            while(tokenizer.hasMoreTokens()) {
                arreglo.add(Integer.parseInt(tokenizer.nextToken()));
            }
            if(arreglo.size() == 3) {
                localDate = LocalDate.of(arreglo.get(0), arreglo.get(1), arreglo.get(2));
            }        
        }
        else if(fecha.contains(",")) {
            StringTokenizer tokenizer = new StringTokenizer(fecha, ",");
            List<Integer> arreglo = new ArrayList<>();
            while(tokenizer.hasMoreTokens()) {
                arreglo.add(Integer.parseInt(tokenizer.nextToken()));
            }
            if(arreglo.size() == 3) {
                localDate = LocalDate.of(arreglo.get(0), arreglo.get(1), arreglo.get(2));
            }
        }
        else if(fecha.contains(" ")) {
            StringTokenizer tokenizer = new StringTokenizer(fecha, " ");
            List<Integer> arreglo = new ArrayList<>();
            while(tokenizer.hasMoreTokens()) {
                arreglo.add(Integer.parseInt(tokenizer.nextToken()));
            }
            if(arreglo.size() == 3) {
                localDate = LocalDate.of(arreglo.get(0), arreglo.get(1), arreglo.get(2));
            }
        }
        return localDate;
    }

    /* --------------- INSERTE COMENTARIO ------------------- */
    private boolean esNumerico(String cadena) {
        return cadena.matches("\\d+");
    }

    /* --------------- INSERTE COMENTARIO ------------------- */
    public void mensajeTemporal(String mensaje, String titulo, int milisegundos) {
        JOptionPane msg = new JOptionPane(mensaje, JOptionPane.INFORMATION_MESSAGE);
        final JDialog dlg = msg.createDialog(titulo);
        dlg.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        new Thread(new Runnable() {
            @Override
            public void run() {
            try {
                Thread.sleep(milisegundos);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dlg.setVisible(false);
            }
        }).start();
        dlg.setVisible(true);
    }

    /* --------------- INSERTE COMENTARIO ------------------- */
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

    /* --------------- INSERTE COMENTARIO ------------------- */
    class ListSelectionHandler implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            llenarCamposFormulario();
        }
    }

    /* --------------- INSERTE COMENTARIO ------------------- */
    class ActionsHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == vista.getBtnEmpleado()) {
                apartadoFormulario = "Empleado";
                vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEmpleadoDAO().getMapEmpleado()));
                popularNombreComboBox(vista.getDropEpsEmpleado(), ingenio.getEpsDAO().getMapEps());
                popularNombreComboBox(vista.getDropFppEmpleado(), ingenio.getFondoDePensionDAO().getMapFondoDePension());
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
                popularNombreComboBox(vista.getDropCodARLEMPRESA(), ingenio.getArlDAO().getMapArl());
                popularNombreComboBox(vista.getDropCodCajaCom(), ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion());
            }
            else if(e.getSource() == vista.getBtnDevegno()) {
                apartadoFormulario = "Devengo";
                vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo()));
            }
            else if(e.getSource() == vista.getBtnDeduccion()) {
                apartadoFormulario = "Deduccion";
                vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion()));
            }
            else if(e.getSource() == vista.getBtnAñadir()) {
                if(apartadoFormulario.equals("Empleado")) {
                    if(esNumerico(vista.getFildEmpleadoId().getText())) {
                        ingenio.getEmpleadoDAO().addEmpleado(new Empleado(vista.getFildEmpleadoId().getText(), 
                            vista.getFildEmpleadoApellido().getText(), 
                            vista.getFildEmpleadoNombre().getText(), 
                            vista.getFildEmpleadoDr().getText(), 
                            getCodByNombre((String) vista.getDropEpsEmpleado().getSelectedItem(), 
                            ingenio.getEpsDAO().getMapEps()), 
                            getCodByNombre((String) vista.getDropFppEmpleado().getSelectedItem(), 
                            ingenio.getFondoDePensionDAO().getMapFondoDePension()), 
                            crearFecha(vista.getFildEmpleadoDateN().getText()), 
                            crearFecha(vista.getFildEmpleadoDateIngr().getText()), 
                            crearFecha(vista.getFildEmpleadoDateRet().getText()), 
                            vista.getDropTipoEmpleado().getSelectedIndex(), 
                            vista.getFildEmpleadoNCuenta().getText()));
                            limpiarCampos("Empleado");
                        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEmpleadoDAO().getMapEmpleado()));
                    }
                    else {
                        mensajeTemporal("Número de identificación debe ser numérico.", "Error de entrada", 1150);
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
                    if(esNumerico(vista.getFildEmpresaNit().getText()) && esNumerico(vista.getFildEmpresaTelefono().getText()) && esNumerico(vista.getFildEmpresaSalariomin().getText()) && esNumerico(vista.getFildEmpresaAuxTrans().getText())) {
                        ingenio.getConfiguracionDeEmpresaDAO().addConfiguracionDeEmpresa(new ConfiguracionDeEmpresa(vista.getFildEmpresaNit().getText(), 
                            vista.getFildEmpresaNombre().getText(), 
                            vista.getFildEmpresaTelefono().getText(), 
                            vista.getFildEmpresaDireccion().getText(), 
                            vista.getFildEmpresaRepre().getText(), 
                            vista.getFildEmpresaCorreo().getText(), 
                            getCodByNombre((String) vista.getDropCodARLEMPRESA().getSelectedItem(), ingenio.getArlDAO().getMapArl()), 
                            getCodByNombre((String) vista.getDropCodCajaCom().getSelectedItem(), ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()), 
                            vista.getFildEmpresaSalariomin().getText(), 
                            vista.getFildEmpresaAuxTrans().getText()));

                            vista.getTablaDatos().setModel(actualizarTableModelString(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa()));
                            limpiarCampos("Empresa");
                    }
                    else {
                        mensajeTemporal("NIT, Teléfono, Salario Mínimo y Auxilio de Transporte deben ser numéricos.", "Error de entrada", 1150);
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
            else if(e.getSource() == vista.getBtnEliminar()) {
                if(apartadoFormulario.equals("Empleado")) {
                    index = vista.getTablaDatos().getSelectedRow();

                    if(index != -1) {
                        Map.Entry<Integer, Empleado> entry = (Map.Entry<Integer, Empleado>) listaMap.get(index);
                        ingenio.getEmpleadoDAO().deleteEmpleado((entry.getKey()));
                        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEmpleadoDAO().getMapEmpleado())); 
                    } else {
                        mensajeTemporal("Elija el usuario que desea eliminar", "Error", 1150);
                    }
                }
                else if(apartadoFormulario.equals("Eps")) {
                    index = vista.getTablaDatos().getSelectedRow();

                    if(index != -1) {
                        Map.Entry<Integer, Eps> entry = (Map.Entry<Integer, Eps>) listaMap.get(index);
                        ingenio.getEpsDAO().deleteEps(entry.getKey());
                        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getEpsDAO().getMapEps()));
                    } else {
                        mensajeTemporal("Elija la eps que desea eliminar", "Error", 1150);
                    }
                }
                else if(apartadoFormulario.equals("FPP")) {
                    index = vista.getTablaDatos().getSelectedRow();

                    if(index != -1) {
                        Map.Entry<Integer, FondoDePension> entry = (Map.Entry<Integer, FondoDePension>) listaMap.get(index);
                        ingenio.getFondoDePensionDAO().deleteFondoDePension(entry.getKey());
                        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getFondoDePensionDAO().getMapFondoDePension()));  
                    } else {
                        mensajeTemporal("Elija el fondo de pension que desea eliminar", "Error", 1150);
                    }
                }
                else if(apartadoFormulario.equals("ARL")) {
                    index = vista.getTablaDatos().getSelectedRow();

                    if(index != -1) {
                        Map.Entry<Integer, Arl> entry = (Map.Entry<Integer, Arl>) listaMap.get(index);
                        ingenio.getArlDAO().deleteArl((entry.getKey()));
                        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getArlDAO().getMapArl()));  
                    } else {
                        mensajeTemporal("Elija la arl que desea eliminar", "Error", 1150);
                    }
                }
                else if(apartadoFormulario.equals("CCompensacion")) {
                    index = vista.getTablaDatos().getSelectedRow();

                    if(index != -1) {
                        Map.Entry<Integer, CajaDeCompensacion> entry = (Map.Entry<Integer, CajaDeCompensacion>) listaMap.get(index);
                        ingenio.getCajaDeCompensacionDAO().deleteCajaDeCompensacion((entry.getKey()));
                        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()));  
                    } else {
                        mensajeTemporal("Elija la caja de compensacion que desea eliminar", "Error", 1150);
                    }
                }
                else if(apartadoFormulario.equals("Empresa")) {
                    index = vista.getTablaDatos().getSelectedRow();

                    if(index != -1) {
                        Map.Entry<String, ConfiguracionDeEmpresa> entry = (Map.Entry<String, ConfiguracionDeEmpresa>) listaMap.get(index);
                        ingenio.getConfiguracionDeEmpresaDAO().deleteConfiguracionDeEmpresa(entry.getKey());
                        vista.getTablaDatos().setModel(actualizarTableModelString(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa()));  
                    } else {
                        mensajeTemporal("Elija la empresa que desea eliminar", "Error", 1150);
                    }
                }
                else if(apartadoFormulario.equals("Devengo")) {
                    index = vista.getTablaDatos().getSelectedRow();

                    if(index != -1) {
                        Map.Entry<Integer, ConceptoDeDevengo> entry = (Map.Entry<Integer, ConceptoDeDevengo>) listaMap.get(index);
                        ingenio.getConceptoDeDevengoDAO().deleteConceptoDeDevengo((entry.getKey()));
                        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo()));    
                    } else {
                        mensajeTemporal("Elija la arl que desea eliminar", "Error", 1150);
                    }
                }
                else if(apartadoFormulario.equals("Deduccion")) {
                    index = vista.getTablaDatos().getSelectedRow();

                    if(index != -1) {
                        Map.Entry<Integer, ConceptoDeDeduccion> entry = (Map.Entry<Integer, ConceptoDeDeduccion>) listaMap.get(index);
                        ingenio.getConceptoDeDeduccionDAO().deleteConceptoDeDeduccion((entry.getKey()));
                        vista.getTablaDatos().setModel(actualizarTableModelInt(ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion()));  
                    } else {
                        mensajeTemporal("Elija la arl que desea eliminar", "Error", 1150);
                    }
                }
            }
            else if(e.getSource() == vista.getBtnEditar()) {
                if(apartadoFormulario.equals("Empleado")) {
                    
                }
                else if(apartadoFormulario.equals("Eps")) {
                    
                }
                else if(apartadoFormulario.equals("FPP")) {
                    
                }
                else if(apartadoFormulario.equals("ARL")) {
                    
                }
                else if(apartadoFormulario.equals("CCompensacion")) {
                    
                }
                else if(apartadoFormulario.equals("Empresa")) {
                    
                }
                else if(apartadoFormulario.equals("Devengo")) {
                    
                }
                else if(apartadoFormulario.equals("Deduccion")) {
                    
                }
            }
            else if(e.getSource() == vista.getBtnLimpiar()) {
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
        }
    }
}
