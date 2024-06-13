package proyectoconstruccion.modelo.POJO;

public class Idioma {
    private Integer idiomaID;
    private String idioma;

    public Idioma(Integer idiomaID, String idioma) {
        this.idiomaID = idiomaID;
        this.idioma = idioma;
    }

    public Integer getIdiomaID() {
        return idiomaID;
    }

    public void setIdiomaID(Integer idiomaID) {
        this.idiomaID = idiomaID;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }
}