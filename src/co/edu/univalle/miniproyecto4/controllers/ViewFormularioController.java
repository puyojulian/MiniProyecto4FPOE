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

import javax.swing.table.DefaultTableModel;

import co.edu.univalle.miniproyecto4.models.Ingenio;
import co.edu.univalle.miniproyecto4.views.ViewFormulario;

public class ViewFormularioController {
  private ViewFormulario vista;
  private Ingenio ingenio;
  private List listaMap;
  private DefaultTableModel modeloTabla;
  

  public ViewFormularioController(ViewFormulario vista) {
    this.vista = vista;
    ingenio = new Ingenio();
    modeloTabla = new DefaultTableModel();

    ActionsHandler manejadorDeActionEvents = new ActionsHandler();
    vista.addListener(manejadorDeActionEvents);

  }
  
  public <T> DefaultTableModel actualizarTableModel(Map<Integer, T> mapa) {
    List<Object> listaTemporal = new ArrayList<>();
      
    if(mapa.size() > 0) {
        Set<Map.Entry<Integer, T>> entrySetMapa = mapa.entrySet();

        modeloTabla.setRowCount(0);

        listaMap = new ArrayList<>(mapa.entrySet());
        
        establecerIdentificadoresColumnas(modeloTabla);

        for (Map.Entry<Integer, T> entry : entrySetMapa){
            Object value = entry.getValue();
            String item = "" + value;
            StringTokenizer tokenizer = new StringTokenizer(item,",");
            while(tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                listaTemporal.add(token);
            }
            
            modeloTabla.addRow(listaTemporal.toArray());
            // listaTemporal.clear();
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
        else if(vista.getBtnEps().isSelected()) {
            String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
            modelo.setColumnIdentifiers(atributosTabla);
        }
        else if(vista.getBtnFondoP().isSelected()) {
            String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
            modelo.setColumnIdentifiers(atributosTabla);
        }
        else if(vista.getBtnARL().isSelected()) {
            String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
            modelo.setColumnIdentifiers(atributosTabla);
        }
        else if(vista.getBtnCajaCompen().isSelected()) {
            String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
            modelo.setColumnIdentifiers(atributosTabla);
        }
        else if(vista.getBtnEmpresa().isSelected()) {
            String[] atributosTabla = {"NIT", "RAZÓN SOCIAL", "NOMBRE", "TELÉFONO", "DIRECCIÓN", "REP. LEGAL", "CORREO CONT.", "CÓD. ARL", "CÓD. CAJA", "SMLV", "AUX. TRANSP."};
            modelo.setColumnIdentifiers(atributosTabla);
        }
        else if(vista.getBtnDevegno().isSelected()) {
            String[] atributosTabla = {"CÓDIGO", "NOMBRE", "HACE BASE"};
            modelo.setColumnIdentifiers(atributosTabla);
        }
        else if(vista.getBtnDeduccion().isSelected()) {
            String[] atributosTabla = {"CÓDIGO", "NOMBRE"};
            modelo.setColumnIdentifiers(atributosTabla);
        }
    }



    class ActionsHandler implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == vista.getBtnEmpleado()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getEmpleadoDAO().getMapEmpleado()));
            }
            else if(e.getSource() == vista.getBtnEps()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getEpsDAO().getMapEps()));
            }
            else if(e.getSource() == vista.getBtnFondoP()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getFondoDePensionDAO().getMapFondoDePension()));
            }
            else if(e.getSource() == vista.getBtnARL()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getArlDAO().getMapArl()));
            }
            else if(e.getSource() == vista.getBtnCajaCompen()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getCajaDeCompensacionDAO().getMapCajaDeCompensacion()));
            }
            else if(e.getSource() == vista.getBtnEmpresa()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getConfiguracionDeEmpresaDAO().getMapConfiguracionDeEmpresa()));
            }
            else if(e.getSource() == vista.getBtnDevegno()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getConceptoDeDevengoDAO().getMapConceptoDeDevengo()));
            }
            else if(e.getSource() == vista.getBtnDeduccion()) {
                vista.getTablaDatos().setModel(actualizarTableModel(ingenio.getConceptoDeDeduccionDAO().getMapConceptoDeDeduccion()));
            }
            else if(e.getSource() == vista.getBtnAñadir()) {
                if(vista.getBtnEmpleado().isSelected()) {
                    
                }
                else if(vista.getBtnEps().isSelected()) {
                    
                }
                else if(vista.getBtnFondoP().isSelected()) {
                    
                }
                else if(vista.getBtnARL().isSelected()) {
                    
                }
                else if(vista.getBtnCajaCompen().isSelected()) {
                    
                }
                else if(vista.getBtnEmpresa().isSelected()) {
                    
                }
                else if(vista.getBtnDevegno().isSelected()) {
                    
                }
                else if(vista.getBtnDeduccion().isSelected()) {
                    
                }
            }
            else if(e.getSource() == vista.getBtnEliminar()) {
                if(vista.getBtnEmpleado().isSelected()) {
                    
                }
                else if(vista.getBtnEps().isSelected()) {
                    
                }
                else if(vista.getBtnFondoP().isSelected()) {
                    
                }
                else if(vista.getBtnARL().isSelected()) {
                    
                }
                else if(vista.getBtnCajaCompen().isSelected()) {
                    
                }
                else if(vista.getBtnEmpresa().isSelected()) {
                    
                }
                else if(vista.getBtnDevegno().isSelected()) {
                    
                }
                else if(vista.getBtnDeduccion().isSelected()) {
                    
                }
            }
            else if(e.getSource() == vista.getBtnEditar()) {
                if(vista.getBtnEmpleado().isSelected()) {
                    
                }
                else if(vista.getBtnEps().isSelected()) {
                    
                }
                else if(vista.getBtnFondoP().isSelected()) {
                    
                }
                else if(vista.getBtnARL().isSelected()) {
                    
                }
                else if(vista.getBtnCajaCompen().isSelected()) {
                    
                }
                else if(vista.getBtnEmpresa().isSelected()) {
                    
                }
                else if(vista.getBtnDevegno().isSelected()) {
                    
                }
                else if(vista.getBtnDeduccion().isSelected()) {
                    
                }
            }
            else if(e.getSource() == vista.getBtnLimpiar()) {
                if(vista.getBtnEmpleado().isSelected()) {
                    
                }
                else if(vista.getBtnEps().isSelected()) {
                    
                }
                else if(vista.getBtnFondoP().isSelected()) {
                    
                }
                else if(vista.getBtnARL().isSelected()) {
                    
                }
                else if(vista.getBtnCajaCompen().isSelected()) {
                    
                }
                else if(vista.getBtnEmpresa().isSelected()) {
                    
                }
                else if(vista.getBtnDevegno().isSelected()) {
                    
                }
                else if(vista.getBtnDeduccion().isSelected()) {
                    
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
