package post;

import io.restassured.RestAssured;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import utils.PayloadUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Slack {
    public static final String APPLICATION_JSON="application/json";
    @Before
    public void setup(){
        RestAssured.baseURI = "https://slack.com";
        RestAssured.basePath = "api/chat.postMessage";
    }

    @Test
    public void slackMessageTest() {
        RestAssured.given()
                .accept("application/json")//instead of .header
                .contentType("application/json")
                .header("Authorization", "Bearer xoxb-2694972852931-3301004561938-5HbvEoX49duFra8t1Gmd8iyj")
                .body(PayloadUtils.getSlackMessagePayload("This is an automation version"))
        .when().post()
        .then().statusCode(200)
        .body("ok", Matchers.is(true));

    }

    @Test
    public void sendMessageTest(){
        Map<String,String> slackMessageMap=new HashMap<>();
        slackMessageMap.put("channel","C0397J4JY3T");
        slackMessageMap.put("text","Uma: serilizied version");
        RestAssured.given()
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .header("Authorization", "Bearer xoxb-2694972852931-3301004561938-5HbvEoX49duFra8t1Gmd8iyj")
                .body(slackMessageMap)
                .when().post()
                .then().statusCode(200)
                .and().body("ok", Matchers.equalTo(true));


    }
    @Test
    public void sendMessage2(){
        File slackMessageFile=new File("src\\test\\resources\\SlackMessage.json");


        RestAssured.given()
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
               // .header("Authorization", "Bearer xoxb-2694972852931-3301004561938-5HbvEoX49duFra8t1Gmd8iyj")
                .body(slackMessageFile)
        .when().post()
        .then().statusCode(200)
        .and().body("ok", Matchers.equalTo(true));
    }
}
