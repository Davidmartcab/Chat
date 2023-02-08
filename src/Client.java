import java.io.*;
import java.net.*;

public class Client extends Thread{

    String mensaje;
    String ip;
    int port;

    public Client(String mensaje, String ip, int port){
        this.mensaje = mensaje;
        this.ip = ip;
        this.port = port;
    }

    public void run(){
        try {
            sender(mensaje, ip, port);
        } catch (Exception e) {
            System.out.println("C. Error: " + e.getMessage());
        }
    }

    public void sender(String mensaje, String ip, int port) throws UnknownHostException, IOException{
        // Realiza la conexión
        Socket socket = new Socket(ip, port);
        // System.out.println("C. Conectado al servidor");
    
        // PrintWriter es el encargado de enviar un mensaje al servidor
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        // BufferedReader es el encargado de recibir la respuesta del servidor tras enviar el mensaje
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    
        // Aquí se envía el mensaje, y se recibe la respuesta
        out.println(mensaje);

        // Aquí se muestra la respuesta del servidor
        // System.out.println("C. Respuesta del servidor: " + in.readLine());
    
        // Aquí se cierran los flujos de datos y el socket
        out.close();
        in.close();
        socket.close();
    }
}
