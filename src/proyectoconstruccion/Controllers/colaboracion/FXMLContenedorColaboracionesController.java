package proyectoconstruccion.Controllers.colaboracion;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.ColaboracionDAO;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class FXMLContenedorColaboracionesController {

    public VBox vBoxBucket;

    public void InicializarComponentes(){

        HashMap<String,Object> seleccion =  ColaboracionDAO.getColaboraciones(null, null,null,null,null,null,null);
        if(seleccion==null){
            System.out.println("da null tu pendejada");
        }


        ArrayList<Colaboracion> colaboraciones= (ArrayList<Colaboracion>) seleccion.get("colaboraciones");
        for (Colaboracion colaboracion:colaboraciones){
            System.out.println("colaboracionAñadida");
            añadirItem(colaboracion);
        }

    }

    private void añadirItem(Colaboracion colaboracion) {
        try {
            FXMLLoader loader = Utils.obtenerLoader("Views/colaboracion/FXMLColaboracionItem.fxml");
            AnchorPane colaboracionItem = loader.load();

            FXMLColaboracionItemController controler = loader.getController();
            controler.InicializarComponentes(colaboracion);

            vBoxBucket.getChildren().add(colaboracionItem);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
