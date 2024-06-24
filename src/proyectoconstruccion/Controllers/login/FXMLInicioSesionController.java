package proyectoconstruccion.Controllers.login;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyectoconstruccion.Controllers.panelPrincipal.FXMLPanelPrincipalController;
import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.Utils.Sesion;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.AutenticacionDAO;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

public class FXMLInicioSesionController implements Initializable {
    @FXML
    public TextField tfUsuario;
    @FXML
    public Button btIniciarSesion;
    @FXML
    private PasswordField pfContrasenia;



    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void btIniciarSesion(ActionEvent actionEvent) {
       String contrasenia = pfContrasenia.getText();
       String usuario = tfUsuario.getText();

       if(validarCampos(usuario, contrasenia)){
           HashMap<String,Object> respuesta = AutenticacionDAO.iniciarSesion(usuario, contrasenia);

           if (!((Boolean) respuesta.get(Constantes.KEY_ERROR))){

              Sesion.getInstancia().setProfesorUsuario((ProfesorUV) respuesta.get("profesor"));

              if (respuesta.get("profesor")==null){
                  Sesion.getInstancia().setRol(Constantes.ADMIN);
                  Utils.mostrarAlertaSimple("Bienvenido","Bienvenido al sistema Coordinador", Alert.AlertType.INFORMATION);
              }else{
                  Sesion.getInstancia().setRol(Constantes.PROFESOR);
                  Utils.mostrarAlertaSimple("Bienvenido","Bienvenido al sistema Profesor "+((ProfesorUV) respuesta.get("profesor")).getNombre(), Alert.AlertType.INFORMATION);
              }

            irAPanelPrincipal();

          }else{
               Utils.mostrarAlertaSimple("Error", (String) respuesta.get(Constantes.KEY_MENSAJE), Alert.AlertType.ERROR);
           }
       }else{
           Utils.mostrarAlertaSimple("Error","Algun campo esta vacio o es invalido", Alert.AlertType.ERROR);
       }
    }

    public void irAPanelPrincipal(){
        try{
            Stage stage = (Stage) btIniciarSesion.getScene().getWindow();
            FXMLLoader loader = Utils.obtenerLoader("Views/panelPrincipal/FXMLPanelPrincipal.fxml");
            Parent root = loader.load();
            FXMLPanelPrincipalController controller = loader.getController();
            root.getStylesheets().add(proyectoconstruccion.AppStartup.class.getResource("Views/style.css").toExternalForm());
            controller.iniciarComponentes();

            Scene escenaPanelPrincipal = new Scene(root);
            stage.setScene(escenaPanelPrincipal);
            stage.show();

        }catch (IOException e){
            e.printStackTrace();
            System.out.println("Error"+e.getMessage());
        }
    }

    public boolean validarCampos(String usuario,String contrasenia) {
        if (contrasenia == null || usuario == null) {
            return false;
        }
        if (usuario.trim().length() < 3 || usuario.trim().length() > 50) {
            return false;
        }
        if (contrasenia.trim().length() < 5 || contrasenia.trim().length() > 50) {
            return false;
        }
        return true;
    }

}
