/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectoconstruccion.Controllers.oferta;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author unaay
 */
public class FXMLRegistrarOfertaUVController implements Initializable {

    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfDuracion;
    @FXML
    private TextField tfIdioma;
    @FXML
    private ComboBox<?> cbPeriodo;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbCorreo;
    @FXML
    private Label lbNumPersonal;
    @FXML
    private Label lbCelular;
    @FXML
    private Label lbAreaAcademica;
    @FXML
    private Label lbCarrera;
    @FXML
    private Label lbExperienciaEducativa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnClicRegresar(ActionEvent event) {
        Stage escenarioRegistrarOfertaUV = (Stage) tfTitulo.getScene().getWindow();
        escenarioRegistrarOfertaUV.close();
    }

    @FXML
    private void btnClicRegistrarOferta(ActionEvent event) {
        
    }
    
}
