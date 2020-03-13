package api;


import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class BasicAPI {

@Test
    public void apiBasic_1(){
    // THIS IS ONE WAY TO SET UP
            given()
            .when()
            .get("http://dummy/restapiexample.com")
            .then().statusCode(200);
}

@Test// THIS IS SECOND WAY TO SET UP
    public void basic_GET(){//http://dummy.restapiexample.com
    RestAssured.baseURI = "http://dummy.restapiexample.com";
    RequestSpecification requestSpecification = RestAssured.given();
    Response response = requestSpecification.get("/api/v1/employees");
    //System.out.println(response.asString());
   // System.out.println(response.headers().asList());
    ResponseBody body = response.getBody();
    //System.out.println(response.getContentType());
    //System.out.println(response.getBody().asString());
    JsonPath path = response.jsonPath();
    String status = path.get("status");
    // BECAUSE IS ONLY ONE ITEM IS STRING
    //System.out.println("status real " + status);
    List<Map<String, String>> data = path.getList("data");
//    for(Map<String, String> map : data){
//        map.forEach((k, v) -> System.out.println(k + "--" + v)); // Lambda
//    }

   String employee_name = path.getString("data.employee_name");// we can get all names by changing data
    //System.out.println(employee_name);

    String lastEmployee = path.getString("data[-1].employee_name");// we can get all names by changing data
    //System.out.println(lastEmployee);// getting last one, or last 2 = -2

    String employee_Age63 = path.getString("data.findAll {data -> data.employee_age = 63}");// we can get all names by changing data
    //System.out.println(employee_Age63);

}
@Test
    public void getMamalsFromSpecies(){
    RestAssured.baseURI = "https://swapi.co";
    RequestSpecification requestSpecification = RestAssured.given();
    Response response = RestAssured.get("/api/species");
    //System.out.println(response.asString());

   // ResponseBody body = response.getBody();
    // looking for specific name or atribute


    // is like xpath for Json
    JsonPath path = response.jsonPath();
    //String status = path.get("status");// BECAUSE IS ONLY ONE ITEM IS STRING
    System.out.println(path.getString("results.findAll {results -> results.classification = 'mammal'}"));


    }
}