DROP TABLE IF EXISTS logging;
DROP TABLE IF EXISTS pesanan;
DROP TABLE IF EXISTS detail_pesanan;



CREATE TABLE pesanan (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_kurir INT DEFAULT 0,
    id_pemesan INT,
    alamat VARCHAR(255),
    nama_penerima VARCHAR(255),
    status ENUM('searching_courier', 'pickup', 'transit', 'delivered'),
    keterangan TEXT,
    harga INT,
    biaya_pengiriman INT
);


CREATE TABLE detail_pesanan (
    id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    id_pesanan INT,
    nama_produk VARCHAR(255),
    quantity INT,
    harga INT,
    FOREIGN KEY (id_pesanan) REFERENCES pesanan(id)
);

CREATE TABLE logging (
    id INT AUTO_INCREMENT PRIMARY KEY,
    ip VARCHAR(255),
    timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    endpoint VARCHAR(255),
    method_accessed VARCHAR(255),
    caller VARCHAR(255)
);

CREATE TABLE api_key (
    id INT AUTO_INCREMENT PRIMARY KEY,
    api_key VARCHAR(255) UNIQUE,
    client VARCHAR(255) UNIQUE
);


