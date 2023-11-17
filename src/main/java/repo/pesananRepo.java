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

    public pesananModel getPesananByIdPesanan(int id_pesanan) throws SQLException{
        String query = "SELECT * FROM pesanan WHERE id = ?;";
        
        try {
            PreparedStatement stmt = this.conn.prepareStatement(query);
            stmt.setString(1, Integer.toString(id_pesanan));
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                int idKurir = rs.getInt("id_kurir");
                String namaKurir = rs.getString("nama_kurir");
                int idPemesan = rs.getInt("id_pemesan");
                String alamat = rs.getString("alamat");
                String namaPenerima = rs.getString("nama_penerima");
                String status = rs.getString("status");
                String keterangan = rs.getString("keterangan");
                int harga = rs.getInt("harga");
                int biaya_pengiriman = rs.getInt("biaya_pengiriman");


                pesananModel pesanan = new pesananModel(id, idKurir, namaKurir, idPemesan, alamat, namaPenerima, status, keterangan, harga, biaya_pengiriman);

                return pesanan;
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<pesananModel> getPesananByKurir(int id_kurir) throws SQLException{
        String query = "SELECT * FROM pesanan WHERE id_kurir = ? and status != ? ;";
        ArrayList<pesananModel> listPesanan = new ArrayList<>();

        try {
            PreparedStatement getPesananByKurir = this.conn.prepareStatement(query);
            getPesananByKurir.setString(1, Integer.toString(id_kurir));
            getPesananByKurir.setString(2, "delivered");
            
            ResultSet resultSet = getPesananByKurir.executeQuery();

            if (!resultSet.isBeforeFirst()){
                return listPesanan;
            }

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                int idKurir = resultSet.getInt("id_kurir");
                String namaKurir = resultSet.getString("nama_kurir");
                int idPemesan = resultSet.getInt("id_pemesan");
                String alamat = resultSet.getString("alamat");
                String namaPenerima = resultSet.getString("nama_penerima");
                String status = resultSet.getString("status");
                String keterangan = resultSet.getString("keterangan");
                int harga = resultSet.getInt("harga");
                int biaya_pengiriman = resultSet.getInt("biaya_pengiriman");


                pesananModel pesanan = new pesananModel(id, idKurir, namaKurir, idPemesan, alamat, namaPenerima, status, keterangan, harga, biaya_pengiriman);
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
        String query = "SELECT * FROM pesanan WHERE id_kurir = ?;";
        ArrayList<pesananModel> listPesanan = new ArrayList<>();

        try {
            PreparedStatement getPesananByKurir = this.conn.prepareStatement(query);
            getPesananByKurir.setString(1, Integer.toString(0));
            
            ResultSet resultSet = getPesananByKurir.executeQuery();

            if (!resultSet.isBeforeFirst()){
                return listPesanan;
            }

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                int idKurir = resultSet.getInt("id_kurir");
                String namaKurir = resultSet.getString("nama_kurir");
                int idPemesan = resultSet.getInt("id_pemesan");
                String alamat = resultSet.getString("alamat");
                String namaPenerima = resultSet.getString("nama_penerima");
                String status = resultSet.getString("status");
                String keterangan = resultSet.getString("keterangan");
                int harga = resultSet.getInt("harga");
                int biaya_pengiriman = resultSet.getInt("biaya_pengiriman");


                pesananModel pesanan = new pesananModel(id, idKurir, namaKurir, idPemesan, alamat, namaPenerima, status, keterangan, harga, biaya_pengiriman);
                listPesanan.add(pesanan);
            }
            // System.out.println(listPesanan);
            return listPesanan;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public String addPesanan(int idPemesan, String alamat, String nama_penerima, String keterangan, int harga, int biaya_pengiriman) throws SQLException{
        String query = "INSERT INTO pesanan (id_pemesan, alamat, nama_penerima, status, keterangan, harga, biaya_pengiriman) VALUES (?, ?, ?, ?, ?, ?, ?);";

        try{
            PreparedStatement addPesanan = this.conn.prepareStatement(query);
            addPesanan.setString(1, Integer.toString(idPemesan));
            addPesanan.setString(2, alamat);
            addPesanan.setString(3, nama_penerima);
            addPesanan.setString(4, "searching_courier");
            addPesanan.setString(5, keterangan);
            addPesanan.setString(6, Integer.toString(harga));
            addPesanan.setString(7, Integer.toString(biaya_pengiriman));
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

    public String ambilPesanan(int id_pesanan, int id_kurir, String nama_kurir) throws SQLException{
        try{
            Boolean pickable = this.validateAmbilPesanan(id_pesanan);
            System.out.println(pickable);
            if(!pickable){
                return "Tidak bisa mengambil pesanan ini";
            }
        }catch(Exception e){
            return "Tidak bisa mengambil pesanan ini";
        }

        String query = "UPDATE pesanan SET id_kurir = ?, status = ?, keterangan = ?, nama_kurir = ? WHERE pesanan.id = ?;";

        try{
            PreparedStatement ambilPesanan = this.conn.prepareStatement(query);
            ambilPesanan.setString(1, Integer.toString(id_kurir));
            ambilPesanan.setString(2, "pickup");
            ambilPesanan.setString(3, "udah dapet kurir gasn, ditunggu yak!");
            ambilPesanan.setString(4, nama_kurir);
            ambilPesanan.setString(5, Integer.toString(id_pesanan));
            

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

    public ArrayList<pesananModel> getPesananByIdUser(int userId){
        String query = "SELECT * FROM pesanan WHERE id_pemesan = ?";
        ArrayList<pesananModel> listPesanan = new ArrayList<>();

        try {
            PreparedStatement getPesananByKurir = this.conn.prepareStatement(query);
            getPesananByKurir.setString(1, Integer.toString(userId));
            ResultSet resultSet = getPesananByKurir.executeQuery();

            if (!resultSet.isBeforeFirst()){
                return listPesanan;
            }

            while(resultSet.next()){
                int id = resultSet.getInt("id");
                int idKurir = resultSet.getInt("id_kurir");
                String namaKurir = resultSet.getString("nama_kurir");
                int idPemesan = resultSet.getInt("id_pemesan");
                String alamat = resultSet.getString("alamat");
                String namaPenerima = resultSet.getString("nama_penerima");
                String status = resultSet.getString("status");
                String keterangan = resultSet.getString("keterangan");
                int harga = resultSet.getInt("harga");
                int biaya_pengiriman = resultSet.getInt("biaya_pengiriman");


                pesananModel pesanan = new pesananModel(id, idKurir, namaKurir, idPemesan, alamat, namaPenerima, status, keterangan, harga, biaya_pengiriman);
                listPesanan.add(pesanan);
            }
            return listPesanan;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
