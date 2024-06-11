package proyectoconstruccion.modelo.DAO;

import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.modelo.ConexionBD;
import proyectoconstruccion.modelo.POJO.Idioma;
import proyectoconstruccion.modelo.POJO.academia.ExperienciaEducativa;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;
import proyectoconstruccion.modelo.POJO.evidencia.Evidencia;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class ColaboracionDAO {
    public static HashMap<String,Object> getColaboraciones(String titulo, String tipoDeColaboracion, String periodo, Integer numeroEstudiantesMax, Integer numeroEstudiantesMin, LocalDate fechaInicio,LocalDate fechaCierre){
        HashMap<String,Object> respuesta = new HashMap<>();
        respuesta.put(Constantes.KEY_ERROR,true);
        Connection conexionBD = ConexionBD.getConexion();

        if(conexionBD != null){
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM colaboracion WHERE 1=1");
            ArrayList<Object> parametros = new ArrayList<>();
            if(titulo != null){
                queryBuilder.append(" AND Titulo = ?");
                parametros.add(titulo);
            }
            if(tipoDeColaboracion != null){
                queryBuilder.append(" AND TipoDeColab = ?");
                parametros.add(tipoDeColaboracion);
            }
            if(periodo != null){
                queryBuilder.append(" AND periodo = ?");
            }

            if (fechaInicio != null && fechaCierre != null) {
                queryBuilder.append(" AND FechaInicio >= ?");
                queryBuilder.append(" AND FechaCierre <= ?");
                parametros.add(fechaInicio);
                parametros.add(fechaCierre);
            } else if (fechaInicio != null) {
                queryBuilder.append(" AND FechaInicio >= ?");
                parametros.add(fechaInicio);
            } else if (fechaCierre != null) {
                queryBuilder.append(" AND FechaCierre <= ?");
                parametros.add(fechaCierre);
            }


            if (numeroEstudiantesMin != null && numeroEstudiantesMax != null) {
                queryBuilder.append(" AND number_of_students BETWEEN ? AND ?");
                parametros.add(numeroEstudiantesMin);
                parametros.add(numeroEstudiantesMax);
            } else if (numeroEstudiantesMin != null) {
                queryBuilder.append(" AND number_of_students >= ?");
                parametros.add(numeroEstudiantesMin);
            } else if (numeroEstudiantesMax != null) {
                queryBuilder.append(" AND number_of_students <= ?");
                parametros.add(numeroEstudiantesMax);
            }

            String consulta = queryBuilder.toString();
            ArrayList<Colaboracion> colaboraciones = new ArrayList<>();

            try{
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);

                for (int i = 0; i < parametros.size(); i++) {
                    prepararSentencia.setObject(i+1, parametros.get(i));
                }

                ResultSet resultado = prepararSentencia.executeQuery();
                while(resultado.next()){
                    Integer id = resultado.getInt("colaboracion_id");
                    String duracion = resultado.getString("Duracion");
                    String estado = resultado.getString("Estado");
                    LocalDate cierre = LocalDate.parse(resultado.getString("FechaCierre"));
                    LocalDate inicio = LocalDate.parse(resultado.getString("FechaInicio"));
                    Integer numeroEstudiantes = resultado.getInt("NumeroEstudiantes");
                    String tipo= resultado.getString("TipoDeColab");
                    String periodoResultado= resultado.getString("Periodo");
                    Integer idioma = resultado.getInt("idioma_id");
                    String tituloResultado = resultado.getString("Titulo");
                    Integer experienciaEducativaid = resultado.getInt("experiencia_educativa_id");
                    Integer evidenciaId = resultado.getInt("evidencia_id");
                    Integer idProfesorUV = resultado.getInt("profesor_uv_id");
                    Integer idProfesorExterno = resultado.getInt("profesor_externo_id");
                    String anotaciones = resultado.getString("anotaciones");

                    Evidencia evidencia= new Evidencia(evidenciaId);


                    Idioma idioma1 = IdiomaDAO.obtenerIdioma(idioma);


                    ProfesorUV profesorUV = (ProfesorUV) ProfesorDAO.getProfesorById(idProfesorUV,0).get("profesor");
                    ProfesorExterno externo = (ProfesorExterno) ProfesorDAO.getProfesorById(idProfesorExterno,1).get("profesor");

                    ExperienciaEducativa experienciaEducativa = (ExperienciaEducativa) ExperienciaEducativaDAO.obtenerExperienciaEducativa(experienciaEducativaid).get("experienciaEducativa");

                    Colaboracion colaboracionResultado = new Colaboracion(id,duracion,periodoResultado,tituloResultado,idioma1,inicio,cierre,tipo,estado,numeroEstudiantes,profesorUV,externo,experienciaEducativa,evidencia,anotaciones);

                    colaboraciones.add(colaboracionResultado);
                }
                respuesta.put("colaboraciones",colaboraciones);
            }catch(SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_CONEXION);
            }


        }

