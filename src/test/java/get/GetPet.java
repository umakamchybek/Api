package get;


import io.restassured.RestAssured;
import org.junit.Test;

public class GetPet {
    @Test
    public void getPetTest(){
        RestAssured.given()
                .header("Accept","application/json")
                .when().get("https://petstore.swagger.io/v2/pet/775")
                .then().statusCode(200);

    }

    @Test
    public void getItunes(){
        RestAssured.given()
                //https://itunes.apple.com/search?limit=1&term=thebeatles
                .header("Accept","application/json")
                .param("limit",1)
                .param("term","thebeatles")
                .when()
                .get("https://itunes.apple.com/search")
                .then().statusCode(200);
    }

}















