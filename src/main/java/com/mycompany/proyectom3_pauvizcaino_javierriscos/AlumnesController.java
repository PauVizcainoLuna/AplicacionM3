/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
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
import modelo.Alumne;
import modelo.GestioDades;
import modelo.Grados;

/**
 * FXML Controller class
 *
 * @author PauVizcaino
 */
public class AlumnesController {

    //Iniciamos la conexion con la base de datos
    GestioDades gestio = new GestioDades();

    //Inicializamos todos los elemenos del FXML
    @FXML
    private TextField usuariCognom;
    @FXML
    private TextField usuariDireccio;
    @FXML
    private TextField usuariCorreu;
    @FXML
    private TextField usuariCodPostal;
    @FXML
    private TextField usuariDataNaix;
    @FXML
    private TextField usuariNom;
    @FXML
    private TableView<Alumne> tablaAlumnos;

    @FXML
    private TableColumn<Alumne, String> idAlumno;
    @FXML
    private TableColumn<Alumne, String> nombreAlumnos;
    @FXML
    private TableColumn<Alumne, String> apellidosAlumnos;
    @FXML
    private TableColumn<Alumne, String> nombreApellidosAlumnos;
    @FXML
    private TableColumn<Alumne, String> fechaAlumnos;
    @FXML
    private TableColumn<Alumne, String> correoAlumnos;
    @FXML
    private TableColumn<Alumne, String> direccionAlumnos;
    @FXML
    private TableColumn<Alumne, String> codigoAlumnos;
    @FXML
    private TableColumn<Alumne, String> colorGradoAlumnos;
    @FXML
    private Tooltip Modificar;
    @FXML
    private Tooltip Añadir;
    @FXML
    private Tooltip Eliminar;
    @FXML
    public ComboBox<Grados> cmbGrados;

    /**
     * Initializes the controller class.
     *
     * @throws java.sql.SQLException
     */
    public void initialize() throws SQLException {

        idAlumno.setCellValueFactory(new PropertyValueFactory<>("id_alumno"));
        nombreAlumnos.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidosAlumnos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        nombreApellidosAlumnos.setCellValueFactory(new PropertyValueFactory<>("nombre_apellidos"));
        correoAlumnos.setCellValueFactory(new PropertyValueFactory<>("correo_elec"));
        fechaAlumnos.setCellValueFactory(new PropertyValueFactory<>("data_naix"));
        direccionAlumnos.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        codigoAlumnos.setCellValueFactory(new PropertyValueFactory<>("cod_postal"));
        colorGradoAlumnos.setCellValueFactory(new PropertyValueFactory<>("colorGrado"));

        tablaAlumnos.setItems(gestio.llistaAlumnes());

        Modificar.setShowDelay(Duration.ONE);
        Añadir.setShowDelay(Duration.ONE);
        Eliminar.setShowDelay(Duration.ONE);
    }

    //Metodo para cambiar a la pantalla Alumne2
    public void cambiarPantalla() throws IOException {
        App.setRoot("Alumne2");
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para volver al menu principal
    public void tornarEnrere() throws IOException {
        App.setRoot("Eleccion");
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para añadir Alumno desde AfegirAlumne de GestioDades
    public void AfegirAlumne() throws SQLException {
        try {
            Grados grado = cmbGrados.getValue();

            // Verificar si algún campo de texto está vacío
            if (usuariNom.getText().isEmpty() || usuariCognom.getText().isEmpty() || usuariDataNaix.getText().isEmpty()
                    || usuariCorreu.getText().isEmpty() || usuariDireccio.getText().isEmpty() || usuariCodPostal.getText().isEmpty()
                    || grado == null) {
                // Mostrar mensaje de error
                gestio.mostrarAlertWarning("ERROR: Debes completar todos los campos y seleccionar un grado.");
                return; // Salir del método sin agregar el alumno
            }

            Alumne alumne = new Alumne(
                    gestio.generarIdAlumnos(),
                    usuariNom.getText(),
                    usuariCognom.getText(),
                    (usuariNom.getText() + " " + usuariCognom.getText()),
                    usuariDataNaix.getText(),
                    usuariCorreu.getText(),
                    usuariDireccio.getText(),
                    Integer.parseInt(usuariCodPostal.getText()),
                    grado.getColor()
            );
            gestio.AfegirAlumne(alumne, grado);

            //Dejamos en blanco los textfield
            usuariNom.setText("");
            usuariCognom.setText("");
            usuariDataNaix.setText("");
            usuariCorreu.setText("");
            usuariDireccio.setText("");
            usuariCodPostal.setText("");
            cmbGrados.setValue(null);

        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Actualizar tabla automáticamente
        tablaAlumnos.getItems().clear();
        tablaAlumnos.setItems(gestio.llistaAlumnes());
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para eliminar alumno desde eliminarAlumnos de GestioDades
    public void eliminarAlumnos() throws SQLException {
        try {
            if (tablaAlumnos.getSelectionModel().getSelectedItem() != null) {
                gestio.eliminarAlumnos(tablaAlumnos.getSelectionModel().getSelectedItem().getId_alumno());
            } else {
                // Mostrar mensaje de error si no se ha seleccionado ningún alumno
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar un alumno.");
                return; // Salir del método sin eliminar el alumno
            }
        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Actualizar tabla automáticamente
        tablaAlumnos.getItems().clear();
        tablaAlumnos.setItems(gestio.llistaAlumnes());
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para modificar alumno  traves del metodo de modificarAlumnos de GestioDades
    public void modificarAlumne() throws SQLException {
        try {
            Alumne alumneSeleccionat = tablaAlumnos.getSelectionModel().getSelectedItem();
            Grados grado = cmbGrados.getValue();

            // Verificar si algún campo de texto está vacío
            if (alumneSeleccionat != null && grado != null && !usuariNom.getText().isEmpty() && !usuariCognom.getText().isEmpty()
                    && !usuariDataNaix.getText().isEmpty() && !usuariCorreu.getText().isEmpty() && !usuariDireccio.getText().isEmpty()
                    && !usuariCodPostal.getText().isEmpty()) {
                gestio.modificarAlumnos(alumneSeleccionat.getId_alumno(),
                        grado,
                        usuariNom.getText(),
                        usuariCognom.getText(),
                        (usuariNom.getText() + " " + usuariCognom.getText()),
                        usuariDataNaix.getText(),
                        usuariCorreu.getText(),
                        usuariDireccio.getText(),
                        Integer.parseInt(usuariCodPostal.getText()));
            } else {
                // Mostrar mensaje de error si falta información
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar un alumno, completar todos los campos y seleccionar un grado.");
                return; // Salir del método sin modificar el alumno
            }

            //Dejamos en blanco los textfield
            usuariNom.setText("");
            usuariCognom.setText("");
            usuariDataNaix.setText("");
            usuariCorreu.setText("");
            usuariDireccio.setText("");
            usuariCodPostal.setText("");
            cmbGrados.setValue(null);
        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Actualizar tabla automáticamente
        tablaAlumnos.getItems().clear();
        tablaAlumnos.setItems(gestio.llistaAlumnes());
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para listar los grados desde ArrayList getListaGrados de GestioDades
    public void listarGrados() {
        ObservableList<Grados> roleList = FXCollections.observableArrayList(gestio.getListaGrados());

        LlenarCombo(cmbGrados, roleList);

    }
    //---------------------------------------------------------------------------------------------------------------------------
    //Para llenar el comboBox

    public static void LlenarCombo(ComboBox<Grados> llenarcombo, ObservableList<Grados> infocombo) {
        llenarcombo.setItems(infocombo);
    }

}
