package api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

import javax.swing.plaf.PanelUI;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class ClassTask {


    @Test
    public void getClassTask(){
        RestAssured.baseURI = "https://testapi.io";
       // RequestSpecification requestSpecification = RestAssured.given();
        Response response = given()
                .get("/api/BlueOcean/resource/StudentsList");// url to work on
        response.prettyPrint();


    }
    @Test
    public void postTask(){
        String bodyName = " {\"student_name\": \"Selv\",\n" +
                "            \"phone_number\": \"20101101010\",\n" +
                "            \"email\": \"selvAs@whitehouse.out\"\n}";

        RestAssured.baseURI = "https://testapi.io";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(bodyName)
                .post("/api/BlueOcean/resource/StudentsList");
        response.prettyPrint();
    }
    @Test
    public void getFirstNameTask(){// Francisco id 19  GET //  39 Selv
        RestAssured.baseURI = "https://testapi.io";
        Response response = given()
                .contentType(ContentType.JSON)
                .get("/api/BlueOcean/resource/StudentsList/19");
        response.prettyPrint();

    }
    @Test
    public void puttingFirstNameToTaskClass(){  // PUT RESQUEST
        String bodyName = " {\"student_name\": \"PACO\",\n" +
                "            \"phone_number\": \"20101101010\",\n" +
                "            \"email\": \"framorales@whitehouse.out\"\n}";

        RestAssured.baseURI = "https://testapi.io";
        Response response = given()
                .contentType(ContentType.JSON)
                .body(bodyName)
                .put("/api/BlueOcean/resource/StudentsList/19");
        response.prettyPrint();


    }



}
