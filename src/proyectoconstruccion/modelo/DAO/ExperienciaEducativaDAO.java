package proyectoconstruccion.modelo.DAO;

import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.modelo.ConexionBD;
import proyectoconstruccion.modelo.POJO.academia.ExperienciaEducativa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class ExperienciaEducativaDAO {
    public static HashMap<String, Object> obtenerExperienciaEducativa(Integer idExperienciaEducativa){
        HashMap <String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.getConexion();
        if(conexionBD != null){

            String consulta = "select * from experienciaeducativa where experiencia_educativa_id = ?";


            try{
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idExperienciaEducativa);
                ResultSet resultado = prepararSentencia.executeQuery();

                ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
                while(resultado.next()){
                    experienciaEducativa.setExperienciaEducativaId(resultado.getInt("experiencia_educativa_id"));
                    experienciaEducativa.setNombreExperienciaEducativa(resultado.getString("nombreExperienciaEducativa"));
                    experienciaEducativa.setCreditos(resultado.getInt("creditos"));
                    experienciaEducativa.setDescripcion(resultado.getString("descripcion"));
                    experienciaEducativa.setProgramaEducativoId(resultado.getInt("programa_educativo_id"));

                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("experienciaEducativa", experienciaEducativa);
                conexionBD.close();
            }catch(SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }


    public static ArrayList<ExperienciaEducativa> obtenerTodasExperienciasEducativas() {
        ArrayList<ExperienciaEducativa> experienciaEducativaList = new ArrayList<>();

        Connection conexionBD = ConexionBD.getConexion();

        if (conexionBD != null) {
            String consulta = "select * from experienciaeducativa";
            try {
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();

                System.out.println("Obteniendo experiencias");
                while (resultado.next()) {

                    ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
                    experienciaEducativa.setExperienciaEducativaId(resultado.getInt("experiencia_educativa_id"));
                    experienciaEducativa.setNombreExperienciaEducativa(resultado.getString("nombreExperienciaEducativa"));
                    experienciaEducativa.setCreditos(resultado.getInt("creditos"));
                    experienciaEducativa.setDescripcion(resultado.getString("descripcion"));
                    experienciaEducativa.setProgramaEducativoId(resultado.getInt("programa_educativo_id"));

                    experienciaEducativaList.add(experienciaEducativa);
                    System.out.println(experienciaEducativa.getNombreExperienciaEducativa());
                }
                System.out.println("Done");
                conexionBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return experienciaEducativaList;
    }

}
