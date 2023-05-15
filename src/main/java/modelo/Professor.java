package modelo;

public class Professor {

    private int ID;
    private String nombre;
    private String apellidos;
    private String nombre_apellidos;

    public Professor() {

    }
    

    public Professor(int ID) {
        this.ID = ID;
    }

    public Professor(int ID, String nombre, String apellidos) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    public Professor(int ID, String nombre, String apellidos, String nombre_apellidos) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombre_apellidos = nombre_apellidos;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre_apellidos() {
        return nombre_apellidos;
    }

    public void setNombre_apellidos(String nombre_apellidos) {
        this.nombre_apellidos = nombre_apellidos;
    }

    @Override
    public String toString() {
        return nombre_apellidos;
    }
}
