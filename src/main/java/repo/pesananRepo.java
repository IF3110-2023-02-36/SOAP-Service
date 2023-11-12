package repo;

import java.sql.Connection;

import models.pesananModel;

public class pesananRepo extends repository{
    private Connection conn;

    public pesananRepo(){
        super();
        this.conn = this.getDatabase().getConnection();
    }

    // public pesananModel getPesananById(int id){

    // }
}
