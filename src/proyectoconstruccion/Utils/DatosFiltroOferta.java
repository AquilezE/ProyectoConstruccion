package proyectoconstruccion.Utils;

import proyectoconstruccion.modelo.POJO.Idioma;
import proyectoconstruccion.modelo.POJO.Periodo;

public class DatosFiltroOferta {

    private String opcionFiltro;

    public String getOpcionFiltro() {
        return opcionFiltro;
    }

    public void setOpcionFiltro(String opcionFiltro) {
        this.opcionFiltro = opcionFiltro;
    }


    @Override
    public String toString() {
        return "DatosFiltroOferta{" +
                "opcionFiltro='" + opcionFiltro + '\'' +
                '}';
    }


}
