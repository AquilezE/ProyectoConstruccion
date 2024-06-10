package proyectoconstruccion.Controllers.evidencias;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLSubirEvidenciasController implements Initializable {
    public TextField tfEvidencias;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnSubir(ActionEvent actionEvent) {
        System.out.println("Boton subir");
    }

    public void btnAgregarMasTarde(ActionEvent actionEvent) {
        System.out.println("Boton agregar mas tarde");
    }
}
