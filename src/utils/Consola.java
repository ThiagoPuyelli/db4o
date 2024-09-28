package utils;

public class Consola {

    public void inicializarConsola(){

       int input;
       input=-1;
       menu();

        do{

         }
        while (input!=0);
    }

    public void menu(){
        System.out.println("0 - Salir");
        System.out.println("1 - Altar Cliente");
        System.out.println("2 - Modificar Cliente");
        System.out.println("3 - Borrar Cliente");
        System.out.println("4 - Mostrar Clientes");
        System.out.println("5 - Altar Factura");
        System.out.println("6 - Modificar Factura");
        System.out.println("7 - Borrar Factura");
        System.out.println("8 - Mostrar Facturas");

    }
}
