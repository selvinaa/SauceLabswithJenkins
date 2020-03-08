package api;




import org.junit.Test;

import java.sql.*;

public class dbSet {


    @Test
    public  void setUp() throws SQLException {

        String url = "jdbc:mysql://db4free.net:3306/techleadacademy?user=techlead&password=students";

        Connection connection = DriverManager.getConnection(url);
        // SCROLL UP AND DOWN AND ONLY READ
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from teachers");

        // wil get the actual count of rows from table teaches
        int results = resultSet.getRow();
        //will get the resu;t of qurey from first row
        resultSet.beforeFirst();
        //will start from the next row and so on
        while (resultSet.next()){
            System.out.println(resultSet.getString(1));
        }




    }
}
