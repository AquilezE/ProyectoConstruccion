package proyectoconstruccion.Controllers.dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import proyectoconstruccion.Controllers.colaboracion.FXMLContenedorColaboraciones;
import proyectoconstruccion.Controllers.oferta.FXMLContenedorOfertas;
import proyectoconstruccion.Utils.Sesion;
import proyectoconstruccion.Utils.Utils;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import proyectoconstruccion.Controllers.oferta.FXMLRegistrarOfertaExternaController;

public class FXMLDashboardController implements Initializable {


    public Tab tabSocilicitudes;
    public Tab tabNumeralia;
    public TabPane tabPane;
    public BorderPane bdPaneColaboraciones;
    public BorderPane bdPaneOfertasColab;
    public BorderPane bdPaneSolicitudesConstancias;
    public BorderPane bdPaneNumerialia;
    @FXML
    private Button btnEliminarrFiltros;



    public void initialize(URL url, ResourceBundle rb) {
        if(Sesion.getInstancia().getRol()=="profesor"){
            //quita Solicitud de constancias
            tabPane.getTabs().remove(3);
            //quita Solicitud de
            tabPane.getTabs().remove(2);
        }
    }

    public void IniciarComponentes(){

    }

    @FXML
    public void btnVerColabs(ActionEvent actionEvent) {
        try{

            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLContenedorColaboraciones.fxml");
            AnchorPane contenedorColaboraciones = loader.load();
            FXMLContenedorColaboraciones controller = loader.getController();

            controller.InicializarComponentes();

            bdPaneColaboraciones.setCenter(contenedorColaboraciones);

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    @FXML
    public void btnVerOfertas(ActionEvent actionEvent) {
        try{
            FXMLLoader loader= Utils.obtenerLoader("Views/oferta/FXMLContenedorOfertas.fxml");
            AnchorPane contenedorColaboraciones = loader.load();
            FXMLContenedorOfertas controller = loader.getController();

            controller.InicializarComponentes();
            bdPaneOfertasColab.setCenter(contenedorColaboraciones);
        }catch(IOException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @FXML
    private void btnClicRegistrarOfertaExterna(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/oferta/FXMLRegistrarOfertaExterna.fxml");
            Parent root = loader.load();
            FXMLRegistrarOfertaExternaController controlador = loader.getController();
            controlador.inicializarValores();
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Registrar oferta de colaboración externa");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
