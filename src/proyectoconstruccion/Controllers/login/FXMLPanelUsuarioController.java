package proyectoconstruccion.Controllers.login;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import proyectoconstruccion.Controllers.RefreshserUtils;
import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.Utils.Sesion;
import proyectoconstruccion.Utils.Utils;

import javax.rmi.CORBA.Util;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLPanelUsuarioController implements Initializable {

    public Label lbUsuario;
    public Label lbCorreo;
    public Label lbTelefono;
    public Label lbNumeroPersonal;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (Sesion.getInstancia().getRol().equals(Constantes.PROFESOR)) {
            lbUsuario.setText(Sesion.getInstancia().getProfesorUsuario().getNombreEntero());
            lbCorreo.setText(Sesion.getInstancia().getProfesorUsuario().getCorreoElectronico());
            lbTelefono.setText(Sesion.getInstancia().getProfesorUsuario().getTelefono());
            lbNumeroPersonal.setText(Sesion.getInstancia().getProfesorUsuario().getNumeroPersonal());
        }
    }

    private void cerrarSesion(){
        limpiarSesion();
        limpiarFiltros();
    }

    private void limpiarSesion(){
        Sesion.getInstancia().setProfesorUsuario(null);
        Sesion.getInstancia().setRol(null);
    }

    private void limpiarFiltros(){
        RefreshserUtils.setOfertasController(null);
        RefreshserUtils.setColaboracionesController(null);
        RefreshserUtils.setOfertasBusquedaCache(null);
        RefreshserUtils.setColaboracionesBusquedaCache(null);
    }

    public void btnCerrarSesion(ActionEvent actionEvent) {

        cerrarSesion();

        try {
            Stage newStage = new Stage();
            FXMLLoader loader= Utils.obtenerLoader("Views/login/FXMLInicioSesion.fxml");
            Parent root = loader.load();
            root.getStylesheets().add(proyectoconstruccion.AppStartup.class.getResource("Views/style.css").toExternalForm());

            Scene scene = new Scene(root);

            newStage.setScene(scene);
            newStage.show();

            Utils.cerrarVentana(actionEvent);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
