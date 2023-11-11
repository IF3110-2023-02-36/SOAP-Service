package ws;

import javax.jws.HandlerChain;
import javax.jws.WebService;

@WebService
@HandlerChain(file = "/middleware/middleware.xml")

public interface WS {
    //nambahin method, ntar pake @WebMethod
} 
