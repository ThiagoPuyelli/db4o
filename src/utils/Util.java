package utils;
import com.db4o.*;
import com.db4o.cs.*;
import entidades.*;

import java.util.Scanner;

public class Util
{
    private static ObjectContainer db = null;
    private static ObjectServer server = null;
    public static void initServer() {
        try{
            server = Db4oClientServer.openServer("mibase.db",8080);
            server.grantAccess("usuario","contrasenia");
            System.out.println("Server db4o running...");
            System.out.println("at localhost,port 8080,database mibase.db ...");
            System.out.println("Press any key to stop");
            System.in.read();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            server.close();
        }
    }

    public static void initClient() {
        try{
            db = Db4oClientServer.openClient("localhost", 8080, "usuario", "contrasenia");
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    public static ObjectContainer getDb() { return db; }
    public static void close() {
        System.out.println("close!");
        db.close();
    }
    public static void fin() {
        System.exit(0);
    }
    public static void insertarAlbum(Album album) {
        try {
            Album albumE = buscarAlbum(album);
            if (albumE != null && albumE.getNombre().equals(album.getNombre())) {
                System.out.println("El album ya existe!");
                return;
            }
            Util.getDb().store(album);
            Util.getDb().commit();
            System.out.println("Album agregado con exito");
        } catch (Exception ext) {
            System.out.println(ext.getMessage());
        }
    }
    public static void modificarAlbum (Album album, Scanner scanner) {
        try {
            Album albumm = buscarAlbum(album);
            if (albumm == null || !(albumm.getNombre().equals(album.getNombre()))) {
                System.out.println("El album no existe");
                return;
            }
            System.out.println("Ingrese nuevo nombre del album: ");
            String nombre = scanner.nextLine();
            albumm.setNombre(nombre);
            Util.getDb().store(albumm);
            Util.getDb().commit();
            System.out.println("Album modificado con exito");
        }catch (Exception ext) {
            System.out.println(ext.getMessage());
        }
    }

    public static void modificarCancion(Album album, Cancion cancion, Scanner scanner) {
        try {
            Album albumm = buscarAlbum(album);
            if (albumm == null || !(albumm.getNombre().equals(album.getNombre()))) {
                System.out.println("El album no existe");
                return;
            }
            Cancion cancionn = buscarCancion(cancion);
            if (cancionn == null || !(cancionn.getNombre().equals(cancion.getNombre()))) {
                System.out.println("La cancion no existe");
                return;
            }
            System.out.println("Ingrese nuevo nombre de cancion: ");
            String nombre = scanner.nextLine();
            cancionn.setNombre(nombre);
            Cancion cancionnn = buscarCancion(new Cancion(cancionn.getNombre(), cancionn.getAlbum()));
            if (cancionnn != null && cancionnn.getNombre().equals(cancionn.getNombre())) {
                System.out.println("Ya existe una cancion con ese nombre");
            } else {
              Util.getDb().store(cancionn);
              Util.getDb().commit();
              System.out.println("Album modificado con exito");
            }
        }catch (Exception ext) {
            System.out.println(ext.getMessage());
        }
    }

    public static void agregarCancion (Album album, Cancion cancion) {
        try {
            Album albumm = buscarAlbum(album);
            if (albumm == null || !(albumm.getNombre().equals(album.getNombre()))) {
                System.out.println("El album no existe");
                return;
            }
            cancion.setAlbum(albumm.getNombre());
            Cancion cancionn = buscarCancion(cancion);
            if (cancionn != null && cancionn.getNombre().equals(cancion.getNombre())) {
                System.out.println("La cancion ya existe");
                return;
            }

            Util.getDb().store(cancion);
            Util.getDb().commit();
            System.out.println("Cancion agregada con exito");
        }catch (Exception ext) {
            System.out.println(ext.getMessage());
        }
    }

    public static void verCanciones (Album album) {
        try {
            Album albumm = buscarAlbum(album);
            if (albumm == null || !(albumm.getNombre().equals(album.getNombre()))) {
                System.out.println("El album no existe");
                return;
            }

            ObjectSet<Cancion> os = getDb().queryByExample(new Cancion(null, album.getNombre()));
            Cancion cancion = null;
            int i = 1;
            while (os.hasNext()) {
                cancion = os.next();
                if (cancion != null) {
                    System.out.println(i + ": " + cancion.getNombre());
                    i++;
                }
            }
        }catch (Exception ext) {
            System.out.println(ext.getMessage());
        }
    }
    public static Album buscarAlbum(Album album) {
        Album encontrado = null;
        try {
            ObjectSet<Album> os = getDb().queryByExample(album);
            while(encontrado == null && os.hasNext()) {
                encontrado = os.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encontrado;
    }

    public static Cancion buscarCancion(Cancion cancion) {
        Cancion encontrado = null;
        try {
            ObjectSet<Cancion> os = getDb().queryByExample(cancion);
            while(encontrado == null && os.hasNext()) {
                encontrado = os.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encontrado;
    }

    public static void eliminarAlbum (Album album) {
        ObjectSet<Album> os = getDb().queryByExample(album);
        if ( os != null ) {
            while(os.hasNext()) {
                try {
                    Album found = os.next();
                    ObjectSet<Cancion> os2 = getDb().queryByExample(new Cancion(null, found.getNombre()));
                    int i = 0;
                    Cancion can = null;
                    while (os2.hasNext()) {
                        can = os2.next();
                        if (can != null); {
                            getDb().delete(can);
                            i++;
                        }
                    }
                    getDb().delete(found);
                    getDb().commit();
                    System.out.println("Eliminacion del album con exito y se eliminaron " + i + " canciones");
                } catch(Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    public static void eliminarCancion(Album album, Cancion cancion) {
        ObjectSet<Album> os = getDb().queryByExample(album);
        if ( os != null ) {
            while(os.hasNext()) {
                try {
                    Album found = os.next();
                    ObjectSet<Cancion> os2 = getDb().queryByExample(cancion);
                    boolean b = false;
                    Cancion can = null;
                    while (os2.hasNext()) {
                        can = os2.next();
                        if (can != null); {
                            getDb().delete(can);
                            b = true;
                        }
                    }
                    getDb().commit();
                    System.out.println("Eliminacion de la cancion con exito");
                } catch(Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }

    public static void listsAlbums(ObjectSet<Album> os) {
        try {
            Album a = null;
            while (os.hasNext()) {
                a = os.next();
                System.out.println("album: " + a.getNombre());
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

}