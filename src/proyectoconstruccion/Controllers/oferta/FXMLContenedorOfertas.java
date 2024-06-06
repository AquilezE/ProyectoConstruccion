package proyectoconstruccion.Controllers.oferta;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.OfertaColaboracionDAO;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracion;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracionExterna;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracionUV;
import proyectoconstruccion.modelo.POJO.profesor.Profesor;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

import java.io.IOException;
import java.util.ArrayList;

public class FXMLContenedorOfertas {

    public VBox vBoxBucket;

    /*
    Aqui es lo cabron de las consultas
    Tienes que hacer un tipo factory de consulta, para que puedas llenar las
    la cubeta de las ofertas que si son.
     */
    public void InicializarComponentes(){
        ArrayList<OfertaColaboracion> ofertas = (ArrayList<OfertaColaboracion>) OfertaColaboracionDAO.getAllOfertasColaboracion().get("ofertas");


        for (OfertaColaboracion oferta: ofertas) {
                añadirItem(oferta);
        }

    }

    public void añadirItem(OfertaColaboracion oferta){
        try{
            FXMLLoader loader = Utils.obtenerLoader("Views/oferta/FXMLOfertaItem.fxml");
            AnchorPane ofertaItem = loader.load();

            FXMLOfertaItem controler = loader.getController();
            controler.InicializarComponentes(oferta);

            vBoxBucket.getChildren().add(ofertaItem);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


}
