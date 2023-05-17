package modelo;

public class Alumne {

    private int id_alumno;
    private String nombre;
    private String apellidos;
    private String nombre_apellidos;
    private String data_naix;
    private String correo_elec;
    private String direccion;
    private int cod_postal;
    private String colorGrado;

    public Alumne() {

    }

    public Alumne(String nombre_apellidos) {
        this.nombre_apellidos = nombre_apellidos;
    }

    public Alumne(String nombre_apellidos, String colorGrado) {
        this.nombre_apellidos = nombre_apellidos;
        this.colorGrado = colorGrado;
    }
    

    public Alumne(int id_alumno, String nombre, String apellidos, String nombre_apellidos, String data_naix, String correo_elec, String direccion, int cod_postal, String colorGrado) {
        this.id_alumno = id_alumno;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.nombre_apellidos = nombre_apellidos;
        this.data_naix = data_naix;
        this.correo_elec = correo_elec;
        this.direccion = direccion;
        this.cod_postal = cod_postal;
        this.colorGrado = colorGrado;
    }
    
    public int getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(int id_alumno) {
        this.id_alumno = id_alumno;
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

    public String getData_naix() {
        return data_naix;
    }

    public void setData_naix(String data_naix) {
        this.data_naix = data_naix;
    }

    public String getCorreo_elec() {
        return correo_elec;
    }

    public void setCorreo_elec(String correo_elec) {
        this.correo_elec = correo_elec;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getCod_postal() {
        return cod_postal;
    }

    public void setCod_postal(int cod_postal) {
        this.cod_postal = cod_postal;
    }

    public String getColorGrado() {
        return colorGrado;
    }

    public void setColorGrado(String colorGrado) {
        this.colorGrado = colorGrado;
    }

    @Override
    public String toString() {
        return nombre_apellidos;
    }

}
