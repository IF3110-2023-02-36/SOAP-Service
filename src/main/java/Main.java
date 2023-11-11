
import javax.xml.ws.Endpoint;

import repo.repository;

public class Main {
    public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:6001/ws/hello", new ws.TestWSImpl());
        System.out.println("Service berhasil di publish");
        repository repo = new repository();
        System.out.println(repo.getConnection());
    }
}