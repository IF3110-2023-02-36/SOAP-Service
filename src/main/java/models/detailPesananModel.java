package models;


import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@XmlRootElement
public class detailPesananModel {
    private int id_pesanan;
    private String nama_product;
    private int quantity;
}
