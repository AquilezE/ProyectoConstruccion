package proyectoconstruccion.Controllers.oferta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.util.StringConverter;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.IdiomaDAO;
import proyectoconstruccion.modelo.DAO.OfertaColaboracionDAO;
import proyectoconstruccion.modelo.DAO.PeriodoDAO;
import proyectoconstruccion.modelo.POJO.Idioma;
import proyectoconstruccion.modelo.POJO.Periodo;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracion;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;

public class FXMLEdicionOfertaColaboracionController implements Initializable {
    private ObservableList<Periodo> periodos;
    private ObservableList<Idioma> idiomas;


    public TextField tfTitulo;
    public TextField tfDuracion;
    @FXML
    private ComboBox<Idioma> cbIdioma;
    @FXML
    private ComboBox<Periodo> cbPeriodo;


    private FXMLDetallesOfertaColaboracionController detallesOfertaColaboracion;
    private OfertaColaboracion ofertaColaboracion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void InicializarDatos(FXMLDetallesOfertaColaboracionController detallesOfertaColaboracion, OfertaColaboracion ofertaColaboracion){
        this.ofertaColaboracion=ofertaColaboracion;
        this.detallesOfertaColaboracion = detallesOfertaColaboracion;
        cargarIdiomas();
        cargarPeriodos();
        if(ofertaColaboracion != null){
            cargarInformacionOfertaExternaEdicion();
        }
    }
    
    public void cargarInformacionOfertaExternaEdicion(){
        this.tfTitulo.setText(ofertaColaboracion.getTitulo());
        this.cbIdioma.getSelectionModel().select(buscarIdIdioma(ofertaColaboracion.getIdiomaId()));
        //this.cbPeriodo.getSelectionModel().select(buscarIdPeriodo(ofertaColaboracion.getPeriodoId()));
        this.tfDuracion.setText(ofertaColaboracion.getDuracion());
    }
    
    private int buscarIdIdioma(int idIdioma){
        for (int i = 0; i < idiomas.size(); i++) {
            if(idiomas.get(i).getIdiomaID() == idIdioma){
                return i;
            }
        }
        return 0;
    }
    
    private int buscarIdPeriodo(int idPeriodo){
        for (int i = 0; i < periodos.size(); i++) {
            if(periodos.get(i).getPeriodoId() == idPeriodo){
                return i;
            }
        }
        return 0;
    }
    
    public void btnFinalizar(ActionEvent actionEvent) {

        if (validateFields()){
            Idioma idioma = cbIdioma.getSelectionModel().getSelectedItem();
            Periodo periodo = cbPeriodo.getSelectionModel().getSelectedItem();
            if (OfertaColaboracionDAO.updateOfertaColaboracion(this.ofertaColaboracion,tfTitulo.getText(),tfDuracion.getText(),idioma.getIdiomaID(),periodo.getDescripcion())){
                System.out.println("Oferta Editada");
                detallesOfertaColaboracion.refreshData();
            }else {
                System.out.println("Oferta No Editada");
            }
            Utils.cerrarVentana(actionEvent);
        }else{
            Utils.mostrarAlertaSimple("Error", "No pueden quedar campos vacíos", Alert.AlertType.ERROR);
        }
    }

    private void cargarIdiomas() {
        idiomas = FXCollections.observableArrayList();
        idiomas.addAll(IdiomaDAO.obtenerIdiomas());

        cbIdioma.setItems(idiomas);
        cbIdioma.setConverter(new StringConverter<Idioma>() {
            @Override
            public String toString(Idioma object) {
                return object.getIdioma();
            }

            @Override
            public Idioma fromString(String string) {
                return idiomas.stream().filter(o -> o.getIdioma().equals(string)).findFirst().orElse(null);
            }
        });
    }

    private void cargarPeriodos(){
        periodos = FXCollections.observableArrayList();
        periodos.addAll(PeriodoDAO.getPeriodos());
        cbPeriodo.setItems(periodos);

        cbPeriodo.setCellFactory(new Callback<ListView<Periodo>, ListCell<Periodo>>() {
            @Override
            public ListCell<Periodo> call(ListView<Periodo> param) {
                return new ListCell<Periodo>() {
                    @Override
                    protected void updateItem(Periodo item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setText(null);
                        } else {
                            setText(item.getDescripcion());
                        }
                    }
                };
            }
        });

        cbPeriodo.setButtonCell(new ListCell<Periodo>() {
            @Override
            protected void updateItem(Periodo item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(null);
                } else {
                    setText(item.getDescripcion());
                }
            }
        });
    }

    private boolean validateFields () {
        boolean valid = true;

        // Check for selected values in combo boxes
        if (cbIdioma.getSelectionModel().getSelectedItem() == null) {
            valid = false;
        }
        if (cbPeriodo.getSelectionModel().getSelectedItem() == null) {
            valid = false;
        }

        // Check for empty fields in text fields, disallow spaces and newlines
        if (tfTitulo.getText().trim().isEmpty() || tfTitulo.getText().trim().isEmpty()) {
            valid = false;
        }
        if (tfDuracion.getText().trim().isEmpty() || tfDuracion.getText().trim().isEmpty()) {
            valid = false;
        }

        return valid;
    }

}
