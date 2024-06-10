package proyectoconstruccion.modelo.DAO;

import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.modelo.ConexionBD;
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
                    String tipodeColab = resultado.getString("TipoDeColab");
                    //Integer idioma = resultado.getInt("idiomaId");
                    String tituloResultado = resultado.getString("Titulo");
                    Integer experienciaEducativaid = resultado.getInt("experiencia_educativa_id");

                    Integer idProfesorUV = resultado.getInt("profesor_uv_id");
                    Integer idProfesorExterno = resultado.getInt("profesor_externo_id");

                    ProfesorUV profesorUV = (ProfesorUV) ProfesorDAO.getProfesorById(idProfesorUV,0).get("profesor");
                    ProfesorExterno externo = (ProfesorExterno) ProfesorDAO.getProfesorById(idProfesorExterno,1).get("profesor");


                    //ESTAS AÑADIENDO EVIDENCIAS, TIENES QUE CREAR EL DAO EVIDENCIAS Y DAO EE
                    Evidencia evidencia= new Evidencia(null,null,null);

                    ExperienciaEducativa experienciaEducativa = (ExperienciaEducativa) ExperienciaEducativaDAO.obtenerExperienciaEducativa(experienciaEducativaid).get("experienciaEducativa");

                    Colaboracion colaboracionResultado = new Colaboracion(id,duracion,periodoResultado,tituloResultado,"",inicio,cierre,tipo,estado,numeroEstudiantes,profesorUV,externo,experienciaEducativa,evidencia);

                    colaboraciones.add(colaboracionResultado);
                }
                respuesta.put("colaboraciones",colaboraciones);
            }catch(SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE,Constantes.MSJ_ERROR_CONEXION);
            }


        }

return respuesta;
    }


}
