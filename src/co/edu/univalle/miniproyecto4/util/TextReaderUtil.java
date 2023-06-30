package co.edu.univalle.miniproyecto4.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/* --------------- Retorna Matriz de resultados (trabajos realizados) por fichaEmpleado ------------------- */
public class TextReaderUtil {
  public static List<ArrayList<String>> getListaCantidadTrabajada(String rutaArchivo, int fichaEmpleado) {
    List<ArrayList<String>> matrizRespuesta = new ArrayList<>();
    List<String> lista = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String ficha = line.substring(0, 4).trim();
        if(ficha.equals(fichaEmpleado+"")) {
          String fechaCorte = line.substring(4, 12).trim();
          String haciendaSuerte = line.substring(12, 18).trim();
          String toneladaCorte = line.substring(22, 27).trim();
          String tipoCana = line.substring(27, 28).trim();
          String diaCorte = line.substring(28).trim();

          lista.add(ficha);
          lista.add(fechaCorte);
          lista.add(haciendaSuerte);
          lista.add(toneladaCorte);
          lista.add(tipoCana);
          lista.add(diaCorte);

          matrizRespuesta.add((ArrayList<String>)lista);

          lista.clear();
          
          // System.out.println("Ficha: " + ficha);
          // System.out.println("Fecha Corte: " + fechaCorte);
          // System.out.println("Hacienda Suerte: " + haciendaSuerte);
          // System.out.println("Tonelada Corte: " + toneladaCorte);
          // System.out.println("Tipo Caña: " + tipoCana);
          // System.out.println("Día de Corte: " + diaCorte);
          // System.out.println("-----------");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return matrizRespuesta;
  }

  /* --------------- INSERTE COMENTARIO ------------------- */
  public static boolean isListaCantidadTrabajada(String rutaArchivo, String lineaPagada) {
    try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
      String line;
      while ((line = reader.readLine()) != null) {
        if(line.equals(lineaPagada)) {
          return true;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return false;
  }

  /* --------------- INSERTE COMENTARIO ------------------- */
  public static void appendLineaArchivo(String rutaArchivo, String linea) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
      writer.write(linea);
      writer.newLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /* --------------- INSERTE COMENTARIO ------------------- */
  public static <T> void printInformacionModeloKeyInt(String rutaArchivo, Map<Integer, T> mapa) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
      Set<Map.Entry<Integer,T>> entrySet = mapa.entrySet();
      for(Map.Entry<Integer,T> entry : entrySet) {
        writer.write(entry.getValue().toString());
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /* --------------- INSERTE COMENTARIO ------------------- */
  public static <T> void printInformacionModeloKeyStr(String rutaArchivo, Map<String, T> mapa) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
      Set<Map.Entry<String,T>> entrySet = mapa.entrySet();
      for(Map.Entry<String,T> entry : entrySet) {
        writer.write(entry.getValue().toString());
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
