package Database;

import util.DBConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mySQLDB {
    public static void main(String[] args) throws SQLException {
        String q = "select * from students";
        String [][] a = fetchDataFromDB(q);
        //System.out.println(fetchDataFromDB(q));
        System.out.println(a);
    }

    public static String [][] fetchDataFromDB(String query) throws SQLException {
        String url = "jdbc:mysql://db4free.net:3306/techleadacademy?user=techlead&password=students";
        //String url = "jdbc:mysql://db4free.net:3306/techleadacademy?user=techlead&password=students";
        DBConnectionManager dbInstance = DBConnectionManager.getInstance(url);
        Connection conn = dbInstance.getConn();
        Statement stat = conn.createStatement();
        ResultSet rs = stat.executeQuery(query);

        int columnCount = rs.getMetaData().getColumnCount();
        rs.last();// it will move to last row
        int rowCount= rs.getRow();
        rs.beforeFirst();// wiil get back to the first row

        String [][] results = new String[columnCount][rowCount];
        int i = 0;
        while (rs.next()){
            for(int j = 0; j < columnCount; j++){
                results[i][j] = rs.getString(j+1);
            }
            i = i + 1;
        }
        return results;
    }
}
