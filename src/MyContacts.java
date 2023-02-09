import java.util.ArrayList;
import java.util.HashMap;

public class MyContacts {

    // public static final String ANSI_RESET = "\u001B[0m";
    // public static final String ANSI_BLACK = "\u001B[30m";
    // public static final String ANSI_RED = "\u001B[31m";
    // public static final String ANSI_GREEN = "\u001B[32m";
    // public static final String ANSI_YELLOW = "\u001B[33m";
    // public static final String ANSI_BLUE = "\u001B[34m";
    // public static final String ANSI_PURPLE = "\u001B[35m";
    // public static final String ANSI_CYAN = "\u001B[36m";
    // public static final String ANSI_WHITE = "\u001B[37m";

    // public static final String BLACK = "\033[0;30m";   // BLACK
    // public static final String RED = "\033[0;31m";     // RED
    // public static final String GREEN = "\033[0;32m";   // GREEN
    // public static final String YELLOW = "\033[0;33m";  // YELLOW
    // public static final String BLUE = "\033[0;34m";    // BLUE
    // public static final String PURPLE = "\033[0;35m";  // PURPLE
    // public static final String CYAN = "\033[0;36m";    // CYAN
    // public static final String WHITE = "\033[0;37m";   // WHITE
    private static final HashMap<String, String> map = new HashMap<>(){{
        put("1", "\033[0;31m");
        put("2", "\033[0;32m");
        put("3", "\033[0;33m");
        put("4", "\033[0;34m");
        put("5", "\033[0;35m");
        put("6", "\033[0;36m");
        put("7", "\033[0;37m");
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
