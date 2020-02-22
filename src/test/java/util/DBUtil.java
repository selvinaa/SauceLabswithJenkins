package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtil {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void establishDBConnection(DBType dbUtil) throws SQLException {
        switch (dbUtil) {
            case ORACLE:
                connection = DriverManager.getConnection(ConfigReader.readProperty("dbUrl"), ConfigReader.readProperty("dbUserName"),
                        ConfigReader.readProperty("dbPassword"));
                break;

            case MYSQL:
                connection = null;

                //connection = DriverManager.getConnection(ConfigReader.readProperty("dbUrl"));
            case MONGODB:
                connection = null;

            default:
                connection = null;


        }


    }

    public static int rowsCount(String sql) throws SQLException {
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(sql);// as paramenter form method
        resultSet.last();
        return resultSet.getRow();
    }
    public static List<Map<String, Object>> runSQLQuery(String query) throws SQLException {
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        resultSet = statement.executeQuery(query);
        List<Map<String, Object>> list = new ArrayList<>();
        ResultSetMetaData rsMdata = resultSet.getMetaData();

        int colCount = rsMdata.getColumnCount();

        while(resultSet.next()){
            Map<String, Object> rowMap = new HashMap<>();
            for(int col = 1; col <= colCount; col++){
                rowMap.put(rsMdata.getColumnName(col), resultSet.getObject(col));// getting key and values from map
            }
            list.add(rowMap);
        }
        return list;

    }

    public static void closeConnections(){
        try {
            if(resultSet != null){
                resultSet.close();
            }
            if(resultSet != null){
                statement.close();
            }
            if(resultSet != null){
                connection.close();
            }
        }catch (Exception e){
            e.getStackTrace();
        }
    }



}



