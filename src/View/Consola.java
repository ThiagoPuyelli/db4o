package View;

import com.db4o.ObjectSet;
import entidades.Album;
import entidades.Cancion;
import utils.Util;

import java.util.Scanner;

public class Consola {
    public static void iniciarConsola () {
        int input;
        Util.initClient();
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("Ingrese una opcion");
            System.out.println("0. Salir");
            System.out.println("1. Agregar album");
            System.out.println("2. Modificar album");
            System.out.println("3. Eliminar album");
            System.out.println("4. Ver albumes");
            System.out.println("5. Ver canciones de album");
            System.out.println("6. Agregar cancion");
            System.out.println("7. Modificar cancion");
            System.out.println("8. Eliminar cancion");

            input = scanner.nextInt();

            if (input < 0 || input > 8) {
                System.out.println("La opcion es incorrecta");
            } else if (input == 0) {
                System.out.println("Cerramos la aplicacion!!");
            } else {
                switch (input) {
                    case 1:
                        agregarAlbum(scanner);
                        break;
                    case 2:
                        modificarAlbum(scanner);
                        break;
                    case 3:
                        eliminarAlbum(scanner);
                        break;
                    case 4:
                        verAlbumes();
                        break;
                    case 5:
                        verCanciones(scanner);
                        break;
                    case 6:
                        agregarCancion(scanner);
                        break;
                    case 7:
                        modificarCancion(scanner);
                        break;
                    case 8:
                        eliminarCancion(scanner);
                        break;
                }
            }
        } while (input != 0);
    }

    private static void agregarAlbum (Scanner scanner) {
        System.out.println("Ingrese el nombre del nuevo album: ");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        Util.insertarAlbum(new Album(nombre));
    }

    private static void verAlbumes () {
        ObjectSet<Album> os = Util.getDb().queryByExample(new Album());
        Util.listsAlbums(os);
    }

    private static void modificarAlbum (Scanner scanner) {
        System.out.println("Ingrese el nombre del album a modificar: ");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        Util.modificarAlbum(new Album(nombre), scanner);
    }

    private static void eliminarAlbum (Scanner scanner) {
        System.out.println("Ingrese el nombre del album a eliminar");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        Util.eliminarAlbum(new Album(nombre));
    }

    private static void agregarCancion (Scanner scanner) {
        System.out.println("Ingrese el nombre del album a agregar cancion");
        scanner.nextLine();
        String nombreAlbum = scanner.nextLine();

        System.out.println("Ingrese el nombre de la nueva cancion");
        String nombreCancion = scanner.nextLine();
        Util.agregarCancion(new Album(nombreAlbum), new Cancion(nombreCancion, nombreAlbum));
    }

    private static void verCanciones(Scanner scanner) {
        System.out.println("Ingrese el nombre del album para ver las canciones");
        scanner.nextLine();
        String nombre = scanner.nextLine();
        Util.verCanciones(new Album(nombre));
    }

    private static void eliminarCancion(Scanner scanner) {
        System.out.println("Ingrese el nombre del album a eliminar cancion");
        scanner.nextLine();
        String nombreAlbum = scanner.nextLine();

        System.out.println("Ingrese el nombre de la cancion a eliminar");
        String nombreCancion = scanner.nextLine();
        Util.eliminarCancion(new Album(nombreAlbum), new Cancion(nombreCancion, nombreAlbum));
    }

    private static void modificarCancion (Scanner scanner) {
        System.out.println("Ingrese el nombre del album a modificar cancion");
        scanner.nextLine();
        String nombreAlbum = scanner.nextLine();

        System.out.println("Ingrese el nombre de la cancion a modificar");
        String nombreCancion = scanner.nextLine();
        Util.modificarCancion(new Album(nombreAlbum), new Cancion(nombreCancion, nombreAlbum), scanner);
    }
}
