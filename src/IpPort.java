public class IpPort {

    private final int port;
    private final String name;

    public IpPort(int port, String name){
        this.port = port;
        this.name = name;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }
}
