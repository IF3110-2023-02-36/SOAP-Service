package ws;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;

import models.detailPesananModel;
import models.pesananModel;
import repo.detailPesananRepo;
import repo.pesananRepo;

@WebService(endpointInterface = "ws.PesananWS")
@HandlerChain(file = "/middleware/middleware.xml")
public class PesananWSImpl implements PesananWS{
    @WebMethod
    public List<pesananModel> getPesananByKurir(int id_kurir){
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
    @WebMethod
    public List<pesananModel> getPesananNoKurir(){
        try{
            pesananRepo pr = new pesananRepo();
            List<pesananModel> listpesanan = pr.getPesananNoKurir();
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

    @WebMethod
    public String addPesanan(int idPemesan, String alamat, String nama_penerima, String keterangan, String harga, int biaya_pengiriman, String nama_product, String quantity){
        detailPesananModel dp = new detailPesananModel();
        try{
            pesananRepo pr = new pesananRepo();

            int total_harga = dp.getTotalHarga(harga, quantity);

            String result = pr.addPesanan(idPemesan, alamat, nama_penerima, keterangan, total_harga, biaya_pengiriman);

            System.out.println(result);
            int lastId = pr.getLastId();
            if(lastId == -1){
                return "Gagal";
            }

            ArrayList<detailPesananModel> detailPesanan = dp.convertFromString(lastId, nama_product, quantity, harga);
            detailPesananRepo dpr = new detailPesananRepo();
            result = dpr.addDetailPesanan(detailPesanan);
            return result;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "Gagal menambahkan pesanan";
        }
    }

    @WebMethod 
    public String ambilPesanan(int id_pesanan, int id_kurir){
        try{
            pesananRepo pr = new pesananRepo();
            String result = pr.ambilPesanan(id_pesanan, id_kurir);
            return result;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "Gagal mengambil pesanan";
        }
    }

    @WebMethod 
    public String updatePesanan(int id_pesanan, int id_kurir, String status, String keterangan){
        try{
            pesananRepo pr = new pesananRepo();
            String result = pr.updatePesanan(id_pesanan, id_kurir, status, keterangan);
            return result;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "Gagal mengedit pesanan";
        }
    }
    
    @WebMethod public ArrayList<pesananModel> getPesananByIdUser(int userId){
        try {
            pesananRepo pr = new pesananRepo();
            ArrayList<pesananModel> result = pr.getPesananByIdUser(userId);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}