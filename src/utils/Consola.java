package utils;

import entidades.Cliente;

import java.util.Objects;
import java.util.Scanner;

public class Consola {

    private Scanner sc = new Scanner(System.in);

    public void iniciarConsola(){
       String input;
       input="-1";
       Util.initClient();

        do{
            menu();
            input = sc.nextLine();
            switch (input){
                case "0":
                    break;
                case "1":
                    altaCliente();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    mostrarClientes();
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                default:
                    System.out.println("Ingrese una opcion correcta (0-8)");
            }
            continuar();
         }
        while (!Objects.equals(input, "0"));
    }


   private  void  altaCliente(){
       System.out.println("Ingresar id de cliente");
       int id = tomarNumero();
       System.out.println("Ingresar descripcion");
       String descripcion  = sc.nextLine();
       Util.agregarCliente(new Cliente(id,descripcion));
   }

    private  void  mostrarClientes(){
        Util.mostrarClientes();
    }


   private int tomarNumero(){
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


    private void menu(){
        System.out.println("0 - Salir del Programa");
        System.out.println("1 - Altar Cliente");
        System.out.println("2 - Modificar Cliente");
        System.out.println("3 - Borrar Cliente");
        System.out.println("4 - Mostrar Clientes");
        System.out.println("5 - Altar Factura");
        System.out.println("6 - Modificar Factura");
        System.out.println("7 - Borrar Factura");
        System.out.println("8 - Mostrar Facturas");

    }
    private  void continuar(){
        System.out.println("presionar enter");
        sc.nextLine();
    }
}
