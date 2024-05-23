package proyectoconstruccion.modelo.POJO.profesor;

public class ProfesorUV extends Profesor{
    private String NumeroPersonal;

    public ProfesorUV(Integer profesorId, String correoElectronico, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, Integer idiomaId, String numeroPersonal) {
        super(profesorId, correoElectronico, nombre, apellidoPaterno, apellidoMaterno, telefono, idiomaId);
        NumeroPersonal = numeroPersonal;
    }


    public ProfesorUV(){
    }

    public String getNumeroPersonal() {
        return NumeroPersonal;
    }

    public void setNumeroPersonal(String numeroPersonal) {
        NumeroPersonal = numeroPersonal;
    }

}
