package get;

import io.restassured.common.mapper.TypeRef;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class DeserializedPokemon {
    @Test
    public void pokemonGet(){
        Response response = given().when().get("https://pokeapi.co/api/v2/pokemon")
                .then().extract().response();
        try{
        Assert.assertTrue("Status code is not successful",response.statusCode()==200);}
        catch(AssertionError error){
            error.printStackTrace();
        }
        Map<String,Object> pokemonResponce= response.as(new TypeRef<Map<String, Object>>() {
        });
        //System.out.println(pokemonResponce);

        List<Map<String,Object>>results = (List<Map<String,Object>>)pokemonResponce.get("results");

        boolean isThereBulbasaur=false;
        for(Map<String,Object> result:results){
            if(result.get("name").toString().equals("bulbasaur")){
                isThereBulbasaur=true;
                break;
            }
        }
       Assert.assertTrue("There is no bulbasaur", isThereBulbasaur);



    }
}
