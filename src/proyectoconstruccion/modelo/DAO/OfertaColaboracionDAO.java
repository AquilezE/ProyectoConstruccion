package proyectoconstruccion.modelo.DAO;

import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.Utils.DatosFiltroOferta;
import proyectoconstruccion.Utils.Sesion;
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

public class OfertaColaboracionDAO {

    public static HashMap<String, Object> getOfertasColaboracion(DatosFiltroOferta filtroOferta) {


        String opcionFiltro = "";
        if (filtroOferta!=null){
             opcionFiltro= filtroOferta.getOpcionFiltro();
        }else{
            System.out.println("FiltroOfertaisNULL");
        }


        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.getConexion();

        if (conexionBD != null) {

            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ofertaColaboracion ");
            ArrayList<Object> parametros = new ArrayList<>();


            if (filtroOferta.getOpcionFiltro()==null ){
                queryBuilder.append(" WHERE 1=1");
                if (Sesion.getInstancia().getRol().equals(Constantes.PROFESOR)) {
                    queryBuilder.append("AND (profesor_id = ? OR type = 1)");
                    parametros.add(Sesion.getInstancia().getProfesorUsuario().getProfesorId());
                }
                queryBuilder.append(" ORDER BY Type");
            }else {
                if (filtroOferta.getOpcionFiltro().equals("Periodo")) {
                    queryBuilder.append("JOIN periodo ON Periodo.descripcion = ofertacolaboracion.Periodo WHERE 1=1 ");
                    if (Sesion.getInstancia().getRol().equals(Constantes.PROFESOR)) {
                        queryBuilder.append("AND (profesor_id = ? OR type = 1) ");
                        parametros.add(Sesion.getInstancia().getProfesorUsuario().getProfesorId());
                    }
                    queryBuilder.append("ORDER BY Periodo.id");
                }
                if (filtroOferta.getOpcionFiltro().equals("Idioma")) {
                    queryBuilder.append(" JOIN idioma ON ofertacolaboracion.Idioma = idioma.idioma_id\n WHERE 1=1 ");
                    if (Sesion.getInstancia().getRol().equals(Constantes.PROFESOR)) {
                        queryBuilder.append(" AND (profesor_id = ? OR type = 1) ");
                        parametros.add(Sesion.getInstancia().getProfesorUsuario().getProfesorId());
                    }
                    queryBuilder.append(" ORDER BY idioma.idioma ");
                }
                if(filtroOferta.getOpcionFiltro().equals("Tipo")){
                    if (Sesion.getInstancia().getRol().equals(Constantes.PROFESOR)) {
                        queryBuilder.append(" WHERE 1=1 AND (profesor_id = ? OR type = 1) ");
                        parametros.add(Sesion.getInstancia().getProfesorUsuario().getProfesorId());

                        queryBuilder.append(" ORDER BY Type");
                    }

                }
            }
            String consulta = queryBuilder.toString();
            System.out.println(consulta);
            ArrayList<OfertaColaboracion> ofertas = new ArrayList<>();

            try {
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);

                for (int i = 0; i < parametros.size(); i++) {
                    System.out.println(parametros.get(i));
                    prepararSentencia.setObject(i + 1, parametros.get(i));
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
                        ofertas.add(new OfertaColaboracionUV(ofertaId, idiomaId, period, titulo, type, duracion, (ProfesorUV) profesor));
                    } else if (profesor instanceof ProfesorExterno) {
                        ofertas.add(new OfertaColaboracionExterna(ofertaId, idiomaId, period, titulo, type, duracion, (ProfesorExterno) profesor));
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


    public static boolean guardarOfertaExterna(OfertaColaboracionExterna oferta) {
        boolean inserted = false;
        Connection conexionBD = ConexionBD.getConexion();
        if (conexionBD != null) {
            String insertStmt = "INSERT INTO ofertaColaboracion (idioma, periodo, titulo, duracion, profesor_id, type) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(insertStmt);
                prepararSentencia.setInt(1, oferta.getIdiomaId());
                prepararSentencia.setString(2, oferta.getPeriodo());
                prepararSentencia.setString(3, oferta.getTitulo());
                prepararSentencia.setString(4, oferta.getDuracion());
                prepararSentencia.setInt(5, oferta.getProfesor().getProfesorId());
                prepararSentencia.setInt(6, 1);

                int numOfInsertedRows = prepararSentencia.executeUpdate();
                inserted = numOfInsertedRows > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inserted;
    }


    public static boolean guardarOfertaUV(OfertaColaboracionUV oferta) {
        boolean inserted = false;
        Connection conexionBD = ConexionBD.getConexion();
        if (conexionBD != null) {
            String insertStmt = "INSERT INTO ofertaColaboracion (idioma, periodo, titulo, duracion, profesor_id, type) VALUES (?, ?, ?, ?, ?, ?)";
            try {
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(insertStmt);
                prepararSentencia.setInt(1, oferta.getIdiomaId());
                prepararSentencia.setString(2, oferta.getPeriodo());
                prepararSentencia.setString(3, oferta.getTitulo());
                prepararSentencia.setString(4, oferta.getDuracion());
                prepararSentencia.setInt(5, oferta.getProfesor().getProfesorId());
                prepararSentencia.setInt(6, 0);

                int numOfInsertedRows = prepararSentencia.executeUpdate();
                inserted = numOfInsertedRows > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return inserted;
    }


    public static boolean updateOfertaColaboracion(OfertaColaboracion oferta, String titulo, String duracion, Integer idioma, String periodo) {
        boolean updated = false;
        Connection conexionBD = ConexionBD.getConexion();
        if (conexionBD != null) {
            String updateStmt = "UPDATE ofertaColaboracion SET idioma = ?, periodo = ?, titulo = ?, duracion = ? WHERE oferta_colaboracion_id = ?";
            try {
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(updateStmt);
                prepararSentencia.setInt(1, idioma);
                prepararSentencia.setString(2, periodo);
                prepararSentencia.setString(3, titulo);
                prepararSentencia.setString(4, duracion);
                prepararSentencia.setInt(5, oferta.getOfertaColaboracionId());

                int numOfUpdatedRows = prepararSentencia.executeUpdate();
                updated = numOfUpdatedRows > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updated;
    }

    public static HashMap<String, Object> getOfertaColaboracionById(Integer id) {
        HashMap<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.getConexion();

        if (conexionBD != null) {

            String query = "SELECT * FROM ofertaColaboracion WHERE oferta_colaboracion_id=?";
            OfertaColaboracion oferta = null;

            try {
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(query);
                prepararSentencia.setInt(1, id);

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
                        oferta = new OfertaColaboracionUV(ofertaId, idiomaId, period, titulo, type, duracion, (ProfesorUV) profesor);
                    } else if (profesor instanceof ProfesorExterno) {
                        oferta = new OfertaColaboracionExterna(ofertaId, idiomaId, period, titulo, type, duracion, (ProfesorExterno) profesor);
                    }
                }
                respuesta.put("oferta", oferta);

                respuesta.put(Constantes.KEY_ERROR, false);
            } catch (SQLException e) {
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        }
        return respuesta;
    }


    public static boolean cambiarEstadoOfertaColaboracion(Integer ofertaId, Integer newEstado) {
        boolean updated = false;
        Connection conexionBD = ConexionBD.getConexion();
        if (conexionBD != null) {
            String updateStmt = "UPDATE ofertaColaboracion SET estado = ? WHERE oferta_colaboracion_id = ?";
            try {
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(updateStmt);
                prepararSentencia.setInt(1, newEstado);
                prepararSentencia.setInt(2, ofertaId);

                int numOfUpdatedRows = prepararSentencia.executeUpdate();
                updated = numOfUpdatedRows > 0;

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updated;
    }


    }
