package com.company;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {

    public static String menu = "MENÚ (sin JCF): \n" +
            "\t 1. Cargar archivo de pacientes. \n" +
            "\t 2. Obtener paciente. \n" +
            "\t 3. Salir del programa.";

    public static void main(String[] args) {
        boolean wantsToContinue = true;
        Scanner input = new Scanner(System.in);

        VectorHeap<Patient> hospitalQueue = new VectorHeap<>();

        do {
            System.out.println(menu);
            int option = input.nextInt();

            switch (option){

                case 1: {
                    System.out.print("Ingrese el archivo de texto que desea procesar: ");
                    String userFile = input.nextLine();
                    userFile = input.nextLine();

                    //Lectura de archivo de texto.
                    try {
                        Stream<String> lines = Files.lines(
                                Paths.get(userFile),
                                StandardCharsets.UTF_8
                        );
                        lines.forEach(line -> {
                            String[] parts = line.toUpperCase().replace(", ", ",").split(",");

                            if (parts.length == 3){
                                String name = parts[0];
                                String syntom = parts[1];
                                String code = parts[2];

                                Patient newPatient = new Patient(name, syntom, code);
                                hospitalQueue.add(newPatient);
                            }

                        });

                        System.out.println("Se han agregado los pacientes al Priority Queue con éxito.");
                    } catch (
                            IOException exception) {
                        System.out.println("Error!");
                    }
                    break;
                }

                case 2: {
                    if (!hospitalQueue.isEmpty()){
                        System.out.println(hospitalQueue.remove());
                    } else {
                        System.out.println("Ya no hay más pacientes en la lista de espera!");
                    }

                    break;
                }

                case 3: {
                    wantsToContinue = false;
                    break;
                }
            }

        }while (wantsToContinue);
    }
}