return respuesta;
    }


    public static boolean registrarColaboracion(Colaboracion colaboracion) {
        Connection conexionBD = ConexionBD.getConexion();
        boolean registroExitoso = false;
        if (conexionBD != null) {
            try {
                String consulta = "INSERT INTO colaboracion (Duracion, Periodo, Titulo, idioma_id, FechaInicio, FechaCierre, TipoDeColab, Estado, NumeroEstudiantes, profesor_uv_id, profesor_externo_id, experiencia_Educativa_id, evidencia_id,anotaciones)" +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, null, ?, ?, ?, ?,null)";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(consulta);
                preparedStatement.setString(1, colaboracion.getDuracion());
                preparedStatement.setString(2, colaboracion.getPeriodo());
                preparedStatement.setString(3, colaboracion.getTitulo());
                preparedStatement.setInt(4, colaboracion.getIdioma().getIdiomaID()); // assuming idiomaID is the field required
                preparedStatement.setDate(5, java.sql.Date.valueOf(colaboracion.getFechaInicio()));
                preparedStatement.setDate(6, java.sql.Date.valueOf(colaboracion.getFechaCierre()));
                preparedStatement.setString(7, colaboracion.getTipo());
                preparedStatement.setString(8, colaboracion.getEstado());
                preparedStatement.setInt(9, colaboracion.getProfesorUv().getProfesorId()); // assuming getProfesorUvId() gets the appropriate id
                preparedStatement.setInt(10, colaboracion.getProfesorExterno().getProfesorId()); // assuming getProfesorExternoId() gets the appropriate id
                preparedStatement.setInt(11, colaboracion.getExperienciaEducativa().getExperienciaEducativaId()); // assuming getExperienciaEducativaId gets the appropriate id
                preparedStatement.setInt(12, colaboracion.getEvidencia().getEvidenciaId()); // assuming getEvidenciaId() gets the appropriate id

                int resultado = preparedStatement.executeUpdate();
                if (resultado != 0) {
                    System.out.println("Colaboracion Inserted Successfully!");
                    registroExitoso = true;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return registroExitoso;
    }


    public static boolean actualizarEstadoColaboracion(Integer colaboracionId, String nuevoEstado) {
        Connection conexionBD = ConexionBD.getConexion();
        boolean actualizarExitoso = false;
        if (conexionBD != null) {
            try {
                String consulta = "UPDATE colaboracion SET Estado = ? WHERE colaboracion_id = ?";
                PreparedStatement preparedStatement = conexionBD.prepareStatement(consulta);
                preparedStatement.setString(1, nuevoEstado);
                preparedStatement.setInt(2, colaboracionId);

                int resultado = preparedStatement.executeUpdate();
                if (resultado != 0) {
                    System.out.println("Colaboracion Updated Successfully!");
                    actualizarExitoso = true;
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return actualizarExitoso;
    }

    public static boolean borrarColaboracion(Integer colaboracionId) {
        Connection conexionBD = ConexionBD.getConexion();
        boolean borrarExitoso = false;
        if (conexionBD != null) {
            try {
                //First delete the evidencias associated with the colaboracion
                if (EvidenciaDAO.borrarEvidencias(colaboracionId)) {
                    String consulta = "DELETE FROM colaboracion WHERE colaboracion_id = ?";
                    PreparedStatement preparedStatement = conexionBD.prepareStatement(consulta);
                    preparedStatement.setInt(1, colaboracionId);

                    int resultado = preparedStatement.executeUpdate();
                    if (resultado != 0) {
                        System.out.println("Colaboracion Deleted Successfully!");
                        borrarExitoso = true;
                    }
                } else {
                    System.out.println("Failed to delete associated evidencias.");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
        return borrarExitoso;
    }


}
