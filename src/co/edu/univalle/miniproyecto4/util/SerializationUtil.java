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
 Herramienta de serialización de objetos. Leer y crear archivos binarios para los modelos.
*/

package co.edu.univalle.miniproyecto4.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil {
    
  public static void serializeObject(Object obj, String filePath) {
    try {
      FileOutputStream fileOut = new FileOutputStream(filePath);
      ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
      objectOut.writeObject(obj);
      objectOut.close();
      fileOut.close();
//        System.out.println("Object serialized successfully.");
    } catch (IOException e) {
      e.printStackTrace();
    }
  } 

  public static Object deserializeObject(String filePath) {
    try {
      FileInputStream fileIn = new FileInputStream(filePath);
      ObjectInputStream objectIn = new ObjectInputStream(fileIn);
      Object obj = objectIn.readObject();
      objectIn.close();
      fileIn.close();
//        System.out.println("Object deserialized successfully.");
      return obj;
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
      return null;
    }
  }
  
  public static boolean isSerializedObjectExists(String filePath) {
    File file = new File(filePath);
    return file.exists();
  }
}
