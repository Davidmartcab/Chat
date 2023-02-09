import java.util.HashMap;

public class MyContacts {

    public static HashMap<String, IpPort> getMap() {
        return new HashMap<>(){{
            put("192.168.13.28", new IpPort(9090, "Mario"));
            put("192.168.13.22", new IpPort(9090, "Asier"));
            put("192.168.13.21", new IpPort(9090, "Goyo"));
            put("192.168.13.37", new IpPort(9090, "David"));
        }};
    }
}
