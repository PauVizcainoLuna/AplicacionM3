/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Alumne;
import modelo.GestioDades;
import modelo.ProfesorAlumne;
import modelo.Professor;

/**
 *
 * @author PauVizcaino
 */
public class Profressors2Controller {

    //Iniciamos la conexion con la base de datos
    GestioDades gestio = new GestioDades();

    @FXML
    private TableView<ProfesorAlumne> tablaProfesores2;
    @FXML
    private TableColumn<Professor, Integer> nombreProfe;
    @FXML
    private TableColumn<Professor, String> nombreAlumno;
    @FXML
    private ComboBox<Professor> cmbBoxProfesor;
    @FXML
    private ComboBox<Alumne> cmbBoxAlumne;

    public void initialize() throws SQLException {
        nombreProfe.setCellValueFactory(new PropertyValueFactory<>("nombreProfesor"));
        nombreAlumno.setCellValueFactory(new PropertyValueFactory<>("nombreAlumno"));
        tablaProfesores2.setItems(gestio.llistaProfeAlumnos());
    }

    //Metodo para cambiar a la pantalla Profesor1
    public void cambiarPantalla() throws IOException {
        App.setRoot("Professors");
    }
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para listar los profesores desde ArrayList getListaProfesores de GestioDades

    public void listarProfesores() {
        ObservableList<Professor> roleList = FXCollections.observableArrayList(gestio.getListaProfesores());

        LlenarComboProfes(cmbBoxProfesor, roleList);

    }
    //---------------------------------------------------------------------------------------------------------------------------
    //Para llenar el comboBox

    public static void LlenarComboProfes(ComboBox<Professor> llenarcombo, ObservableList<Professor> infocombo) {
        llenarcombo.setItems(infocombo);
    }
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para listar los profesores desde ArrayList getListaProfesores de GestioDades

    public void listarAlumnos() {
        ObservableList<Alumne> roleList = FXCollections.observableArrayList(gestio.getListaAlunos());

        LlenarComboAlumnos(cmbBoxAlumne, roleList);

    }
    //---------------------------------------------------------------------------------------------------------------------------
    //Para llenar el comboBox

    public static void LlenarComboAlumnos(ComboBox<Alumne> llenarcombo, ObservableList<Alumne> infocombo) {
        llenarcombo.setItems(infocombo);
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para afegirProfesorAlumno usando el metodo de insertarProfeorAlumno de gestio dades
    public void AfegirProfesor() throws SQLException {
        try {
            Professor nombreProfesor = cmbBoxProfesor.getValue();
            Alumne nombreAlumnos = cmbBoxAlumne.getValue();

            // Verificar si algun campo esta vacio
            if (nombreProfesor == null || nombreAlumnos == null) {
                // Mensaje de error si no se han completado todos los campos

                gestio.mostrarAlertWarning("ERROR: Debes seleccionar un profesor y un alumno.");
            } else {

                gestio.insertarProfesorAlumno(nombreProfesor.getNombre_apellidos(),
                        nombreAlumnos.getNombre_apellidos());

            }
        } catch (SQLException e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
        // Limpiar la selección de los ComboBox
        cmbBoxProfesor.getSelectionModel().clearSelection();
        cmbBoxAlumne.getSelectionModel().clearSelection();
        // Actualizar tabla automáticamente
        tablaProfesores2.getItems().clear();
        tablaProfesores2.setItems(gestio.llistaProfeAlumnos());

    }

//---------------------------------------------------------------------------------------------------------------------------
    public void eliminarProfesorAlumno() throws SQLException {
        try {
            // Obtener el profesor-alumno seleccionado en la tabla
            if (tablaProfesores2.getSelectionModel().getSelectedItem() != null) {
                gestio.eliminarProfesorAlumno(tablaProfesores2.getSelectionModel().getSelectedItem().getNombreProfesor(),
                        tablaProfesores2.getSelectionModel().getSelectedItem().getNombreAlumno());

            } else {
                // Mostrar mensaje de error
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar un profesor-alumno.");

            }
        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Actualizar tabla automáticamente
        tablaProfesores2.getItems().clear();
        tablaProfesores2.setItems(gestio.llistaProfeAlumnos());

    }
    //---------------------------------------------------------------------------------------------------------------------------

    public void modificarProfesoresAlumnos() throws SQLException {
        try {

            // Verificar si hay un profesor-alumno seleccionado
            if (tablaProfesores2.getSelectionModel().getSelectedItem() != null) {

                gestio.modificarProfesorAlumno(tablaProfesores2.getSelectionModel().getSelectedItem().getNombreProfesor(),
                        tablaProfesores2.getSelectionModel().getSelectedItem().getNombreAlumno(),
                        cmbBoxProfesor.getValue().getNombre_apellidos(),
                        cmbBoxAlumne.getValue().getNombre_apellidos());

            } else {
                // Mostrar mensaje de error 
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar un profesor-alumno.");

            }

        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Limpiar la selección de los ComboBox
        cmbBoxProfesor.getSelectionModel().clearSelection();
        cmbBoxAlumne.getSelectionModel().clearSelection();
        // Actualizar tabla automáticamente
        tablaProfesores2.getItems().clear();
        tablaProfesores2.setItems(gestio.llistaProfeAlumnos());
    }

}
