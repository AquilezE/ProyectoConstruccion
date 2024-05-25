package proyectoconstruccion.modelo.POJO.ofertacolaboracion;

import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

public class OfertaColaboracionUV extends OfertaColaboracion {

    public OfertaColaboracionUV(Integer ofertaColaboracionId, Integer idiomaId,String periodo, String titulo, Integer tipo, String duracion, ProfesorUV profesorUV) {
        super(ofertaColaboracionId, idiomaId, periodo, titulo, tipo,duracion,profesorUV);
    }

}
