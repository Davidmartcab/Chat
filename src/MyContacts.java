import java.util.HashMap;

public class MyContacts {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static HashMap<String, IpPort> getMap() {
        return new HashMap<>(){{
            put("192.168.13.28", new IpPort(9090, "Mario", ANSI_RED));
            put("192.168.13.22", new IpPort(9090, "Asier", ANSI_BLUE));
            put("192.168.13.21", new IpPort(9090, "Goyo", ANSI_GREEN));
            put("192.168.13.37", new IpPort(9090, "David", ANSI_YELLOW));
        }};
    }
}
