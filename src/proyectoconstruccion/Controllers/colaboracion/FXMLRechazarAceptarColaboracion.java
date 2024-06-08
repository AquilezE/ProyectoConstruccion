package proyectoconstruccion.Controllers.colaboracion;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLRechazarAceptarColaboracion implements Initializable {
    public Label lbNumeroColaboracion;
    public Label lbTitulo;
    public Label lbNombreProfesor;
    public Label lbNombreColaborador;
    public Label lbIdioma;
    public Label lbPeriodo;
    public Label lbFechas;
    public Label lbExperienciaEducativa;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnAutorizar(ActionEvent actionEvent) {
        System.out.println("Boton autorizar");
    }

    public void btnDenegar(ActionEvent actionEvent) {
        System.out.println("Boton denegar");
    }
}
