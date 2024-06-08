package proyectoconstruccion.Controllers.constancias;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLConsultarSolicitudesConstancias implements Initializable {

    public Label lbNumeroColaboracion;
    public Label lbMateria;
    public Label lbProfesor;
    public Label lbNombreColaborador;
    public Label lbIdioma;
    public Label lbExperienciaEducativa;
    public Label lbPeriodo;
    public Label lbFechas;
    public TableColumn tvEstudiantes;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnRegresar(ActionEvent actionEvent) {
    }

    public void btnAprobrarConstancia(ActionEvent actionEvent) {
        System.out.println("Boton aprobar constancias");
    }

    public void btnEvidencia(ActionEvent actionEvent) {
        System.out.println("Boton evidencia");
    }

    public void btnSyllabus(ActionEvent actionEvent) {
        System.out.println("Boton syllabus");
    }

    public void btnDescargarListaEstudiantes(ActionEvent actionEvent) {
        System.out.println("Boton descargar lista estudiantes");
    }
}
