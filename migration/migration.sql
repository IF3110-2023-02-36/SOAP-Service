DROP TABLE IF EXISTS logging;
DROP TABLE IF EXISTS pesanan;
DROP TABLE IF EXISTS detail_pesanan;



CREATE TABLE pesanan (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_kurir INT DEFAULT 0,
    alamat VARCHAR(255),
    nama_penerima VARCHAR(255),
    status ENUM('searching_courier', 'pickup', 'transit', 'delivered'),
    keterangan TEXT
);


CREATE TABLE detail_pesanan (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_pesanan INT,
    nama_produk VARCHAR(255),
    quantity INT,
    FOREIGN KEY (id_pesanan) REFERENCES pesanan(id)
);


