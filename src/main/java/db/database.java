package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class database {
    private Connection conn;

    public database() {
        try {
            String url = System.getenv("MYSQL_URL");
            String username = System.getenv("MYSQL_USERNAME");
            String password = System.getenv("MYSQL_PASSWORD");
            System.out.println("ini test");
            System.out.println(url);
            System.out.println(username);
            System.out.println(password);
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("ini test2")  ;
            this.conn = DriverManager.getConnection(url, username, password);
            System.out.println("Koneksi ke MySQL berhasil!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.err.println("Gagal terhubung ke MySQL");
        }
    }

    public Connection getConnection(){
        return this.conn;
    }
} 
