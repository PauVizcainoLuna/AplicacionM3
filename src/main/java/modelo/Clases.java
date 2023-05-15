package modelo;

public class Clases {

    public int ID;
    private String horario;
    private String nombre_apellidos_profesor;

    public Clases() {

    }

    public Clases(int ID, String horario, String nombre_apellidos_profesor) {
        this.ID = ID;
        this.horario = horario;
        this.nombre_apellidos_profesor = nombre_apellidos_profesor;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getNombre_apellidos_profesor() {
        return nombre_apellidos_profesor;
    }

    public void setNombre_apellidos_profesor(String nombre_apellidos_profesor) {
        this.nombre_apellidos_profesor = nombre_apellidos_profesor;
    }

}
