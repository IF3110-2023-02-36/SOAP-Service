package models;


import java.util.ArrayList;

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

    public ArrayList<detailPesananModel> convertFromString(int id_pesanan, String nama_product, String quantity){
        ArrayList<Integer> list_id_pesanan = new ArrayList<>();
        ArrayList<String> list_nama_product = new ArrayList<>();
        ArrayList<Integer> list_quantity = new ArrayList<>();


        String[] nama_product_items = nama_product.split(",");

        for(String item: nama_product_items){
            list_nama_product.add(item);
        }

        String[] quantity_items = quantity.split(",");
        for(String item: quantity_items){
            list_quantity.add(Integer.parseInt(item.trim()));
        }

        ArrayList<detailPesananModel> detailPesanan = new ArrayList<>();
        for(int i = 0; i < list_quantity.size(); i++){
            detailPesananModel temp = new detailPesananModel(id_pesanan, list_nama_product.get(i), list_quantity.get(i));
            detailPesanan.add(temp);
        }

        return detailPesanan;
    }
}
