
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import ws.TestWS;

public class ServiceTester {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:6001/ws/hello?wsdl");
   
        QName qname = new QName("http://ws/", "TestWSImplService");
        Service service = Service.create(url, qname);
        TestWS hello = service.getPort(TestWS.class);
        System.out.println(hello.getHelloWorldAsString("jamal"));
    }
}
