package get;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import pojo.PokemonPojo;
import java.util.List;
import java.util.Map;

public class Pokemon {

//    @Before
//    public void setup(){
//        RestAssured.baseURI="https://pokeapi.co";
//        RestAssured.basePath="api/v2/pokemon";
//    }


    @Test
    public void pokemonTest() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when().get("https://pokeapi.co/api/v2/pokemon")
                .then().statusCode(200).extract().response();

        PokemonPojo deserializedResp = response.as(PokemonPojo.class);
        Assert.assertEquals(1126, deserializedResp.getCount());
    }

    @Test
    public void pokemonTest2() {
        Response response = RestAssured.given()
                .header("Accept", "application/json")
                .when().get("https://pokeapi.co/api/v2/pokemon")
                .then().statusCode(200).extract().response();

        JsonPath jsonPath = response.jsonPath();
       String nextUrl= jsonPath.getString("next");
        System.out.println(nextUrl);


       String firstPokemonName= jsonPath.getString("results[0].name");
        System.out.println(firstPokemonName);

        List<Map<String, String>> resultList=jsonPath.getList("results");
        //System.out.println(resultList);
        for(Map<String, String> pokemon: resultList){
            System.out.println(pokemon.get("name"));

        }
    }
    @Test
    public void PokemonTest3(){
        RestAssured.given()
                .header("Accept", "application/json").log().all()
                .when().get("https://pokeapi.co/api/v2/pokemon")

                .then().statusCode(200).body("count", Matchers.equalTo(1126))
        .and()
        .body("next",Matchers.is("https://pokeapi.co/api/v2/pokemon?offset=20&limit=20"))
        .log().body();



    }
}
