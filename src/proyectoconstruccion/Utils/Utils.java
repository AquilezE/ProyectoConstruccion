package proyectoconstruccion.Utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class Utils {
    public static void mostrarAlertaSimple(String titulo, String mesnaje, Alert.AlertType tipo){
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mesnaje);
        alerta.showAndWait();
    }

    public static boolean mostrarAlertaConfirmacion(String titulo, String mesnaje){
        Alert alerta = new Alert(Alert.AlertType.CONFIRMATION);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mesnaje);
        Optional<ButtonType> botonSeleccionado = alerta.showAndWait();
        return (botonSeleccionado.get()==ButtonType.OK);
    }

    public static FXMLLoader obtenerLoader(String ruta){
        return new FXMLLoader(proyectoconstruccion.AppStartup.class.getResource(ruta));
    }
}
