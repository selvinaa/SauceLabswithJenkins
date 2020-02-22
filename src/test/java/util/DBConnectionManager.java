package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {

    private static DBConnectionManager instance;
    private static Connection conn;
    private DBConnectionManager(String url){
        try {
            Class.forName("con.mysql.cj.jdbc.Driver");// IMPLEMENTING THE CLASS MYSQL
            this.conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn(){
        return this.conn;
    }

    public static DBConnectionManager getInstance(String url) throws SQLException {
        if(instance == null){
            instance = new DBConnectionManager(url);
        }else if (instance.getConn().isClosed()){
            instance = new DBConnectionManager(url);
        }
        return instance;
    }
}
