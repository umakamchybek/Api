package post;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;
import sun.security.util.ManifestDigester;
import utils.PayloadUtils;

public class Pet {
String petName="simba";
String petStatus="missing";
int petId= 235689;
@Test
    public void createPetTest(){
    Response response = RestAssured.given()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .body(PayloadUtils.getPetPayLoad(petId,petName,petStatus))
            .when().post("https://petstore.swagger.io/v2/pet")
            .then().statusCode(200).extract().response();

    PetPojo parsedResponse=response.as(PetPojo.class);
    Assert.assertEquals(petId,parsedResponse.getId());
    Assert.assertEquals(petName,parsedResponse.getName());
    Assert.assertEquals(petStatus, parsedResponse.getStatus());


    /*
    add get https://petstore.swagger.io/v2/pet/235689
    validate name, id, status are still same
     */
    //1st method
//    Response response1 = RestAssured.given()
//            .header("Accept", "application/json")
//            .when().get("https://petstore.swagger.io/v2/pet/235689")
//            .then().statusCode(200).extract().response();
//
//    PetPojo parsedResponse1=response.as(PetPojo.class);
//    Assert.assertEquals("simba", parsedResponse1.getName());
//    Assert.assertEquals(235689, parsedResponse1.getId());
//    Assert.assertEquals("missing", parsedResponse1.getStatus());

    //second method
    RestAssured.given()
            .header("Accept", "application/json")
            .when().get("https://petstore.swagger.io/v2/pet/"+petId)
            .then().statusCode(200)
            .and()
            .body("id", Matchers.equalTo(petId))
            .and()
            .body("name", Matchers.is(petName))
            .body("status", Matchers.equalTo(petStatus))
            .body("category.id",Matchers.is(123));


}
}
