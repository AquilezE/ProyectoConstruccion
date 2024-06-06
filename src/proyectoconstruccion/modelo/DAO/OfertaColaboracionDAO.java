package proyectoconstruccion.modelo.DAO;

import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.modelo.ConexionBD;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracion;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracionExterna;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracionUV;
import proyectoconstruccion.modelo.POJO.profesor.Profesor;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class OfertaColaboracionDAO {

    public static HashMap<String,Object> getAllOfertasColaboracion() {
        HashMap<String,Object> resultado = getOfertasColaboracion(null, null);
        return resultado;
    }

    public static HashMap<String,Object> getOfertasColaboracion(String periodo, Integer idioma) {
        HashMap <String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.getConexion();

        if (conexionBD != null) {

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ofertaColaboracion WHERE 1=1");
            ArrayList<Object> parameters = new ArrayList<>();

            if (periodo != null) {
                queryBuilder.append(" AND Periodo LIKE ?");
                parameters.add("%" + periodo + "%");
            }
            if (idioma != null) {
                queryBuilder.append(" AND Idioma = ?");
                parameters.add(idioma);
            }

            String consulta = queryBuilder.toString();
            ArrayList<OfertaColaboracion> ofertas = new ArrayList<>();

            try {
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);

                for (int i = 0; i < parameters.size(); i++) {
                    prepararSentencia.setObject(i + 1, parameters.get(i));
                }

                ResultSet resultado = prepararSentencia.executeQuery();
                while (resultado.next()) {

                    int ofertaId = resultado.getInt("oferta_colaboracion_id");
                    int idiomaId = resultado.getInt("Idioma");
                    String period = resultado.getString("Periodo");
                    String titulo = resultado.getString("Titulo");
                    String duracion = resultado.getString("Duracion");
                    int profesorId = resultado.getInt("profesor_id");
                    int type = resultado.getInt("type");

                    Profesor profesor = (Profesor) ProfesorDAO.getProfesorById(profesorId, type).get("profesor");
                    if (profesor instanceof ProfesorUV) {
                        ofertas.add(new OfertaColaboracionUV(ofertaId, idiomaId, period, titulo,type, duracion, (ProfesorUV) profesor));
                    } else if (profesor instanceof ProfesorExterno) {
                        ofertas.add(new OfertaColaboracionExterna(ofertaId, idiomaId, period, titulo,type, duracion, (ProfesorExterno) profesor));
                    }
                }
                respuesta.put("ofertas", ofertas);

                respuesta.put(Constantes.KEY_ERROR, false);
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        }
        return respuesta;
        }

}
