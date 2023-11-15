package ws;

import java.util.ArrayList;
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

    // @WebMethod
    // public String addDetailPesanan (String id_pesanan, String nama_product, String quantity){
    //     ArrayList<Integer> list_id_pesanan = new ArrayList<>();
    //     ArrayList<String> list_nama_product = new ArrayList<>();
    //     ArrayList<Integer> list_quantity = new ArrayList<>();

    //     String[] id_pesanan_items = id_pesanan.split(",");

    //     for (String item : id_pesanan_items) {
    //         list_id_pesanan.add(Integer.parseInt(item.trim()));
    //     }

    //     String[] nama_product_items = nama_product.split(",");

    //     for(String item: nama_product_items){
    //         list_nama_product.add(item);
    //     }

    //     String[] quantity_items = quantity.split(",");
    //     for(String item: quantity_items){
    //         list_quantity.add(Integer.parseInt(item.trim()));
    //     }

    //     ArrayList<detailPesananModel> detailPesanan = new ArrayList<>();
    //     for(int i = 0; i < list_quantity.size(); i++){
    //         detailPesananModel temp = new detailPesananModel(list_id_pesanan.get(i), list_nama_product.get(i), list_quantity.get(i));
    //         detailPesanan.add(temp);
    //     }

    //     detailPesananRepo dpr = new detailPesananRepo();
    //     String result = dpr.addDetailPesanan(detailPesanan);
    //     return result;
    // }
}
