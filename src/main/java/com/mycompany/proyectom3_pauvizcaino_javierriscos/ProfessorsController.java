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
    //Metodo para añadir profesor desde AfegirProfesor de GestioDdaes
    public void AfegirProfesor() throws SQLException {
        try {
            // Verificar si los algun campo esta vacio
            if (profeNom.getText().isEmpty() || profeCognom.getText().isEmpty()) {
                // Mensaje de error si no se han completado todos los campos
                gestio.mostrarAlertWarning("ERROR: Debes completar todos los campos.");

            } else {
                Professor profesor = new Professor(gestio.generarIdProfesores(),
                        profeNom.getText(),
                        profeCognom.getText(),
                        (profeNom.getText() + " " + profeCognom.getText()));

                gestio.AfegirProfesor(profesor);

                // Dejar en blanco los campos de texto
                profeNom.setText("");
                profeCognom.setText("");

            }

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
                // Mensaje de error si no se ha seleccionado ningún profesor
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar un profesor.");
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

            // Verificar si hay un profesor seleccionado y los campos no estan vacios
            if (profesorSeleccionat != null && !profeNom.getText().isEmpty() && !profeCognom.getText().isEmpty()) {

                gestio.modificarProfesor(profesorSeleccionat.getID(),
                        profeNom.getText(),
                        profeCognom.getText(),
                        (profeNom.getText() + " " + profeCognom.getText()));
            } else {
                // Mensaje de error si no se han completado todos los campos
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar un profesor y completar todos los campos.");
            }
        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
        // Dejar en blanco los campos de texto
        profeNom.setText("");
        profeCognom.setText("");

        // Actualizar tabla automáticamente
        tablaProfesores.getItems().clear();
        tablaProfesores.setItems(gestio.llistaProfe());
    }

}
