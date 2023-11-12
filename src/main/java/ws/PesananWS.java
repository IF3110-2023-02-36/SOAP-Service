package ws;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import models.pesananModel;
import repo.pesananRepo;

@WebService
@SOAPBinding(style = Style.DOCUMENT)
public interface PesananWS {
    @WebMethod public List<pesananModel> getPesananByKurir(int id_kurir);
}
