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
import modelo.Professor;

/**
 *
 * @author PauVizcaino
 */
public class Profressors2Controller {

    //Iniciamos la conexion con la base de datos
    GestioDades gestio = new GestioDades();

    @FXML
    private TableView<Professor> tablaProfesores2;
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

}
