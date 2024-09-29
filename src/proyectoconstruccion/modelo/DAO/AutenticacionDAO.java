package proyectoconstruccion.modelo.DAO;

import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.Utils.PasswordUtils;
import proyectoconstruccion.modelo.ConexionBD;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class AutenticacionDAO {
    public static HashMap<String,Object> iniciarSesion(String email,String password){
        HashMap<String,Object> respuesta=new HashMap<>();
        respuesta.put(Constantes.KEY_ERROR,true);

        String hashedPassword = PasswordUtils.hashPassword(password);

        System.out.println(hashedPassword);

        
        //existen dos maneras de ingresar al sistema, como admin (Coordinador COIL-VIC) y como profesor UV
        if(email.equals("admin") && hashedPassword.equals(PasswordUtils.hashPassword("admin"))){
            respuesta.put(Constantes.KEY_ERROR,false);
            respuesta.put("profesor",null);
            respuesta.put(Constantes.KEY_MENSAJE,"El usuario ingresado es un Admin, no necesita credenciales");

            return respuesta;
        }

        String consulta= "select p.profesor_id, CorreoElectronico, Telefono, idioma_id, Nombre, ApellidoPaterno, ApellidoMaterno,pu.NumeroPersonal from profesor p JOIN profesoruv pu ON p.profesor_id = pu.profesor_id WHERE pu.password = ? AND CorreoElectronico = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement prepararSentencia = conexion.prepareStatement(consulta)){

            prepararSentencia.setString(1,hashedPassword);
            prepararSentencia.setString(2,email);

            try(ResultSet resultado = prepararSentencia.executeQuery();) {

                if(resultado.next()){
                    Integer profesorId = resultado.getInt("profesor_id");
                    String correoElectronico = resultado.getString("CorreoElectronico");
                    String telefono = resultado.getString("Telefono");
                    Integer idiomaId = resultado.getInt("idioma_id");
                    String nombre = resultado.getString("Nombre");
                    String apellidoPaterno = resultado.getString("ApellidoPaterno");
                    String apellidoMaterno = resultado.getString("ApellidoMaterno");
                    String numeroPersonnal = resultado.getString("NumeroPersonal");

                    ProfesorUV profesorParaSesion = new ProfesorUV(profesorId, correoElectronico, nombre, apellidoPaterno, apellidoMaterno, telefono, idiomaId, numeroPersonnal);

                    respuesta.put("profesor", profesorParaSesion);
                    respuesta.put(Constantes.KEY_ERROR, false);
                    respuesta.put(Constantes.KEY_MENSAJE, "Profesor " + profesorId + " recuperado con exito");
                }else{
                    respuesta.put(Constantes.KEY_MENSAJE, "Contraseña o correo incorrecto");
                }

            }

        }catch (SQLException e){
            e.printStackTrace();
            respuesta.put(Constantes.KEY_MENSAJE,e.getMessage());
        }

    return respuesta;
    }
}
