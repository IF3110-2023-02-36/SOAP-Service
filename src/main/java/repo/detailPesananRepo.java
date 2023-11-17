package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntBinaryOperator;

import models.detailPesananModel;

public class detailPesananRepo extends repository{
    private Connection conn;

    public detailPesananRepo(){
        super();
        this.conn = this.getDatabase().getConnection();
    }

    public List<detailPesananModel> getDetailPesanan(int id_pesanan) throws SQLException{
        String query = "SELECT * FROM detail_pesanan WHERE id_pesanan = ?";
        ArrayList<detailPesananModel> listDetailPesanan = new ArrayList<>();

        try {
            PreparedStatement getDetailPesanan = this.conn.prepareStatement(query);
            getDetailPesanan.setString(1, Integer.toString(id_pesanan));
            ResultSet resultSet = getDetailPesanan.executeQuery();

            if (!resultSet.isBeforeFirst()){
                return listDetailPesanan;
            }

            while(resultSet.next()){
                int idPesanan = resultSet.getInt("id_pesanan");
                String namaProduk = resultSet.getString("nama_produk");
                int quantity = resultSet.getInt("quantity");
                int harga = resultSet.getInt("harga");

                detailPesananModel detailPesanan = new detailPesananModel(idPesanan, namaProduk, quantity, harga);
                listDetailPesanan.add(detailPesanan);
            }
            return listDetailPesanan;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String addDetailPesanan(ArrayList<detailPesananModel> detailPesanan){
        String query = "INSERT INTO detail_pesanan(id_pesanan, nama_produk, quantity, harga) VALUES";
        for(int i = 0; i < detailPesanan.size(); i++){
            String temp;
            if(i == 0){
                temp = "(?, ?, ?, ?)";
            }else{
                temp = ", (?, ?, ?, ?)";
            }

            query += temp;
        }
        query +=";";

        //binding
        try{
            int paramIndex = 1;
            PreparedStatement addDetailPesanan = this.conn.prepareStatement(query);
            for(int i = 0; i < detailPesanan.size(); i++){
                addDetailPesanan.setString(paramIndex, Integer.toString(detailPesanan.get(i).getId_pesanan()));
                paramIndex++;
                addDetailPesanan.setString(paramIndex, detailPesanan.get(i).getNama_product());
                paramIndex++;
                addDetailPesanan.setString(paramIndex, Integer.toString(detailPesanan.get(i).getQuantity()));
                paramIndex++;
                addDetailPesanan.setString(paramIndex, Integer.toString(detailPesanan.get(i).getHarga()));
                paramIndex++;
            }

            addDetailPesanan.execute();
            addDetailPesanan.close();

            String result = "succes";
            return result;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "gagal menambah detail";
        }
        
    }

    
}
