import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Server{

    private static final int port = 9090;


    private static HashMap<String, IpPort> map = MyContacts.getMap();
    private static String myIp;

    public static void main(String[] args) throws Exception {
        try (final DatagramSocket datagramSocket = new DatagramSocket()) {
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
            myIp = datagramSocket.getLocalAddress().getHostAddress();
        }
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.print("\033[H\033[2J");  
            System.out.println("S: Servidor escuchando en el puerto " + port);

            // Bucle infinito de escucha
            while (true) {
                // Aceptamos una nueva conexión con accept()
                Socket clientSocket = serverSocket.accept();
                // Muestra la dirección IP del cliente
                String name;
                String color = "\u001B[37m";
                if(clientSocket.getInetAddress().getHostAddress().equals(myIp)){
                    name = "Yo";
                    color = "\u001B[32m";
                }else{
                    IpPort sender = map.get(clientSocket.getInetAddress().getHostAddress());
                    if(sender == null) name = clientSocket.getInetAddress().getHostAddress();
                    else {
                        name = sender.getName();
                        color = sender.getColor();
                    }
                }

                // BufferedReader es el encargado de enviar la respuesta al cliente
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Leemos el mensaje del cliente
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    switch(inputLine.toLowerCase()){
                        case "//newagenda":
                            map = MyContacts.getMap();
                        break;
                        case "//cleanserver":
                            System.out.print("\033[H\033[2J");
                            System.out.println("S: Servidor escuchando en el puerto " + port);
                        break;
                        default:
                            System.out.println(color+"S. " + name + ": " + inputLine + "\033[0m");
                        break;
                    }
                    // Si hay texto lo puestra y le envía un mensaje de vuelta
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
