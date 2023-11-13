package ws;

import java.sql.SQLException;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import models.pesananModel;
import repo.pesananRepo;

@WebService(endpointInterface = "ws.PesananWS")
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
    public String addPesanan(String alamat, String nama_penerima, String keterangan){
        try{
            pesananRepo pr = new pesananRepo();
            String result = pr.addPesanan(alamat, nama_penerima, keterangan);
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
}