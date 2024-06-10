package proyectoconstruccion.Controllers.colaboracion;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDetallesColaboracionTerminadoController implements Initializable {
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnRegresar(ActionEvent actionEvent) {
        System.out.println("Boton de regresar");
    }

    public void btnAprobarConstancia(ActionEvent actionEvent) {
        System.out.println("Boton de aprobar constancia");
    }

    public void btnEvidencia(ActionEvent actionEvent) {
        System.out.println("Bton de evidencia");
    }

    public void btnSyllabus(ActionEvent actionEvent) {
        System.out.println("Boton de syllabus");
    }

    public void btnDescargarListaEstudiantes(ActionEvent actionEvent) {
        System.out.println("Boton de descargar lista");
    }
}
