package proyectoconstruccion.Controllers.listaEstudiantes;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLSubirListaEstudiantes implements Initializable {
    public VBox vbPrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnAñadir(ActionEvent actionEvent) {
        System.out.println("Añadir boton");
    }

    public void btnFinalizar(ActionEvent actionEvent) {
        System.out.println("Finalizar boton");
    }
}
