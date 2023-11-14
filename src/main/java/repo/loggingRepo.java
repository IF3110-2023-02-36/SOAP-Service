package repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import models.loggingModel;

public class loggingRepo extends repository {
    private Connection conn;

    public loggingRepo(){
        super();
        this.conn = this.getDatabase().getConnection();
    }

    public void addLogging(loggingModel log) throws SQLException {
        
        String query = "INSERT INTO logging (ip, endpoint, method_accessed, caller) VALUES (?, ?, ?, ?);";

        try{
            PreparedStatement addLogging = this.conn.prepareStatement(query);
            addLogging.setString(1, log.getIp());
            addLogging.setString(2, log.getEndpoint());
            addLogging.setString(3, log.getMethodAccesed());
            addLogging.setString(4, log.getCaller());

            addLogging.execute();
            addLogging.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("add logging");
    }
}
