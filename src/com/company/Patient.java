package com.company;

public class Patient implements Comparable<Patient> {
    String name;
    String syntom;
    String code;

    public Patient(String nombre, String sintoma, String codigo) {
        this.name = nombre;
        this.syntom = sintoma;
        this.code = codigo;
    }

    @Override
    public int compareTo(Patient o) {
        //Mayor prioridad
        if (this.code.compareTo(o.code) < 0){
            return -1;
        }

        //Menor prioridad
        if (this.code.compareTo(o.code) > 0){
            return 1;
        }

        //Igual prioridad.
        return 0;
    }

    @Override
    public String toString() {
        return "Nombre: " + name + "\n" +
                "Síntomas: " + syntom + "\n" +
                "Código: " + code;
    }
}
