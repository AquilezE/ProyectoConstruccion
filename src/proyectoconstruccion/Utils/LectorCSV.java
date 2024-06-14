package proyectoconstruccion.Utils;

import proyectoconstruccion.modelo.POJO.Estudiante;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LectorCSV {


    public List<Estudiante> leerCSV(InputStream inputStream) {
        List<Estudiante> estudiantes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");
                if (campos.length == 4) {
                    String matricula = campos[0].trim();
                    String nombreCompleto = campos[1].trim();
                    String[] nombreApellidos = nombreCompleto.split("\\s+");
                    String nombre = nombreApellidos[0];
                    String apellidoPaterno = nombreApellidos[1];
                    String apellidoMaterno = nombreApellidos.length > 2 ? nombreApellidos[2] : "";
                    int calificacion = Integer.parseInt(campos[2].trim());
                    int faltas = Integer.parseInt(campos[3].trim());

                    Estudiante estudiante = new Estudiante(null, nombre, apellidoMaterno, apellidoPaterno, matricula, calificacion, faltas);
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
