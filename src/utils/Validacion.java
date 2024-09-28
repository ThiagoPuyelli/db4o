package utils;

import java.util.Scanner;

public class Validacion {

    static  int tomarNumero(Scanner sc){
        int numero = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Por favor, ingrese un número entero: ");
            try {
                numero = Integer.parseInt(sc.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no válida. Debe ingresar un número entero.");
            }
        }
        return numero;
    }

    static  double tomarNumeroDouble(Scanner sc) {
        double numero = 0.0;
        boolean valido = false;

        while (!valido) {
            System.out.print("Por favor, ingrese un número decimal: ");
            try {
                numero = Double.parseDouble(sc.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("Error: Entrada no válida. Debe ingresar un número decimal.");
            }
        }
        return numero;
    }

}
