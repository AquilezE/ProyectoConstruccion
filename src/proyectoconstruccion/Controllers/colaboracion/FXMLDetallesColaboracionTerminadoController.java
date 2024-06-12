package proyectoconstruccion.Controllers.colaboracion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import proyectoconstruccion.Utils.CSVReader;
import proyectoconstruccion.Utils.Constantes;
import proyectoconstruccion.Utils.Sesion;
import proyectoconstruccion.modelo.DAO.EvidenciaDAO;
import proyectoconstruccion.modelo.POJO.Estudiante;
import proyectoconstruccion.modelo.POJO.colaboracion.Colaboracion;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorExterno;
import proyectoconstruccion.modelo.POJO.profesor.ProfesorUV;

import java.io.IOException;
import java.io.InputStream;


import java.awt.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FXMLDetallesColaboracionTerminadoController implements Initializable {
    public Label lbIdColaboracion;
    public Label lbTitulo;
    public Label lbNombreProfesorUV;
    public Label lbNombreProfesorExterno;
    public Label lbIdioma;
    public Label lbExperienciaEducativa;
    public Label lbPeriodo;
    public Label lbFechas;


    public TableView<Estudiante> tvEstudiantes;
    public TableColumn<?,?> colMatricula;
    public TableColumn<?,?> colNombre;
    public TableColumn<?,?> colCalificacion;
    public TableColumn<?,?> colFaltas;

    public Button btnAprobarConstan;

    Colaboracion colaboracion;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarEstudiantesDesdeCSV();
    }

    public void inicializarValores(Colaboracion colaboracion){

        if (Sesion.getInstancia().getRol().equals(Constantes.PROFESOR)){
            btnAprobarConstan.setVisible(false);
        }
        this.colaboracion = colaboracion;
        lbIdColaboracion.setText(String.valueOf(colaboracion.getColaboracionId()));
        lbIdColaboracion.setText(String.valueOf(colaboracion.getColaboracionId()));
        lbTitulo.setText(colaboracion.getTitulo());


        ProfesorUV profesorUV = colaboracion.getProfesorUv();
        ProfesorExterno profesorExterno = colaboracion.getProfesorExterno();

        if (profesorUV != null) {
            String nombreProfesor = profesorUV.getNombre() + " " + profesorUV.getApellidoMaterno() + " " + profesorUV.getApellidoPaterno();
            lbNombreProfesorUV.setText(nombreProfesor);
        }

        if (profesorExterno != null) {
            String nombreProfesorExterno = profesorExterno.getNombre() + " " + profesorExterno.getApellidoMaterno() + " " + profesorExterno.getApellidoPaterno();
            lbNombreProfesorExterno.setText(nombreProfesorExterno);
        }
        lbIdioma.setText(colaboracion.getIdioma().getIdioma());
        lbPeriodo.setText(colaboracion.getPeriodo());


        lbFechas.setText(colaboracion.getFechaInicio().toString() + " - " + colaboracion.getFechaCierre().toString());

        lbExperienciaEducativa.setText(colaboracion.getExperienciaEducativa().getNombreExperienciaEducativa());

        lbPeriodo.setText(colaboracion.getPeriodo());


    }

    public void btnRegresar(ActionEvent actionEvent) {
        System.out.println("Boton de regresar");
    }

    public void btnAprobarConstancia(ActionEvent actionEvent) {
        System.out.println("Boton de aprobar constancia");
    }

    public void btnEvidencia(ActionEvent actionEvent) {

        try {
            // Retrieve the input stream of the "Evidencia.zip" file from EvidenciaDAO
            InputStream is = EvidenciaDAO.getEvidencia(this.colaboracion.getEvidencia().getEvidenciaId());

            // Create a FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Evidencia");

            // Set the initial file name to "evidenciasColaboracion" + this.colaboracion.getColaboracionId()
            fileChooser.setInitialFileName("evidenciasColaboracion" + this.colaboracion.getColaboracionId() + ".zip");

            // Show the save file dialog
            File savedFile = fileChooser.showSaveDialog(null);

            // Use the Files.copy() method to copy the content of the InputStream to the selected location
            if (savedFile != null) {
                Files.copy(is, savedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            System.out.println("File downloaded successfully!");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void btnSyllabus(ActionEvent actionEvent) {
        try {
            File tempFile = EvidenciaDAO.getSyllabus(colaboracion.getEvidencia().getEvidenciaId());
            if (tempFile != null) {
                // Asigning the PDF format to the temp file.
                File pdfFile = new File(tempFile.getAbsolutePath() + ".pdf");

                if (!pdfFile.exists()) {
                    boolean renameStatus = tempFile.renameTo(pdfFile);

                    // Check status of renaming operation
                    if(!renameStatus){
                        System.out.println("Error: unable to rename file to PDF");
                        return;
                    }
                }

                openFileInDefaultBrowser(pdfFile);
                monitorAndDelete(pdfFile);
            } else {
                System.out.println("Documento de escritura no encontrado.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void openFileInDefaultBrowser(File file) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.BROWSE)) {
                desktop.browse(file.toURI());
            } else {
                System.out.println("Abrir el navegador no es compatible en este sistema.");
            }
        } else {
            System.out.println("El escritorio no es compatible en este sistema.");
        }
    }

    private static void monitorAndDelete(File file) {
        Executors.newSingleThreadScheduledExecutor().schedule(() -> {
            while (file.exists()) {
                try {
                    Files.deleteIfExists(file.toPath());
                    TimeUnit.SECONDS.sleep(1);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 5, TimeUnit.SECONDS);
    }

    public void btnDescargarListaEstudiantes(ActionEvent actionEvent) {

        try {
            // Assuming there is a method in a DAO class that retrieves the list of students as an InputStream
            InputStream is = EvidenciaDAO.getListaDeEstudiantes(this.colaboracion.getEvidencia().getEvidenciaId());

            // Create a FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Student List");

            // Set the initial file name to "listaEstudiantesColaboracion" + this.colaboracion.getColaboracionId()
            fileChooser.setInitialFileName("listaEstudiantesColaboracion" + this.colaboracion.getColaboracionId() + ".csv");

            // Show the save file dialog
            File savedFile = fileChooser.showSaveDialog(null);

            // Use the Files.copy() method to copy the content of the InputStream to the selected location
            if (savedFile != null) {
                Files.copy(is, savedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }

            System.out.println("File downloaded successfully!");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }
    public void cargarEstudiantesDesdeCSV() {
        CSVReader excelReader = new CSVReader();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            // Utilizamos el lector de Excel para obtener la lista de estudiantes desde el archivo CSV
            List<Estudiante> estudiantes = excelReader.readCSV(file);

            // Limpiamos cualquier dato previamente existente en el TableView
            tvEstudiantes.getItems().clear();

            // Configuramos las columnas del TableView
            colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
            colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            colCalificacion.setCellValueFactory(new PropertyValueFactory<>("calificacion"));
            colFaltas.setCellValueFactory(new PropertyValueFactory<>("faltas"));

            // Agregamos los estudiantes al TableView
            tvEstudiantes.getItems().addAll(estudiantes);
        } else {
            System.out.println("No file selected");
        }
    }




}
