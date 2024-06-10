package proyectoconstruccion.modelo.DAO;

import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.modelo.ConexionBD;
import proyectoconstruccion.modelo.POJO.Idioma;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class IdiomaDAO {
    public static HashMap<String, Object> obtenerIdioma(Integer idiomaID){
        HashMap <String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.getConexion();
        if(conexionBD != null){

            String consulta = "select * from idioma where id = ?";


            try{
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idiomaID);
                ResultSet resultado = prepararSentencia.executeQuery();

                ArrayList<Idioma> lista = new ArrayList<>();
                while(resultado.next()){
                    String idioma = resultado.getString("Idioma");
                    lista.add(new Idioma(idiomaID,idioma));
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("idioma", lista);
                conexionBD.close();
            }catch(SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }

    public static ArrayList<Idioma> obtenerIdiomas() {

        ArrayList<Idioma> idiomas = new ArrayList<>();
        Connection conexionBD = ConexionBD.getConexion();
        if (conexionBD != null) {
            String consulta = "select * from idioma";
            try {
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();
                while (resultado.next()) {
                    Integer idiomaID = resultado.getInt("idioma_id");
                    String idioma = resultado.getString("Idioma");
                    idiomas.add(new Idioma(idiomaID, idioma));
                }
                conexionBD.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return idiomas;
    }
}
