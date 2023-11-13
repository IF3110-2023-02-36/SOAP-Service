package ws;

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
    @WebMethod public String addPesanan(String alamat, String nama_penerima, String keterangan);
}
