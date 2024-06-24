package proyectoconstruccion.Controllers;

import proyectoconstruccion.Controllers.colaboracion.FXMLContenedorColaboracionesController;
import proyectoconstruccion.Controllers.oferta.FXMLContenedorOfertasController;
import proyectoconstruccion.Utils.DatosFiltroColaboracion;
import proyectoconstruccion.Utils.DatosFiltroOferta;

public class RefreshserUtils {


    public static FXMLContenedorColaboracionesController colaboracionesController;
    public static DatosFiltroColaboracion colaboracionesBusquedaCache;

    public static FXMLContenedorOfertasController ofertasController;
    public static DatosFiltroOferta ofertasBusquedaCache;


    // Instance of Singleton class
    private static RefreshserUtils singleton_instance = null;

    // Making the class singleton
    public static RefreshserUtils getInstance() {
        if (singleton_instance == null)
            singleton_instance = new RefreshserUtils();

        return singleton_instance;
    }

    private RefreshserUtils() {}


    //Colaboraciones
    public static FXMLContenedorColaboracionesController getColaboracionesController() {
        return colaboracionesController;
    }

    public static void setColaboracionesController(FXMLContenedorColaboracionesController colaboracionesController) {
        RefreshserUtils.colaboracionesController = colaboracionesController;
    }


    public static DatosFiltroColaboracion getColaboracionesBusquedaCache() {
        return colaboracionesBusquedaCache;
    }

    public static void setColaboracionesBusquedaCache(DatosFiltroColaboracion colaboracionesBusquedaCache) {
        RefreshserUtils.colaboracionesBusquedaCache = colaboracionesBusquedaCache;
    }


    //Ofertas
    public static DatosFiltroOferta getOfertasBusquedaCache() {
        return ofertasBusquedaCache;
    }

    public static void setOfertasBusquedaCache(DatosFiltroOferta ofertasBusquedaCache) {
        RefreshserUtils.ofertasBusquedaCache = ofertasBusquedaCache;
    }

    public static void setOfertasController(FXMLContenedorOfertasController ofertasController) {
        RefreshserUtils.ofertasController = ofertasController;
    }

    public static FXMLContenedorOfertasController getOfertasController() {
        return ofertasController;
    }


}
