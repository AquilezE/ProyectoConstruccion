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

public class FXMLDashboardController implements Initializable {


    public Tab tabSocilicitudes;
    public Tab tabNumeralia;
    public TabPane tabPane;
    public BorderPane bdPaneColaboraciones;
    public BorderPane bdPaneOfertasColab;
    public BorderPane bdPaneSolicitudesConstancias;
    public BorderPane bdPaneNumerialia;



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
}
