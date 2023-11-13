package ws;

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
}
