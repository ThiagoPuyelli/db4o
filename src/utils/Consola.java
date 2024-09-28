package utils;

import entidades.Cliente;
import entidades.Factura;

import java.util.Objects;
import java.util.Scanner;

import static utils.Validacion.tomarNumero;
import static utils.Validacion.tomarNumeroDouble;

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
                    modificarCliente();
                    break;
                case "3":
                    borrarCliente();
                    break;
                case "4":
                    mostrarClientes();
                    break;
                case "5":
                    altaFactura();
                    break;
                case "6":
                    modificarFactura();
                    break;
                case "7":
                    borrarFactura();
                    break;
                case "8":
                    mostrarFacturas();
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
       int id = tomarNumero(sc);
       System.out.println("Ingresar descripcion");
       String descripcion  = sc.nextLine();
       Util.agregarCliente(new Cliente(id,descripcion));
   }

    private  void  borrarCliente(){
        System.out.println("Ingresar id de cliente a borrar");
        int id = tomarNumero(sc);
        Util.borrarCliente(new Cliente(id,null));
    }


    private  void  modificarCliente(){
        System.out.println("Ingresar una id para modificar");
        int id = tomarNumero(sc);
        Util.modificarCliente(new Cliente(id,null), sc);
    }


    private  void  mostrarClientes(){
        Util.mostrarClientes();
    }




    private  void  altaFactura(){
        int nroF = tomarNumero(sc);
        int idC  = tomarNumero(sc);
        double importe = tomarNumeroDouble(sc);
        Factura f= new Factura(nroF,idC,importe);
        Util.agregarFactura(f);
    }


    private  void  mostrarFacturas(){
        Util.mostrarFacturas();
    }

    private  void  modificarFactura(){
        System.out.println("Ingresar un Nro de factura para modificar");
        int nro = tomarNumero(sc);
        Util.modificarFactura(new Factura(nro,0,null), sc);
    }


    private  void  borrarFactura(){
        System.out.println("Ingresar nro de factura a borrar");
        int nro = tomarNumero(sc);
        Util.borrarFactura(new Factura(nro,0,null));
    }





    private void menu(){
        System.out.println("0 - Salir del Programa");
        System.out.println("1 - Alta Cliente");
        System.out.println("2 - Modificar Cliente");
        System.out.println("3 - Borrar Cliente");
        System.out.println("4 - Mostrar Clientes");
        System.out.println("5 - Alta Factura");
        System.out.println("6 - Modificar Factura");
        System.out.println("7 - Borrar Factura");
        System.out.println("8 - Mostrar Facturas");

    }
    private  void continuar(){
        System.out.println("presionar enter");
        sc.nextLine();
    }
}
