
package utils;
import com.db4o.*;
import com.db4o.cs.*;
import com.db4o.query.*;
import entidades.Cliente;


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
    public static ObjectSet<Cliente> find(Cliente c) {
        ObjectSet<Cliente> os = null;
        try {
            os = getDb().queryByExample(c);
        } catch(Exception ex) {
            System.out.println("**error**");
            System.err.println(ex.getMessage());
            System.out.println("**fin error**");
        }
        return os;
    }
    public static StringBuilder list(Cliente c) {
        return list(find(c));
    }
    public static StringBuilder list(ObjectSet<Cliente> os) {
        StringBuilder sb = new StringBuilder();
        try {
            Cliente found = null;
            while(os.hasNext()) {
                found = os.next();
                sb.append("Id: "+found.getId()+
                        "\n"+ "Descripcion:"+found.getDescripcion());
                System.out.println("list(os)!="+sb.toString());
            }
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        return sb;
    }
    public static void deleteAll() {
        try {
            ObjectSet<Object> os = getDb().queryByExample(new Object());
            while(os.hasNext()) {
                getDb().delete(os.next());
            }
            getDb().commit();
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    public static StringBuilder listNotName(String nombre) {
        StringBuilder sb = new StringBuilder();
        try {
            Query query = getDb().query();
            query.constrain(Cliente.class);
            query.descend("nombre").constrain(nombre).not();
            ObjectSet<Cliente> os = query.execute();
            sb=list(os);
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        return sb;
    }
    public static StringBuilder listGtCodigo(int codigo) {
        StringBuilder sb = new StringBuilder();
        try {
            Query query = getDb().query();
            query.constrain(Cliente.class);
            query.descend("codigo").constrain(codigo).greater();
            ObjectSet<Cliente> os = query.execute();
            sb=list(os);
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        return sb;
    }
    public static StringBuilder listGtCodigoAndLike(int codigo,String nombre) {
        StringBuilder sb = new StringBuilder();
        try {
            Query query = getDb().query();
            query.constrain(Cliente.class);
            query.descend("codigo").constrain(codigo).greater().and(
                    query.descend("nombre").constrain(nombre).like()
            );
            ObjectSet<Cliente> os = query.execute();
            sb=list(os);
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        return sb;
    }
    /**
     * Listado de prueba
     */


    public static StringBuilder listTest() {
        StringBuilder sb = new StringBuilder();
        try {
            Query query = getDb().query();
            query.constrain(Cliente.class);
            query.descend("codigo").constrain(0).greater().and(
                    query.descend("codigo").constrain(100).greater().not()
            ).or(query.descend("saldo").constrain(1000).greater().not());
            ObjectSet<Cliente> os = query.execute();
            sb=list(os);
        } catch(Exception ex) {
            System.err.println(ex.getMessage());
        }
        return sb;
    }

}


