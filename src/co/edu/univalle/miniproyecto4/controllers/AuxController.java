package co.edu.univalle.miniproyecto4.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;

import co.edu.univalle.miniproyecto4.models.ModelInterface;

public class AuxController {
  /* --------------- MUESTREO: POPULAR COMBOBOX CON BASE EN MAPA (MULTIPLES ITEMS) ------------------- */
  public static <T extends ModelInterface> List<Integer> popularNombreComboBox(JComboBox<String> comboBox, Map<Integer, T> mapa) {
    List<Integer> keyList = new ArrayList<>();
    comboBox.removeAllItems();
    comboBox.addItem("Seleccionar");
    keyList.add(-1);
    if(mapa.size() > 0) {
      Set<Map.Entry<Integer, T>> entrySetMapa = mapa.entrySet();
      for (Map.Entry<Integer, T> entry : entrySetMapa) {
          comboBox.addItem(entry.getValue().getNombre());
          keyList.add((Integer) entry.getKey());
      }
    }
    return keyList;
  }

  /* --------------- MUESTREO: POPULAR COMBOBOX CON BASE EN STRING (UNICO ITEM)------------------- */
  public static void popularNombreComboBox(JComboBox<String> comboBox, String elemento) {
    int itemCount = comboBox.getItemCount();
    for (int i = 0; i < itemCount; i++) {
      String currentItem = comboBox.getItemAt(i);
      if (currentItem.equals(elemento)) {
          comboBox.setSelectedIndex(i);
          break;
      }
      else {
        comboBox.setSelectedIndex(0);
      }
    }
  }

  /* --------------- RESPUESTA: MUESTRA MENSAJE TEMPORAL ------------------- */
  public static void mensajeTemporal(String mensaje, String titulo, int milisegundos) {
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

  /* --------------- VERIFICACIÓN: VERIFICAR SI EL NOMBRE ESTÁ REGISTRADO ------------------- */
  public static <T extends ModelInterface> boolean isNombreUnico(String nombre, Map<Integer, T> mapa) {
    Set<Map.Entry<Integer, T>> entrySetMapa = mapa.entrySet();

    for (Map.Entry<Integer, T> entry : entrySetMapa){
      if(entry.getValue().getNombre().replaceAll("\\s", "").equalsIgnoreCase(nombre.replaceAll("\\s", ""))) {
        return false;
      }
    }
    return true;
  }

  /* --------------- VERIFICACIÓN: VERIFICA SI EL CÓDIGO ESTÁ REGISTRADO ------------------- */
  public static <T extends ModelInterface> boolean isCodigoUnico(int codigo, Map<Integer, T> mapa) {
    Set<Map.Entry<Integer, T>> entrySetMapa = mapa.entrySet();

    for (Map.Entry<Integer, T> entry : entrySetMapa){
      if(entry.getValue().getCodigo() == codigo) {
        return false;
      }
    }
    return true;
  }

  /* --------------- VERIFICACIÓN: VERIFICA SI LA STRING ES NUMÉRICA ------------------- */
  public static boolean esNumerico(String cadena) {
    return cadena.matches("\\d+");
  }

  /* --------------- UTILIDAD: RETORNA CODIGO BASADOS EN EL NOMBRE ------------------- */
  public static <T extends ModelInterface> int getCodByNombre(String nombre, Map<Integer, T> mapa) {
    Set<Map.Entry<Integer, T>> entrySetMapa = mapa.entrySet();

    for (Map.Entry<Integer, T> entry : entrySetMapa){
      if(entry.getValue().getNombre().equals(nombre)) {
        return entry.getValue().getCodigo();
      }
    }
    return 0;
  }

  /* --------------- FORMATO/FECHAS: LOCALDATE TO STRING ------------------- */
  public static String fechaToString(LocalDate fecha) {
    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return fecha.format(formateador);
  }

  /* --------------- FORMATO/FECHAS: STRING TO LOCALDATE ------------------- */
  public static LocalDate crearFecha(String fecha) {
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

  /* --------------- FORMATO: RETORNA LISTA CON ELEMENTOS DE JLISTA ------------------- */
  public static List<String> getNombresLista(JList<String> jlist) {
    ListModel<String> listModel = jlist.getModel();
    List<String> lista = new ArrayList<>();
    for (int i = 0; i < listModel.getSize(); i++) {
      String element = listModel.getElementAt(i);
      lista.add(element);
    }
    return lista;
  }

  /* --------------- FORMATO: RETORNA LISTA CON CÓDIGOS DESDE ELEMENTOS DE JLISTA ------------------- */
  public static <T extends ModelInterface> List<Integer> getCodigosJLista(JList<String> jlist, Map<Integer, T> mapa) {
    ListModel<String> listModel = jlist.getModel();
    List<Integer> lista = new ArrayList<>();
    for (int i = 0; i < listModel.getSize(); i++) {
      String element = listModel.getElementAt(i);
      lista.add(getCodByNombre(element, mapa));
    }
    return lista;
  }
  
  /* --------------- VERIFICACIÓN: VERIFICA SI EL ELEMENTO ESTÁ EN LA JLISTA ------------------- */
  public static boolean elementoYaEnLista(DefaultListModel<String> listModel, JComboBox<String> jComboBox) {
    if(!listModel.isEmpty()) {
      for (int i = 0; i < listModel.getSize(); i++) {
        if(listModel.getElementAt(i).equals(jComboBox.getSelectedItem())) {
          return true;
        }
      }
    }
    return false;
  }
}
