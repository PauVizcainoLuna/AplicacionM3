package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

public class GestioDades {

    public GestioDades() {

    }

    //---------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------
    //Metodo WARING
    public void mostrarAlertWarning(String error) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("ERROR");
        alert.setContentText(error);
        alert.showAndWait();
    }
    //---------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------
    //Metodo WARING

    public void mostrarAlertCorrecto(String correcto) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setTitle("CORRECTO");
        alert.setContentText(correcto);
        alert.showAndWait();
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para generar id automatica de alumnos
    public int generarIdAlumnos() {
        String SQL = "SELECT id_alumno FROM alumnos ORDER BY id_alumno ASC", toArray = "";
        Connection connection = new Connexio().connecta();
        try {
            Statement ordreAlumnes = connection.createStatement();
            ResultSet resultSet = ordreAlumnes.executeQuery(SQL);

            while (resultSet.next()) {
                toArray += resultSet.getString(1) + ";";
            }
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
        if (toArray == "") {
            this.mostrarAlertWarning("ERROR DE INDICES");
            return 0;
        }
        String[] array = toArray.split(";");
        int id = Integer.parseInt(array[array.length - 1]) + 1;
        return id;
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para generar id automatica de clases
    public int generarIdClases() {
        String SQL = "SELECT id_clase FROM clases ORDER BY id_clase ASC", toArray = "";
        Connection connection = new Connexio().connecta();
        try {
            Statement ordreClases = connection.createStatement();
            ResultSet resultSet = ordreClases.executeQuery(SQL);

            while (resultSet.next()) {
                toArray += resultSet.getString(1) + ";";
            }
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
        if (toArray == "") {
            this.mostrarAlertWarning("ERROR DE INDICES");
            return 0;
        }
        String[] array = toArray.split(";");
        int id = Integer.parseInt(array[array.length - 1]) + 1;
        return id;
    }
    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para generar id automatica de grados

    public int generarIdGrados() {
        String SQL = "SELECT id_grado FROM grados ORDER BY id_grado ASC", toArray = "";
        Connection connection = new Connexio().connecta();
        try {
            Statement ordreClases = connection.createStatement();
            ResultSet resultSet = ordreClases.executeQuery(SQL);

            while (resultSet.next()) {
                toArray += resultSet.getString(1) + ";";
            }
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
        if (toArray == "") {
            this.mostrarAlertWarning("ERROR DE INDICES");
            return 0;
        }
        String[] array = toArray.split(";");
        int id = Integer.parseInt(array[array.length - 1]) + 1;
        return id;
    }
//---------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------
    //Metodo para generar id automatica de profesores

    public int generarIdProfesores() {
        String SQL = "SELECT id_profesor FROM profesores ORDER BY id_profesor ASC", toArray = "";
        Connection connection = new Connexio().connecta();
        try {
            Statement ordreClases = connection.createStatement();
            ResultSet resultSet = ordreClases.executeQuery(SQL);

            while (resultSet.next()) {
                toArray += resultSet.getString(1) + ";";
            }
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
        if (toArray == "") {
            this.mostrarAlertWarning("ERROR DE INDICES");
            return 0;
        }
        String[] array = toArray.split(";");
        int id = Integer.parseInt(array[array.length - 1]) + 1;
        return id;
    }

//---------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------
    //OservableList de alumnos
    public ObservableList llistaAlumnes() throws SQLException {

        Connection connection = new Connexio().connecta();
        ObservableList<Alumne> llistaAlumnes = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM alumnos";//Consulta SQL para select

        //Estructura general de la tabla
        try {
            Statement ordreAlumnes = connection.createStatement();
            ResultSet resultSet = ordreAlumnes.executeQuery(SQL);
            while (resultSet.next()) {
                llistaAlumnes.add(
                        new Alumne(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4),
                                resultSet.getString(5),
                                resultSet.getString(6),
                                resultSet.getString(7),
                                resultSet.getInt(8),
                                resultSet.getString("color")//Hacemos referencia a color ya que es clave ajena
                        ));
            }
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
        return llistaAlumnes;

    }
//---------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------
    //Metodo añadir alumno 

    public void AfegirAlumne(Alumne alumne, Grados grados) throws SQLException {
        Connection connection = new Connexio().connecta();
        String SQL = "INSERT INTO alumnos VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement ordre = connection.prepareStatement(SQL);
        try {
            ordre.setInt(1, this.generarIdAlumnos());//Ponemos el generador de indices
            ordre.setString(2, alumne.getNombre());
            ordre.setString(3, alumne.getApellidos());
            ordre.setString(4, alumne.getNombre_apellidos());
            ordre.setString(5, alumne.getCorreo_elec());
            ordre.setString(6, alumne.getData_naix());
            ordre.setString(7, alumne.getDireccion());
            ordre.setInt(8, alumne.getCod_postal());
            ordre.setString(9, grados.getColor());//Referencia de grados clave ajena

            ordre.executeUpdate();
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }

//---------------------------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------------------------
    //Metodo para borrar alumnos
    public void eliminarAlumnos(int ID) throws SQLException {

        Connection connection = new Connexio().connecta();
        String SQL = "DELETE FROM alumnos WHERE alumnos.id_alumno = " + ID;
        PreparedStatement ordreEliminar = connection.prepareStatement(SQL);
        try {
            connection.createStatement().execute(SQL);
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para modificarAlumnos
    public void modificarAlumnos(Integer Id_alumno, Grados grados, String nom, String cognom, String nombre_apellidos, String data_naix, String correo_elec, String direccion, Integer codPostal) throws SQLException {
        String sql = "UPDATE ALUMNOS SET NOMBRE=?, "
                + "APELLIDOS=?, NOMBRE_APELLIDO=?, "
                + "CORREO_ELEC=?, DATA_NAIX=?, "
                + "DIRECCION=?, COD_POSTAL=?, "
                + "COLOR=? "
                + "WHERE ID_ALUMNO=?";

        try {
            Connection connection = new Connexio().connecta();
            PreparedStatement ordreModificar = connection.prepareStatement(sql);
            ordreModificar.setString(1, nom);
            ordreModificar.setString(2, cognom);
            ordreModificar.setString(3, nombre_apellidos);
            ordreModificar.setString(4, correo_elec);
            ordreModificar.setString(5, data_naix);
            ordreModificar.setString(6, direccion);
            ordreModificar.setInt(7, codPostal);
            ordreModificar.setString(8, grados.getColor()); //Usamos el getColor para coger el color de grados
            ordreModificar.setInt(9, Id_alumno);

            ordreModificar.executeUpdate();
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //OservableList de clases
    public ObservableList llistaClase() throws SQLException {

        Connection connection = new Connexio().connecta();
        ObservableList<Clases> llistaClases = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM clases";//Consulta SQL para select
        try {
            Professor profesor = new Professor();
            Statement ordreClases = connection.createStatement();
            ResultSet resultSet = ordreClases.executeQuery(SQL);
            while (resultSet.next()) {
                llistaClases.add(
                        new Clases(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString("nombre_apellidos_profesor")//Hacemos referencia a nombre_apellidos_profesor que es clave ajena
                        ));
            }
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
        return llistaClases;
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo añadir clase mediante estructura
    public void AfegirClase(Clases clase, Professor profesor) throws SQLException {

        Connection connection = new Connexio().connecta();
        String SQL = "INSERT INTO clases VALUES (?,?,?)";//consulta SQL para insert
        PreparedStatement ordre = connection.prepareStatement(SQL);
        try {

            ordre.setInt(1, this.generarIdClases());//Añadimos generador de indices de clases
            ordre.setString(2, clase.getHorario());
            ordre.setString(3, profesor.getNombre_apellidos());//Referencia a clave ajena nombre_apellidos
            ordre.execute();
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para borrar clases
    public void eliminarClases(int ID) throws SQLException {

        Connection connection = new Connexio().connecta();
        String SQL = "DELETE FROM clases WHERE clases.id_clase = " + ID;
        PreparedStatement ordreEliminar = connection.prepareStatement(SQL);
        try {
            connection.createStatement().execute(SQL);
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }
    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para modificar clases

    public void modificarClase(Integer id_clase, String horario, Professor professor) throws SQLException {
        String sql = "UPDATE CLASES SET HORARIO = ?, NOMBRE_APELLIDOS_PROFESOR = ? WHERE ID_CLASE = ?";

        try {
            Connection connection = new Connexio().connecta();
            PreparedStatement ordreModificar = connection.prepareStatement(sql);
            ordreModificar.setString(1, horario);
            ordreModificar.setString(2, professor.toString());//Referencia a metodo to string de profesor
            ordreModificar.setInt(3, id_clase);
            ordreModificar.executeUpdate();
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //OservableList de profesores
    public ObservableList llistaProfe() throws SQLException {

        Connection connection = new Connexio().connecta();
        ObservableList<Professor> llistaProfe = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM profesores";

        //Estructura general de la tabla
        try {
            Statement ordreProfesores = connection.createStatement();
            ResultSet resultSet = ordreProfesores.executeQuery(SQL);
            while (resultSet.next()) {
                llistaProfe.add(
                        new Professor(
                                resultSet.getInt(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)
                        ));
            }
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
        return llistaProfe;
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo añadir profesor mediante estructura
    public void AfegirProfesor(Professor profesor) throws SQLException { //Ponemos  tambien el objeto de clases porque tenemos una foreign key de clases

        Connection connection = new Connexio().connecta();
        String SQL = "INSERT INTO profesores VALUES (?,?,?,?)";
        PreparedStatement ordre = connection.prepareStatement(SQL);
        try {
            ordre.setInt(1, this.generarIdProfesores());//Añadimos generador de indice automatico de profesores
            ordre.setString(2, profesor.getNombre());
            ordre.setString(3, profesor.getApellidos());
            ordre.setString(4, profesor.getNombre_apellidos());
            ordre.execute();
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo borrar profesor
    public void eliminarProfesor(int ID) throws SQLException {
        Connection connection = new Connexio().connecta();
        String SQL = "DELETE FROM profesores WHERE profesores.id_profesor = " + ID;
        PreparedStatement ordreEliminar = connection.prepareStatement(SQL);
        try {
            connection.createStatement().execute(SQL);
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para modificar profesor
    public void modificarProfesor(Integer id_profesor, String Nombre, String Apellidos, String nombre_apellido) throws SQLException { //Ponemos  tambien el objeto de clases porque tenemos una foreign key de clases
        String SQL = "UPDATE PROFESORES SET NOMBRE=?,  APELLIDOS=?, NOMBRE_APELLIDO=? WHERE ID_PROFESOR=?";
        Connection conection = new Connexio().connecta();
        PreparedStatement ordreModificar = conection.prepareStatement(SQL);
        try {
            ordreModificar.setString(1, Nombre);
            ordreModificar.setString(2, Apellidos);
            ordreModificar.setString(3, nombre_apellido);
            ordreModificar.setInt(4, id_profesor);
            ordreModificar.executeUpdate();

        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //OservableList de grados
    public ObservableList<Grados> llistaGrados() throws SQLException {
        Connection connection = new Connexio().connecta();
        ObservableList<Grados> llistaGrados = FXCollections.observableArrayList();
        String SQL = "SELECT * FROM grados ORDER BY id_grado";
        try {
            Statement ordreClases = connection.createStatement();
            ResultSet resultSet = ordreClases.executeQuery(SQL);
            while (resultSet.next()) {
                llistaGrados.add(
                        new Grados(
                                resultSet.getInt(1),
                                resultSet.getString(2)
                        ));
            }
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
        return llistaGrados;
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo añadir grados mediante estructura
    public void AfegirGrados(Grados grados) throws SQLException { //Ponemos  tambien el objeto de clases porque tenemos una foreign key de clases

        Connection connection = new Connexio().connecta();
        String SQL = "INSERT INTO grados VALUES (?,?)";//Consulta SQL para insert
        PreparedStatement ordre = connection.prepareStatement(SQL);
        try {
            ordre.setInt(1, this.generarIdGrados());//Añadimos indice automatico de grados
            ordre.setString(2, grados.getColor());
            ordre.execute();
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }
    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo borrar grados

    public void eliminarGrados(int ID) throws SQLException {
        Connection connection = new Connexio().connecta();
        String SQL = "DELETE FROM grados WHERE grados.id_grado = " + ID;//Consulta SQL para delete
        PreparedStatement ordreEliminar = connection.prepareStatement(SQL);
        try {
            connection.createStatement().execute(SQL);
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para modificar grados
    public void modificarGrados(Integer id_grado, String color) throws SQLException {
        String SQL = "UPDATE GRADOS SET COLOR=? WHERE ID_GRADO=?";
        Connection connection = new Connexio().connecta();
        PreparedStatement ordreModificar = connection.prepareStatement(SQL);
        try {
            ordreModificar.setString(1, color);
            ordreModificar.setInt(2, id_grado);
            ordreModificar.executeUpdate();
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo ArrayList para obtener datos de la tabla de grados que se usará para el comboBox 
    public ArrayList getListaGrados() {
        ArrayList mListaGrados = new ArrayList();
        Connection conection = new Connexio().connecta();

        try {
            Statement consulta = conection.createStatement();
            ResultSet resultado = consulta.executeQuery("SELECT * FROM grados ");
            while (resultado.next()) {
                mListaGrados.add(
                        new Grados(
                                resultado.getInt("id_grado"),//Cogemos los datos directamente
                                resultado.getString("color")//Cogemos los datos directamente
                        ));
            }
        } catch (SQLException e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
        return mListaGrados;
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo ArrayList para obtener datos de la tabla de profeosores que se usará para el comboBox 
    public ArrayList getListaProfesores() {
        ArrayList mListaProfesores = new ArrayList();
        Connection conection = new Connexio().connecta();

        try {
            Statement consulta = conection.createStatement();
            ResultSet resultado = consulta.executeQuery("SELECT * FROM profesores ");
            while (resultado.next()) {
                mListaProfesores.add(
                        new Professor(
                                resultado.getInt("id_profesor"),//Cogemos los datos directamente
                                resultado.getString("nombre"),//Cogemos los datos directamente
                                resultado.getString("apellidos"),//Cogemos los datos directamente
                                resultado.getString("nombre_apellido")));//Cogemos los datos directamente

            }
        } catch (SQLException e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
        return mListaProfesores;
    }
    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo ArrayList para obtener datos de la tabla de profeosores que se usará para el comboBox 

    public ArrayList getListaAlunos() {
        ArrayList mListaAlumnos = new ArrayList();
        Connection conection = new Connexio().connecta();

        try {
            Statement consulta = conection.createStatement();
            ResultSet resultado = consulta.executeQuery("SELECT * FROM alumnos ");
            while (resultado.next()) {
                mListaAlumnos.add(
                        new Alumne(resultado.getInt("id_alumno"),//Cogemos los datos directamente
                                resultado.getString("nombre"),//Cogemos los datos directamente
                                resultado.getString("apellidos"),//Cogemos los datos directamente
                                resultado.getString("nombre_apellido"),//Cogemos los datos directamente
                                resultado.getString("data_naix"),//Cogemos los datos directamente
                                resultado.getString("correo_elec"),//Cogemos los datos directamente
                                resultado.getString("direccion"),//Cogemos los datos directamente
                                resultado.getInt("cod_postal"),//Cogemos los datos directamente
                                resultado.getString("color")));//Cogemos los datos directamente

            }
        } catch (SQLException e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
        return mListaAlumnos;
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Observable list de ProfesorAlumne donde mostramos un JOIN de los alumnos que corresponden a cada profesor
    public ObservableList<ProfesorAlumne> llistaProfeAlumnos() throws SQLException {
        Connection connection = new Connexio().connecta();
        ObservableList<ProfesorAlumne> llistaProfeAlumnos = FXCollections.observableArrayList();

        String SQL = "SELECT p.nombre_apellido AS nombre_profesor, a.nombre_apellido AS nombre_alumno "
                + "FROM tienen t "
                + "JOIN profesores p ON p.id_profesor = t.id_profesor "
                + "JOIN alumnos a ON a.id_alumno = t.id_alumno "
                + "ORDER BY p.nombre_apellido, a.nombre_apellido";

        try {
            Statement ordreProfeAlumnos = connection.createStatement();
            ResultSet resultSet = ordreProfeAlumnos.executeQuery(SQL);

            while (resultSet.next()) {
                llistaProfeAlumnos.add(new ProfesorAlumne(
                        resultSet.getString("nombre_profesor"),
                        resultSet.getString("nombre_alumno")
                ));
            }
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }

        return llistaProfeAlumnos;
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
// Metodo para insertarProfesorAlumno a la tabla tienen, mediante sus id, mostrando el nombre_apellido
    public void insertarProfesorAlumno(String nombreProfesor, String nombreAlumno) throws SQLException {

        // Establece la conexión a la base de datos
        Connection connection = new Connexio().connecta();

        // Crea la consulta SQL de inserción con parámetros
        String SQL = "INSERT INTO tienen (id_profesor, id_alumno) "
                + "SELECT p.id_profesor, a.id_alumno "
                + "FROM profesores p, alumnos a "
                + "WHERE p.nombre_apellido = ? AND a.nombre_apellido = ?";

        try {
            // Crea una declaración preparada para ejecutar la consulta SQL
            PreparedStatement statement = connection.prepareStatement(SQL);

            // Establece los valores de los parámetros en la consulta preparada
            statement.setString(1, nombreProfesor);
            statement.setString(2, nombreAlumno);
            statement.execute();

        } catch (Exception e) {
            // Muestra una alerta en caso de error
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    //Metodo para elimianrProfesorAlumno
    public void eliminarProfesorAlumno(String nombreProfesor, String nombreAlumno) throws SQLException {
        try {
            // Establece la conexión a la base de datos
            Connection connection = new Connexio().connecta();

            // Crea la consulta SQL de eliminación con parámetros
            String SQL = "DELETE FROM tienen WHERE id_profesor = "
                    + "(SELECT id_profesor FROM profesores WHERE nombre_apellido = ?)"
                    + " AND id_alumno = (SELECT id_alumno FROM alumnos WHERE nombre_apellido = ?)";

            // Crea una declaración preparada para ejecutar la consulta SQL
            PreparedStatement statement = connection.prepareStatement(SQL);

            // Establece los valores de los parámetros en la consulta preparada
            statement.setString(1, nombreProfesor);
            statement.setString(2, nombreAlumno);

            // Ejecuta la consulta
            statement.executeUpdate();

            // Cierra la conexión y la declaración
            statement.close();
            connection.close();
        } catch (Exception e) {
            // Muestra una alerta en caso de error
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------------------
    public void modificarProfesorAlumno(String nombreProfesorAnterior, String nombreAlumnoAnterior, String nuevoNombreProfesor, String nuevoNombreAlumno) throws SQLException {

        String SQL = "UPDATE tienen SET id_profesor = "
                + "(SELECT id_profesor FROM profesores WHERE nombre_apellido = ?), "
                + "id_alumno = (SELECT id_alumno FROM alumnos WHERE nombre_apellido = ?) "
                + "WHERE id_profesor = (SELECT id_profesor FROM profesores WHERE nombre_apellido = ?) "
                + "AND id_alumno = (SELECT id_alumno FROM alumnos WHERE nombre_apellido = ?)";

        Connection connection = new Connexio().connecta();
        PreparedStatement ordreModificar = connection.prepareStatement(SQL);
        try {
            ordreModificar.setString(1, nuevoNombreProfesor);
            ordreModificar.setString(2, nuevoNombreAlumno);
            ordreModificar.setString(3, nombreProfesorAnterior);
            ordreModificar.setString(4, nombreAlumnoAnterior);
            ordreModificar.executeUpdate();
        } catch (Exception e) {
            this.mostrarAlertWarning("ERROR: " + e.getMessage());
        }
    }
}
