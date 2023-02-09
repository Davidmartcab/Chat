public class Contact {
    private final String ip;
    private final int port;
    private final String name;
    private final String color;

    public Contact(String ip, int port, String name, String color){
        this.ip = ip;
        this.port = port;
        this.name = name;
        this.color = color;
    }

    public String getIp() {
        return ip;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
