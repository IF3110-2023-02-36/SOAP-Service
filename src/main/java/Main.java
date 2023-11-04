
import javax.xml.ws.Endpoint;

public class Main {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:7779/ws/hello", new ws.TestWSImpl());
        System.out.println("Service berhasil di publish");
    }
}