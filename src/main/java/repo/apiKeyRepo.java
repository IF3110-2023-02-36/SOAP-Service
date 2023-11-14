package repo;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.apiKeyModel;


public class apiKeyRepo extends repository{
    private Connection conn;

    public apiKeyRepo(){
        super();
        this.conn = this.db.getConnection();
    }

    public String getClientByApiKey(String api_key){
        String query = "SELECT client FROM api_key WHERE api_key = ?;";

        try{
            PreparedStatement getClient = this.conn.prepareStatement(query);
            getClient.setString(1, api_key);
            ResultSet resultSet = getClient.executeQuery();

            if (resultSet.next()) {
                String client = resultSet.getString("client");
                return client;
            } else {
                return null;
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
