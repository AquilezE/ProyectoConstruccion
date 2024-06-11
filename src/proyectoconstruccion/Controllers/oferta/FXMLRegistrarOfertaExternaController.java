/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectoconstruccion.Controllers.oferta;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import proyectoconstruccion.Controllers.profesorExterno.FXMLRegistrarProfesorExternoController;
import proyectoconstruccion.Utils.Utils;
import proyectoconstruccion.modelo.DAO.*;
import proyectoconstruccion.modelo.POJO.Idioma;
import proyectoconstruccion.modelo.POJO.Periodo;
import proyectoconstruccion.modelo.POJO.Universidad;
import proyectoconstruccion.modelo.POJO.academia.ExperienciaEducativa;
import proyectoconstruccion.modelo.POJO.ofertacolaboracion.OfertaColaboracionExterna;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;

/**
 * FXML Controller class
 *
 * @author unaay
 */
public class FXMLRegistrarOfertaExternaController implements Initializable {
    public TextField tfExperienciaEducativa;
    private ObservableList<Periodo> periodos;
    private ObservableList<ProfesorExterno> profesores;
    private ObservableList<ExperienciaEducativa> experiencias;
    private ObservableList<Idioma> idiomas;

    @FXML
    private TextField tfTitulo;
    @FXML
    private TextField tfDuracion;

    @FXML
    private ComboBox<ProfesorExterno> cbProfesorExterno;
    @FXML
    private ComboBox<Idioma> cbIdioma;
    @FXML
    private ComboBox<Periodo> cbPeriodo;

    @FXML
    private Label lbNombre;
    @FXML
    private Label lbCorreo;
    @FXML
    private Label lbUniversidad;
    @FXML
    private Label lbPais;
    @FXML
    private Button btnRegistrarProfesorExterno;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void inicializarValores(){



        //btnRegistrarProfesorExterno.setVisible(false);
        lbNombre.setText("");
        lbCorreo.setText("");
        lbUniversidad.setText("");
        lbPais.setText("");
        //cargarExperienciasEducativas();
        cargarIdiomas();
        cargarPeriodos();
        cargarProfesExternos();

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

    public void cargarProfesExternos(){
        profesores = FXCollections.observableArrayList();
        profesores.addAll(ProfesorDAO.obtenerTodosProfesoresExternos());
        cbProfesorExterno.setItems(profesores);

        // Asegurándose de que solo el nombre del profesor se muestre
        cbProfesorExterno.setCellFactory(new Callback<ListView<ProfesorExterno>, ListCell<ProfesorExterno>>() {
            @Override
            public ListCell<ProfesorExterno> call(ListView<ProfesorExterno> l) {
                return new ListCell<ProfesorExterno>() {
                    @Override
                    protected void updateItem(ProfesorExterno item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getNombre());
                        }
                    }
                };
            }
        });
        // Establece un listener para cuando un profesor es seleccionado
        cbProfesorExterno.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ProfesorExterno>() {
            @Override
            public void changed(ObservableValue<? extends ProfesorExterno> observable, ProfesorExterno oldValue, ProfesorExterno newValue) {
                if (newValue != null) {
                    // Actualiza los campos de texto con los detalles del profesor seleccionado
                    lbNombre.setText(newValue.getNombre());
                    lbCorreo.setText(newValue.getCorreoElectronico());

                    //Tienes que usar DAO Universidad
                    Universidad universidadSeleccionada = UniversidadDAO.getUniversidadById(newValue.getUniversidadID());

                    lbUniversidad.setText(universidadSeleccionada.getNombre());
                    lbPais.setText(universidadSeleccionada.getPais());

                } else {
                    // Vacía los campos si no hay profesor seleccionado
                    lbNombre.setText("");
                    lbCorreo.setText("");
                    lbUniversidad.setText("");
                    lbPais.setText("");
                }
            }
        });
        // Asegurándose de que solo el nombre del profesor se muestre en el campo de texto del ComboBox
        cbProfesorExterno.setConverter(new StringConverter<ProfesorExterno>() {
            @Override
            public String toString(ProfesorExterno profesor) {
                return profesor != null ? profesor.getNombre() : "";
            }

            @Override
            public ProfesorExterno fromString(String id) {
                return cbProfesorExterno.getItems().stream().filter(profesor ->
                        profesor.getNombre().equals(id)).findFirst().orElse(null);
            }
        });

    }

//    private void cargarExperienciasEducativas(){
//        experiencias = FXCollections.observableArrayList();
//        experiencias.addAll(ExperienciaEducativaDAO.obtenerTodasExperienciasEducativas());
//
//        // Configurar el renderizador de celda
//        cbExperienciaEducativa.setCellFactory(new Callback<ListView<ExperienciaEducativa>, ListCell<ExperienciaEducativa>>() {
//            @Override
//            public ListCell<ExperienciaEducativa> call(ListView<ExperienciaEducativa> l) {
//                return new ListCell<ExperienciaEducativa>() {
//                    @Override
//                    protected void updateItem(ExperienciaEducativa item, boolean empty) {
//                        super.updateItem(item, empty);
//                        if (item == null || empty) {
//                            setGraphic(null);
//                        } else {
//                            setText(item.getNombreExperienciaEducativa());  // asumiendo que "nombre" es el atributo que quieres mostrar
//                        }
//                    }
//                };
//            }
//        });
//
//        cbExperienciaEducativa.setItems(experiencias);
//    }
        
    @FXML
    private void btnClicRegistrarProfesorExterno(ActionEvent event) {
        try {
            Stage escenario = new Stage();
            FXMLLoader loader = Utils.obtenerLoader("Views/profesorExterno/FXMLRegistrarProfesorExterno.fxml");
            Parent root = loader.load();
            FXMLRegistrarProfesorExternoController controlador = loader.getController();
            controlador.inicializarValores(this);
            Scene escena = new Scene(root);
            escenario.setScene(escena);
            escenario.setTitle("Registrar profesor externo");
            escenario.initModality(Modality.APPLICATION_MODAL);
            escenario.showAndWait();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void btnClicRegresar(ActionEvent event) {
        Stage escenarioRegistrarOfertaExterna = (Stage) tfTitulo.getScene().getWindow();
        escenarioRegistrarOfertaExterna.close();
    }

    @FXML
    private void btnClicRegistrarOfertaColaboracionExterna(ActionEvent event) {

        if (validateFields()) {
// Fetch selected values from the combo boxes and text fields
            Periodo selectedPeriodo = cbPeriodo.getSelectionModel().getSelectedItem();
            ProfesorExterno selectedProfesorExterno = cbProfesorExterno.getSelectionModel().getSelectedItem();
            String title = tfTitulo.getText();
            String duration = tfDuracion.getText();
            Integer idIdioma = cbIdioma.getValue().getIdiomaID();

// Create Oferta object using those values
            OfertaColaboracionExterna oferta = new OfertaColaboracionExterna();
            oferta.setPeriodo(selectedPeriodo.getDescripcion());
            oferta.setProfesor(selectedProfesorExterno);
            oferta.setIdiomaID(idIdioma);
            oferta.setTitulo(title);
            oferta.setDuracion(duration);


// Save the newly created oferta (you would need a method for this in your OfertaDAO)
            OfertaColaboracionDAO.guardarOfertaExterna(oferta);
            Utils.cerrarVentana(event);
        } else {
            System.out.println("Campos invalidos");
        }
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
        if (cbProfesorExterno.getSelectionModel().getSelectedItem() == null) {
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

