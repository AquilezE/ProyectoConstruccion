package proyectoconstruccion.Controllers.oferta;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import proyectoconstruccion.Utils.Utils;
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

    Profesor profesor1=new ProfesorUV(1,"muyAlto@uv.com","Guillermo","MuyAlto","Heinekken","2282788117",1,"UV012024");

    Profesor profesor2=new ProfesorExterno(2,"Gignac@","Gignac","Champagne","DeGea","5534554534",2,2);

    ArrayList<OfertaColaboracion> ofertas=new ArrayList<OfertaColaboracion>();

    OfertaColaboracion oferta1 = new OfertaColaboracionUV(1,1,"Febrero-2023","Gio es mi Hijo, Reflexion","3 semanas", (ProfesorUV) profesor1);
    OfertaColaboracion oferta2 = new OfertaColaboracionExterna(2,1,"Agosto-2023","Tigres vs Manchester UD","2 semanas",(ProfesorExterno) profesor2);

    /*
    Aqui es lo cabron de las consultas
    Tienes que hacer un tipo factory de consulta, para que puedas llenar las
    la cubeta de las ofertas que si son.
     */
    public void InicializarComponentes(){
        ofertas.add(oferta1);
        ofertas.add(oferta2);

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
