package org.app.functions;

import kong.unirest.core.json.JSONObject;
import org.app.panels.userPanel;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetBirthChart {
    public static String chartData(String date){

        String body = """
{
  "datetime_local": "%sT12:00:00",
  "timezone": "UTC",
  "location": {
    "lat": 40.7128,
    "lon": -74.0060,
    "elevation_m": 10
  },
  "house_system": "WHOLE"
}
""".formatted(date);

        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:8000/api/v1/natal"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public static String chartData(){
        return chartData(userPanel.date);
    }



    public static BufferedImage getChartSvg(){
        String body = """
{
  "chart_data":  %s ,
  "size": 500,
  "show_aspects": true
}
""".formatted(chartData());
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://127.0.0.1:8000/api/v1/svg"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = null;
        String responseBody = "";
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            responseBody = response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        JSONObject json = new JSONObject(responseBody);
        String svgString= json.getString("svg_content");
        return svgUtil.svgToImage(svgString,400,400);
    }
}
