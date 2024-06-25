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


    private static RefreshserUtils instancia = null;

    public static RefreshserUtils getInstance() {
        if (instancia == null)
            instancia = new RefreshserUtils();

        return instancia;
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
