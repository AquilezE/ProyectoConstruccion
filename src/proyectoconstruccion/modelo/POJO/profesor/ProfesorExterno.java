package proyectoconstruccion.modelo.POJO.profesor;

public class ProfesorExterno extends Profesor{
        Integer universidadID;


    public ProfesorExterno(Integer profesorID, String correoElectronico, String nombre, String apellidoMaterno,String apellidoPaterno, String telefono,Integer idiomaId,Integer universidadID) {
        super(profesorID, correoElectronico,nombre,apellidoPaterno,apellidoMaterno,telefono,idiomaId);
        this.universidadID = universidadID;
    }

    public ProfesorExterno() {
    }

    public Integer getUniversidadID() {
        return universidadID;
    }

    public void setUniversidadID(Integer universidadID) {
        this.universidadID = universidadID;
    }

}
