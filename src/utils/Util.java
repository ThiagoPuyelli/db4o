
package utils;
import com.db4o.*;
import com.db4o.cs.*;
import com.db4o.query.*;
import entidades.Cliente;
import entidades.Factura;

import java.util.Scanner;

import static utils.Validacion.tomarNumeroDouble;


/**
 * Esta clase encapsula las operaciones para persistir a clientes 
 * dentro de b.d. db4o
 * Tambien permite ejecutar db4o y conectarse a su contenedor de objetos
 * en forma embebida o bien ejecutar servidor o bien ejecutar cliente
 *
 * @author G. Cherencio
 * @version 1.0
 */


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
    public static void initEmbebido() {
        db = Db4oEmbedded.openFile(Db4oEmbedded.newConfiguration(),"mibase.db");
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
    public static void agregarCliente(Cliente nuevo) {
        try {
            Cliente found = buscarCliente(nuevo.getId());
            if ( found != null ){
                System.out.println("El cliente ya se encuentra");
            }
            else{
            found = nuevo;
            Util.getDb().store(found);
            Util.getDb().commit();
            System.out.println("Cliente agregado exitosamente!");
            }
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }

    }


    public static void mostrarClientes() {
        try {
            ObjectSet<Cliente> clientes = db.query(Cliente.class);
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }

        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }

    }



    public static void borrarCliente(Cliente aborrar) {
        Cliente cliente= buscarCliente(aborrar.getId());
        if ( cliente != null ) {
                try {
                    getDb().delete(cliente);
                    getDb().commit();
                    System.out.println("Cliente borrado exitosamente");
                } catch(Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
        else {
            System.out.println("El cliente no existe!");
        }
    }

    public static  void  modificarCliente(Cliente modificado, Scanner sc) {
        Cliente encontrado = buscarCliente(modificado.getId());
        try {
            if (encontrado!=null){
                System.out.println("Ingrese nueva descripcion");
                String descripcion = sc.nextLine();
                encontrado.setDescripcion(descripcion);
                db.store(encontrado);
                System.out.println("Cliente modificado exitosamente");
            }
            else {
                System.out.println("No existe el cliente con la id "+ modificado.getId());
            }

        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }

    }


    public static Cliente buscarCliente(int id) {
        Cliente encontrado = null;
        try {
            Query query = db.query();
            query.constrain(Cliente.class);
            query.descend("id").constrain(id);
            ObjectSet<Cliente> resultados = query.execute();
            if (!resultados.isEmpty()) {
                encontrado= resultados.get(0);
            }

        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        return encontrado;

    }








    public static void agregarFactura(Factura nuevo) {
        try {

            Factura encontrado = buscarFactura(nuevo.getNro());
            if ( encontrado != null ){
                System.out.println("La factura ya se encuentra");
            }
            else{
                Cliente c = buscarCliente(nuevo.getId());
                if (c!=null){
                    encontrado=nuevo;
                    Util.getDb().store(encontrado);
                    Util.getDb().commit();
                    System.out.println("Factura agregada exitosamente!");
                }
                else {
                    System.out.println("No existe el cliente con la id "+nuevo.getId());
                }

            }
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }

    }


    public static void mostrarFacturas() {
        try {
            ObjectSet<Factura> facturas = db.query(Factura.class);
            for (Factura f : facturas) {
                System.out.println(f);
            }

        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }

    }

    public static void modificarFactura(Factura modificado, Scanner sc) {
        Factura encontrado = buscarFactura(modificado.getNro());
        try {
            if (encontrado!=null){
                System.out.println("Ingrese nuevo importe");
                double importe = tomarNumeroDouble(sc);
                encontrado.setImporte(importe);
                db.store(encontrado);
                System.out.println("Factura modificada exitosamente");
            }
            else {
                System.out.println("No existe la Factura con el nro "+ modificado.getNro());
            }

        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }


    }



    public static Factura buscarFactura(int nro) {
        Factura encontrado = null;
        try {
            Query query = db.query();
            query.constrain(Factura.class);
            query.descend("nro").constrain(nro);
            ObjectSet<Factura> resultados = query.execute();
            if (!resultados.isEmpty()) {
                encontrado= resultados.get(0);
            }

        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        return encontrado;

    }


    public static void borrarFactura(Factura aborrar) {
        Factura factura= buscarFactura(aborrar.getNro());
        if ( factura != null ) {
            try {
                getDb().delete(factura);
                getDb().commit();
                System.out.println("Factura borrada exitosamente");
            } catch(Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
        else {
            System.out.println("La Factura no existe!");
        }
    }




}


