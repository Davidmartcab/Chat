import java.io.*;
import java.util.ArrayList;

public class Gest {
    
    private ArrayList<Contact> contacts = new ArrayList<>();

    public Gest() {
        contacts();
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    private void contacts(){
        // Leer el fichero agenda.txt
        // Cada línea es un contacto
        // Cada contacto tiene 4 campos separados por comas
        // Cada campo es un atributo del contacto
        // Cada contacto se añade a la lista de contactos

        String fichero = "agenda.txt";
        String linea = null;
        String[] campos = null;
        Contact contacto = null;

        try {
            FileReader fr = new FileReader(fichero);
            BufferedReader br = new BufferedReader(fr);

            while((linea = br.readLine()) != null){
                campos = linea.split(",");
                contacto = new Contact(campos[0], Integer.parseInt(campos[1]), campos[2], campos[3]);
                contacts.add(contacto);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}
