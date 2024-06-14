package proyectoconstruccion.modelo.DAO;

import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.Utils.Sesion;
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
    public static HashMap<String,Object> getColaboraciones(String titulo, String estadoP, String periodo, LocalDate fechaInicio,LocalDate fechaCierre, Integer experienciaEducativaId, String tipoUsuario){
        HashMap<String,Object> respuesta = new HashMap<>();
        respuesta.put(Constantes.KEY_ERROR,true);
        Connection conexionBD = ConexionBD.getConexion();


        System.out.println("PARAM VALUES");
        System.out.println("Parametros de búsqueda:");
        System.out.println("Titulo: " + titulo);
        System.out.println("Estado: " + estadoP);
        System.out.println("Periodo: " + periodo);
        System.out.println("FechaInicio: " + fechaInicio);
        System.out.println("FechaFin: " + fechaCierre);
        System.out.println("ExperienciaEducativa: " + experienciaEducativaId);



        if(conexionBD != null){
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM colaboracion WHERE 1=1 ");
            ArrayList<Object> parametros = new ArrayList<>();

            if(tipoUsuario.equals(Constantes.PROFESOR)){
                queryBuilder.append(" AND profesor_uv_id = ?");
                parametros.add(Sesion.getInstancia().getProfesorUsuario().getProfesorId());
            }

            if(titulo != null){
                queryBuilder.append(" AND Titulo = ?");
                parametros.add(titulo);
            }
            if(estadoP != null){
                queryBuilder.append(" AND Estado = ?");
                parametros.add(estadoP);
            }
            if(periodo != null){
                queryBuilder.append(" AND periodo = ?");
                parametros.add(periodo);
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

            if (experienciaEducativaId != null) {
                queryBuilder.append(" AND experiencia_educativa_id = ?");
                parametros.add(experienciaEducativaId);
            }



            String consulta = queryBuilder.toString();
            ArrayList<Colaboracion> colaboraciones = new ArrayList<>();


            System.out.println(consulta);
            try{
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);

                for (int i = 0; i < parametros.size(); i++) {
                    prepararSentencia.setObject(i+1, parametros.get(i));
                    System.out.println(prepararSentencia);
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
                    System.out.println("Colaboracion añadida");
                }

            }catch(SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_CONEXION);
            }
            respuesta.put("colaboraciones",colaboraciones);


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

    public static boolean aniadirAnotaciones(Integer colaboracionId,String anotaciones){

        boolean updateSuccessful = false;
        Connection connection = ConexionBD.getConexion();
        if (connection != null) {
            String query = "UPDATE colaboracion SET anotaciones = ? WHERE colaboracion_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, anotaciones);
                preparedStatement.setInt(2, colaboracionId);

                int result = preparedStatement.executeUpdate();
                if (result != 0) {
                    System.out.println("Anotaciones Updated Successfully!");
                    updateSuccessful = true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updateSuccessful;

    }

    public static void actualizarNumeroEstudiantes(Integer colaboracionId, Integer numeroEstudiantes) {

        Connection connection = ConexionBD.getConexion();
        if (connection != null) {
            String query = "UPDATE colaboracion SET NumeroEstudiantes = ? WHERE colaboracion_id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, numeroEstudiantes);
                preparedStatement.setInt(2, colaboracionId);

                int result = preparedStatement.executeUpdate();
                if (result != 0) {
                    System.out.println("Numero de estudiantes actualizado: " + colaboracionId);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
