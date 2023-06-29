package co.edu.univalle.miniproyecto4.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TextReaderUtil {
    public static void parseFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String ficha = line.substring(0, 4).trim();
                String fechaCorte = line.substring(4, 12).trim();
                String haciendaSuerte = line.substring(12, 18).trim();
                String toneladaCorte = line.substring(22, 27).trim();
                String tipoCana = line.substring(27, 28).trim();
                String diaCorte = line.substring(28).trim();

                // Process the extracted values as needed
                System.out.println("Ficha: " + ficha);
                System.out.println("Fecha Corte: " + fechaCorte);
                System.out.println("Hacienda Suerte: " + haciendaSuerte);
                System.out.println("Tonelada Corte: " + toneladaCorte);
                System.out.println("Tipo Caña: " + tipoCana);
                System.out.println("Día de Corte: " + diaCorte);
                System.out.println("-----------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
