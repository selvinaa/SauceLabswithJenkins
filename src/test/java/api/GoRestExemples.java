package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class GoRestExemples {
      // key
      //j5rvaYpjhoyJszZf8cvdI89QSHvg9V1ARm4H = Bearer token
      //
//    RequestSpecification requestSpecification = RestAssured.given();
//    Response response = RequestSpecification.("/public-api/users");
    Header token = new Header("Authorization", "Bearer j5rvaYpjhoyJszZf8cvdI89QSHvg9V1ARm4H");//Authorizations


    @Before
    public void setUp(){
        RestAssured.baseURI = "http://gorest.co.in/";
        RequestSpecification requestSpecification = RestAssured.given();
        //response = requestSpecification.get("/public-api/user");// to get the sources

    }
    @Test
    public void validateIfContains(){
        Response response = given()
                .header(token)
                .when()
                .get("https://gorest.co.in/public-api/users");// url to work on
        response.then()
                .statusCode(200)
                .body(containsString("totalCount"));


    }

    // +++++++++++********************++++++++++++++GET  REGUEST INFORMATION
    @Test
    public void getUsingUserId(){
        RestAssured.baseURI = "https://gorest.co.in/";
        Response response = RestAssured.given()
                .header(token)
                .get("/public-api/users/2484");// resourse
        response.prettyPrint();


    }

    @Test
    public void getUsingUserIdselv(){
        RestAssured.baseURI = "https://gorest.co.in/";
        Response response = RestAssured.given()
                .header(token)
                .get("/public-api/users/60");// resourse
        response.prettyPrint();


    }
    @Test
    public void usingParameter(){
        RestAssured.baseURI = "https://gorest.co.in/public-api";
        Response response = RestAssured.given()
                .header(token)
                .when()
                .queryParam("first_name", "Antone")// to find specific values
                .get("/users");

        String  storing = response.prettyPrint();// format to print
        //storing.toUpperCase();

    }

    @Test
    public void usingQueryParameter(){
        RestAssured.baseURI = "https://gorest.co.in/public-api";
        Response response = RestAssured.given()
                .header(token)
                .when()
                .queryParam("gender", "male")// to find specific values
                .get("/users");

        response.prettyPrint();// format to print
        //https://gorest.co.in/public-api/user?gender=male


    }
    @Test
    public void usingPathParameter(){
        RestAssured.baseURI = "https://gorest.co.in/public-api";
        Response response = RestAssured.given()
                .header(token)
                .when()
                .pathParam("id", "3080")// to find specific values
                .get("/users/{id}");
        response.prettyPrint();// format to print
        //https://gorest.co.in/public-api/user/300

    }
    // ************************************************POST REQUEST
    @Test
    public void createUser(){// creatting object // Serialization from string java to Json format
        String body = "{\n" +
                "\"first_name\":\"Meme\",\n" +
                "\"last_name\":\"Lope\",\n" +
                "\"gender\":\"male\",\n" +
                "\"dob\":\"1998-01-01\",\n" +
                "\"email\":\"memelope@chapter.one\",\n" +
                "\"status\":\"active\"\n" +
                "}";

        RestAssured.baseURI = "https://gorest.co.in/public-api";
        Response response = RestAssured.given()
                .header(token)
                .contentType(ContentType.JSON)
                .body(body)
                .post("/users");//Meme Lope= memelope id 2532
        //https://gorest.co.in/public-api/users/2532
        System.out.println("status code"+ response.getStatusCode());
        response.prettyPrint();
    }

    @Test
    public void createUser2(){// creatting object

        Map<String, String> map = new HashMap<>();
        map.put("first_name", "Rodolf");
        map.put("last_name", "Ramo");
        map.put("gender", "male");
        map.put("dob", "2000-02-01");
        map.put("email", "rodolframo@yahoo.com");
        map.put("status", "inactive");


        RestAssured.baseURI = "https://gorest.co.in/public-api";
        Response response = RestAssured.given()
                .header(token)
                .contentType(ContentType.JSON)
                .body(map)
                .post("/users");//Meme Lope= memelope id 2532
        //https://gorest.co.in/public-api/users/2532
        System.out.println("status code"+ response.getStatusCode());
        response.prettyPrint();
    }
    //***********************************************--UPDATE REQUEST
    @Test
    public void updateUser(){// creatting object
        Map<String, String> map = new HashMap<>();
        map.put("first_name", "Junior");
        map.put("last_name", "Ramo");
        map.put("gender", "male");
        map.put("dob", "2000-02-01");
        map.put("email", "Juniorramo@yahoo.com");
        map.put("status", "inactive");

        RestAssured.baseURI = "https://gorest.co.in/public-api";
        Response response = RestAssured.given()
                .header(token)
                .contentType(ContentType.JSON)
                .body(map)
                .put("/users/2532");
        //https://gorest.co.in/public-api/users/2532
        System.out.println("status code"+ response.getStatusCode());
        response.prettyPrint();
    }
//***********************************************--DELETE REQUEST
    @Test
    public void deleteUser(){// creatting object

        RestAssured.baseURI = "https://gorest.co.in/public-api";
        Response response = RestAssured.given()
                .header(token)
                .delete("/users/2532");
        //https://gorest.co.in/public-api/users/2532
        System.out.println("status code"+ response.getStatusCode());
        response.prettyPrint();
    }

    //*******************************************Desirialiezed JSON into POJO
    @Test
    public void deserializeJson(){
        //Meme Lope= memelope id 2532
        // USING POJO CLASS USER_POJO
        RestAssured.baseURI = "https://gorest.co.in/";
        given()
                .contentType(ContentType.JSON)
                .header(token)
                .get("/public-api/users/300")
                .then()
                .statusCode(200)
                .body(containsString("OK. Everything worked as expected."))
                .log().body();


//        User_POJO user1 = response.jsonPath().getObject("result", User_POJO.class);
//        System.out.println(user1.getFirst_name());
//        System.out.println(user1.getEmail());

    }




}
