import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Server{

    private static final int port = 9090;


    private final static HashMap<String, IpPort> map = MyContacts.getMap();

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("S: Servidor escuchando en el puerto " + port);

            // Bucle infinito de escucha
            while (true) {
                // Aceptamos una nueva conexión con accept()
                Socket clientSocket = serverSocket.accept();
                // Muestra la dirección IP del cliente
                IpPort sender = map.get(clientSocket.getInetAddress().getHostAddress());
                String name = clientSocket.getInetAddress().getHostAddress();
                if(sender != null) name = sender.getName();
                
                // BufferedReader es el encargado de enviar la respuesta al cliente
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Leemos el mensaje del cliente
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    // Si hay texto lo puestra y le envía un mensaje de vuelta
                    System.out.println("S. " + name + ": " + inputLine);
                }

                // Cerramos los flujos de datos y el socket
                in.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
