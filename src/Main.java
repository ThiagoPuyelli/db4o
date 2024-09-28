import View.View;
import client.Client;
import server.Server;
import utils.Util;

public class Main {
    public static void main(String[] args) {
        Util.initEmbebido();
        System.out.println("db="+Util.getDb());
        new View();

    }
}