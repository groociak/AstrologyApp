package org.app.functions;

import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;

public class DrawTarot {
    private String name;
    private String type;
    private String suit;
    private String meaning_up;
    private String meaning_rev;

    public DrawTarot() {
        try {
            HttpResponse<JsonNode> response = Unirest.get("https://tarotapi.dev/api/v1/cards/random?n=1")
                    .asJson();

            var card = response.getBody()
                    .getObject()
                    .getJSONArray("cards")
                    .getJSONObject(0);

            this.name = card.getString("name");
            this.type = card.getString("type");
            this.suit = card.optString("suit");
            this.meaning_up = card.getString("meaning_up");
            this.meaning_rev = card.getString("meaning_rev");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public String getMeaning(int state){
        if(state==0){
            return meaning_up;
        }
        else{
            return "Reversed: " + meaning_rev;
        }
    }

    @Override
    public String toString() {
        return name;
    }

}
