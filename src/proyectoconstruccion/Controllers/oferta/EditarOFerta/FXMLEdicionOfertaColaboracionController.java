package proyectoconstruccion.Controllers.oferta.EditarOFerta;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLEdicionOfertaColaboracionController implements Initializable {
    public TextField tfTiturlo;
    public TextField tfPeriodo;
    public TextField tfIdioma;
    public TextField tdDuracion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnFinalizar(ActionEvent actionEvent) {

        Stage stage = (Stage) tfTiturlo.getScene().getWindow();

        stage.close();

    }
}
