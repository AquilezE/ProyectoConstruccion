package proyectoconstruccion.modelo.DAO;

import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.modelo.ConexionBD;
import proyectoconstruccion.modelo.POJO.profesor.Profesor;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

import java.sql.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ProfesorDAO {

    public static HashMap<String,Object> obtenerProfesorExterno(Integer idProfesorExterno){
        HashMap<String,Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR,true);
        Connection conexionBD = ConexionBD.getConexion();
        if(conexionBD != null){
            String consulta = "SELECT profesor.profesor_id, CorreoElectronico, Telefono, idioma_id, Nombre, ApellidoPaterno, ApellidoMaterno, universidad_id " +
                    "FROM profesor " +
                    "JOIN profesorexterno ON profesor.profesor_id = profesorexterno.profesor_id " +
                    "WHERE profesor.profesor_id = ?;";


            try{
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idProfesorExterno);
                ResultSet resultado = prepararSentencia.executeQuery();

                ProfesorExterno profesor = new ProfesorExterno();
                while(resultado.next()){

                        profesor.setProfesorId(resultado.getInt("profesor_id"));
                        profesor.setCorreoElectronico(resultado.getString("CorreoElectronico"));
                        profesor.setTelefono(resultado.getString("Telefono"));
                        profesor.setIdiomaId(resultado.getInt("idioma_id"));
                        profesor.setNombre(resultado.getString("Nombre"));
                        profesor.setApellidoPaterno(resultado.getString("ApellidoPaterno"));
                        profesor.setApellidoMaterno(resultado.getString("ApellidoMaterno"));
                        profesor.setUniversidadID(resultado.getInt("universidad_id"));
                        respuesta.put("profesor", profesor);
                }

                respuesta.put(Constantes.KEY_ERROR, false);

                conexionBD.close();
            }catch(SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }

    public static HashMap<String,Object> obtenerProfesorUV(Integer idProfesorUV){
        HashMap<String,Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR,true);
        Connection conexionBD = ConexionBD.getConexion();
        if(conexionBD != null){
            String consulta = "SELECT profesor.profesor_id, CorreoElectronico, Telefono, idioma_id, Nombre, ApellidoPaterno, ApellidoMaterno, NumeroPersonal " +
                    "FROM profesor " +
                    "JOIN profesoruv ON profesor.profesor_id = profesoruv.profesor_id " +
                    "WHERE profesor.profesor_id = ?;";


            try{
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idProfesorUV);
                ResultSet resultado = prepararSentencia.executeQuery();

                ProfesorUV profesor = new ProfesorUV();
                while(resultado.next()){

                    profesor.setProfesorId(resultado.getInt("profesor_id"));
                    profesor.setCorreoElectronico(resultado.getString("CorreoElectronico"));
                    profesor.setTelefono(resultado.getString("Telefono"));
                    profesor.setIdiomaId(resultado.getInt("idioma_id"));
                    profesor.setNombre(resultado.getString("Nombre"));
                    profesor.setApellidoPaterno(resultado.getString("ApellidoPaterno"));
                    profesor.setApellidoMaterno(resultado.getString("ApellidoMaterno"));
                    profesor.setNumeroPersonal(resultado.getString("NumeroPersonal"));
                    respuesta.put("profesor", profesor);
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                conexionBD.close();
            }catch(SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }

    public static HashMap<String,Object> getProfesorById(int profesorId, int type){

        HashMap<String,Object> respuesta;

        if (type == 0) {
            respuesta = obtenerProfesorUV(profesorId);
        } else {
            respuesta = obtenerProfesorExterno(profesorId);
        }

        return respuesta;
    }

    public static String getTipoProfesor(Integer idProfesor){
        String tipoProfesor="No existe este profesor";

        // First try with UV
        HashMap<String,Object> respuesta = getProfesorById(idProfesor, 0);

        // if "profesor" is not null and instance of ProfesorUV
        if(respuesta.get("profesor") != null && respuesta.get("profesor") instanceof ProfesorUV){
            tipoProfesor="uv";
        }
        else {
            // If not found in UV, try with externo
            respuesta = getProfesorById(idProfesor, 1);

            // if "profesor" is not null and instance of ProfesorExterno
            if(respuesta.get("profesor") != null && respuesta.get("profesor") instanceof ProfesorExterno){
                tipoProfesor="externo";
            }
            else {
                System.out.println("El profesor no existe");
            }
        }

        return tipoProfesor;
    }
}
