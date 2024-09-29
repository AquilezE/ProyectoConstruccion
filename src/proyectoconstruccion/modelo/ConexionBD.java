package proyectoconstruccion.modelo;

import proyectoconstruccion.Utils.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    public static final String URI_CONEXION ="jdbc:mysql://"+ Constantes.HOSTNAME+":"+Constantes.PUERTO+"/"+Constantes.NOMBRE_BD+"?allowPublicKeyRetrieval=true&useSSL=false";

    public static Connection getConexion() {
        Connection conexionBD=null;
        try{
            Class.forName(Constantes.Driver);
            conexionBD = DriverManager.getConnection(URI_CONEXION,Constantes.USUARIO,Constantes.PASSWORD);
        }catch (ClassNotFoundException ex){

        }catch (SQLException ex){

        }
        return conexionBD;
    }
}


