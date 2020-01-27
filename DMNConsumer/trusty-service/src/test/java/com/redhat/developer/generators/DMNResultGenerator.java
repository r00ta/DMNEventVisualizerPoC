package com.redhat.developer.generators;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redhat.developer.domain.DMNResult;

public class DMNResultGenerator {
    private final static String result = "{\n" +
            "      \"messages\" : [ ],\n" +
            "      \"model-namespace\" : \"https://github.com/kiegroup/drools/kie-dmn/_A4BCA8B8-CF08-433F-93B2-A2598F19ECFF\",\n" +
            "      \"model-name\" : \"Traffic Violation\",\n" +
            "      \"decision-name\" : [ ],\n" +
            "      \"dmn-context\" : {\n" +
            "        \"Violation\" : {\n" +
            "          \"Type\" : \"speed\",\n" +
            "          \"Speed Limit\" : 100,\n" +
            "          \"Actual Speed\" : 135\n" +
            "        },\n" +
            "        \"Driver\" : {\n" +
            "          \"Points\" : 15\n" +
            "        },\n" +
            "        \"Fine\" : {\n" +
            "          \"Points\" : 7,\n" +
            "          \"Amount\" : 1000\n" +
            "        },\n" +
            "        \"Should the driver be suspended?\" : \"Yes\"\n" +
            "      },\n" +
            "      \"decision-results\" : {\n" +
            "        \"_4055D956-1C47-479C-B3F4-BAEB61F1C929\" : {\n" +
            "          \"messages\" : [ ],\n" +
            "          \"decision-id\" : \"_4055D956-1C47-479C-B3F4-BAEB61F1C929\",\n" +
            "          \"decision-name\" : \"Fine\",\n" +
            "          \"result\" : {\n" +
            "            \"Points\" : 7,\n" +
            "            \"Amount\" : 1000\n" +
            "          },\n" +
            "          \"status\" : \"SUCCEEDED\"\n" +
            "        },\n" +
            "        \"_8A408366-D8E9-4626-ABF3-5F69AA01F880\" : {\n" +
            "          \"messages\" : [ ],\n" +
            "          \"decision-id\" : \"_8A408366-D8E9-4626-ABF3-5F69AA01F880\",\n" +
            "          \"decision-name\" : \"Should the driver be suspended?\",\n" +
            "          \"result\" : \"Yes\",\n" +
            "          \"status\" : \"SUCCEEDED\"\n" +
            "        }\n" +
            "      }\n" +
            "    \n" +
            "  }\n";

    public static DMNResult GetDMNResult(){
        ObjectMapper objectMapper = new ObjectMapper();
        DMNResult obj = null;
        try {
            obj = objectMapper.readValue(result, DMNResult.class);
        } catch (JsonProcessingException e) {
            System.out.println("Got a non valid event");
            e.printStackTrace();
        }
        return obj;
    }
}
