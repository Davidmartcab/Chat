import java.io.*;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;


public class App {
// Hay que ejecutar el Server.java y el App.java, en ese orden.
// El App.java es el cliente, y el Server.java es el servidor.
// Ir añadiendo aquí ti IP para añadirte a la lista de contactos.
    private static HashMap<String, IpPort> map = MyContacts.getMap();
    private static String myIp;

    public static void main(String[] args) throws Exception {
        try (final DatagramSocket datagramSocket = new DatagramSocket()) {
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
            myIp = datagramSocket.getLocalAddress().getHostAddress();
        }

        String texto;
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        System.out.println("C: //help: para ver comandos");
        System.out.println("C: Introducir líneas. Línea vacía para terminar.");
        System.out.println("C: Línea > ");
        try {
            while((texto = br.readLine()) != null){
                if(texto.contains("//")){
                    if(texto.contains("//msg:")) sendMsg(texto);
                    else if(texto.toLowerCase().equals("//newagenda")) newAgenda();
                    else if(texto.toLowerCase().equals("//help")) help();
                    else if(texto.equals("//showip")) showIp();    
                    else if(texto.equals("//exit")) exit();
                    else System.out.println("C. Comando no reconocido");
                }else{
                    if(!texto.equals("")) 
                        for(String ip : map.keySet()) new Client(texto, ip, map.get(ip).getPort()).start();
                }
            }
        } catch (Exception e) {
            System.out.println("A. Error: " + e.getMessage());
        }
    }

    private static void showIp(){
        System.out.println("C. My IP: " + myIp);
        for(String ip : map.keySet()) System.out.println("C. " + ip + " - " + map.get(ip).getName() + " - " + map.get(ip).getPort());
    }

    private static void newAgenda(){
        map = MyContacts.getMap();
        new Client("//newagenda", myIp, map.get(myIp).getPort()).start();
    }

    private static void sendMsg(String texto){
        String[] parts = texto.split(":");
        String ip = parts[1];
        String msg = parts[2];
        if(map.containsKey(ip)){
            msg = "Private: " + msg;
            if(!ip.equals(myIp)) new Client(msg, ip, map.get(ip).getPort()).start();
            new Client(msg, myIp, map.get(myIp).getPort()).start();
        }else System.out.println("C. No se ha encontrado la IP: " + ip);
    }

    private static void help(){
        System.out.println("C. Comandos disponibles:");
        System.out.println("C. //newagenda: Recarga la lista de contactos");
        System.out.println("C. //msg:ip:texto: Envia el texto solo a la IP indicada");
        System.out.println("C. //showip: Muestra las IPs de los contactos");
        System.out.println("C. //exit: Cierra la aplicación");
    }

    private static void exit(){
        System.out.println("C. Cerrando aplicación...");
        System.exit(0);
    }

}
