package middleware;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import com.sun.net.httpserver.HttpExchange;

import models.loggingModel;


import repo.apiKeyRepo;
import repo.loggingRepo;

public class Middleware implements SOAPHandler<SOAPMessageContext>{
    //yang perlu kerjain untuk semua servis contoh : cek api key dan logging
    public boolean handleMessage(SOAPMessageContext ctx) {
        Boolean isOutbound = (Boolean) ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        
        Map<String, Object> Header = (Map) ctx.get(ctx.HTTP_REQUEST_HEADERS);
        List<String> apiKeyHeader = (List<String>) Header.get("apiKey");
        
        if(apiKeyHeader == null){
            return false;
        }

        String apiKey = apiKeyHeader.get(0);
        // System.out.println(apiKey);

        apiKeyRepo apiKeyRepo = new apiKeyRepo();
        String client = apiKeyRepo.getClientByApiKey(apiKey);
        String endpoint = "http://0.0.0.0:6001/ws/";
        String method = null;
        String IP = null;

        List<String> args = new ArrayList<String>();

        if(client == null){
            return false;
        }

        
        if (!isOutbound) {
            SOAPMessage soapMessage = ctx.getMessage();
            try {
                SOAPBody soapBody = soapMessage.getSOAPBody();

                Iterator<?> bodyElements = soapBody.getChildElements();
                while (bodyElements.hasNext()) {
                    Object element = bodyElements.next();
                    if (element instanceof SOAPElement) {
                        SOAPElement soapElement = (SOAPElement) element;
                        //dapetin method yang dipanggil
                        method = soapElement.getLocalName();

                        Iterator<?> params= soapElement.getChildElements();
                        while(params.hasNext()){
                            Object param = params.next();
                            if(param instanceof SOAPElement){
                                SOAPElement paramElement = (SOAPElement) param;
                                //dapeting parameternya
                                args.add(paramElement.getTextContent());
                            }
                        }
                    }
                }

                //dapetin IP
                HttpExchange httpExchange = (HttpExchange)ctx.get("com.sun.xml.internal.ws.http.exchange");
                InetSocketAddress remoteAddress = httpExchange.getRemoteAddress();
                IP = remoteAddress.getHostName();


                String Service = ((QName) ctx.get(MessageContext.WSDL_SERVICE)).getLocalPart();    //dapetin webservice
                //endpoint
                switch (Service) {
                    case "PesananWSImplService":
                        endpoint = endpoint+"pesanan";
                        break;
                    case "detailPesananWSImplService":
                        endpoint = endpoint+"detailPesanan";
                    default:
                        break;
                }
                
            } catch (SOAPException e) {
                e.printStackTrace();
            }
        }

        method += "(";
        for(int i=0; i<args.size(); i++) {
            method += args.get(i);
            if(i<args.size()-1){
                method += ", ";
            }   
        }
        method += ")";

        loggingModel lm = new loggingModel(IP, null, endpoint, method, client);
        loggingRepo lr = new loggingRepo();

        if(IP == null || method == null) {
            return false;
        }
        try{
            lr.addLogging(lm);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        // console logging();
        System.out.println("API Call from : " + client);
        System.out.println("Accessed Endpoint : " + endpoint);
        System.out.println("Accessed Method : " + method);
        System.out.println();

        return true;
    }
    

    public Set<QName> getHeaders(){
        //ini gimick doang karena harus ini implement interface
        return null;
    }

    public boolean handleFault(SOAPMessageContext ctx){
        //ini gimick doang karena harus ini implement interface
        return false;
    }

    public void close(MessageContext ctx){
        //ini gimick doang karena harus ini implement interface
    }
}
