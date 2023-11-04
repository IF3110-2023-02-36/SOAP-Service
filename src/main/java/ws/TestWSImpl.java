package ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "ws.TestWS")
public class TestWSImpl implements TestWS {
    @WebMethod
    public String getHelloWorldAsString(String name) {
        return "Hello World JAX-WS " + name;
    }
}