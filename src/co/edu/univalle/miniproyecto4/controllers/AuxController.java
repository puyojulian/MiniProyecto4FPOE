package co.edu.univalle.miniproyecto4.controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import co.edu.univalle.miniproyecto4.models.ModelInterface;

public class AuxController {
  /* --------------- MUESTREO: POPULAR COMBOBOX CON BASE EN MAPA (MULTIPLES ITEMS) ------------------- */
  public static <T extends ModelInterface> void popularNombreComboBox(JComboBox<String> comboBox, Map<Integer, T> mapa) {
      comboBox.removeAllItems();
      comboBox.addItem("Seleccionar");
      if(mapa.size() > 0) {
          Set<Map.Entry<Integer, T>> entrySetMapa = mapa.entrySet();
          for (Map.Entry<Integer, T> entry : entrySetMapa) {
              comboBox.addItem(entry.getValue().getNombre());
          }
      }
  }

  /* --------------- MUESTREO: POPULAR COMBOBOX CON BASE EN STRING (UNICO ITEM)------------------- */
  public static void popularNombreComboBox(JComboBox<String> comboBox, String elemento) {
      comboBox.addItem(elemento);
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
          if(entry.getValue().getNombre().equals(nombre)) {
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
}
