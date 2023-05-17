package com.mycompany.proyectom3_pauvizcaino_javierriscos;

import java.io.IOException;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Alumne;
import modelo.GestioDades;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author PauVizcaino
 */
public class Alumnes2Controller {

    GestioDades gestio = new GestioDades();
    @FXML
    private TableColumn<Alumne, String> nombreAlumno;
    @FXML
    private TableColumn<Alumne, String> colorGrado;
    @FXML
    private TableView<Alumne> tablaAlumnosGrados;

    public void initialize() throws SQLException {
        nombreAlumno.setCellValueFactory(new PropertyValueFactory<>("nombre_apellidos"));
        colorGrado.setCellValueFactory(new PropertyValueFactory<>("colorGrado"));
        tablaAlumnosGrados.setItems(gestio.llistaAlumnesGrados());

    }

    //Metodo para cambiar a la pantalla Alumne2
    public void cambiarPantalla() throws IOException {
        App.setRoot("Alumne");
    }
}
