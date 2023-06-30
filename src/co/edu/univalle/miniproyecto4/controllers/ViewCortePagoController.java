package co.edu.univalle.miniproyecto4.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import co.edu.univalle.miniproyecto4.util.TextReaderUtil;

public class ViewCortePagoController {
  private List<ArrayList<String>> matrizPendiente;
  private List<ArrayList<String>> matrizFacturado;
  private DefaultTableModel modeloTabla;
  private String apartadoFormulario;

  public DefaultTableModel actualizarTableModel(int fichaEmpleado) {
    List<ArrayList<String>> matrizTemporal = new ArrayList<>();

    modeloTabla.setRowCount(0);

    String[] atributosTabla = {"FECHA", "TONELADAS", "TIPO CAÑA", "DÍA"};
    modeloTabla.setColumnIdentifiers(atributosTabla);

    if(apartadoFormulario.equals("Pendiente")) {
      matrizPendiente = TextReaderUtil.getListaCantidadTrabajada("PagosPendientes.txt", fichaEmpleado);
      matrizTemporal = matrizPendiente;
    }
    else if(apartadoFormulario.equals("Facturado")) {
      matrizFacturado = TextReaderUtil.getListaCantidadTrabajada("PagosFacturados.txt", fichaEmpleado);
      matrizTemporal = matrizFacturado;
    }

    for(int i = 0; i < matrizTemporal.size(); i++) {
      // Debe corresponder con: "FECHA" (1), "TONELADAS" (3), "TIPO CAÑA" (4), "DÍA" (5).
      String[] arregloTemporal = {matrizTemporal.get(i).get(1), matrizTemporal.get(i).get(3), matrizTemporal.get(i).get(4), matrizTemporal.get(i).get(5)};
      modeloTabla.addRow(arregloTemporal);
    }

    return modeloTabla;
  }

  public String listToString(List<String> lista) {
    String listaString = "";
    for(int i = 0; i < lista.size(); i++) {
      listaString+= lista.get(i);
    }
    return listaString;
  }

}
