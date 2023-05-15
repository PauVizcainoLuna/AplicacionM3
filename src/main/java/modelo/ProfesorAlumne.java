/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author PauVizcaino
 */
public class ProfesorAlumne {

    private String nombreProfesor;
    private String nombreAlumno;

    public ProfesorAlumne(String nombreProfesor, String nombreAlumno) {
        this.nombreProfesor = nombreProfesor;

        this.nombreAlumno = nombreAlumno;
    }

    // Getters y setters
    public String getNombreProfesor() {
        return nombreProfesor;
    }

    public void setNombreProfesor(String nombreProfesor) {
        this.nombreProfesor = nombreProfesor;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

}
