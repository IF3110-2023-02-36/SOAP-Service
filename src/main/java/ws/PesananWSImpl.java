package ws;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import models.pesananModel;
import repo.pesananRepo;

@WebService(endpointInterface = "ws.PesananWS")
public class PesananWSImpl implements PesananWS{
    @WebMethod
    public List<pesananModel> getPesananByKurir(@WebParam(name="id_kurir")int id_kurir){
        try{
            System.out.println(id_kurir);
            pesananRepo pr = new pesananRepo();
            List<pesananModel> listpesanan = pr.getPesananByKurir(id_kurir);
            System.out.println("list pesanan");
            System.out.println(listpesanan);
            for(int i = 0; i<listpesanan.size(); i++){
                System.out.println(listpesanan.get(i));
            }
            return listpesanan;
        }catch(SQLException e){
            System.err.println(e.getErrorCode());
        }
        return null;
    }

}