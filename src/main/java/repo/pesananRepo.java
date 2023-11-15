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


    public int getLastId(){
        String query = "SELECT id from pesanan order by id DESC limit 1;";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }else{
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
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

    public boolean validateAmbilPesanan(int id_pesanan) throws SQLException{
        String query = "SELECT id_kurir FROM pesanan WHERE id = ?";
        
        try{
            PreparedStatement validate = this.conn.prepareStatement(query);
            validate.setString(1, Integer.toString(id_pesanan));
            ResultSet resultSet = validate.executeQuery();

            if (resultSet.next()) {
                int id_kurir = resultSet.getInt("id_kurir");
                System.out.println(id_kurir);
                return (id_kurir == 0);
            } else {
                System.out.println("No rows found for id_pesanan: " + id_pesanan);
                return false;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String ambilPesanan(int id_pesanan, int id_kurir) throws SQLException{
        try{
            Boolean pickable = this.validateAmbilPesanan(id_pesanan);
            System.out.println(pickable);
            if(!pickable){
                return "Tidak bisa mengambil pesanan ini";
            }
        }catch(Exception e){
            return "Tidak bisa mengambil pesanan ini";
        }

        String query = "UPDATE pesanan SET id_kurir = ?, status = ? WHERE pesanan.id = ?;";

        try{
            PreparedStatement ambilPesanan = this.conn.prepareStatement(query);
            ambilPesanan.setString(1, Integer.toString(id_kurir));
            ambilPesanan.setString(2, "pickup");
            ambilPesanan.setString(3, Integer.toString(id_pesanan));

            ambilPesanan.executeUpdate();
            ambilPesanan.close();

            String result = "succes mengambil pesanan";
            return result;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
    }


    public Boolean validateUpdatePesanan(int id_pesanan, int id_kurir){
        String query = "SELECT id_kurir FROM pesanan WHERE id = ?";
        
        try{
            PreparedStatement validate = this.conn.prepareStatement(query);
            validate.setString(1, Integer.toString(id_pesanan));
            ResultSet resultSet = validate.executeQuery();

            if (resultSet.next()) {
                int kurir = resultSet.getInt("id_kurir");
                System.out.println(kurir);
                return (id_kurir == kurir);
            } else {
                System.out.println("No rows found for id_pesanan: " + id_pesanan);
                return false;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public String updatePesanan(int id_pesanan, int id_kurir, String status, String keterangan){
        String query = "UPDATE pesanan SET status = ?, keterangan = ? WHERE pesanan.id = ?;";

        try{
            Boolean pickable = this.validateUpdatePesanan(id_pesanan, id_kurir);
            System.out.println(pickable);
            if(!pickable){
                return "Tidak bisa mengubah pesanan ini";
            }
        }catch(Exception e){
            return "Tidak bisa mengubah pesanan ini";
        }

        try{
            PreparedStatement updatePesanan = this.conn.prepareStatement(query);
            updatePesanan.setString(1, status);
            updatePesanan.setString(2, keterangan);
            updatePesanan.setString(3, Integer.toString(id_pesanan));

            updatePesanan.executeUpdate();
            updatePesanan.close();

            String result = "succes mengedit pesanan";
            return result;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "error";
        }
    }
}
