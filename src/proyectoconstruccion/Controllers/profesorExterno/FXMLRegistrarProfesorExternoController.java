/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectoconstruccion.Controllers.profesorExterno;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import proyectoconstruccion.modelo.POJO.Idioma;

/**
 * FXML Controller class
 *
 * @author unaay
 */
public class FXMLRegistrarProfesorExternoController implements Initializable {
    private ObservableList<Idioma> idiomas;
    @FXML
    private ComboBox<?> cbIdioma;
    @FXML
    private TextField tfNombre;
    @FXML
    private TextField tfCorreo;
    @FXML
    private TextField tfUniversidad;
    @FXML
    private TextField tfTelefono;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void inicializarValores() {
        //cargarIdiomas();
    }
    
    public void cargarIdiomas(){
        idiomas = FXCollections.observableArrayList();
        //idiomas.addAll((ArrayList<String>) CatalogoDAO.obtenerIdiomas().get("idiomas"));
        //cbIdioma.setItems(idiomas);
    }
}
