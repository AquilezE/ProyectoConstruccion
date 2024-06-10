package proyectoconstruccion.Controllers.oferta.EditarOFerta;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoconstruccion.Utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLEditarOfertaColaboracionController implements Initializable {


    public Label idTitulo;
    public Label lbIdioma;
    public Label lbPeriodo;
    public Label lbDuracion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void btnCancelar(ActionEvent actionEvent) {
        Stage stage = (Stage) idTitulo.getScene().getWindow();
        stage.close();
    }


    public void btnEditar(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = Utils.obtenerLoader("Views/oferta/EditarOferta/FXMLEdicionOfertaColaboracion.fxml");
            AnchorPane root = loader.load();
            FXMLEdicionOfertaColaboracionController controller = loader.getController();
            // Puedes pasar datos a la nueva ventana si es necesario

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}


