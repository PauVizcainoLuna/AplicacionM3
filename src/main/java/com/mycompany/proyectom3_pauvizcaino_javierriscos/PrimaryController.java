package com.mycompany.proyectom3_pauvizcaino_javierriscos;

import modelo.Connexio;
import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    Connexio connexio = new Connexio();

    //Metodo para entrar dentro de la app mediante metodo en Connexio
    @FXML
    private void conexionInicial() throws IOException {
        connexio.connecta();
        App.setRoot("Eleccion");
    }
    
    //Metodo para confirmar el salir de la App mediante metodo en Connexio
    @FXML
    private void mensajeConfirmar () throws IOException{
      connexio.mensajeConfirmar();
    }
    
}
