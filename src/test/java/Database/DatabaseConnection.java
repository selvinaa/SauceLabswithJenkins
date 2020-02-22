package Database;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.type.MapLikeType;
import org.junit.Test;
import util.ConfigReader;
import util.DBType;
import util.DBUtil;

import javax.crypto.spec.PSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatabaseConnection {
    //ec2-54-173-170-46.compute-1.amazonaws.com
    // String dbURL = "jdbc:oracle:thin:@ec2-54-173-170-46.compute-1.amazonaws.com:1521:xe";// path url database connect
//    String dbUsername = "hr";
//    String dbPassword = "hr";
    @Test
    public void dbConnectionJDBC() throws SQLException {
        //getConnection is an Interface
        //Statement is an Interface
        // ResultSEt is an Interface
        /**
         * dbConnectionJDBC will set a connection to oracle db
         * Connection takes dbURL, dbUsername, dbPassword as paramenters
         * Statement  will allow the to scroll
         * ResultSet will execute Query
         * Examples  in executeQuery: select * from employees
         * @trows SQLException
         * @Autor Selvin Asencio
         **/

        Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("select * from employees");

        resultSet.last();// looking for the last value
        int rowCount = resultSet.getRow();
        //int rowCount1 = resultSet.findColumn("FIRST_NAME");
        //int colCount = resultSet.findColumn("email");
        //System.out.println("Row count : " + rowCount);

        resultSet.first();
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1)
                    + "<-->" + resultSet.getString("first_name")
                    + resultSet.getString("last_name"));
        }
        resultSet.close();
        statement.close();
        con.close();

    }

    //ec2-18-207-164-54.compute-1.amazonaws.com
    /**
     * Write a query that will print hte cont of employees working in department_id 100
     */

    String dbURL = "jdbc:oracle:thin:@ec2-18-207-164-54.compute-1.amazonaws.com";
    String dbUsername = "hr";
    String dbPassword = "hr";

    @Test
    public void countOfEmployeeInDep100() throws SQLException {
        Connection con = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = st.executeQuery("select * from employees where department_id = 100");

        resultSet.first();// looking for the last value

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) +
                    "<-->" + resultSet.getString("first_name")
                    + resultSet.getString("last_name"));
        }
//        resultSets.close();
//        resultSets.close();
//        con.close();


    }

    @Test
    public void countTheFirst5() throws SQLException {
        Connection con1 = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement1 = con1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet1 = statement1.executeQuery("select * from employees where rownum <= 5");

        resultSet1.first();
        while (resultSet1.next()) {
            //GETTING DEPARTMENT_ID, FIRST_NAME AND LAST_NAME TO PRINT
            System.out.println(resultSet1.getString("department_id") +
                    "<-->" + resultSet1.getString("first_name")
                    + resultSet1.getString("last_name"));

        }

    }

    @Test
    public void dbMetadata() throws SQLException{
        Connection connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String query = "select employee_id, last_name,job_id, salary from employees";
        // GETTING ALL DATA FROM TABLE

        ResultSet resultSet = statement.executeQuery(query);

        DatabaseMetaData dbMetadata = connection.getMetaData();
        System.out.println("user : " + dbMetadata.getUserName());
        System.out.println("Database Type : " + dbMetadata.getDatabaseProductName());

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
        System.out.println("Columns count : " + resultSetMetaData.getColumnCount());
       // System.out.println(resultSetMetaData.getCatalogName(4) + " name of 4");
        for(int i = 1; i <= resultSetMetaData.getColumnCount(); i++){// give us the number of the columns in table
            System.out.println(i + "-->"+ resultSetMetaData.getColumnName(i));
        }
        List<Map<String, Object>> mapList = new ArrayList<>();// to have access to a Map inside of List and add it to.
        ResultSetMetaData resultSetMetaData1 = resultSet.getMetaData();// to get access to data table

        int colCount = resultSetMetaData.getColumnCount();
        while(resultSet.next()){
            Map<String , Object> rowMap = new HashMap<>();
            for(int col = 1; col <= colCount; col++) {
                rowMap.put(resultSetMetaData.getColumnName(col), resultSet.getObject(col));// getting keys and values of table

            }
            mapList.add(rowMap);// outside of loop adding Map into List Of Maps

            }

        for (Map<String, Object> map : mapList) {// Iterates Map and MapList
            System.out.println(map.get("LAST_NAME"));// (CASE SENSITIVE OT GET VALUES OF)) THIS WAY WE GET SOME COLUMN
            System.out.println(map.get("SALARY")); System.out.println(map.get("EMPLOYEE_ID"));

            statement.close();connection.close();resultSet.close();// always closes windows
}


        }


    }









