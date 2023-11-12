package models;

public class pesananModel {
    private int id_kurir;
    private String alamat;
    private String nama_penerima;
    private Status status;
    private String keterangan;

    public pesananModel(){
        this.id_kurir = 0;
        this.alamat = "";
        this.nama_penerima = "";
        this.status = Status.searching_courier;
        this.keterangan = "";
    }

    public pesananModel(int id_kurir, String alamat, String nama_penerima, Status status, String keterangan){
        this.id_kurir = id_kurir;
        this.alamat = alamat;
        this.nama_penerima = nama_penerima;
        this.status = status;
        this.keterangan = keterangan;
    }
}
