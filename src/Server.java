import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Server{

    private static int port = 9090;


    private static HashMap<String, IpPort> map = MyContacts.getMap();

    public static void main(String[] args) throws Exception {
        try {
            System.out.println("S. Introcude tu puerto, dejalo vació para el 9090");
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            port = Integer.parseInt(br.readLine());
        } catch (Exception e) {
            // TODO: handle exception
        }
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("S: Servidor escuchando en el puerto " + port);

            // Bucle infinito de escucha
            while (true) {
                // Aceptamos una nueva conexión con accept()
                Socket clientSocket = serverSocket.accept();
                // Muestra la dirección IP del cliente
                IpPort sender = map.get(clientSocket.getInetAddress().getHostAddress());
                String name;
                String color = "\u001B[37m";
                if(sender == null) name = clientSocket.getInetAddress().getHostAddress();
                else {
                    name = sender.getName();
                    color = sender.getColor();
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
