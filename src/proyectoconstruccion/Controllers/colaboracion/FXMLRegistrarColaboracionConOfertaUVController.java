package proyectoconstruccion.Controllers.colaboracion;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoconstruccion.Controllers.profesorExterno.FXMLRegistrarProfesorExternoController;
import proyectoconstruccion.Utils.Utils;

public class FXMLRegistrarColaboracionConOfertaUVController implements Initializable {

    @FXML
    private Label lbNombreProfesorUV;
    @FXML
    private Label lbTitulo;
    @FXML
    private Label lbCorreoElectronicoProfeUV;
    @FXML
    private Label lbNumPersonal;
    @FXML
    private ComboBox<?> cbIdioma;
    @FXML
    private Label lbPeriodo;
    @FXML
    private Label lbExperienciaEducativa;
    @FXML
    private ComboBox<?> cbProfesorExterno;
    @FXML
    private Label lbNombreProfesorExterno;
    @FXML
    private Label lbPais;
    @FXML
    private Label lbCorreoElectronicoProfeExterno;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnClicRegresar(ActionEvent event) {
    }

    @FXML
    private void btnClicRegistrarColaboracion(ActionEvent event) {
        Stage escenarioRegistrarColaboracionConOfertaUV = (Stage) lbTitulo.getScene().getWindow();
        escenarioRegistrarColaboracionConOfertaUV.close();
    }

    @FXML
    private void btnClicRegistrarProfesorExterno(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/profesorExterno/FXMLRegistrarProfesorExterno.fxml");
            Parent root = loader.load();
            FXMLRegistrarProfesorExternoController controlador = loader.getController();
            //controlador.inicializarValores(this);
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Registrar profesor externo");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
