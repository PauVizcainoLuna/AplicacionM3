package com.mycompany.proyectom3_pauvizcaino_javierriscos;

import java.io.IOException;
import javafx.fxml.FXML;
import modelo.Connexio;

public class EleccionController {

    Connexio connexio = new Connexio();

    @FXML
    private void seguentAlumne() throws IOException {
        App.setRoot("Alumne");
    }

    @FXML
    private void seguentProfessor() throws IOException {
        App.setRoot("Professors");
    }

    @FXML
    private void seguentClasse() throws IOException {
        App.setRoot("Clases");
    }
    @FXML
    private void seguentGrado()throws IOException{
       App.setRoot("Grados");
    }

    //Para salir de la App mediante el metodo, ya que hemos anulado el evento de cerrar ventana
    @FXML
    private void salirApp() throws IOException {
        connexio.mensajeConfirmar();
    }
    
    
}
