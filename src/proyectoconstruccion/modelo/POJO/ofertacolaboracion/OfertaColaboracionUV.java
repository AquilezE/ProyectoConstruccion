package proyectoconstruccion.modelo.POJO.ofertacolaboracion;

import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

public class OfertaColaboracionUV extends OfertaColaboracion {

    public OfertaColaboracionUV(Integer ofertaColaboracionId, Integer idiomaID,String periodo, String titulo, String duracion, ProfesorUV profesorUV) {
        super(ofertaColaboracionId, idiomaID,periodo, titulo, duracion,profesorUV);
    }

}
