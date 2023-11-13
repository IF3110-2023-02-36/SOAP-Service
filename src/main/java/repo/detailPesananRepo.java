package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
                return null;
            }

            while(resultSet.next()){
                int idPesanan = resultSet.getInt("id_pesanan");
                String namaProduk = resultSet.getString("nama_produk");
                int quantity = resultSet.getInt("quantity");

                detailPesananModel detailPesanan = new detailPesananModel(idPesanan, namaProduk, quantity);
                listDetailPesanan.add(detailPesanan);
            }
            System.out.println(listDetailPesanan);
            return listDetailPesanan;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
