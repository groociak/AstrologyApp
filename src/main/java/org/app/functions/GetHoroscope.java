package org.app.functions;


import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;

public class GetHoroscope {
    private String date;
    private String sign;
    private String horoscope;

    public GetHoroscope(String userSign){
        try{
            HttpResponse<JsonNode> response = Unirest.get("https://freehoroscopeapi.com/api/v1/get-horoscope/daily?sign="+userSign).asJson();

            var horoscopeData = response.getBody()
                    .getObject()
                    .getJSONObject("data");

            this.date = horoscopeData.getString("date");
            this.sign = horoscopeData.getString("sign");
            this.horoscope = horoscopeData.getString("horoscope");


        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
