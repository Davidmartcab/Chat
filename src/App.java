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
                    else if(texto.toLowerCase().equals("//newagendaonlyclient")) newAgendaOnlyClient();
                    else if(texto.toLowerCase().equals("//help")) help();
                    else if(texto.equals("//showip")) showIp();
                    else if(texto.contains("//add")) addContact(texto);
                    else if(texto.contains("//delete")) deleteContact(texto);
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

    private static void newAgendaOnlyClient(){
        map = MyContacts.getMap();
    }

    private static void addContact(String texto){
        String[] parts = texto.split(":");
        if(parts.length != 5){
            System.out.println("C. Formato incorrecto. //add:ip:port:name:color");
            return;
        }
        try {
            String ip = parts[1];
            int port = Integer.parseInt(parts[2]);
            String name = parts[3];
            String color = parts[4];
            int colo = Integer.parseInt(color);
            if(colo < 0 || colo > 7) throw new Exception("A. Color no válido");
            new Gest(ip, name, port, color);
            System.out.println("C. Contacto añadido");
        } catch (Exception e) {
            System.out.println("A. Error: " + e.getMessage());
        }
    }

    private static void deleteContact(String texto){
        String[] parts = texto.split(":");
        if(parts.length != 2){
            System.out.println("C. Formato incorrecto. //delete:ip");
            return;
        }
        try {
            String ip = parts[1];
            new Gest(ip);
            System.out.println("C. Contacto eliminado");
        } catch (Exception e) {
            System.out.println("A. Error: " + e.getMessage());
        }
    }

    private static void sendMsg(String texto){
        String[] parts = texto.split(":");
        if(parts.length != 3){
            System.out.println("C. Formato incorrecto. //msg:ip:texto");
            return;
        }
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
        System.out.println("C. //newagendaonlyclient: Recarga la lista de contactos, solo del cliente");
        System.out.println("C. //msg:ip:texto: Envia el texto solo a la IP indicada");
        System.out.println("C. //add:ip:port:name:color: Añade un contacto");
        System.out.println("C. //delete:ip: Elimina un contacto");
        System.out.println("C. //showip: Muestra las IPs de los contactos");
        System.out.println("C. //exit: Cierra la aplicación");
    }

    private static void exit(){
        System.out.println("C. Cerrando aplicación...");
        System.exit(0);
    }

}
