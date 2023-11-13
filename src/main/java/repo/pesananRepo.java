package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.pesananModel;

public class pesananRepo extends repository{
    private Connection conn;

    public pesananRepo(){
        super();
        this.conn = this.getDatabase().getConnection();
    }

    public List<pesananModel> getPesananByKurir(int id_kurir) throws SQLException{
        String query = "SELECT * FROM pesanan WHERE id_kurir = ?";
        ArrayList<pesananModel> listPesanan = new ArrayList<>();

        try {
            PreparedStatement getPesananByKurir = this.conn.prepareStatement(query);
            getPesananByKurir.setString(1, Integer.toString(id_kurir));
            ResultSet resultSet = getPesananByKurir.executeQuery();

            if (!resultSet.isBeforeFirst()){
                return null;
            }

            while(resultSet.next()){
                int idKurir = resultSet.getInt("id_kurir");
                String alamat = resultSet.getString("alamat");
                String namaPenerima = resultSet.getString("nama_penerima");
                String status = resultSet.getString("status");
                String keterangan = resultSet.getString("keterangan");

                System.out.println(idKurir);
                System.out.println(alamat);
                System.out.println(namaPenerima);
                System.out.println(status);
                System.out.println(keterangan);

                pesananModel pesanan = new pesananModel(idKurir, alamat, namaPenerima, status, keterangan);
                listPesanan.add(pesanan);
            }
            // System.out.println(listPesanan);
            return listPesanan;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public List<pesananModel> getPesananNoKurir() throws SQLException{
        String query = "SELECT * FROM pesanan WHERE id_kurir = ?";
        ArrayList<pesananModel> listPesanan = new ArrayList<>();

        try {
            PreparedStatement getPesananByKurir = this.conn.prepareStatement(query);
            getPesananByKurir.setString(1, Integer.toString(0));
            ResultSet resultSet = getPesananByKurir.executeQuery();

            if (!resultSet.isBeforeFirst()){
                return null;
            }

            while(resultSet.next()){
                int idKurir = resultSet.getInt("id_kurir");
                String alamat = resultSet.getString("alamat");
                String namaPenerima = resultSet.getString("nama_penerima");
                String status = resultSet.getString("status");
                String keterangan = resultSet.getString("keterangan");

                System.out.println(idKurir);
                System.out.println(alamat);
                System.out.println(namaPenerima);
                System.out.println(status);
                System.out.println(keterangan);

                pesananModel pesanan = new pesananModel(idKurir, alamat, namaPenerima, status, keterangan);
                listPesanan.add(pesanan);
            }
            // System.out.println(listPesanan);
            return listPesanan;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String addPesanan(String alamat, String nama_penerima, String keterangan) throws SQLException{
        String query = "INSERT INTO pesanan (alamat, nama_penerima, status, keterangan) VALUES (?, ?, ?, ?);";

        try{
            PreparedStatement addPesanan = this.conn.prepareStatement(query);
            addPesanan.setString(1, alamat);
            addPesanan.setString(2, nama_penerima);
            addPesanan.setString(3, "searching_courier");
            addPesanan.setString(4, keterangan);
            addPesanan.execute();
            addPesanan.close();
            String result = "succes menambah pesanan";
            return result;
        }catch(Exception e){
            System.out.println(e.getMessage());
            String result = "gagal menambah pesanan";
            return result;
        }
    }
}
