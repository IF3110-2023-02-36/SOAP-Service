package models;


import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@XmlRootElement
public class pesananModel {
    private int id;
    private int id_kurir;
    private int id_pemesan;
    private String alamat;
    private String nama_penerima;
    private String status;
    private String keterangan;
    private int harga;
    private int biaya_pengiriman;
}
