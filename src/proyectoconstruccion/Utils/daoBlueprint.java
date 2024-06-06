package proyectoconstruccion.Utils;

import proyectoconstruccion.modelo.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class daoBlueprint {
    public static HashMap<String, Object> obtenerObjeto(Integer idObjeto){
        HashMap <String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put(Constantes.KEY_ERROR, true);
        Connection conexionBD = ConexionBD.getConexion();
        if(conexionBD != null){

            String consulta = "select * from something where id = ?";


            try{
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                prepararSentencia.setInt(1, idObjeto);
                ResultSet resultado = prepararSentencia.executeQuery();

                ArrayList<Object> lista = new ArrayList<>();
                while(resultado.next()){
                    //doSomething
                }
                respuesta.put(Constantes.KEY_ERROR, false);
                respuesta.put("nombreObjeto", lista);
                conexionBD.close();
            }catch(SQLException e){
                respuesta.put(Constantes.KEY_MENSAJE, e.getMessage());
            }
        }else{
            respuesta.put(Constantes.KEY_MENSAJE, Constantes.MSJ_ERROR_CONEXION);
        }
        return respuesta;
    }

}
