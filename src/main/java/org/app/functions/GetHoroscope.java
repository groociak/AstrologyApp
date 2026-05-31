package org.app.functions;


import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;

public class GetHoroscope {
    public GetHoroscope() {}
    public static String horoscopeData(String userSign, String choice) {
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://freehoroscopeapi.com/api/v1/get-horoscope/"+choice+"?sign="+userSign).asJson();

            var horoscopeData = response.getBody()
                    .getObject()
                    .getJSONObject("data");

            return horoscopeData.getString("horoscope");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Failed to load horoscope.";
    }
}
