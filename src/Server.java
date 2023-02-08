import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Server extends Thread{

    private final int port;
    private final HashMap<String, IpPort> map;

    public Server (int port, HashMap<String, IpPort> map) {
        this.port = port;
        this.map = map;
    }

    public void run(){
        // Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("S: Servidor escuchando en el puerto " + port);

            // Bucle infinito de escucha
            while (true) {
                // Aceptamos una nueva conexión con accept()
                Socket clientSocket = serverSocket.accept();
                // Muestra la dirección IP del cliente
                IpPort sender = map.get(clientSocket.getInetAddress().getHostAddress());
                String name;
                if(sender == null) name = clientSocket.getInetAddress().getHostAddress();
                else name = sender.getName();
                
                // System.out.println("S: Conexión aceptada desde " + name);
                // PrintWriter es el encargado de recibir el mensaje del cliente
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                // BufferedReader es el encargado de enviar la respuesta al cliente
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Leemos el mensaje del cliente
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    // SI hay texto lo puestra y le envía un mensaje de vuelta
                    System.out.println("\nS. " + name + ": " + inputLine);
                }

                // Cerramos los flujos de datos y el socket
                out.close();
                in.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
