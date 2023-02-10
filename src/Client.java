import java.io.*;
import java.net.*;

public class Client extends Thread{

    String mensaje;
    String ip;
    

    public Client(String mensaje, String ip){
        this.mensaje = mensaje;
        this.ip = ip;
    }

    public void run(){
        try {
            sender(mensaje, ip);
        } catch (Exception e) {
        }
    }

    public void sender(String mensaje, String ip) throws UnknownHostException, IOException{
        // Realiza la conexión
        Socket socket = new Socket();
        // Esto siver para realizar la conexión y configurar un timeOut máximo de 5 segundos
        socket.connect(new InetSocketAddress(ip, 9090), 5000);
        
        // PrintWriter es el encargado de enviar un mensaje al servidor
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    
        // Aquí se envía el mensaje, y se recibe la respuesta
        out.println(mensaje);
    
        // Aquí se cierran los flujos de datos y el socket
        out.close();
        socket.close();
    }
}
