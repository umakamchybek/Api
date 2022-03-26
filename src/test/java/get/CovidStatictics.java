package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class CovidStatictics {
    @Test
    public void allCountryCount(){

        Response response= RestAssured.given().header("Accept","application/json")
                .when()
                .get("https://corona.lmao.ninja/v2/all")
                .then().statusCode(200).extract().response();

        Map<String, Object> deserializedResponse=response.as(new TypeRef<Map<String, Object>>() {
        });
        int affectedcountries=(int)deserializedResponse.get("affectedCountries");
        Assert.assertEquals(227,affectedcountries);
    }
}
