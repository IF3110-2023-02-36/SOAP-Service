
import javax.xml.ws.Endpoint;


public class Main {
    public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:6001/ws/pesanan", new ws.PesananWSImpl());
        Endpoint.publish("http://0.0.0.0:6001/ws/detailPesanan", new ws.detailPesananWSImpl());
        System.out.println("Service berhasil di publish");
        // repository repo = new repository();
        // System.out.println(repo.getConnection());
    }
}