public class Contact {
    private final String ip;
    private final String name;
    private final String color;

    public Contact(String ip, String name, String color){
        this.ip = ip;
        this.name = name;
        this.color = color;
    }

    public String getIp() {
        return ip;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
