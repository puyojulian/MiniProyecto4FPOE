/**
 Archivo: SerializationUtil.java
 Proyecto IV - Sistema de liquididacion de un Ingenio
 24 de junio de 2023

 Autores:
  @author Julian Puyo
  @author Sebastian Orrego
  @author Juan David Rodriguez
  @author Manuel Cardoso
  @author Luis Carlos Lucero
 
 Intencion:
 Herramienta para lectura y escritura de archivos de Texto.
*/

package co.edu.univalle.miniproyecto4.util;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import co.edu.univalle.miniproyecto4.controllers.AuxController;
import co.edu.univalle.miniproyecto4.models.Empleado;
import co.edu.univalle.miniproyecto4.repository.EmpleadoDAO;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextReaderUtil {

  /* --------------- OBTIENE MATRIZ DE LISTAS CON INFORMACIÓN DE CORTE TRABAJADO POR EMPLEADO ------------------- */
  public static List<ArrayList<String>> getListaCantidadTrabajada(String rutaArchivo, int fichaEmpleado) {
    List<ArrayList<String>> matrizRespuesta = new ArrayList<>();
    
    try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
      String line;
      while ((line = reader.readLine()) != null) {
        List<String> lista = new ArrayList<>();
        String ficha = line.substring(0, 4).trim();
        if(Integer.parseInt(ficha) == fichaEmpleado) {
          String fechaCorte = line.substring(4, 12).trim();
          String haciendaSuerte = line.substring(12, 18).trim();
          String toneladaCorte = line.substring(22, 27).trim();
          String tipoCana = line.substring(27, 28).trim();
          String diaCorte = line.substring(28,29).trim();

          lista.add(ficha);
          lista.add(fechaCorte);
          lista.add(haciendaSuerte);
          lista.add(toneladaCorte);
          lista.add(tipoCana);
          lista.add(diaCorte);

          matrizRespuesta.add((ArrayList<String>)lista);

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

  /* --------------- OBTIENE MATRIZ DE LISTAS CON INFORMACIÓN DE CORTE TRABAJADO TODOS------------------- */
  public static List<ArrayList<String>> getListaCantidadTrabajadaTodos(String rutaArchivo) {
    List<ArrayList<String>> matrizRespuesta = new ArrayList<>();
    
    try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
      String line;
      while ((line = reader.readLine()) != null) {
        List<String> lista = new ArrayList<>();
        String ficha = line.substring(0, 4).trim();
        String fechaCorte = line.substring(4, 12).trim();
        String haciendaSuerte = line.substring(12, 18).trim();
        String toneladaCorte = line.substring(22, 27).trim();
        String tipoCana = line.substring(27, 28).trim();
        String diaCorte = line.substring(28,29).trim();

        lista.add(ficha);
        lista.add(fechaCorte);
        lista.add(haciendaSuerte);
        lista.add(toneladaCorte);
        lista.add(tipoCana);
        lista.add(diaCorte);

        if(lista.size() == 6) {
          matrizRespuesta.add((ArrayList<String>)lista);
        }
        
        // System.out.println(line.length()+"");
        // System.out.println("Ficha: " + ficha);
        // System.out.println("Fecha Corte: " + fechaCorte);
        // System.out.println("Hacienda Suerte: " + haciendaSuerte);
        // System.out.println("Tonelada Corte: " + toneladaCorte);
        // System.out.println("Tipo Caña: " + tipoCana);
        // System.out.println("Día de Corte: " + diaCorte);
        // System.out.println("-----------");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    // System.out.println(matrizRespuesta);
    return matrizRespuesta;
  }

  /* --------------- VERIFICA SI LA LÍNEA ESTÁ EN EL ARCHIVO ------------------- */
  public static boolean isLineaCantidadTrabajada(String rutaArchivo, String lineaPagada) {
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

  /* --------------- AGREGA AL ARCHIVO UNA NUEVA LINEA ------------------- */
  public static void appendLineaArchivo(String rutaArchivo, String linea) {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
      writer.write(linea);
      writer.newLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /* --------------- EXPORTA ARCHIVO DE TEXTO CON INFORMACIÓN DE OBJETOS DEL PAQUETE MODELO (SEPARADO POR COMAS) CON CLAVE INTEGER------------------- */
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

  /* --------------- EXPORTA ARCHIVO DE TEXTO CON INFORMACIÓN DE OBJETOS DEL PAQUETE MODELO (SEPARADO POR COMAS) CON CLAVE STRING------------------- */
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

  /* --------------- ELIMINA LA LINEA DEL ARCHIVO QUE COINCIDA CON LA PROVISTA ------------------- */
  public static void borrarLinea(String rutaArchivo, String lineaABorrar) {
    try {
      File inputFile = new File(rutaArchivo);
      File tempFile = new File("temp.txt");
      BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo)); 
      BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
      String line;
      while ((line = reader.readLine()) != null) {
        if(!line.replaceAll("\\s", "").equals(lineaABorrar)) {
          writer.write(line);
          writer.newLine();
        }
      }

      reader.close();
      writer.close();

      if (inputFile.delete()) {
        tempFile.renameTo(inputFile);
        System.out.println("Linea borrada exitosamente");
      } 
      else {
        System.out.println("Intento de borrar la linea fallido.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /* --------------- INCIALIZACIÓN: INSTANCIA EMPLEADOS A BASE DE ARCHIVO DE TEXTO ------------------- */
  public static void instanciarEmpleadosTxt(String rutaArchivo, EmpleadoDAO empleadoDAO){
    List<String> listaParametros = new ArrayList<>();
    try(BufferedReader brEmpleados = new BufferedReader(new FileReader(rutaArchivo))) {
      String linea;
      while((linea = brEmpleados.readLine()) != null) {
        StringTokenizer tokenizer = new StringTokenizer(linea,",");
        while(tokenizer.hasMoreTokens()) {
          String token = tokenizer.nextToken();
          listaParametros.add(token);
        }
        if(listaParametros.size() == 11) {
          empleadoDAO.addEmpleado(new Empleado((String) listaParametros.get(0), 
            listaParametros.get(1).trim(),
            listaParametros.get(2).trim(),
            listaParametros.get(3).trim(),
            Integer.parseInt(listaParametros.get(4).trim()),
            Integer.parseInt(listaParametros.get(5).trim()),
            AuxController.crearFecha(listaParametros.get(6).trim()),
            AuxController.crearFecha(listaParametros.get(7).trim()),
            AuxController.crearFecha(listaParametros.get(8).trim()),
            Integer.parseInt(listaParametros.get(9).trim()),
            listaParametros.get(10).trim()));
        }
        listaParametros.clear();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
