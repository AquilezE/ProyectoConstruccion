package proyectoconstruccion.modelo.POJO.profesor;

public abstract class Profesor {
    private Integer profesorId;

    private String correoElectronico;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;

    private Integer idiomaId;

    public Profesor(Integer profesorId, String correoElectronico, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, Integer idiomaId) {
        this.profesorId = profesorId;
        this.correoElectronico = correoElectronico;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.idiomaId = idiomaId;
    }

    public Profesor(String correoElectronico, String nombre, String apellidoPaterno, String apellidoMaterno, String telefono, Integer idiomaId) {
        this.profesorId = null;
        this.correoElectronico = correoElectronico;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.idiomaId = idiomaId;
    }

    public Profesor() {
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public Integer getIdiomaId() {
        return idiomaId;
    }

    public void setIdiomaId(Integer idiomaId) {
        this.idiomaId = idiomaId;
    }

    public Integer getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Integer profesorId) {
        this.profesorId = profesorId;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombreEntero(){
        return nombre+" "+apellidoPaterno+" "+apellidoMaterno;
    }
}
