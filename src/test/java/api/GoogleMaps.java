package api;

import io.cucumber.java.it.Ma;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.rootPath;
import static org.hamcrest.Matchers.*;

public class GoogleMaps {
    // GOOGLE KEY AIzaSyBavBRSOLuNiJPo6bnjZtzcfvMuVnJwqRk


    @Test
    public void getGoogleMaps() {
        //https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=38.8442399, -77.2945064&radius=200&food=spanish &key=AIzaSyBavBRSOLuNiJPo6bnjZtzcfvMuVnJwqRk
        RestAssured.baseURI = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
        // RequestSpecification requestSpecification = RestAssured.given();
        given()
                .queryParam("location", "38.8442399,-77.2945064")
                .queryParam("radius", "5000")
                .queryParam("type", "restaurant")
                .queryParam("keyword", "american")
                .queryParam("key", "AIzaSyBavBRSOLuNiJPo6bnjZtzcfvMuVnJwqRk")
                .when()
                .get()
                .then()
                .statusCode(200)
                .body(containsString("Fairfax"))
                .body(containsString("Hooters"));

        }
    @Test
    public void getGoogleMaps2() {
        //Header token = new Header("Authorization", "Bearer raWAI4UzsatXQlahC8lEczF6LtaUqfjVkEvi");

            RestAssured.baseURI = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
            Response response = given()
                    .queryParam("location", "38.844564,-77.2943731")
                    .queryParam("radius", "5000")
                    .queryParams("type", "restaurant")
                    .queryParam("keyword", "american")
                    .queryParam("key", "AIzaSyBmD8Ckoo9j1-VN2O93BY5MlTxSKxp6RAg")
                    .when()
                    .get();
            JsonPath path = response.jsonPath();
//        String [] a = path.getString("results.name").split(",");
//        for (String s : a){
//            System.out.println(s);
//        }
            List<Map<String, Object>> a = path.get("results.findAll {results -> results.name == 'Hooters'}");
            for (Map<String, Object> m : a) {
                if (m.containsKey("opening_hours"))
                    System.out.println(m.get("name"));
                Map<String, Boolean> map = (Map<String, Boolean>) m.get("opening_hours");
                System.out.println(map.get("open_now"));
            }
        }


    }



