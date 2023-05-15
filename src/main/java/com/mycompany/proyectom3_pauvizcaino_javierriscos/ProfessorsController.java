package com.mycompany.proyectom3_pauvizcaino_javierriscos;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;
import modelo.GestioDades;
import modelo.Professor;

public class ProfessorsController {

    //Iniciamos la conexion con la base de datos
    GestioDades gestio = new GestioDades();

    //Inicializamos los elementos del FXML
    @FXML
    private TextField profeNom;
    @FXML
    private TextField profeCognom;
    @FXML
    private TableView<Professor> tablaProfesores;
    @FXML
    private TableColumn<Professor, Integer> idProfe;
    @FXML
    private TableColumn<Professor, String> nombreProfe;
    @FXML
    private TableColumn<Professor, String> apellidosProfe;
    @FXML
    private TableColumn<Professor, String> nombreApellidosProfe;
    @FXML
    private Tooltip Modificar;
    @FXML
    private Tooltip Añadir;
    @FXML
    private Tooltip Eliminar;

    public void initialize() throws SQLException {
        idProfe.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nombreProfe.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidosProfe.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        nombreApellidosProfe.setCellValueFactory(new PropertyValueFactory<>("nombre_apellidos"));

        tablaProfesores.setItems(gestio.llistaProfe());
        Modificar.setShowDelay(Duration.ONE);
        Añadir.setShowDelay(Duration.ONE);
        Eliminar.setShowDelay(Duration.ONE);

    }

    public void cambiarPantalla() throws IOException {
        App.setRoot("Professors2");
    }

    //---------------------------------------------------------------------------------------------------------------------------
    public void tornarEnrere() throws IOException {
        App.setRoot("Eleccion");
    }

    //---------------------------------------------------------------------------------------------------------------------------
    public void AfegirProfesor() throws SQLException {
        try {
            // Verificar si los campos están vacíos
            if (profeNom.getText().isEmpty() || profeCognom.getText().isEmpty()) {
                // Mostrar mensaje de error
                gestio.mostrarAlertWarning("ERROR: Debes completar todos los campos.");
                return; // Salir del método sin crear el profesor
            }

            Professor profesor = new Professor(gestio.generarIdProfesores(),
                    profeNom.getText(),
                    profeCognom.getText(),
                    (profeNom.getText() + " " + profeCognom.getText()));

            gestio.AfegirProfesor(profesor);

            // Dejar en blanco los campos de texto
            profeNom.setText("");
            profeCognom.setText("");
        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Actualizar tabla automáticamente
        tablaProfesores.getItems().clear();
        tablaProfesores.setItems(gestio.llistaProfe());
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para eliminar profesor  desde eliminarProfesor de GestioDades
    public void eliminarProfesores() throws SQLException {
        try {
            if (tablaProfesores.getSelectionModel().getSelectedItem() != null) {
                gestio.eliminarProfesor(tablaProfesores.getSelectionModel().getSelectedItem().getID());
            } else {
                // Mostrar mensaje de error si no se ha seleccionado ningún profesor
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar un profesor.");
                return; // Salir del método sin eliminar el profesor
            }
        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Actualizar tabla automáticamente
        tablaProfesores.getItems().clear();
        tablaProfesores.setItems(gestio.llistaProfe());
    }

    //---------------------------------------------------------------------------------------------------------------------------
    public void modificarProfesores() throws SQLException {
        try {
            Professor profesorSeleccionat = tablaProfesores.getSelectionModel().getSelectedItem();

            // Verificar si hay un profesor seleccionado y los campos no están vacíos
            if (profesorSeleccionat != null && !profeNom.getText().isEmpty() && !profeCognom.getText().isEmpty()) {

                gestio.modificarProfesor(profesorSeleccionat.getID(),
                        profeNom.getText(),
                        profeCognom.getText(),
                        (profeNom.getText() + " " + profeCognom.getText()));
            } else {
                // Mostrar mensaje de error si falta información
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar un profesor y completar todos los campos.");
                return; // Salir del método sin modificar el profesor
            }
        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Actualizar tabla automáticamente
        tablaProfesores.getItems().clear();
        tablaProfesores.setItems(gestio.llistaProfe());
    }

}
