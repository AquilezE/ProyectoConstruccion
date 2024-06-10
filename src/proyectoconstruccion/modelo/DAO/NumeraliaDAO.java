package proyectoconstruccion.modelo.DAO;


import proyectoconstruccion.modelo.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class NumeraliaDAO {

    public static Integer obtenerNumeroEstudiantes (){
        Connection conexionBD = ConexionBD.getConexion();
        int contadorEstudiantes=0;
        if(conexionBD != null){
            String consulta= "select NumeroEstudiantes " +
                    "from colaboracion " +
                    "where colaboracion_id=? AND Estado= 'Concluida' ";
            try{
                PreparedStatement prepararSentencia = conexionBD.prepareStatement(consulta);
                ResultSet resultado = prepararSentencia.executeQuery();

                while(resultado.next()){
                    contadorEstudiantes+=resultado.getInt("NumeroEstudiantes");
                }

                conexionBD.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return contadorEstudiantes;

    }
}
