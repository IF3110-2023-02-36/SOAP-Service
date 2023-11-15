package ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import models.detailPesananModel;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface detailPesananWS {
    @WebMethod public List<detailPesananModel> getDetailPesanan(int id_pesanan);

    // @WebMethod public String addDetailPesanan (String id_pesanan, String nama_product, String quantity);
}


