package proyectoconstruccion.Controllers.oferta.EditarOFerta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import proyectoconstruccion.Controllers.oferta.FXMLDetallesOfertaColaboracion;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.IdiomaDAO;
import proyectoconstruccion.modelo.DAO.OfertaColaboracionDAO;
import proyectoconstruccion.modelo.DAO.PeriodoDAO;
import proyectoconstruccion.modelo.POJO.Idioma;
import proyectoconstruccion.modelo.POJO.Periodo;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracion;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLEdicionOfertaColaboracionController implements Initializable {
    private ObservableList<Periodo> periodos;
    private ObservableList<Idioma> idiomas;


    public TextField tfTitulo;
    public TextField tfDuracion;
    @FXML
    private ComboBox<Idioma> cbIdioma;
    @FXML
    private ComboBox<Periodo> cbPeriodo;


    private FXMLDetallesOfertaColaboracion detallesOfertaColaboracion;
    private OfertaColaboracion ofertaColaboracion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void InicializarDatos(FXMLDetallesOfertaColaboracion detallesOfertaColaboracion, OfertaColaboracion ofertaColaboracion){

            this.ofertaColaboracion=ofertaColaboracion;
            this.detallesOfertaColaboracion = detallesOfertaColaboracion;
            this.tfTitulo.setText(ofertaColaboracion.getTitulo());
            this.tfDuracion.setText(ofertaColaboracion.getDuracion());
            cargarIdiomas();
            cargarPeriodos();

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
