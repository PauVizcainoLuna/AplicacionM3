package com.mycompany.proyectom3_pauvizcaino_javierriscos;

import java.io.IOException;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import modelo.Clases;
import modelo.GestioDades;
import modelo.Professor;

public class ClasesController {

    //Iniciamos la conexion con la base de datos
    GestioDades gestio = new GestioDades();

    //Inicializamos los elementos del FXML
    @FXML
    private TextField claseHorario;
    @FXML
    private TableView<Clases> tablaClases;
    @FXML
    private TableColumn<Clases, Integer> idClases;
    @FXML
    private TableColumn<Clases, String> horarioClases;
    @FXML
    private TableColumn<Clases, String> nombreProfesorClases;
    @FXML
    private ComboBox<Professor> cmbProfesores;
    @FXML
    private Tooltip Modificar;
    @FXML
    private Tooltip Añadir;
    @FXML
    private Tooltip Eliminar;

    public void initialize() throws SQLException {
        idClases.setCellValueFactory(new PropertyValueFactory<>("ID"));
        horarioClases.setCellValueFactory(new PropertyValueFactory<>("horario"));
        nombreProfesorClases.setCellValueFactory(new PropertyValueFactory<>("nombre_apellidos_profesor"));

        tablaClases.setItems(gestio.llistaClase());

        Modificar.setShowDelay(Duration.ONE);
        Eliminar.setShowDelay(Duration.ONE);
        Añadir.setShowDelay(Duration.ONE);

    }
//---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para cambiar a la pantalla Clase2

    public void cambiarPantalla() throws IOException {
        App.setRoot("Clases2");
    }

//---------------------------------------------------------------------------------------------------------------------------
//Metodo volver a eleccion
    public void tornarEnrere() throws IOException {
        App.setRoot("Eleccion");
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para añadir Clase desde afegirclase de GestioDades
    public void AfegirClase() throws SQLException {
        try {
            Professor profesor = cmbProfesores.getValue();
            String horario = claseHorario.getText();

            // Verificar si algun campo esta vacio
            if (profesor == null || horario.isEmpty()) {
                // Mensaje de error si no se han completado todos los campos
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar un profesor y completar el campo de horario.");

            } else {
                Clases clases = new Clases(gestio.generarIdClases(),
                        horario,
                        profesor.getNombre_apellidos());
                gestio.AfegirClase(clases, profesor);
                //Dejamos en blanco los textfield
                claseHorario.setText("");
                cmbProfesores.setValue(null);

                gestio.mostrarAlertCorrecto("HA AÑADIDO LA CLASE CORRECTAMENTE");

            }

        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Actualizar tabla automáticamente
        tablaClases.getItems().clear();
        tablaClases.setItems(gestio.llistaClase());
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para eliminar clase desde eliminarClases de GestioDades
    public void eliminarClases() throws SQLException {
        try {
            if (tablaClases.getSelectionModel().getSelectedItem() != null) {
                gestio.eliminarClases(tablaClases.getSelectionModel().getSelectedItem().getID());

                gestio.mostrarAlertCorrecto("HA ELIMINADO LA CLASE CORRECTAMENTE");

            } else {
                // Mostrar mensaje de error si no se ha seleccionado ninguna clase
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar una clase para eliminar.");
            }
        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Actualizar tabla automáticamente
        tablaClases.getItems().clear();
        tablaClases.setItems(gestio.llistaClase());
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para modificar clase  traves del metodo de modificarClases de GestioDades
    public void modificarClases() throws SQLException {
        try {
            Clases claseSeleccionada = tablaClases.getSelectionModel().getSelectedItem();
            Professor professor = cmbProfesores.getValue();
            String horario = claseHorario.getText();

            // Verificar si se ha seleccionado una clase, un profesor y si el horario no está vacío
            if (claseSeleccionada != null && professor != null && !horario.isEmpty()) {
                claseSeleccionada.setHorario(horario);
                gestio.modificarClase(claseSeleccionada.getID(),
                        claseSeleccionada.getHorario(),
                        professor);

                //Dejamos en blanco los textfield
                claseHorario.setText("");
                cmbProfesores.setValue(null);
                gestio.mostrarAlertCorrecto("HA MODIFICADO LA CLASE CORRECTAMENTE");

            } else {
                // Mensaje de error si no se han completado todos los campos
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar una clase, un profesor y completar el campo de horario.");
            }
        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Actualizar tabla automáticamente
        tablaClases.getItems().clear();
        tablaClases.setItems(gestio.llistaClase());
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para listar los profesores desde ArrayList getListaProfesores de GestioDades
    public void listarProfesores() {
        ObservableList<Professor> roleList = FXCollections.observableArrayList(gestio.getListaProfesores());

        LlenarCombo(cmbProfesores, roleList);

    }
    //---------------------------------------------------------------------------------------------------------------------------
    //Para llenar el comboBox

    public static void LlenarCombo(ComboBox<Professor> llenarcombo, ObservableList<Professor> infocombo) {
        llenarcombo.setItems(infocombo);
    }
}
