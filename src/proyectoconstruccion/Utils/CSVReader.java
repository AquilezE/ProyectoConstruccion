package proyectoconstruccion.Utils;
import proyectoconstruccion.modelo.POJO.Estudiante;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public List<Estudiante> readCSV(File file) {
        List<Estudiante> estudiantes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            // Saltamos la primera línea que contiene los encabezados
            br.readLine();
            // Lee cada línea del archivo CSV
            while ((line = br.readLine()) != null) {
                // Divide la línea en campos usando coma como delimitador
                String[] campos = line.split(",\\s*");
                if (campos.length == 4) { // Asumiendo que hay cuatro campos: Matrícula, Nombre completo, Calificación, Número de faltas
                    String matricula = campos[0].trim();
                    String nombreCompleto = campos[1].trim();
                    // Dividimos el nombre completo en nombre y apellidos
                    String[] nombreApellidos = nombreCompleto.split("\\s+");
                    String nombre = nombreApellidos[0];
                    String apellidoPaterno = nombreApellidos[1];
                    String apellidoMaterno = nombreApellidos.length > 2 ? nombreApellidos[2] : "";
                    int calificacion = Integer.parseInt(campos[2].trim());
                    int faltas = Integer.parseInt(campos[3].trim());

                    // Crea un objeto Estudiante y agrégalo a la lista
                    Estudiante estudiante = new Estudiante(null, nombre, apellidoMaterno, apellidoPaterno, matricula);
                    estudiantes.add(estudiante);
                } else {
                    System.err.println("Formato de línea incorrecto: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return estudiantes;
    }
}
