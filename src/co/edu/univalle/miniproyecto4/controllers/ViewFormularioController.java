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

import javax.swing.JDialog;
import javax.swing.JOptionPane;
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
import co.edu.univalle.miniproyecto4.views.ViewFormulario;

public class ViewFormularioController {
    private ViewFormulario vista;
    private Ingenio ingenio;
    private List listaMap;
    private DefaultTableModel modeloTabla;
    private String apartadoFormulario = "";
  

    public ViewFormularioController(ViewFormulario vista) {
    this.vista = vista;
    ingenio = new Ingenio();
    modeloTabla = new DefaultTableModel();

    ActionsHandler manejadorDeActionEvents = new ActionsHandler();
    vista.addListener(manejadorDeActionEvents);

    }
  
    public <T> DefaultTableModel actualizarTableModel(Map<Integer, T> mapa, String apartadoFormulario) {
        List<Object> listaTemporal = new ArrayList<>();

        modeloTabla.setRowCount(0);
        establecerIdentificadoresColumnas(modeloTabla, apartadoFormulario);

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
            }
            return modeloTabla;
        }
        else {
            return modeloTabla;
        }
    }  

    public void establecerIdentificadoresColumnas(DefaultTableModel modelo, String apartadoFormulario) {
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

    private <T extends ModelInterface> int getCodByNombre(String nombre, Map<Integer, T> mapa) {
        Set<Map.Entry<Integer, T>> entrySetMapa = mapa.entrySet();

        for (Map.Entry<Integer, T> entry : entrySetMapa){
            if(entry.getValue().getNombre().equals(nombre)) {
                return entry.getValue().getCodigo();
            }
        }
        return 0;
    }

    private <T extends ModelInterface> boolean isNombreUnico(String nombre, Map<Integer, T> mapa) {
        Set<Map.Entry<Integer, T>> entrySetMapa = mapa.entrySet();

        for (Map.Entry<Integer, T> entry : entrySetMapa){
            if(entry.getValue().getNombre().equals(nombre)) {
                return false;
            }
        }
        return true;
    }

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

    private boolean esNumerico(String cadena) {
        return cadena.matches("\\d+");
    }

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
    }

    class ActionsHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == vista.getBtnEmpleado()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getEmpleadoDAO().getMapEmpleado(), "Empleado"));
                apartadoFormulario = "Empleado";
            }
            else if(e.getSource() == vista.getBtnEps()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getEpsDAO().getMapEps(), "Eps"));
                apartadoFormulario = "Eps";
            }
            else if(e.getSource() == vista.getBtnFondoP()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getFondoDePensionDAO().getMapFondoDePension(), "FPP"));
                apartadoFormulario = "FPP";
            }
            else if(e.getSource() == vista.getBtnARL()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getArlDAO().getMapArl(), "ARL"));
                apartadoFormulario = "ARL";
            }
            else if(e.getSource() == vista.getBtnCajaCompen()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion(), "CCompensacion"));
                apartadoFormulario = "CCompensacion";
            }
            else if(e.getSource() == vista.getBtnEmpresa()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa(), "Empresa"));
                apartadoFormulario = "Empresa";
            }
            else if(e.getSource() == vista.getBtnDevegno()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo(), "Devengo"));
                apartadoFormulario = "Devengo";
            }
            else if(e.getSource() == vista.getBtnDeduccion()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion(), "Deduccion"));
                apartadoFormulario = "Deduccion";
            }
            else if(e.getSource() == vista.getBtnAñadir()) {
                if(apartadoFormulario.equals("Empleado")) {
                    if(esNumerico(vista.getFildEmpleadoId().getText())) {
                        ingenio.getEmpleadoDAO().addEmpleado(new Empleado(Integer.parseInt(vista.getFildEmpleadoId().getText()), 
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
                        vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getEmpleadoDAO().getMapEmpleado(), "Empleado"));
                    }
                    else {
                        mensajeTemporal("Número de identificación debe ser numérico.", "Error de entrada", 1150);
                    }
                }
                else if(apartadoFormulario.equals("Eps")) {
                    ingenio.getEpsDAO().addEps(new Eps(vista.getFildEPSNombre().getText()));
                    limpiarCampos("Eps");
                }
                else if(apartadoFormulario.equals("FPP")) {
                    ingenio.getFondoDePensionDAO().addFondoDePension(new FondoDePension(vista.getFildFPPnombre().getText()));
                    limpiarCampos("FPP");
                }
                else if(apartadoFormulario.equals("ARL")) {
                    ingenio.getArlDAO().addArl(new Arl(vista.getFildARLnombre().getText()));
                    limpiarCampos("ARL");
                }
                else if(apartadoFormulario.equals("CCompensacion")) {
                    ingenio.getCajaDeCompensacionDAO().addCajaDeCompensacion(new CajaDeCompensacion(vista.getFildCajaComNombre().getText()));
                    limpiarCampos("CCompensacion");
                }
                else if(apartadoFormulario.equals("Empresa")) {
                    if(esNumerico(vista.getFildEmpresaNit().getText()) && esNumerico(vista.getFildEmpresaTelefono().getText()) && esNumerico(vista.getFildEmpresaSalariomin().getText()) && esNumerico(vista.getFildEmpresaAuxTrans().getText())) {
                        ingenio.getConfiguracionDeEmpresaDAO().addConfiguracionDeEmpresa(new ConfiguracionDeEmpresa(Integer.parseInt(vista.getFildEmpresaNit().getText()), 
                            vista.getFildEmpresaNombre().getText(), 
                            Integer.parseInt(vista.getFildEmpresaTelefono().getText()), 
                            vista.getFildEmpresaDireccion().getText(), 
                            vista.getFildEmpresaRepre().getText(), 
                            vista.getFildEmpresaCorreo().getText(), 
                            getCodByNombre((String) vista.getDropCodARLEMPRESA().getSelectedItem(), ingenio.getArlDAO().getMapArl()), 
                            getCodByNombre((String) vista.getDropCodCajaCom().getSelectedItem(), ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()), 
                            Integer.parseInt(vista.getFildEmpresaSalariomin().getText()), 
                            Integer.parseInt(vista.getFildEmpresaAuxTrans().getText())));
                            limpiarCampos("Empresa");
                    }
                    else {
                        mensajeTemporal("NIT, Teléfono, Salario Mínimo y Auxilio de Transporte deben ser numéricos.", "Error de entrada", 1150);
                    }
                }
                else if(apartadoFormulario.equals("Devengo")) {
                    ingenio.getConceptoDeDevengoDAO().addConceptoDeDevengo(new ConceptoDeDevengo(vista.getFildDevengonombre().getText()));
                    limpiarCampos("Devengo");
                }
                else if(apartadoFormulario.equals("Deduccion")) {
                    ingenio.getConceptoDeDeduccionDAO().addConceptoDeDeduccion(new ConceptoDeDeduccion(vista.getFildDeduccionNombre().getText()));
                    limpiarCampos("Deduccion");
                }
            }
            else if(e.getSource() == vista.getBtnEliminar()) {
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
