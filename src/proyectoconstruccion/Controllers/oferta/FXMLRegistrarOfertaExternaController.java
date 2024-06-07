/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectoconstruccion.Controllers.oferta;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoconstruccion.Controllers.profesorExterno.FXMLRegistrarProfesorExternoController;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.POJO.academia.ExperienciaEducativa;
import proyectoconstruccion.modelo.POJO.profesor.Profesor;

/**
 * FXML Controller class
 *
 * @author unaay
 */
public class FXMLRegistrarOfertaExternaController implements Initializable {
    private ObservableList<String> periodos;
    private ObservableList<Profesor> profesores;
    private ObservableList<ExperienciaEducativa> experiencias;
    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfDuracion;
    @FXML
    private TextField tfIdioma;
    @FXML
    private ComboBox<?> cbPeriodo;
    @FXML
    private ComboBox<?> cbProfesorExterno;
    @FXML
    private ComboBox<?> cbExperienciaEducativa;
    @FXML
    private Label lbNombre;
    @FXML
    private Label lbCorreo;
    @FXML
    private Label lbUniversidad;
    @FXML
    private Label lbPais;
    @FXML
    private Button btnRegistrarProfesorExterno;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void inicializarValores(){
        //btnRegistrarProfesorExterno.setVisible(false);
        lbNombre.setText("");
        lbCorreo.setText("");
        lbUniversidad.setText("");
        lbPais.setText("");
        //cargarPeriodos();
        //cargarProfesExternos();
        //cargarExperienciasEducativas();
    }
    
    private void cargarPeriodos(){
        periodos = FXCollections.observableArrayList();
        //periodos.addAll((ArrayList<String>) CatalogoDAO.obtenerPeriodos().get("periodos"));
        //cbPeriodo.setItems(periodos);
    } 
        
    private void cargarProfesExternos(){
        profesores = FXCollections.observableArrayList();
        //profesores.addAll((ArrayList<Profesor>) CatalogoDAO.obtenerProfesores().get("profesores"));
        //cbProfesorExterno.setItems(profesores);
    } 
    
    private void cargarExperienciasEducativas(){
        experiencias = FXCollections.observableArrayList();
        //experiencias.addAll((ArrayList<Estado>) CatalogoDAO.obtenerEEs().get("experiencias"));
        //cbExperienciaEducativa.setItems(experiencias);
    } 
        
    @FXML
    private void btnClicRegistrarProfesorExterno(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/profesorExterno/FXMLRegistrarProfesorExterno.fxml");
            Parent root = loader.load();
            FXMLRegistrarProfesorExternoController controlador = loader.getController();
            controlador.inicializarValores();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Registrar profesor externo");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnClicRegresar(ActionEvent event) {
        Stage escenarioRegistrarOfertaExterna = (Stage) tfTitulo.getScene().getWindow();
        escenarioRegistrarOfertaExterna.close();
    }

    @FXML
    private void btnClicRegistrarColaboracion(ActionEvent event) {
    }
    
}
