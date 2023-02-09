public class IpPort {

    private final int port;
    private final String name;
    private final String color;

    public IpPort(int port, String name, String color){
        this.port = port;
        this.name = name;
        this.color = color;
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
