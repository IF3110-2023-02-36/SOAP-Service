package ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import models.pesananModel;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface PesananWS {
    @WebMethod public List<pesananModel> getPesananByKurir(int id_kurir);
    @WebMethod public List<pesananModel> getPesananNoKurir();
    @WebMethod public String addPesanan(int idPemesan, String alamat, String nama_penerima, String keterangan, String harga, int biaya_pengiriman, String nama_product, String quantity);
    @WebMethod public String ambilPesanan(int id_pesanan, int id_kurir);
    @WebMethod public String updatePesanan(int id_pesanan, int id_kurir, String status, String keterangan);
    @WebMethod public ArrayList<pesananModel> getPesananByIdUser(int userId);
}
