package org.app.functions;


import kong.unirest.core.Unirest;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.json.JSONArray;
import kong.unirest.core.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.Set;

public class AstrologyCompatibilityService {

    private static final String BASE_URL = "http://127.0.0.1:8000/api/v1";


    public static String getSynastry(String date1, String date2) {
            String rawA = GetBirthChart.chartData(date1);
            String rawB = GetBirthChart.chartData(date2);
            JSONObject body = new JSONObject()
                    .put("chart_a", new JSONObject(rawA))
                    .put("chart_b", new JSONObject(rawB));


            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://127.0.0.1:8000/api/v1/synastry"))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();

            java.net.http.HttpResponse<String> response = null;
            try {
                response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
//                System.out.println(response.body());
//                System.out.println("^synastry");
                return response.body();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    public static String getComposite(String date1, String date2) {
            String rawA = GetBirthChart.chartData(date1);
            String rawB = GetBirthChart.chartData(date2);
            JSONObject body = new JSONObject()
                    .put("chart_a", new JSONObject(rawA))
                    .put("chart_b", new JSONObject(rawB));


            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://127.0.0.1:8000/api/v1/composite"))
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(body.toString()))
                    .build();

            java.net.http.HttpResponse<String> response = null;
            try {
                response = client.send(request, java.net.http.HttpResponse.BodyHandlers.ofString());
//                System.out.println(response.body());
//                System.out.println("^composite");
                return response.body();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    private static final Set<String> IMPORTANT_PLANETS = Set.of(
            "Sun",
            "Moon",
            "Mercury",
            "Venus",
            "Mars",
            "Jupiter",
            "Saturn"
    );

    private static double getPlanetWeight(String p1, String p2) {

        double weight = 1.0;

        if (p1.equals("Moon") || p2.equals("Moon"))
            weight += 1.;

        if (p1.equals("Venus") || p2.equals("Venus"))
            weight += 0.8;

        if (p1.equals("Mars") || p2.equals("Mars"))
            weight += 0.5;

        if (p1.equals("Sun") || p2.equals("Sun"))
            weight += 0.4;

        return weight;
    }

    public static int calculateCompatibility(String date1, String date2) {

        JSONObject synastry = new JSONObject(getSynastry(date1, date2));
        JSONArray aspects = synastry.getJSONArray("interaspects");

        double positive = 0;

        double negative = 0;

        for (int i = 0; i < aspects.length(); i++) {

            JSONObject a = aspects.getJSONObject(i);

            String p1 = a.getString("p1");
            String p2 = a.getString("p2");

            // ignoruj mało znaczące obiekty
            if (!IMPORTANT_PLANETS.contains(p1)
                    || !IMPORTANT_PLANETS.contains(p2)) {
                continue;
            }

            String aspect = a.getString("aspect");

            double aspectScore = scoreAspect(aspect);

            if (aspectScore == 0)
                continue;

            // orb
            double off = Math.abs(a.optDouble("off", 0));

            // 1.0 dla idealnego aspektu
            // ~0.3 dla słabego
            double orbWeight = Math.max(0.3, 1.0 - off / 8.0);

            double planetWeight = getPlanetWeight(p1, p2);

            double value = Math.abs(
                    aspectScore
                            * orbWeight
                            * planetWeight
            );

            if (aspectScore > 0)
                positive += value;
            else
                negative += value;
        }

        if (positive + negative == 0)
            return 50;

        double ratio = positive / (positive + negative);


        return (int) Math.round(ratio * 100);
    }

    private static int scoreAspect(String aspect) {

        return switch (aspect.toLowerCase()) {

            case "trine" -> 10;
            case "sextile" -> 8;
            case "conjunction" -> 6;
            case "quintile" -> 4;
            case "opposition" -> -8;
            case "square" -> -10;
            case "quincunx" -> -5;
            case "semisquare" -> -3;
            case "sesquiquadrate" -> -4;

            default -> 0;
        };
    }





    }

