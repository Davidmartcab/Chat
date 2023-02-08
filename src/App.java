import java.io.*;
import java.util.HashMap;


public class App {
// Hay que ejecutar el Server.java y el App.java, en ese orden.
// El App.java es el cliente, y el Server.java es el servidor.
// Ir añadiendo aquí ti IP para añadirte a la lista de contactos.
    private final static HashMap<String, IpPort> map = MyContacts.getMap();

    public static void main(String[] args) throws Exception {
        
        String texto;
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        System.out.println("C: Introducir líneas. Línea vacía para terminar.");
        System.out.println("C: Línea > ");
        try {
            while((texto = br.readLine()) != null && texto.length() > 0){
                for(String ip : map.keySet()){
                    new Client(texto, ip, map.get(ip).getPort()).start();
                }
            }
        } catch (Exception e) {
            System.out.println("A. Error: " + e.getMessage());
        }
    }
}
