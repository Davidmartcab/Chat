import java.io.*;
import java.util.ArrayList;

public class Gest {
    
    private ArrayList<Contact> contacts = new ArrayList<>();

    public Gest() {
        contacts();
    }

    public Gest(String ip){
        contacts();
        for(Contact c : contacts){
            if(c.getIp().equals(ip)){
                contacts.remove(c);
                break;
            }
        }
        writeContact();
    }

    public Gest(String ip, String name, String color){
        contacts();
        contacts.add(new Contact(ip, name, color));
        writeContact();
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    private void contacts(){
        String fichero = "agenda.txt";
        String linea = null;
        String[] campos = null;
        Contact contacto = null;

        try {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            while((linea = br.readLine()) != null){
                campos = linea.split(",");
                if(campos.length == 3){
                    contacto = new Contact(campos[0], campos[1], campos[2]);
                    contacts.add(contacto);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private void writeContact(){
        String fichero = "agenda.txt";

        try {
            FileWriter fw = new FileWriter(fichero);
            BufferedWriter bw = new BufferedWriter(fw);

            for(Contact c : contacts){
                bw.write(c.getIp() + "," + c.getName() + "," + c.getColor());
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
