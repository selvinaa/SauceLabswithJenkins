package Database;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import util.ConfigReader;
import util.DBType;
import util.DBUtil;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UsingDBUtil extends DBUtil {

   @BeforeClass
    public  void setUp() throws SQLException {
        DBUtil.establishDBConnection(DBType.ORACLE);

    }
    @AfterClass
    public  void closeConnection(){
        DBUtil.closeConnections();
    }

    //Query select department_name from departments;

    @Test
    public  void test1() throws SQLException {

        List<Map<String, Object>> result = DBUtil.runSQLQuery("select department_name from departments");
        System.out.println(result);
        System.out.println(result.get(1));

        }

    @Test
    public void test2() throws SQLException {
       //String query = "select first_name, last_name from employees WHERE employee_id = 105";
        List<Map<String, Object>> result = DBUtil.runSQLQuery("select first_name, last_name from employees WHERE employee_id = 105");
        Assert.assertEquals(result.get(0).get("FIRST_NAME"),"David");
        Assert.assertEquals(result.get(0).get("LAST_NAME"),"Austin");


    }

    @Test
    public void test3() throws SQLException {
       List<Map<String,Object>> result = DBUtil.runSQLQuery("select country_id from countries where country_id like '%BR%'");
        Assert.assertEquals(result.get(0).get("COUNTRY_ID"),"BR");
    }

    }






