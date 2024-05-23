package proyectoconstruccion.modelo.POJO.colaboracion;

import proyectoconstruccion.modelo.POJO.academia.ExperienciaEducativa;
import proyectoconstruccion.modelo.POJO.evidencia.Evidencia;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

import java.util.Date;

public class Colaboracion {

    private Integer colaboracionId;

    private String Duracion;
    private String Periodo;
    private String Titulo;
    private String Idioma;
    private Date fechaInicio;
    private Date fechaCierre;
    private String tipo;
    private String estado;

    private ProfesorUV profesorUv;
    private ProfesorExterno profesorExterno;
    private ExperienciaEducativa experienciaEducativa;
    private Evidencia evidencia;

    public Colaboracion(Integer colaboracionId, String duracion, String periodo, String titulo, String idioma, Date fechaInicio, Date fechaCierre, String tipo, String estado, ProfesorUV profesorUv, ProfesorExterno profesorExterno, ExperienciaEducativa experienciaEducativa, Evidencia evidencia) {
        this.colaboracionId = colaboracionId;
        Duracion = duracion;
        Periodo = periodo;
        Titulo = titulo;
        Idioma = idioma;
        this.fechaInicio = fechaInicio;
        this.fechaCierre = fechaCierre;
        this.tipo = tipo;
        this.estado = estado;
        this.profesorUv = profesorUv;
        this.profesorExterno = profesorExterno;
        this.experienciaEducativa = experienciaEducativa;
        this.evidencia = evidencia;
    }


    public ExperienciaEducativa getExperienciaEducativa() {
        return experienciaEducativa;
    }

    public void setExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        this.experienciaEducativa = experienciaEducativa;
    }

    public Evidencia getEvidencia() {
        return evidencia;
    }

    public void setEvidencia(Evidencia evidencia) {
        this.evidencia = evidencia;
    }

    //Falta saber la implementacion de como crear Colaboracion para poder hacer su creador



    public Integer getColaboracionId() {
        return colaboracionId;
    }

    public void setColaboracionId(Integer colaboracionId) {
        this.colaboracionId = colaboracionId;
    }

    public String getDuracion() {
        return Duracion;
    }

    public void setDuracion(String duracion) {
        Duracion = duracion;
    }

    public String getPeriodo() {
        return Periodo;
    }

    public void setPeriodo(String periodo) {
        Periodo = periodo;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getIdioma() {
        return Idioma;
    }

    public void setIdioma(String idioma) {
        Idioma = idioma;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ProfesorUV getProfesorUv() {
        return profesorUv;
    }

    public void setProfesorUv(ProfesorUV profesorUv) {
        this.profesorUv = profesorUv;
    }

    public ProfesorExterno getProfesorExterno() {
        return profesorExterno;
    }

    public void setProfesorExterno(ProfesorExterno profesorExterno) {
        this.profesorExterno = profesorExterno;
    }

}
