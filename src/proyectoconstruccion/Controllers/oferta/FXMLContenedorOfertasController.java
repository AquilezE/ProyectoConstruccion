package proyectoconstruccion.Controllers.oferta;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import proyectoconstruccion.Utils.DatosFiltroOferta;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.OfertaColaboracionDAO;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracion;

import java.io.IOException;
import java.util.ArrayList;

public class FXMLContenedorOfertasController {

    public VBox vBoxBucket;

    public void InicializarComponentes(DatosFiltroOferta filtroOferta){
        actualizarOfertas(filtroOferta);
    }

    public void actualizarOfertas(DatosFiltroOferta filtroOferta){
        eliminarItems();
        ArrayList<OfertaColaboracion> ofertas = (ArrayList<OfertaColaboracion>) OfertaColaboracionDAO.getOfertasColaboracion(filtroOferta).get("ofertas");


        for (OfertaColaboracion oferta: ofertas) {
            añadirItem(oferta);
        }
    }
    public void añadirItem(OfertaColaboracion oferta){
        try{
            FXMLLoader loader = Utils.obtenerLoader("Views/oferta/FXMLOfertaItem.fxml");
            AnchorPane ofertaItem = loader.load();

            FXMLOfertaItemController controler = loader.getController();
            controler.InicializarComponentes(oferta);

            vBoxBucket.getChildren().add(ofertaItem);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void eliminarItems(){
        vBoxBucket.getChildren().clear();
    }}
