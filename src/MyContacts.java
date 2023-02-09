import java.util.ArrayList;
import java.util.HashMap;

public class MyContacts {

    private static final HashMap<String, String> map = new HashMap<>(){{
        put("1", "\033[0;31m");
        put("2", "\033[0;32m");
        put("3", "\033[0;33m");
        put("4", "\033[0;34m");
        put("5", "\033[0;35m");
        put("6", "\033[0;36m");
    }};
    private static ArrayList<Contact> contacts = new ArrayList<>();

    public static HashMap<String, IpPort> getMap() {
        Gest gest = new Gest();
        contacts = gest.getContacts();
        return new HashMap<>(){{
            for(Contact contact : contacts){
                put(contact.getIp(), new IpPort(contact.getPort(), contact.getName(), map.get(contact.getColor())));
            }
        }};
    }
}
