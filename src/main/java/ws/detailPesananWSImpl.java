package ws;

import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;

import models.detailPesananModel;
import repo.detailPesananRepo;

@WebService(endpointInterface = "ws.detailPesananWS")
@HandlerChain(file = "/middleware/middleware.xml")
public class detailPesananWSImpl implements detailPesananWS{
    @WebMethod
    public List<detailPesananModel> getDetailPesanan(int id_pesanan){
        try{
            detailPesananRepo detailPesananRepo = new detailPesananRepo(); 
            List<detailPesananModel> result = detailPesananRepo.getDetailPesanan(id_pesanan);
            return result;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
}
