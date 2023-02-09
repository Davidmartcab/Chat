import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Client extends Thread{

    String mensaje;
    String ip;
    int port;
    ArrayList<String> errores;
    

    private final HashMap<String, IpPort> map = MyContacts.getMap();

    public Client(String mensaje, String ip, int port, ArrayList<String> errores){
        this.mensaje = mensaje;
        this.ip = ip;
        this.port = port;
        this.errores = errores;
    }

    public void run(){
        try {
            sender(mensaje, ip, port);
        } catch (Exception e) {
            String name;
            if(map.get(ip) == null) name = ip;
            else name = map.get(ip).getName();
            errores.add("C. Error: El mensaje: '" + mensaje + "', no ha llegado al receptor: " + name + ".");
        }
    }

    public void sender(String mensaje, String ip, int port) throws UnknownHostException, IOException{
        // Realiza la conexión
        Socket socket = new Socket();
        // Esto siver para realizar la conexión y configurar un timeOut máximo de 5 segundos
        socket.connect(new InetSocketAddress(ip, port), 5000);
        // System.out.println("C. Conectado al servidor");
        
        // PrintWriter es el encargado de enviar un mensaje al servidor
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    
        // Aquí se envía el mensaje, y se recibe la respuesta
        out.println(mensaje);
    
        // Aquí se cierran los flujos de datos y el socket
        out.close();
        socket.close();
    }
}
