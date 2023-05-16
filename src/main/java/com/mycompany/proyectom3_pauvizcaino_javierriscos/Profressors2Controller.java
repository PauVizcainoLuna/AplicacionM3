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
            // Obtener el nombre del profesor seleccionado en el ComboBox cmbBoxProfesor
            String nombreProfesor = cmbBoxProfesor.getValue().getNombre_apellidos();

            // Obtener el nombre del alumno seleccionado en el ComboBox cmbBoxAlumne
            String nombreAlumno = cmbBoxAlumne.getValue().getNombre_apellidos();

            // Verificar si los campos están vacíos
            if (nombreProfesor == null || nombreAlumno == null) {
                // Mostrar mensaje de error
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar un profesor y un alumno.");
                return; // Salir del método sin crear el profesor
            }

            // Crear un objeto ProfesorAlumne con los datos proporcionados
            ProfesorAlumne profesorAlumno = new ProfesorAlumne(nombreProfesor, nombreAlumno);

            // Llamar al método de inserción de ProfesorAlumne
            gestio.insertarProfesorAlumno(nombreProfesor, nombreAlumno);

            // Limpiar la selección de los ComboBox
            cmbBoxProfesor.getSelectionModel().clearSelection();
            cmbBoxAlumne.getSelectionModel().clearSelection();
        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Actualizar tabla automáticamente
        tablaProfesores2.getItems().clear();
        tablaProfesores2.setItems(gestio.llistaProfeAlumnos());
    }
    //---------------------------------------------------------------------------------------------------------------------------

    public void eliminarProfesorAlumno() throws SQLException {
        try {
            // Obtener el profesor-alumno seleccionado en la tabla
            ProfesorAlumne profesorAlumno = tablaProfesores2.getSelectionModel().getSelectedItem();

            // Verificar si se ha seleccionado un profesor-alumno
            if (profesorAlumno == null) {
                // Mostrar mensaje de error
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar un profesor-alumno.");
                return; // Salir del método sin eliminar el profesor-alumno
            }

            // Llamar al método de eliminación de ProfesorAlumno y obtener los nombres de Profesor y alumno
            gestio.eliminarProfesorAlumno(profesorAlumno.getNombreProfesor(), profesorAlumno.getNombreAlumno());

            // Limpiar la selección de la tabla
            tablaProfesores2.getSelectionModel().clearSelection();
        } catch (SQLException e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Actualizar tabla automáticamente
        tablaProfesores2.getItems().clear();
        tablaProfesores2.setItems(gestio.llistaProfeAlumnos());
    }
    //---------------------------------------------------------------------------------------------------------------------------

    public void modificarProfesoresAlumnos() throws SQLException {
        try {
            ProfesorAlumne profesorSeleccionat = tablaProfesores2.getSelectionModel().getSelectedItem();

            // Verificar si hay un profesor-alumno seleccionado
            if (profesorSeleccionat != null) {

                //Asignamos a traves del metodo modificarProfesorAlumno de gestioDades
                gestio.modificarProfesorAlumno(profesorSeleccionat.getNombreProfesor(),
                        profesorSeleccionat.getNombreAlumno(),
                        cmbBoxProfesor.getValue().getNombre_apellidos(),
                        cmbBoxAlumne.getValue().getNombre_apellidos());
            } else {
                // Mostrar mensaje de error si no hay profesor-alumno seleccionado
                gestio.mostrarAlertWarning("ERROR: Debes seleccionar un profesor-alumno.");
                return; // Salir del método sin modificar el profesor-alumno
            }

        } catch (Exception e) {
            gestio.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        // Actualizar tabla automáticamente
        tablaProfesores2.getItems().clear();
        tablaProfesores2.setItems(gestio.llistaProfeAlumnos());
    }

}
