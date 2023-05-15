package modelo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class Connexio {

    private final String URL = "jdbc:mysql://localhost:3306/final_base_datos_m3";//nom bd
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String USER = "root";
    private final String PASSWD = "";

    public Connection connecta() {
        Connection connexio = null;
        try {
            //Carreguem el driver          
            Class.forName(DRIVER);
            connexio = DriverManager.getConnection(URL, USER, PASSWD);
        } catch (SQLException | ClassNotFoundException throwables) {
            System.out.println(throwables.getLocalizedMessage());
        }
        return connexio;
    }

    //Metodo salir de la app
    private void salir() throws IOException {
        System.exit(0);//Salir de la app
    }

    public void mensajeConfirmar() throws IOException {
        Alert Mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        Mensaje.setTitle("CONFIRMAR");
        Mensaje.setHeaderText("Salir del programa.");
        Mensaje.setContentText("¿Esta seguro que desea salir?");
        ButtonType botonSi = new ButtonType("Si");
        ButtonType botonNo = new ButtonType("No");
        Mensaje.getButtonTypes().setAll(botonSi, botonNo);
        Optional<ButtonType> opcion = Mensaje.showAndWait();
        if (opcion.get() == botonSi) {
            this.salir();
        } else if (opcion.get() == botonNo) {
            Mensaje.close();
        }
    }

}
