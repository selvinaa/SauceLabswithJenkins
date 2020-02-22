package Database;

import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class columnDBTest {
    /**
     * QUERY THE DETAILS FOR EMPLOYEES WHOS ID IS EITHER (150 OR 160).
     */

    String dbURL = "jdbc:oracle:thin:@ec2-18-207-164-54.compute-1.amazonaws.com";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void detailsEmployeeId150Or160() throws SQLException {
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String query = "select * from employees where employee_id in (150,160)";// GETTING THE QUERY OF DATABASE
        ResultSet resultSet = statement.executeQuery(query);
        DatabaseMetaData dbMetadata = connection.getMetaData();
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        List<Map<String, Object>> mapList = new ArrayList<>();
        ResultSetMetaData resultSetMetaData1 = resultSet.getMetaData();

        int colCount = resultSetMetaData.getColumnCount();
        while(resultSet.next()){
            Map<String , Object> rowMap = new HashMap<>();
            for(int i = 1; i <= colCount; i++) {
                rowMap.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));//GETTING KEY(COLUMN)AND VALUES (OBJET)

            }
            mapList.add(rowMap);

        }

        for (Map<String, Object> a : mapList) {
            System.out.println(a);// (CASE SENSITIVE OT GET VALUES OF)) THIS WAY WE GET SOME COLUMN

            statement.close()
            ;connection.close()
            ;resultSet.close();
        }


    }
    
}

