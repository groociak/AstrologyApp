package org.app.functions;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import kong.unirest.core.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class GetMoonPhase {
    static String now = LocalDateTime.now()
            .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    public static JSONObject moonPhaseData(){
        try{
            //api request, api key under environment var "moonApiKey"
            HttpResponse<JsonNode> response = Unirest.get(
                            "https://api.freeastroapi.com/api/v1/moon/phase")
                    .queryString("date", now)
                    .queryString("style_moon_color", "#E0E0E0")
                    .queryString("style_shadow_color", "#1A1A1A")
                    .queryString("include_visuals", true)
                    .queryString("include_zodiac", true)
                    .queryString("include_rise_set", false)
                    .queryString("include_traditional_moon", false)
                    .queryString("include_interpretation", true)
                    .header("x-api-key", System.getenv("MOONAPIKEY"))
                    .header("Content-Type", "application/json")
                    .asJson();

            //checking if we get a response
            if (response.getStatus() != 200) {
                throw new RuntimeException("API error: " + response.getStatus());
            }
            //getting necessary objects and strings;
            JSONObject body = response.getBody().getObject();
            String phaseName = body.getJSONObject("phase").getString("name");
            JSONObject interpretation = body.getJSONObject("interpretation");
            String interpretationText = interpretation.getString("body");
            String zodiacSign = body.getJSONObject("zodiac").getString("sign");
            String svg = body.getJSONObject("moon_visual").getString("svg");

            //Returning only necessary values
            return new JSONObject()
                    .put("phase_name", phaseName)
                    .put("interpretation", interpretationText)
                    .put("zodiac_sign", zodiacSign)
                    .put("svg", svg);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
