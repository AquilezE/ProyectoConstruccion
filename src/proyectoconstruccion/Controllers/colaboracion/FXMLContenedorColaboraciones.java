package proyectoconstruccion.Controllers.colaboracion;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.POJO.academia.ExperienciaEducativa;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;
import proyectoconstruccion.modelo.POJO.evidencia.Evidencia;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class FXMLContenedorColaboraciones {

    public VBox vBoxBucket;
    ProfesorExterno profesorExterno1 = new ProfesorExterno(1, "correo1@ejemplo.com", "Nombre1", "ApellidoMaterno1", "ApellidoPaterno1", "Telefono1", 1, 101);
    ProfesorExterno profesorExterno2 = new ProfesorExterno(2, "correo2@ejemplo.com", "Nombre2", "ApellidoMaterno2", "ApellidoPaterno2", "Telefono2", 2, 102);
    ProfesorExterno profesorExterno3 = new ProfesorExterno(3, "correo3@ejemplo.com", "Nombre3", "ApellidoMaterno3", "ApellidoPaterno3", "Telefono3", 3, 103);

    // Crear instancias de ProfesorUV
    ProfesorUV profesorUV1 = new ProfesorUV(1, "correo1@uv.com", "NombreUV1", "ApellidoPaternoUV1", "ApellidoMaternoUV1", "TelefonoUV1", 1, "NumeroPersonal1");
    ProfesorUV profesorUV2 = new ProfesorUV(2, "correo2@uv.com", "NombreUV2", "ApellidoPaternoUV2", "ApellidoMaternoUV2", "TelefonoUV2", 2, "NumeroPersonal2");
    ProfesorUV profesorUV3 = new ProfesorUV(3, "correo3@uv.com", "NombreUV3", "ApellidoPaternoUV3", "ApellidoMaternoUV3", "TelefonoUV3", 3, "NumeroPersonal3");

    // Crear instancias de Evidencia
    Evidencia evidencia1 = new Evidencia(1, 101, 201);
    Evidencia evidencia2 = new Evidencia(2, 102, 202);
    Evidencia evidencia3 = new Evidencia(3, 103, 203);

    // Crear instancias de ExperienciaEducativa
    ExperienciaEducativa experienciaEducativa1 = new ExperienciaEducativa(1, 10, "Descripción1", "NombreEE1");
    ExperienciaEducativa experienciaEducativa2 = new ExperienciaEducativa(2, 20, "Descripción2", "NombreEE2");
    ExperienciaEducativa experienciaEducativa3 = new ExperienciaEducativa(3, 30, "Descripción3", "NombreEE3");

    Colaboracion colaboracion1 = new Colaboracion(1,
            "1semana",
            "Agosto2023-Octubre2024",
            "Agape",
            "Tamil",
            Date.from(Instant.now()),
             Date.from(Instant.now()),
             "Corridita",
             "abierta",
             profesorUV1,
             profesorExterno1,
             experienciaEducativa1,
             evidencia1
            );
    Colaboracion colaboracion2 = new Colaboracion(2,
            "2 semanas",
            "Septiembre2023-Noviembre2024",
            "Betico",
            "Sánscrito",
            Date.from(Instant.now()),
            Date.from(Instant.now()),
            "Cerrada",
            "en curso",
            profesorUV2,
            profesorExterno2,
            experienciaEducativa2,
            evidencia2
    );

    Colaboracion colaboracion3 = new Colaboracion(3,
            "3 semanas",
            "Octubre2023-Diciembre2024",
            "Charlie",
            "Chino",
            Date.from(Instant.now()),
            Date.from(Instant.now()),
            "Corridita",
            "terminada",
            profesorUV3,
            profesorExterno3,
            experienciaEducativa3,
            evidencia3
    );

    ArrayList<Colaboracion> colaboraciones= new ArrayList<>();



    public void IniciarComponentes(){
        colaboraciones.add(colaboracion1);
        colaboraciones.add(colaboracion2);
        colaboraciones.add(colaboracion3);

        for (Colaboracion colaboracion:colaboraciones){
            añadirComponente(colaboracion);
        }

    }

    private void añadirComponente(Colaboracion colaboracion) {
        try {
            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLColaboracionComponente.fxml");
            HBox colaboracionComponente = loader.load();

            FXMLColaboracionComponente controler = loader.getController();
            controler.InicializarComponentes(colaboracion);

            vBoxBucket.getChildren().add(colaboracionComponente);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
