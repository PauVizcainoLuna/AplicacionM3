module com.mycompany.proyectom3_pauvizcaino_javierriscos {
 requires javafx.controls;
    requires javafx.fxml;
    requires  java.sql; 
    requires javafx.swing;
    requires java.desktop; 
    requires javafx.base;
    requires java.base;
    opens com.mycompany.proyectom3_pauvizcaino_javierriscos to javafx.fxml;
    exports com.mycompany.proyectom3_pauvizcaino_javierriscos;
    exports modelo;
}
