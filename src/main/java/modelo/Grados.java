/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author PauVizcaino
 */
public class Grados {
    private int id_grado;
    private String color;

    public Grados() {
    }

    public Grados(String color) {
        this.color = color;
    }

    
    public Grados(int id_grado) {
        this.id_grado = id_grado;
    }

    public Grados(int id_grado, String color) {
        this.id_grado = id_grado;
        this.color = color;
    }

    public int getId_grado() {
        return id_grado;
    }

    public void setId_grado(int id_grado) {
        this.id_grado = id_grado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    
    @Override
    public String toString(){
        return getColor();
    }
}
