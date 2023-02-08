import java.io.*;
import java.util.HashMap;


public class App {
// Hay que ejecutar el Server.java y el App.java, en ese orden.
// El App.java es el cliente, y el Server.java es el servidor.
    private final static HashMap<String, IpPort> map = new HashMap<>(){{
        put("localhost", new IpPort(9090, "Yo"));
        put("192.168.13.28", new IpPort(9090, "Mario"));
        put("192.168.13.22", new IpPort(9090, "Asier"));
        put("192.168.13.37", new IpPort(9090, "David"));
    }};

    public static void main(String[] args) throws Exception {
        Client client = new Client();
        String texto;
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        System.out.println("C: Introducir líneas. Línea vacía para terminar.");
        System.out.println("C: Línea > ");
        try {
            while((texto = br.readLine()) != null && texto.length() > 0){
                for(String ip : map.keySet()){
                    client.sender(texto, ip, map.get(ip).getPort());
                }
            }
        } catch (Exception e) {
            System.out.println("C. Error: " + e.getMessage());
        }
    }
}
