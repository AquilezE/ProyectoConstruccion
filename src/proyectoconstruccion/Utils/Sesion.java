package proyectoconstruccion.Utils;

import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

public class Sesion {

    private static Sesion instancia;

    private String rol;
    private ProfesorUV profesorUsuario;

    private Sesion(){}

    public static Sesion getInstancia(){
        if(instancia == null){
            instancia = new Sesion();
        }
        return instancia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public ProfesorUV getProfesorUsuario() {
        return profesorUsuario;
    }

    public void setProfesorUsuario(ProfesorUV profesorUsuario) {
        this.profesorUsuario = profesorUsuario;
    }
}
