package utils;

public class PayloadUtils {

    public static String getPetPayLoad(int id, String petName, String petStatus){
        String petPayLoad="{\n" +
                "  \"id\": "+id+",\n" +
                "  \"category\": {\n" +
                "    \"id\": 123,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \""+petName+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \""+petStatus+"\"\n" +
                "}";
                return petPayLoad;
    }

    public static String getSlackMessagePayload(String message){
        String slackPayload="{\n" +
                "    \"channel\":\"C0397J4JY3T\",\n" +
                "    \"text\":\"Uma: "+message+"\"\n" +
                "}";
        return slackPayload;
    }
}
